/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Raymond
 */
@WebServlet(name = "LoginFunction", urlPatterns = {"/do.login"})
public class LoginFunction extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
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
        String username = request.getParameter("username");
        String pass = request.getParameter("password"); // Fix: match the parameter name with the form field
        HttpSession session = request.getSession();
        boolean logged = false;
        request.setAttribute("User", username);
//        request.setAttribute("Pass", password);
        
        // Null checks for input fields
        if (username == null || username.isEmpty() || pass == null || pass.isEmpty()) {
            response.sendRedirect("loginPage.jsp?error=All%20fields%20are%20required!");
            return;
        }else{
            DigestUtils.getSha256Digest();
            String pass_SHA = DigestUtils.sha256Hex(pass);
            
            Connection conn = null;
            try {
            conn = loadClass();
            if (conn != null) {
                String query = "SELECT * FROM user_account WHERE username = ? AND pass = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, pass_SHA);
                ResultSet rs = pstmt.executeQuery();
                String main_role = "admin";
                String user_role = "user";
                boolean isAdmin = false;
                log("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+ pass_SHA);


                if (rs.next()) {
                        // Login success, redirect to profile.jsp
                        if(main_role.equals(rs.getString("user_role"))){
                            response.sendRedirect("Admin/Admin_index.jsp");
                            isAdmin = true;
                            session.setAttribute("isAdmin", isAdmin);
                        }else if(user_role.equals(rs.getString("user_role"))){
                            response.sendRedirect("do.showprofile");
                        }
                    logged = true;
                    session.setAttribute("isLogged", logged);
                    session.setAttribute("loggedInUser", username); 
                } else {
                    // Username or password not found
                    response.sendRedirect("loginPage.jsp?error=Invalid%20username%20or%20password!");
                }
            } else {
                response.getWriter().println("Error: Database connection is null.");
            }
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
            response.getWriter().println("Error: Unable to login due to SQL error.");
        }finally {
            try {
                if (conn != null){
                    conn.close();
                    log("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+ pass_SHA);

                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
        
      
        
    }

    private Connection loadClass() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DbCredentials.getDbUrl(), DbCredentials.getDbUser(), DbCredentials.getDbPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to the database.");
        }
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
