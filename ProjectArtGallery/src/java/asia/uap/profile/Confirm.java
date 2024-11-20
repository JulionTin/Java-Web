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
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BIR
 */
@WebServlet(name = "do.confirm", urlPatterns = {"/do.confirm"})
public class Confirm extends HttpServlet {

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
        String uri = "confirm.jsp";
        String[] isBanned = request.getParameterValues("Ban");
        String[] reports = request.getParameterValues("Reports");
        int[] reports_list = new int[reports.length];
        
        if (isBanned != null && reports != null) {
            request.setAttribute("isBanned", isBanned);
            request.setAttribute("Reports", reports);
            
            RequestDispatcher rd = request.getRequestDispatcher(uri);
            rd.forward(request, response);
        }else{
            Connection conn = null;
            try{
                 conn= loadClass();
                 for(int i = 0; i<reports.length; i++){
                      int dump_report = Integer.parseInt(reports[i]);
                      reports_list[i] = dump_report;
                  }

                  String deletereport = "delete from reports where report_id =?";
                  PreparedStatement ps4 = conn.prepareStatement(deletereport);
                  for (int i = 0; i < reports_list.length; i++) {
                      ps4.setInt(1, reports_list[i]);
                      int rs4 = ps4.executeUpdate();
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
            response.sendRedirect("do.ban");
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
