/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author BIR
 */
@WebServlet(name = "do.set", urlPatterns = {"/do.set"})
public class Set extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String uri = "/jsp/edit_event.jsp";
        int ID = Integer.parseInt(request.getParameter("ID"));
        DBIO IO = new DBIO();
        
        Connection conn = null;
        try {
             conn = IO.loadClass();


                //Gets rooms based on the building name gotten from the building form from the previous page
                String getReservationQuery = "SELECT r.reservation_id, r.title, r.res_date, r.res_time, r.capacity, r.res_description, r.res_status, b.building_name, f.floor_name, rm.room_name " + 
                                                                            "FROM reservation r " + 
                                                                            "LEFT JOIN building b ON r.building_id = b.building_id " + 
                                                                            "LEFT JOIN floor f ON r.floor_id = f.floor_id " + "LEFT JOIN room rm ON r.room_id = rm.room_id " + 
                                                                            "WHERE r.reservation_id = ?";
                PreparedStatement ps2 = conn.prepareStatement(getReservationQuery);
                ps2.setInt(1, ID);
                ResultSet rs2 = ps2.executeQuery();

                while(rs2.next()){
                    Reservation reservation = new Reservation(); 
                    reservation.setReservationID(rs2.getInt("r.reservation_id")); 
                    reservation.setTitle(rs2.getString("r.title")); 
                    reservation.setDate(rs2.getDate("r.res_date").toLocalDate()); 
                    reservation.setTime(rs2.getTime("r.res_time").toLocalTime()); 
                    reservation.setCapacity(rs2.getInt("r.capacity")); 
                    reservation.setDescription(rs2.getString("r.res_description")); 
                    reservation.setStatus(rs2.getString("r.res_status")); 
                    reservation.setBuilding(rs2.getString("b.building_name")); 
                    reservation.setFloor(rs2.getString("f.floor_name")); 
                    reservation.setRoom(rs2.getString("rm.room_name")); 
                    request.setAttribute("reservation", reservation);

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
