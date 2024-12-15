/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile.edit;

import asia.uap.methods.DbCredentials;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author poopy
 */
@WebServlet(name = "SectionArt", urlPatterns = {"/do.addart"})
public class SectionArt extends HttpServlet {

    private DbCredentials cred = new DbCredentials();

    
    
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
         String uri = "WEB-INF/jsp/success.jsp";
         
         String selectedSection = request.getParameter("section");
         String selectedArtwork = request.getParameter("artwork");
         
         Connection conn = null;
         
         ArrayList<String> requestData = new ArrayList<>();
        requestData.add("successfully loaded "+ selectedArtwork +" into " +  selectedSection + "!");
        requestData.add("do.add");
        requestData.add("add another artwork");
        request.setAttribute("data", requestData);
         
         try{
             conn = cred.loadClass();
             
             //get userID
            String username = (String) session.getAttribute("loggedInUser");
            int userId = -1;
            int artId = -1;
            
            String getUserQuery = "select * from user_account where username = ?";
            PreparedStatement ps1 = conn.prepareStatement(getUserQuery);
            ps1.setString(1, username);
            
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                userId = rs1.getInt("user_id");     
            }
             
            //get art ID
            String getArtworkQuery = "select * from artwork where art_name = ?";
            PreparedStatement ps2 = conn.prepareStatement(getArtworkQuery);
            ps2.setString(1, selectedArtwork);
            
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()){
                artId = rs2.getInt("art_id");     
            }
            
            //get section ID
            String checkSection = "select * from section where section_id = ?";
            PreparedStatement ps3 = conn.prepareStatement(checkSection);
            ps3.setString(1, selectedSection);
            
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next() == false){
                //if one input is invalid, it gives an error
                response.getWriter().println("<script>alert('invalid input detected. enter a valid section name and/or art name');</script>");
            }else {
                //if everything is valid, it puts the art into the selected session
                String insert = "insert into section_art (section_id, art_id, user_id) values (?, ?, ?)";
                PreparedStatement ps4 = conn.prepareStatement(insert);
                ps4.setString(1, selectedSection);
                ps4.setInt(2, artId);
                ps4.setInt(3, userId);
                
                int rs4 = ps4.executeUpdate();
                RequestDispatcher rd = request.getRequestDispatcher(uri);
                rd.forward(request, response);

            }
            
         } catch (SQLException e){
             e.printStackTrace();
             log("error");
         }
         
         
        
         
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
