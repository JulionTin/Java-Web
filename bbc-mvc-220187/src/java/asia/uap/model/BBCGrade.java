/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.model;

import java.io.Serializable;

/**
 *
 * @author 220187
 */
public class BBCGrade implements Serializable{
    private String Subject;
    private int Subject_id;
    private String Student;
    private int Student_id;
    private int grade;

    public BBCGrade() {
        this.Subject = "";
        this.Subject_id = 0;
        this.Student = "";
        this.Student_id = 0;
        this.grade = 0;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public int getSubject_id() {
        return Subject_id;
    }

    public void setSubject_id(int Subject_id) {
        this.Subject_id = Subject_id;
    }

    public String getStudent() {
        return Student;
    }

    public void setStudent(String Student) {
        this.Student = Student;
    }

    public int getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(int Student_id) {
        this.Student_id = Student_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    
}
