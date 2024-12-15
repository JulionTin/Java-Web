/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile;

import asia.uap.methods.DbCredentials;
import asia.uap.profile.edit.ArtData;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @
 */
@WebServlet(name = "ShowDescription", urlPatterns = {"/do.showdescription"})
public class ShowDescription extends HttpServlet {

    public DbCredentials cred = new DbCredentials();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String artId = request.getParameter("artId");
        ArtData artData = new ArtData();
        ArrayList<CommentData> commentList = new ArrayList<>();
        request.setAttribute("artData", artData);
        String uri = "description.jsp";
        Connection conn = null;
        
        try {
             conn = cred.loadClass();
            
            String query = "SELECT c.content AS comment_string, u.username AS commenter_username FROM `comment` c JOIN user_account u ON c.commenter_id = u.user_id WHERE c.art_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, artId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CommentData commentSection = new CommentData();
                commentSection.setCommentContent(rs.getString("comment_string"));
                commentSection.setUsername(rs.getString("commenter_username"));
                commentList.add(commentSection);
            }
            request.setAttribute("donationCount", countDonations(Integer.parseInt(artId)));
            request.setAttribute("comments",commentList);
        } catch (SQLException e){
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
        
        
        RequestDispatcher rd = request.getRequestDispatcher(uri);
        rd.forward(request, response);
    }
    
    private int countDonations(int artId){
        int count = 0;
        Connection conn = null;
        try {
            conn = cred.loadClass();
            String query = "SELECT COUNT(*) AS confirmed_donations FROM donation_request WHERE art_id = ? AND request_status = 'confirmed'";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, artId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next() == true){
                count = rs.getInt("confirmed_donations");
            }
        } catch (SQLException e){
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
        
        return count;
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
