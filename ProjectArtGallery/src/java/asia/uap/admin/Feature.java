/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.admin;

import asia.uap.AccountBean;
import asia.uap.methods.DbCredentials;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "do.feature", urlPatterns = {"/do.feature"})
public class Feature extends HttpServlet {

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

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String uri = "/Admin/FeatureArtist.jsp";
        Connection conn = null;
        try {
             conn = cred.loadClass();
            
            
            HttpSession session = request.getSession();
            ArrayList<AccountBean> users = new ArrayList<AccountBean>();
            
            
            String getUserQuery = "select user_account.username, user_account.user_id from user_account left join featured_user on user_account.user_id = featured_user.user_id where featured_user.user_id is null";
            PreparedStatement ps1 = conn.prepareStatement(getUserQuery);
            ResultSet rs1 = ps1.executeQuery();
            
            if(rs1 !=null){
                while(rs1.next()){
                    String username = rs1.getNString("username");
                    int id = rs1.getInt("user_id");
                    AccountBean user = new AccountBean();
                    user.setUsername(username);
                    user.setUser_id(id);
                    users.add(user);                    
                    request.setAttribute("User", users);
                }
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
