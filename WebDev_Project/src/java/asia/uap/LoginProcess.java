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
@WebServlet(name = "LoginProcess", urlPatterns = {"/do.login"})
public class LoginProcess extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        DBIO IO = new DBIO();
        
        if(email == null || email.isEmpty() || password == null || password.isEmpty()){
                            response.sendRedirect("index.jsp");
                            return;
        } else{
            //Database handling login
            Connection conn = null;
            try {
                
                conn = IO.loadClass();
                if(conn != null){
                    String Query = "select * from accounts where email = ? and pass = ?";
                    PreparedStatement ps1 = conn.prepareStatement(Query);
                    ps1.setNString(1, email);
                    ps1.setNString(2, password);
                    ResultSet rs1  = ps1.executeQuery();
                    
                    if(rs1.next()) {
                        session.setAttribute("email", rs1.getNString("email"));
                        session.setAttribute("pass", rs1.getNString("pass"));
                        session.setAttribute("role", rs1.getNString("acc_role"));
                        session.setAttribute("acc_id", rs1.getInt("acc_id"));
                        
                        String role = rs1.getNString("acc_role");
                        if ("admin".equals(role)) {
                            response.sendRedirect("jsp/admin.jsp");
                        } else {
                            response.sendRedirect("jsp/home.jsp");
                        }
                    }
                    else {
                        request.setAttribute("errorMessage", "Account has not been registered yet.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                }else{
                    log("no connection");
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
