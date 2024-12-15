/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

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
 * @author poopy
 */
@WebServlet(name = "HomePage", urlPatterns = {"/index.jsp"})
public class HomePage extends HttpServlet {
private DbCredentials cred = new DbCredentials();

   

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = "homepage.jsp";
        HttpSession session = request.getSession();
        ArrayList<AccountBean> featuredusers = new ArrayList<AccountBean>();
        
        
        //insert code here that gets the featured artworks from sql
        //put them in a session and display it in homepage.jsp
        
        
        String[] isChecked = request.getParameterValues("Check");
        String reset = request.getParameter("Reset");

        Connection conn = null;
        try {
            conn = cred.loadClass();
            if(reset != null){
                String deleteQuery = "delete from featured_user";
                PreparedStatement ps1 = conn.prepareStatement(deleteQuery);
                int rs1 = ps1.executeUpdate();
                response.sendRedirect("do.feature");
            }else{
            
            
            
    
            
            
            //gets current user ID
            if(isChecked != null){
                int[] checked_Users = new int[isChecked.length];
                for(int i = 0; i<isChecked.length; i++){
                    int checked = Integer.parseInt(isChecked[i]);
                    checked_Users[i] = checked;
                    log(isChecked[i]);
                }
               
                String addfeatured = "insert into featured_user (user_id) values (?)";
                PreparedStatement ps3 = conn.prepareStatement(addfeatured);
                for(int j = 0; j<checked_Users.length;j++){
                    ps3.setInt(1, checked_Users[j]);
                    int rs3 = ps3.executeUpdate();
                }

              
            }

            String getUserQuery = "select user_account.username, user_account.user_id from featured_user left join user_account on featured_user.user_id = user_account.user_id;";
            PreparedStatement ps2 = conn.prepareStatement(getUserQuery);
            ResultSet rs2 = ps2.executeQuery();

            while(rs2.next()){
                String username = rs2.getNString("username");
                int id = rs2.getInt("user_id");
                AccountBean featureduser = new AccountBean();
                featureduser.setUser_id(id);
                featureduser.setUsername(username);
                featuredusers.add(featureduser);
                request.setAttribute("Users", featuredusers);
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(uri);
            rd.forward(request, response);
            
            }
            
        } catch (SQLException e){
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

        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<%@ include file=\"includes/header.jsp\" %>");
//            out.println("<head>");
//            out.println("<title>Servlet HomePage</title>");  
//            out.println("<link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\"/>"); 
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<div class=\"featured-section\">");
//            out.println("<h1>This Month's Featured Pieces</h1>");
//            out.println("</div>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
