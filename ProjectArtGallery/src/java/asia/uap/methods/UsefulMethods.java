/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.Part;

/**
 *
 * @author poopy
 */
public class UsefulMethods {
    // import this class then feel free to use any of the methods here
    // also if you add a method here, be sure to comm it to the group
    public boolean isNullCheck(String str){        
    boolean isnull = false;            
    if(str == null || str.isEmpty()){
        isnull = true;
    }     
    return isnull;
    }
    
    public boolean isDigit(String str){
        int num;
        boolean isDigit;
        try {
            num = Integer.parseInt(str);
            isDigit = true;
        } catch (NumberFormatException e) {
            isDigit = false;
        }
        return isDigit;
    }
    
    public boolean isImg(Part part){
        boolean isImg = false;  
        if (part.getContentType().startsWith("image/")){
            isImg = true;
        }
        return isImg;
    }
    
    public static String getGcash(String username){
        String gcash = "";
        
        Connection conn = null;
        DbCredentials cred = new DbCredentials();
        try{
            conn = cred.loadClass();
            String query = "select * from user_account where username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true){
                gcash = rs.getString("gcash_credentials");
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
        
        return gcash;
    }
}
