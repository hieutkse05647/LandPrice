/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Tu Khac Hieu
 */
public class Area {
    
    private double currentPrice;
    private LinkedHashMap<Double, Double> map;
    private ArrayList<Location> mapsl;
    
    public Area(double currentPrice, LinkedHashMap<Double, Double> map) {
        this.currentPrice = currentPrice;
        this.map = map;
    }
    // đoạn này trả ra map luôn thì chưa biết xử lý thế nào :( 
    private int AreaID;
    private String AreaName;
    private double AreaMaxPrice;
    private double AreaMinPrice;
    private String AreaDescription;
    
    public Area( int AreaID, String AreaName, double AreaMaxPrice, double AreaMinPrice,double currentPrice) {
        this.currentPrice = currentPrice;
        this.AreaID = AreaID;
        this.AreaName = AreaName;
        this.AreaMaxPrice = AreaMaxPrice;
        this.AreaMinPrice = AreaMinPrice;
    }

    public Area(double currentPrice, ArrayList<Location> mapsl, int AreaID, String AreaName, double AreaMaxPrice, double AreaMinPrice) {
        this.currentPrice = currentPrice;
        this.map = map;
        this.mapsl = mapsl;
        this.AreaID = AreaID;
        this.AreaName = AreaName;
        this.AreaMaxPrice = AreaMaxPrice;
        this.AreaMinPrice = AreaMinPrice;
    }

    public Area( int AreaID, String AreaName, String description, double currentPrice, LinkedHashMap<Double, Double> map) {
        this.currentPrice = currentPrice;
        this.AreaID = AreaID;
        this.AreaName = AreaName;
        this.AreaDescription = description;
        this.map = map;
    }

    public String getAreaDescription() {
        return AreaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.AreaDescription = areaDescription;
    }
    
    public int getAreaID() {
        return AreaID;
    }

    public void setAreaID(int AreaID) {
        this.AreaID = AreaID;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    public double getAreaMaxPrice() {
        return AreaMaxPrice;
    }

    public void setAreaMaxPrice(double AreaMaxPrice) {
        this.AreaMaxPrice = AreaMaxPrice;
    }

    public double getAreaMinPrice() {
        return AreaMinPrice;
    }

    public void setAreaMinPrice(double AreaMinPrice) {
        this.AreaMinPrice = AreaMinPrice;
    }

        
    public double getCurrentPrice() {
        return currentPrice;
    }

    public LinkedHashMap<Double, Double> getMap() {
        return map;
    }
}
