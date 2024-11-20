/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.admin;

import asia.uap.DbCredentials;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BIR
 */
@WebServlet(name = "do.delete", urlPatterns = {"/do.delete"})
public class Delete extends HttpServlet {

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
        String[] isBanned = request.getParameterValues("ban");
        String[] reports = request.getParameterValues("report");
        String Yes = request.getParameter("Yes");
        String No = request.getParameter("No");
        int[] banned_Art = new int[isBanned.length];
        int[] reports_list = new int[reports.length];
        
        Connection conn = null;
        try {
            conn= loadClass();
            
            if(isBanned != null && reports != null){
                if(Yes != null){
                    
                    for(int i = 0; i<isBanned.length; i++){
                        int banned = Integer.parseInt(isBanned[i]);
                        banned_Art[i] = banned;
                        log("Number"+ banned_Art[i]);
                    }
                    

                   
                    for(int i = 0; i<reports.length; i++){
                        int report = Integer.parseInt(reports[i]);
                        reports_list[i] = report;
                    }
                    
                    String deletereport = "delete from reports where report_id =?";
                    PreparedStatement ps1 = conn.prepareStatement(deletereport);
                    
                    String deleteartsection = "delete from section_art where art_id =?";
                    PreparedStatement ps2 = conn.prepareStatement(deleteartsection);
                    
                    String deleteart = "delete from artwork where art_id =?";
                    PreparedStatement ps3 = conn.prepareStatement(deleteart);
                    
                    for (int i = 0; i < reports_list.length; i++) {
                        ps1.setInt(1, reports_list[i]);
                        int rs1 = ps1.executeUpdate();
                    }
                    
                    for(int j = 0; j<banned_Art.length;j++){
                        ps2.setInt(1, banned_Art[j]);
                        ps3.setInt(1, banned_Art[j]);
                    }

                    int rs2 = ps2.executeUpdate();
                    int rs3 = ps3.executeUpdate();
                    
                   
                    response.sendRedirect("index.jsp");
                    return;
                }else if (No != null|| Yes == null || Yes.isEmpty()){
                    
                    
                    
                    response.sendRedirect("index.jsp");
                    return;
                }
            }else if(reports == null || No!= null || Yes == null || Yes.isEmpty()){
               response.sendRedirect("do.ban");
               return;
            }
        }catch (SQLException e){
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
