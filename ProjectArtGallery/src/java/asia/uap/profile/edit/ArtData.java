/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile.edit;

import asia.uap.methods.DbCredentials;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;


public class ArtData {
   private DbCredentials cred = new DbCredentials();
   private Blob art;
   private String title;
   private String desc;
   private int userId;
   //change made
   private int artId;

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public Blob getArt() {
        return art;
    }

    public void setArt(Blob art) {
        this.art = art;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    //change made
    public String getImage(int artId){
        Blob art = null;
        String name;
        Connection conn = null;
        try{
            conn = cred.loadClass();
            //gets the userId of the profile being shown
            String getArtId = "select * from artwork where art_id = ?";
            PreparedStatement ps = conn.prepareStatement(getArtId);
            ps.setInt(1, artId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                art = rs.getBlob("artpiece");
                name = rs.getNString("art_name");
            }
        } catch (SQLException e){
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
        
            
        byte byteArray[] = null;
        
        try {
           byteArray = art.getBytes(1,(int)art.length());
        } catch( SQLException e){
            e.printStackTrace();
        }

        

        String base64String = Base64.getEncoder().encodeToString(byteArray);

        return base64String;


    }
    
   
    
    public String getTitle(int artId){
        String title = null;
        Connection conn = null;
        try{
             conn = cred.loadClass();
            //gets the userId of the profile being shown
            String getArtId = "select art_name from artwork where art_id = ?";
            PreparedStatement ps = conn.prepareStatement(getArtId);
            ps.setInt(1, artId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                title = rs.getString("art_name");
            }
        } catch (SQLException e){
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
       

        return title;


    }
}
