/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BIR
 */
public class DBIO {
    private String DBURL = "jdbc:mysql://localhost:3306/reservedb?serverTimezone=UTC";
    private String DBUser = "root";
    private String DBPass = "chowders" ;

    public String getDBURL() {
        return DBURL;
    }

    public String getDBUser() {
        return DBUser;
    }

    public String getDBPass() {
        return DBPass;
    }
    
    public Connection loadClass() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(this.getDBURL(), this.getDBUser(), this.getDBPass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to the database.");
        }
        return conn;
    }
    
}
