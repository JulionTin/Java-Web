/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.admin;

import asia.uap.donate.DonationData;
import asia.uap.methods.DbCredentials;
import java.io.IOException;
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

/**
 *
 * @author poopy
 */
@WebServlet(name = "DonationRequest", urlPatterns = {"/do.showdonationrequests"})
public class DonationRequest extends HttpServlet {

    private DbCredentials cred = new DbCredentials();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = "Admin/donationRequest.jsp";
        
        ArrayList<DonationData> requestList = new ArrayList<>();
        Connection conn = null;
        try{
            conn = cred.loadClass();
            
            String requests = "select * from donation_request";
            PreparedStatement ps = conn.prepareStatement(requests);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                DonationData data = new DonationData();
                data.setDonation_id(rs.getInt("donation_id"));
                data.setArt_id(rs.getInt("art_id"));
                data.setDonation_amount(rs.getDouble("donation_amount"));
                data.setProof_of_payment(rs.getBlob("proof_of_payment"));
                data.setRequest_status(rs.getString("request_status"));
                requestList.add(data);
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

        request.setAttribute("Donations", requestList);
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
