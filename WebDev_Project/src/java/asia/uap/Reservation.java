/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asia.uap;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Joseph
 */
public class Reservation implements Serializable{
    
    private int account_id;
    
    private int reservation_id;
    private String  title;
    private LocalDate date;
    private LocalTime time;
    private int capacity;
    
    private String building;
    private String floor;
    private String room;
    private int building_id;
    private int floor_id;
    private int room_id;
    
    private String status;
    
    private String description;

    public Reservation() {
        
        this.account_id = 0;
        
        this.reservation_id = 0;
        
        this.title = "";
        this.date = LocalDate.now();
        this.capacity = 0;
        
        this.building = "";
        this.floor = "";
        this.room = "";
        
        this.building_id = 0;
        this.floor_id = 0;
        this.room_id = 0;
        
        this.time = LocalTime.now();
        this.description = "";
        
        this.status = "reserved";
    }
    
    public int getAccountID() {
        return account_id;
    }
    
    public void setAccountID(int account_id) {
        this.account_id = account_id;
    }
    
    public int getReservationID() {
        return reservation_id;
    }
    
    public void setReservationID(int reservation_id) {
        this.reservation_id = reservation_id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public LocalDate getDate(){
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getBuilding(){
        return building;
    }
    
    public void setBuilding(String building) {
        this.building = building;
    }
    
    public String getFloor(){
        return floor;
    }
    
    public void setFloor(String floor) {
        this.floor = floor;
    }
    
    public String getRoom(){
        return room;
    }
    
    public void setRoom(String room) {
        this.room = room;
    }
    
    public LocalTime getTime(){
        return time;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setBuildingID(int building_id) {
        this.building_id = building_id;
    }
    
    public void setFloorID(int floor_id) {
        this.floor_id = floor_id;
    }
    
    public void setRoomID(int room_id) {
        this.room_id = room_id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public int getFloor_id() {
        return floor_id;
    }

    public int getRoom_id() {
        return room_id;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    

    
    /*public void setTitleID(int title_id) {
        this.title_id = title_id;
    }
    
    public void setDateID(int date_id) {
        this.date_id = date_id;
    }
    
    public void setTimeID(int time_id) {
        this.time_id = time_id;
    }
    
    public void setCapacityID(int capacity_id) {
        this.capacity_id = capacity_id;
    }
    
    
    
    public void setDescriptionID(int description_id) {
        this.description_id = description_id;
    }*/

}
