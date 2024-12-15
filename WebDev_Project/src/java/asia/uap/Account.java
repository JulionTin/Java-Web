/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.io.Serializable;

/**
 *
 * @author BIR
 */
public class Account implements Serializable{
    private String fname;
    private String lname;
    private String email;
    private String pass;

    public Account() {
        this.fname = "";
        this.lname = "";
        this.email = "";
        this.pass = "";
    }

    public String getFname() {
        return fname;
    }
    
    public String getLname() {
        return lname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPass() {
        return pass;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    
    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
