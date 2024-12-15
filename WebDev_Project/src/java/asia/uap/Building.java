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
public class Building implements Serializable{
    private String building_name;

    public Building() {
        this.building_name = "";
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }
    
    
}
