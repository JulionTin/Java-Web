/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.profile;

import asia.uap.DbCredentials;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author poopy
 */
@WebServlet(name = "ShowProfile", urlPatterns = {"/do.showprofile"})
public class ShowProfile extends HttpServlet {

    private Connection loadClass() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DbCredentials.getDbUrl(), DbCredentials.getDbUser(), DbCredentials.getDbPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to the database.");
        }
        return conn;
    }
    
    public boolean isNullCheck(String str){        
    boolean isnull = false;            
    if(str == null || str.isEmpty()){
        isnull = true;
    }     
    return isnull;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String uri;
        
        String userProfile = request.getParameter("user");
        log(userProfile);
        int userId = -1;
        
        if(isNullCheck(userProfile)){
            userProfile = (String) session.getAttribute("loggedInUser");
        }
        
        uri="profile.jsp?user=" + userProfile;
        // change here
        request.setAttribute("showedProfile", userProfile);
        
        ArrayList<SectionData> sectionList = new ArrayList<>();
        
       
        Connection conn = null;
        try {
            conn = loadClass();
            //gets the userId of the profile being shown
            String getUserId = "select * from user_account where username = ?";
            PreparedStatement ps = conn.prepareStatement(getUserId);
            ps.setString(1, userProfile);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                userId = rs.getInt("user_id");
            }
            //gets all sections
            String getUserSections = "select * from section where user_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(getUserSections);
            ps1.setInt(1, userId);
            
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()){
                
                SectionData section = new SectionData();
                section.setSectionName(rs1.getString("section_id"));
                
                //fill the section with their artworks

                    //gets all art_ids that are associated with the section
                    String getArtworks = "select * from section_art where user_id = ? and section_id = ?";
                    PreparedStatement ps2 = conn.prepareStatement(getArtworks);
                    ps2.setInt(1, userId);
                    ps2.setString(2, rs1.getString("section_id"));

                    ArrayList<ArtData> artwork = new ArrayList<>();
                    ResultSet rs2 = ps2.executeQuery();
                    while (rs2.next()){
                        
                        
                        String getArt = "select * from artwork where art_id = ?";
                        PreparedStatement ps3 = conn.prepareStatement(getArt);
                        ps3.setInt(1, rs2.getInt("art_id"));
                        ResultSet rs3 = ps3.executeQuery();
                        while(rs3.next()){
                            ArtData art = new ArtData();
                            // note to self
                            //blob cannot be converted to input stream
                            //input stream can be converted to blob
                            art.setArt(rs3.getBlob("artpiece"));
                            art.setDesc(rs3.getString("art_desc"));
                            art.setTitle(rs3.getString("art_name"));
                            art.setUserId(rs3.getInt("user_id"));
                            art.setArtId(rs3.getInt("art_id"));
                            artwork.add(art);
                        }
                        
                    }

             section.setArtworks(artwork);
             sectionList.add(section);
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
        
        //changed session to request
        request.setAttribute("Sections", sectionList);
        RequestDispatcher rd = request.getRequestDispatcher(uri);
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
