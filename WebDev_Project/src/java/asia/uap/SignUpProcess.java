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
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "SignUpProcess", urlPatterns = {"/do.signup"})
public class SignUpProcess extends HttpServlet {

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
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
        HttpSession session = request.getSession();
        //to call the conection object
        DBIO IO = new DBIO();
        
        if(fname == null || fname.isEmpty() || lname == null || lname.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()){
                            response.sendRedirect("signup.jsp");
                            return;
        } else{
            
        Connection conn = null;
        try {
            ArrayList<Account> account = new ArrayList<>();
            conn = IO.loadClass();
            Account acc = new Account();
            acc.setFname(fname);
            acc.setLname(lname);
            acc.setEmail(email);
            acc.setPass(password);
            
            
            String Insert = "insert into accounts(email, pass, fname, lname) values (?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(Insert);
            ps1.setNString(1, acc.getEmail());
            ps1.setNString(2, acc.getPass());
            ps1.setNString(3, acc.getFname());
            ps1.setNString(4, acc.getLname());
            int rs1 = ps1.executeUpdate();
            
            account.add(acc);
            
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
            
            session. setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("fname", fname);
            session.setAttribute("lname", lname);
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
