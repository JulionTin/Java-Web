/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile;

import asia.uap.methods.DbCredentials;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "AddComment", urlPatterns = {"/do.addcomment"})
public class AddComment extends HttpServlet {
    private DbCredentials cred = new DbCredentials();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("code start");
        HttpSession session = request.getSession();
        String comment = request.getParameter("comment");
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        int artId = Integer.parseInt(request.getParameter("artId"));
        Connection conn = null;
        
        
        log("adding comment");
        try  {
            conn = cred.loadClass();
            // Get the user ID of the commenter
            int commenterId = getUserId(conn, loggedInUser);
            log("got userID");
            
            log("inserting comment");
            if (commenterId != -1) {
                // Insert the comment into the database
                String insertSQL = "INSERT INTO `comment` (commenter_id, art_id, content) VALUES (?, ?, ?)";
                
                PreparedStatement pstmt = conn.prepareStatement(insertSQL);
                    pstmt.setInt(1, commenterId);
                    pstmt.setInt(2, artId);
                    pstmt.setString(3, comment);
                    int rs = pstmt.executeUpdate();
                    log("comment added");
                
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error!", e);
        }finally {
            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        // Fetch updated comments
//        List<CommentData> comments = getComments(artId);

        // Set attributes for the JSP
//        request.setAttribute("comments", comments);
//        request.setAttribute("artId", artId);

        // Forward back to the JSP page
        request.getRequestDispatcher("/do.showdescription?user="+request.getParameter("user")+"&desc="+request.getParameter("desc")+"&artId="+request.getParameter("artId")).forward(request, response);
    }

    private int getUserId(Connection conn, String username) throws SQLException {
        String sql = "SELECT user_id FROM user_account WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
            }
        }
        return -1; // User not found
    }

    private List<CommentData> getComments(int artId) {
        List<CommentData> comments = new ArrayList<>();
        String selectSQL = "SELECT ua.username, c.content FROM `comment` c "
                         + "JOIN user_account ua ON c.commenter_id = ua.user_id "
                         + "WHERE c.art_id = ?";
        Connection conn = null;
        try {
            conn = cred.loadClass();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setInt(1, artId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Use the CommentData bean
                    CommentData comment = new CommentData();
                    comment.setUsername(rs.getString("username"));
                    comment.setCommentContent(rs.getString("content"));
                    comments.add(comment);
                }
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
        return comments;
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
