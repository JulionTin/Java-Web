/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joseph
 */
@WebServlet(name = "CreateEvent", urlPatterns = {"/do.create"})
public class CreateEvent extends HttpServlet {

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
        
        String title = request.getParameter("title");
        
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        
        String building = request.getParameter("building");
        String floor = request.getParameter("floor");
        String room = request.getParameter("room");
        
        String dateStr = request.getParameter("date");
        LocalDate date = null; 
        
        if (dateStr != null) { 
            try { 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(dateStr, formatter); 
            } catch (Exception e) { 
                // Handle the exception, e.g., set a default value or log an error 
                date = LocalDate.now(); // Example of setting a default value 
            } 
        }
        
        String description = request.getParameter("description");
        
        String timeStr = request.getParameter("time");
        LocalTime time =null;
        
        if (timeStr != null) { 
            try { 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                time = LocalTime.parse(timeStr, formatter);
            }
            catch (Exception e) { 
                // Handle the exception, e.g., set a default value or log an error 
                time = LocalTime.of(0, 0); // Example of setting a default value 
            } 
        }
        
        HttpSession session = request.getSession();
        int acc_id = (int) session.getAttribute("acc_id");
        //to call the conection object
        DBIO IO = new DBIO();
        
        if(title == null || title.isEmpty() || date == null || capacity <= 0 ||building == null || building.isEmpty() || floor == null || floor.isEmpty() || room == null || room.isEmpty() || time == null){
                            response.sendRedirect("create_event.jsp");
                            return;
        } else{
            
        Connection conn = null;
        try {
            ArrayList<Reservation> reservation = new ArrayList<>();
            conn = IO.loadClass();
            Reservation res = new Reservation();
            
            //gets the room name from the form and gets its id
            String Queryroom = "select room_id from room where room_name = ?";
            PreparedStatement ps2 = conn.prepareStatement(Queryroom);
            ps2.setString (1, room);
            ResultSet rs2 = ps2.executeQuery();
            int room_id = 0;
            
            while(rs2.next()){
                room_id = rs2.getInt("room_id");
            }
            
            //gets the building name from the form and gets its id
            String Querybuilding = "select building_id from building where building_name = ?";
            PreparedStatement ps3 = conn.prepareStatement(Querybuilding);
            ps3.setString (1, building);
            ResultSet rs3 = ps3.executeQuery();
            int building_id = 0;
            while(rs3.next()){
                building_id = rs3.getInt("building_id");
            }
            
            //gets the floor name from the form and gets its id
            String Queryfloor = "select floor_id from floor where floor_name = ?";
            PreparedStatement ps4 = conn.prepareStatement(Queryfloor);
            ps4.setString (1, floor);
            ResultSet rs4 = ps4.executeQuery();
            int floor_id = 0;
            while(rs4.next()){
                floor_id = rs4.getInt("floor_id");
            }
                        
            res.setTitle(title);
            res.setDate(date);
            res.setTime(time);
            res.setCapacity(capacity);
            
            //Putting the info we got from the queries and putting them in the object
            res.setBuildingID(building_id);
            res.setFloorID(floor_id);
            res.setRoomID(room_id);
                      
            res.setDescription(description);
            
            
            String Insert = "insert into reservation(title, res_date, res_time, capacity, building_id, floor_id, room_id, res_description, acc_id) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(Insert);
            ps1.setString(1, res.getTitle());
            ps1.setDate(2, java.sql.Date.valueOf(res.getDate()));
            ps1.setTime(3, java.sql.Time.valueOf(res.getTime()));
            
            ps1.setInt(4, res.getCapacity());
            
            ps1.setInt(5, res.getBuilding_id());
            ps1.setInt(6, res.getFloor_id());
            ps1.setInt(7, res.getRoom_id());
            
            ps1.setString(8, res.getDescription());
            ps1.setInt(9, acc_id);
            int rs1 = ps1.executeUpdate();
            
            reservation.add(res);
            
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
            
            session. setAttribute("title", title);
            session.setAttribute("date", date);
            session.setAttribute("time", time);
            session.setAttribute("capacity", capacity);
            
            session.setAttribute("building", building);
            session.setAttribute("floor", floor);
            session.setAttribute("room", room);
            
            session.setAttribute("description", description);
            
            response.sendRedirect("jsp/home.jsp");
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
