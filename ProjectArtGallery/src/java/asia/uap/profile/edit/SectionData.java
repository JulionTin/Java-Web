/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile.edit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author poopy
 */
public class SectionData {
    private String sectionName;
    private ArrayList<ArtData> artworks;

    public ArrayList<ArtData> getArtworks() {
        return artworks;
    }

    public void setArtworks(ArrayList<ArtData> artworks) {
        this.artworks = artworks;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    
    public String showImage(Blob blob){
        byte byteArray[] = null;
        try {
           byteArray = blob.getBytes(1,(int)blob.length());
        } catch( SQLException e){
            e.printStackTrace();
        }

        String base64String = Base64.getEncoder().encodeToString(byteArray);

        return base64String;


    }

}
