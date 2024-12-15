/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile.edit;

import asia.uap.methods.DbCredentials;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author poopy
 */
@WebServlet(name = "EditNumber", urlPatterns = {"/do.editnumber"})
public class EditNumber extends HttpServlet {

    private DbCredentials cred = new DbCredentials();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String credentials = request.getParameter("credentials");
        HttpSession session = request.getSession();
        int userId = -1;
        
        Connection conn = null;
        try {
            conn = cred.loadClass();
            
            String query = "select * from user_account where username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, (String)session.getAttribute("loggedInUser"));
            
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true){
                userId = rs.getInt("user_id");
            }
            
            String query1 = "update user_account set gcash_credentials = ? where user_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setString(1, credentials);
            ps1.setInt(2, userId);
            
            int rs1 = ps1.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }finally{
            try {
                if (conn != null){
                    conn.close();
            }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        
        response.sendRedirect("editProfile.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
