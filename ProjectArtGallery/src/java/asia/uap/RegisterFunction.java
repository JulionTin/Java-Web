/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import asia.uap.methods.DbCredentials;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private DbCredentials cred = new DbCredentials();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html"); // Set content type to HTML for script
        
        String msg = "";

        // Get form parameters
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String confirmPass = request.getParameter("confirmPass");
        HttpSession session = request.getSession();
        AccountBean user = new AccountBean();
        ArrayList<AccountBean> userList = (ArrayList<AccountBean>) session.getAttribute("user");
        

        // Null or empty check for form fields
        if (username == null || username.isEmpty() || pass == null || pass.isEmpty() || confirmPass == null || confirmPass.isEmpty()) {
//            response.getWriter().println("<script>alert('All fields are required!'); window.history.back();</script>");
            msg = "please+fill+out+all+fields";
            response.sendRedirect("registerPage.jsp?msg="+msg);
            return;
        }else{
            
            if(userList == null){
                userList = new ArrayList<>();
            }
            
            DigestUtils.getSha256Digest();
            String pass_SHA = DigestUtils.sha256Hex(pass);
            
            
            user.setUsername(username);
            user.setPassword(pass_SHA);
            userList.add(user);
            request.setAttribute("User", user.getUsername());
            request.setAttribute("Pass", user.getPassword());
        }

        // Validate that password and confirm password match
        if (!pass.equals(confirmPass)) {
//            response.getWriter().println("<script>alert('Passwords do not match!'); window.history.back();</script>");
            msg = "passwords+do+not+match.+Please+try+again";
            response.sendRedirect("registerPage.jsp?msg="+msg);
            return;
        }

        Connection conn = null;
        // Register the user
        try {
            conn = cred.loadClass();
            if (conn != null) {
                String query = "INSERT INTO user_account (username, pass) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.executeUpdate();

                // Success message and redirect
//                response.getWriter().println("<script>alert('Registered successfully!'); window.location='index.jsp';</script>");
                msg = "registration+successful!+you+may+now+login";
                response.sendRedirect("loginPage.jsp?msg="+msg);
            } else {
                msg = "connection+failed!+please+try+again";
                response.sendRedirect("registerPage.jsp?msg="+msg);
                
//                response.getWriter().println("<script>alert('Connection failed!'); window.history.back();</script>");
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
