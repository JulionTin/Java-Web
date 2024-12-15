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
public class ReportBean implements Serializable{
    private String reporter;
    private String art_owner;
    private int art_id;
    private int report_id;
    private String art_name;
    private String report_desc;

    public ReportBean() {
        this.reporter = "";
        this.art_owner = "";
        this.art_id = 0;
        this.art_name = "";
        this.report_desc = "";
        this.report_id = 0;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }


    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getArt_owner() {
        return art_owner;
    }

    public void setArt_owner(String art_owner) {
        this.art_owner = art_owner;
    }

    public int getArt_id() {
        return art_id;
    }

    public void setArt_id(int art_id) {
        this.art_id = art_id;
    }

    public String getReport_desc() {
        return report_desc;
    }

    public void setReport_desc(String report_desc) {
        this.report_desc = report_desc;
    }

    public String getArt_name() {
        return art_name;
    }

    public void setArt_name(String art_name) {
        this.art_name = art_name;
    }
    
    
    
}
