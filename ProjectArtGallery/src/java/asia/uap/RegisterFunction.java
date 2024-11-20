/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 * @author poopy
 */
@WebServlet(name = "RegisterFunction", urlPatterns = {"/do.register"})
public class RegisterFunction extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); // Set content type to HTML for script

        // Get form parameters
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        HttpSession session = request.getSession();
        AccountBean user = new AccountBean();
        ArrayList<AccountBean> userList = (ArrayList<AccountBean>) session.getAttribute("user");
        

        // Null or empty check for form fields
        if (username == null || username.isEmpty() || pass == null || pass.isEmpty() || confirmPass == null || confirmPass.isEmpty()) {
            response.getWriter().println("<script>alert('All fields are required!'); window.history.back();</script>");
            return;
        }else{
            
            if(userList == null){
                userList = new ArrayList<>();
            }
            
            DigestUtils.getSha256Digest();
            String pass_SHA = DigestUtils.sha256Hex(pass);
            
            log("AAAAAAAAAAAAAAAAAAAAAAAAAA"+ pass_SHA);
            user.setUsername(username);
            user.setPassword(pass_SHA);
            userList.add(user);
            request.setAttribute("User", user.getUsername());
            request.setAttribute("Pass", user.getPassword());
        }

        // Validate that password and confirm password match
        if (!pass.equals(confirmPass)) {
            response.getWriter().println("<script>alert('Passwords do not match!'); window.history.back();</script>");
            return;
        }

        Connection conn = null;
        // Register the user
        try {
            conn = loadClass();
            if (conn != null) {
                String query = "INSERT INTO user_account (username, pass) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.executeUpdate();

                // Success message and redirect
                response.getWriter().println("<script>alert('Registered successfully!'); window.location='index.jsp';</script>");
            } else {
                response.getWriter().println("<script>alert('Connection failed!'); window.history.back();</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }finally {
            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    
    private Connection loadClass() throws SQLException {
    Connection conn = null;
    try {
        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    // Get connection using the credentials from DbCredentials
    conn = DriverManager.getConnection(DbCredentials.getDbUrl(), DbCredentials.getDbUser(), DbCredentials.getDbPassword());
    
    return conn;
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
