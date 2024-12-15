/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap.donate;

import asia.uap.methods.DbCredentials;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author poopy
 */
public class DonationData {
    int donation_id;
    double donation_amount;
    Blob proof_of_payment;
    int art_id;
    String request_status;

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
    }

    public double getDonation_amount() {
        return donation_amount;
    }

    public void setDonation_amount(double donation_amount) {
        this.donation_amount = donation_amount;
    }

    public Blob getProof_of_payment() {
        return proof_of_payment;
    }

    public void setProof_of_payment(Blob proof_of_payment) {
        this.proof_of_payment = proof_of_payment;
    }

    public int getArt_id() {
        return art_id;
    }

    public void setArt_id(int art_id) {
        this.art_id = art_id;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }
 
    
    public static String showImage(Blob proof){
           
        byte byteArray[] = null;
        
        try {
           byteArray = proof.getBytes(1,(int)proof.length());
        } catch( SQLException e){
            e.printStackTrace();
        }

        

        String base64String = Base64.getEncoder().encodeToString(byteArray);

        return base64String;


    }
}
