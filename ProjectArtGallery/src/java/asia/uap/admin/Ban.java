/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.admin;

import asia.uap.DbCredentials;
import asia.uap.ReportBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "do.ban", urlPatterns = {"/do.ban"})
public class Ban extends HttpServlet {

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
        
        String uri = "/Admin/BanArt.jsp";
        Connection conn = null;
        try {
            ArrayList<ReportBean> reports = new ArrayList<>();
            HashMap<Integer, Integer> Arttocount = new HashMap<>();
            conn = loadClass();
            
            String getBanQuery = " select reports.report_id, a.username as reporter, b.username as art_owner, reports.report_desc,"
                    + " artwork.art_id, artwork.art_name from reports left join user_account a on reports.reporter "
                    + "= a.user_id left join user_account b on reports.art_owner = b.user_id left join 	artwork on "
                    + "reports.art_id = artwork.art_id order by reports.art_id";
            PreparedStatement ps1 = conn.prepareStatement(getBanQuery);
            
            ResultSet rs1 = ps1.executeQuery();

            if(rs1 != null){
                while(rs1.next()){
                    String reporter = rs1.getNString("reporter");
                    String art_owner = rs1.getNString("art_owner");
                    String art_name = rs1.getNString("artwork.art_name");
                    String report_desc = rs1.getNString("reports.report_desc");
                    int art_id = rs1.getInt("artwork.art_id");
                    int report_id = rs1.getInt("reports.report_id");
                    ReportBean report = new ReportBean();
                    report.setReport_id(report_id);
                    report.setArt_id(art_id);
                    report.setReporter(reporter);
                    report.setArt_owner(art_owner);
                    report.setArt_name(art_name);
                    report.setReport_desc(report_desc);
                    reports.add(report);
                    
                    
                                 
                    
                }
            }
            request.setAttribute("R", reports);
           
            for (int i = 0; i < reports.size(); i++) {
                String getCountQuery = "select count(art_id) as art_count , art_id from reports where art_id = ? having count(art_id) > 1";
                PreparedStatement ps2 = conn.prepareStatement(getCountQuery);
                ps2.setInt(1, reports.get(i).getArt_id());

                ResultSet rs2 = ps2.executeQuery();

                if(rs2 != null){
                    

                    while(rs2.next()){
                        int art_count = rs2.getInt("art_count");
                        int art_id = rs2.getInt("art_id");
                        Arttocount.put(art_id, art_count);

                    }
                    request.setAttribute("art_count", Arttocount);
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
