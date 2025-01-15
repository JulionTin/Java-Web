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
@WebServlet(name = "index.jsp", urlPatterns = {"/index.jsp"})
public class Home extends HttpServlet {

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
        String uri = "WEB-INF/jsp/index.jsp";
        ArrayList<BBCSubject> subjects = null;
        ArrayList<BBCStudent> students = null;
        BCCDao DB = new BCCDao();
        String student_id = request.getParameter("student");
        String subject_code = request.getParameter("subject");
        String grade = request.getParameter("grade");
        BBCGrade report = new BBCGrade();
        
        try{
            subjects = DB.loadSubjects();
            students = DB.loadStudents();
            int subject_id = DB.loadSubject_ids(subject_code);
            report.setSubject_id(subject_id);
            request.setAttribute("subjects", subjects);
            request.setAttribute("students", students);
        }catch(SQLException e){
            e.getStackTrace();
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
