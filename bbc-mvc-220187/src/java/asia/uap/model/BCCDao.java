/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 220187
 */
public class BCCDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bbcdb?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "chowders";

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
    
    public Connection loadClass() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(this.getDbUrl(), this.getDbUser(), this.getDbPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to the database.");
        }
        return conn;
    }
     
    public ArrayList<BBCSubject> loadSubjects() throws SQLException{
        Connection conn = null;
        ArrayList<BBCSubject> subjects = new ArrayList<BBCSubject>();
        try {
           conn = this.loadClass(); 
           
           String getBanQuery = "select * from subject";
           PreparedStatement ps1 = conn.prepareStatement(getBanQuery);
           ResultSet rs1 = ps1.executeQuery();
           
           while(rs1.next()){
               String subject_code = rs1.getNString("subject_code");
               String subject_name = rs1.getNString("subject_name");
               BBCSubject subject = new BBCSubject();
               subject.setSubject_code(subject_code);
               subject.setSubject_name(subject_name);
               subjects.add(subject);
               
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
       return subjects; 
    }
    
    public ArrayList<BBCStudent> loadStudents() throws SQLException{
        Connection conn = null;
        ArrayList<BBCStudent> students = new ArrayList<BBCStudent>();
        try {
           conn = this.loadClass(); 
           
           String getBanQuery = "select * from student";
           PreparedStatement ps1 = conn.prepareStatement(getBanQuery);
           ResultSet rs1 = ps1.executeQuery();
           
           while(rs1.next()){
               int student_id = rs1.getInt("student_id");
               String first_name = rs1.getNString("first_name");
               String last_name = rs1.getNString("last_name");
               BBCStudent student = new BBCStudent();
               student.setStudent_id(student_id);
               student.setFirst_name(first_name);
               student.setLast_name(last_name);
               students.add(student);
               
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
       return students; 
    }
    
    public int getReport(BBCGrade report) throws SQLException{
        Connection conn = null;
        int rs1 =0;
        try {
           conn = this.loadClass(); 
           
           String Insert = "insert into student_grade(student_id, subject_id, grade)values(?,?,?)";
           PreparedStatement ps1 = conn.prepareStatement(Insert);
           ps1.setInt(1, report.getStudent_id());
           ps1.setInt(2, report.getSubject_id());
           ps1.setInt(3, report.getGrade());
           rs1 = ps1.executeUpdate();
          
           
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
        return rs1;
    }
    
    public ArrayList<BBCGrade> loadReports() throws SQLException{
        Connection conn = null;
        ArrayList<BBCGrade> reports = new ArrayList<BBCGrade>();
        try {
           conn = this.loadClass(); 
           
           String getQuery = "select * from student_grade";
           PreparedStatement ps1 = conn.prepareStatement(getQuery);
           ResultSet rs1 = ps1.executeQuery();
           
           while(rs1.next()){
               int sg_id = rs1.getInt("sg_id");
               int student_id = rs1.getInt("student_id");
               int subject_id = rs1.getInt("subject_id");
               int grade = rs1.getInt("grade");
               BBCGrade report = new BBCGrade();
               report.setSubject_id(sg_id);
               report.setStudent_id(student_id);
               report.setSubject_id(subject_id);
               report.setGrade(grade);
               reports.add(report);
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
       return reports; 
    }
    
    public int loadSubject_ids(String code) throws SQLException{
        Connection conn = null;
        int subject_id = 0;
        try {
           conn = this.loadClass(); 
           
           String getBanQuery = "select subject_id from subject where subject_code = ?";
           PreparedStatement ps1 = conn.prepareStatement(getBanQuery);
           ps1.setString(1, code);
           ResultSet rs1 = ps1.executeQuery();
           
           while(rs1.next()){
               subject_id = rs1.getInt("subject_id");
               
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
       return subject_id; 
    }
    
    public ArrayList<BBCGrade> loadGrades() throws SQLException{
        Connection conn = null;
        ArrayList<BBCGrade> reports = new ArrayList<BBCGrade>();
        try {
           conn = this.loadClass(); 
           
           String getBanQuery = "select * from student_grade";
           PreparedStatement ps1 = conn.prepareStatement(getBanQuery);
           ResultSet rs1 = ps1.executeQuery();
           
           while(rs1.next()){
               int sg_id = rs1.getInt("sg_id");
               int student_id = rs1.getInt("student_id");
               int subject_id = rs1.getInt("subject_id");
               int grade = rs1.getInt("grade");
               BBCGrade report = new BBCGrade();
               report.setSubject_id(sg_id);
               report.setStudent_id(student_id);
               report.setSubject_id(subject_id);
               report.setGrade(grade);
               reports.add(report);
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
       return reports; 
    }
}
