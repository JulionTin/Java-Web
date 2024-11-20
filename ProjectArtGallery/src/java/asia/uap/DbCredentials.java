/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

/**
 *
 * @author Raymond
 */
public class DbCredentials {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/art_gallery?serverTimezone=UTC";
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
}
