/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.donate;

import asia.uap.methods.DbCredentials;
import asia.uap.methods.UsefulMethods;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author poopy
 */
@WebServlet(name = "ProcessDonation", urlPatterns = {"/do.donate"})
@MultipartConfig
public class ProcessDonation extends HttpServlet {

    private DbCredentials cred = new DbCredentials();
    private UsefulMethods method = new UsefulMethods();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = "WEB-INF/jsp/donationSuccess.jsp";
        
        String donated = request.getParameter("DonationAmt");
        Part proof = request.getPart("PayProof");
        
        
        Connection conn = null;
        try{
            conn = cred.loadClass();
            
            
            String donationRequest = "insert into donation_request (donation_amount, proof_of_payment, art_id) values (?, ?, ?)";           
            PreparedStatement ps = conn.prepareStatement(donationRequest);
            if (method.isDigit(donated)){
                ps.setDouble(1, Double.parseDouble(donated));;
            }
            
            InputStream content = proof.getInputStream();
            ps.setBlob(2, content);
            ps.setString(3, request.getParameter("artId"));

            int rs = ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        request.setAttribute("user", request.getParameter("user"));
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
