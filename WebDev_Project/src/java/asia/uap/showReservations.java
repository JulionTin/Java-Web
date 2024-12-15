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
 * @author Joseph
 */
@WebServlet(name = "showReservations", urlPatterns = {"/do.showreservations"})
public class showReservations extends HttpServlet {

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
        String uri = "/jsp/building.jsp";
        String building = request.getParameter("building");
        String reset = request.getParameter("reset");
        DBIO IO = new DBIO();
        log(building);

        Connection conn = null;
        try {
            conn = IO.loadClass();

            if (reset != null) {
                String deleteQuery = "delete from reservation";
                PreparedStatement ps3 = conn.prepareStatement(deleteQuery);
                int rs3 = ps3.executeUpdate();
                response.sendRedirect("index.jsp");
                return;
            } else {
                HttpSession session = request.getSession();
                ArrayList<Room> room = new ArrayList<Room>();

                //Gets rooms based on the building name gotten from the building form from the previous page
                String getRoomQuery = "select building_name, room.room_id, room_name, room.res_status from room left join building on room.building_id = building.building_id  where building.building_name = ?";
                PreparedStatement ps1 = conn.prepareStatement(getRoomQuery);
                ps1.setString(1, building);
                ResultSet rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    Room r = new Room();
                    int room_id = rs1.getInt("room_id");
                    String room_name = rs1.getNString("room_name");
                    String res_status = rs1.getNString("room.res_status");
                    r.setRoom_id(room_id);
                    r.setRoom_name(room_name);
                    r.setStatus(res_status);
                    room.add(r);
                    //request attribute gets the arraylist of objects made from the sql query
                    request.setAttribute("rooms", room);
                }

                ArrayList<Reservation> reservations = new ArrayList<>();

                //Gets rooms based on the building name gotten from the building form from the previous page
                String getReservationQuery = "SELECT r.reservation_id, r.title, r.res_date, r.res_time, r.capacity, r.res_description, r.res_status, b.building_name, f.floor_name, rm.room_name "
                        + "FROM reservation r "
                        + "LEFT JOIN building b ON r.building_id = b.building_id "
                        + "LEFT JOIN floor f ON r.floor_id = f.floor_id " + "LEFT JOIN room rm ON r.room_id = rm.room_id "
                        + "WHERE b.building_name = ?";
                PreparedStatement ps2 = conn.prepareStatement(getReservationQuery);
                ps2.setString(1, building);
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
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
                    reservations.add(reservation);

                }

                request.setAttribute("reservations", reservations);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //MVC which connects this servlet and jsp
        RequestDispatcher rd = request.getRequestDispatcher(uri);
        rd.forward(request, response);

        try (PrintWriter out = response.getWriter()) {
            out.println("<a href='index.jsp'>Back to Index</a>");
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
