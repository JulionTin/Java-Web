/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile.edit;

import asia.uap.methods.DbCredentials;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@WebServlet(name = "SaveArt", urlPatterns = {"/do.saveart"})
@MultipartConfig
public class SaveArt extends HttpServlet {

    private DbCredentials cred = new DbCredentials();

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String uri = "WEB-INF/jsp/success.jsp";
        
        //get parameters for art name, artpiece and art description
        String title = request.getParameter("title");
        String desc = request.getParameter("description");
        Part art = request.getPart("artwork");
        
        
        
        if (!art.getContentType().startsWith("image/")){
            
        }
        ArrayList<String> requestData = new ArrayList<>();
        requestData.add("successfully saved "+title+"!");
        requestData.add("do.add");
        requestData.add("add your art to a section");
        request.setAttribute("data", requestData);
        
        //WARNING BEFORE TESTING
        //I HAVE NOT TESTED WHETHER THIS CAN CHECK FOR THINGS OTHER THAN PNG or JPEG
        InputStream content = art.getInputStream();
        HttpSession session = request.getSession();
        Connection conn = null;
        try {
             conn = cred.loadClass();
            
            String username = (String) session.getAttribute("loggedInUser");
            int userId = -1;
            
            String getUserQuery = "select * from user_account where username = ?";
            PreparedStatement ps1 = conn.prepareStatement(getUserQuery);
            ps1.setString(1, username);
            
            ResultSet rs1 = ps1.executeQuery();
            
            
            while (rs1.next()){
                userId = rs1.getInt("user_id");     
            }
            
            String uploadArtQuery = "insert into artwork (user_id, art_name, artpiece, art_desc) values (?, ?, ?, ?)";           
            PreparedStatement ps2 = conn.prepareStatement(uploadArtQuery);
            ps2.setInt(1, userId);
            ps2.setString(2, title);
            //input stream is used to converted into blob in database
            ps2.setBlob(3, content);
            ps2.setString(4, desc);

            int rs2 = ps2.executeUpdate();
            
        } catch(SQLException e){
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
