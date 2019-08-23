/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Tu Khac Hieu
 */
public class Location {
    private int AreaID;
    private Double latitude;
    private Double longtitude;

    public Location(int AreaID,Double latitude, Double longtitude) {
        this.AreaID = AreaID;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public int getAreaID() {
        return AreaID;
    }

    public void setAreaID(int AreaID) {
        this.AreaID = AreaID;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    
    
}
