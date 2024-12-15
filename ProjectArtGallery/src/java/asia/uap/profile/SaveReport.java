/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile;

import asia.uap.AccountBean;
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
 * @author BIR
 */
@WebServlet(name = "do.report", urlPatterns = {"/do.report"})
public class SaveReport extends HttpServlet {

    private DbCredentials cred = new DbCredentials();
        
        
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String art_id = request.getParameter("art_id");
        String uri = "report.jsp";

        
        if(art_id != null){
            Connection conn = null;
            try {
                conn = cred.loadClass();

               String getArtQuery = "select art_name from artwork where art_id = ?";
               PreparedStatement ps4 = conn.prepareStatement(getArtQuery);
                
                    int art = Integer.parseInt(art_id);
                    ps4.setInt(1, art);
                    ResultSet rs4 = ps4.executeQuery();
                    while(rs4.next()){
                        String art_name = rs4.getNString("art_name");
                        request.setAttribute("art_name", art_name);
                        request.setAttribute("art_id", art);
                    }
               
        
               
             

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
