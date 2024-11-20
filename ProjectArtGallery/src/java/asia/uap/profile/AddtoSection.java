/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile;

import asia.uap.DbCredentials;
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
 * @author BIR
 */
@WebServlet(name = "do.add", urlPatterns = {"/do.add"})
public class AddtoSection extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DbCredentials cred = new DbCredentials();

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uri = "addArt.jsp";
        ArrayList<String> artworks = new ArrayList<String>();
        ArrayList<String> sections = new ArrayList<String>();
        HttpSession session = request.getSession();
        String logged_user = (String) session.getAttribute("loggedInUser");
        
        Connection conn = null;
        try {
            conn = loadClass();


            String getArtworkQuery = "select art_name from artwork left join user_account on artwork.user_id = user_account.user_id where username = ?";
            PreparedStatement ps1 = conn.prepareStatement(getArtworkQuery);
            ps1.setString(1, logged_user);
            ResultSet rs1 = ps1.executeQuery();
            
            while(rs1.next()){
               String art = rs1.getNString("art_name");
               artworks.add(art);
            }
            request.setAttribute("artworks", artworks);

             
            
            String getSectionQuery = "select section_id from section left join user_account on section.user_id = user_account.user_id where username = ?";
            PreparedStatement ps2 = conn.prepareStatement(getSectionQuery);
            ps2.setString(1, logged_user);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
               String section = rs2.getNString("section_id");
               sections.add(section);
            }
            request.setAttribute("sections", sections);

            
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
