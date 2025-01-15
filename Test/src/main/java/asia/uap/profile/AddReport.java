/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile;

import asia.uap.DbCredentials;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(name = "do.addreport", urlPatterns = {"/do.addreport"})
public class AddReport extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        HttpSession session = request.getSession();
        String loggedUser = (String) session.getAttribute("loggedInUser");
        log(loggedUser);
        String report_desc = request.getParameter("report_desc");
        String art_owner = request.getParameter("art_owner");
        String art_id = request.getParameter("art_id");
        log(art_owner);
        
        if(report_desc == null || report_desc.isEmpty()){
            response.sendRedirect("do.showprofile");
            return;
        }else{
            if(art_id != null){
            Connection conn = null;
            try {
                conn = loadClass();
                

               int art = Integer.parseInt(art_id);   
               String getUserQuery = "select * from user_account where username = ?";
               String getArtQuery = "select * from artwork where art_id = ?";
               PreparedStatement ps1 = conn.prepareStatement(getUserQuery);
               PreparedStatement ps2 = conn.prepareStatement(getArtQuery);
               ps1.setString(1, loggedUser);
               ps2.setInt(1, art);
               ResultSet rs1 = ps1.executeQuery();
               ResultSet rs2 = ps2.executeQuery();
               
                


               String updateReport = "insert into reports(reporter, art_owner, art_id, report_desc) values(?,?,?,?)";
               PreparedStatement ps3 = conn.prepareStatement(updateReport);
               while (rs1.next()){
                   int reporter = rs1.getInt("user_id");
                   ps3.setInt(1, reporter);
               }
               while (rs2.next()){
                    int owner_Id = rs2.getInt("user_id");
                    log("Owner_id" + owner_Id);
                    ps3.setInt(2, owner_Id);
               }   
               ps3.setInt(3, art);
               ps3.setString(4,report_desc);
               
               int rs3 = ps3.executeUpdate();
             
               
             

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
            }
            response.sendRedirect("index.jsp");
            return;
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
