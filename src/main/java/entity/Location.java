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
    private float latitude;
    private float longtitude;

    public Location(int AreaID,float latitude, float longtitude) {
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }
    
    
}
