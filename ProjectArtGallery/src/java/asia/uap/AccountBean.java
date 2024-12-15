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
public class AccountBean implements Serializable{
    private int user_id;
    private String Username;
    private String Password;
    private String Role;

    public AccountBean() {
        this.user_id = 0;
        this.Username = "";
        this.Password = "";
        this.Role = "";
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    
    
    
    
    
}
