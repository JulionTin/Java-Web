/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.controller;

import asia.uap.model.BBCGrade;
import asia.uap.model.BBCStudent;
import asia.uap.model.BBCSubject;
import asia.uap.model.BCCDao;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author 220187
 */
@WebServlet(name = "do.eval", urlPatterns = {"/do.eval"})
public class Evaluate extends HttpServlet {

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
        String uri = "WEB-INF/jsp/process.jsp";
        BCCDao DB = new BCCDao();
        String student_id = request.getParameter("student");
        String subject_code = request.getParameter("subject");
        String grade = request.getParameter("grade");
        BBCGrade report = new BBCGrade();
        
        
        
        if(student_id == null || student_id.isEmpty() || subject_code == null || subject_code.isEmpty() || grade == null || grade.isEmpty()){
            response.sendRedirect("index.jsp");
            return;
        }else
        {
            if(student_id != null && grade != null){
                int parse_id = Integer.parseInt(student_id);
                int parse_grade = Integer.parseInt(grade);
                if(parse_grade < 40 || parse_grade > 100){
                    response.sendRedirect("index.jsp");
                    return;
                }else{
                    report.setStudent_id(parse_id);
                    report.setGrade(parse_grade);
                }
                
            try{
                report.setSubject_id(DB.loadSubject_ids(subject_code));
                DB.getReport(report);
            }catch(SQLException e){
                log("No stuff");
            }
            
            }
            
            RequestDispatcher rd = request.getRequestDispatcher(uri);
            rd.forward(request, response);        
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
