/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnect.DBContext;
import entity.Area;
import entity.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tu Khac Hieu
 */
public class LocationDAO {   
    public static ArrayList<Location> getLocation(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ArrayList<Location> locationList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int areaID = rs.getInt("AreaID");
            Double latitude = rs.getDouble("Latitude");
            Double longtitude = rs.getDouble("Longtitude");
            locationList.add(new Location(areaID, latitude,longtitude));
        }
        rs.close();
        conn.close();
        return locationList;
    }

    //method get Location by Area ID 
    public static ArrayList<Location> getLocationByAreaID(int areaIdInput) throws Exception {
        String query = "Select * From Location Where AreaID = " + areaIdInput;
        return getLocation(query);
    }
    
   


    // add object - Location - Marker
    public static void addNewLocation(Location locate) throws Exception {
        String query = "insert into Location(AreaId,Latitude,Longtitude) values(?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, locate.getAreaID());
        ps.setDouble(2, locate.getLatitude());
        ps.setDouble(3, locate.getLongtitude());
        ps.executeUpdate();
        conn.close();
    }

    
    //when submit form add New marker (Location)
    // user choose Area in ListBox -> Controller get ID and add with this AreaID. 
    public static void addNewLocationWithForm(int areaID, Double latitude , Double longtitude) throws Exception {
<<<<<<< HEAD
        String query = "INSERT INTO Location values(?,?,?)";
=======
        String query = "INSERT INTO Location(AreaId,Latitude,Longtitude) values(?,?,?)";
>>>>>>> 42f78622fb2c341c3c8e18aaa4326bfc6330abf2
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, areaID);
        ps.setDouble(2, latitude);
        ps.setDouble(3, longtitude);
        ps.executeUpdate();
        conn.close();
    }
    
    
    //update Area
    public static void updateLocation(int areaID,  Double latitude , Double longtitude) throws Exception {
        String query = "UPDATE Location SET AreaID = '" + areaID + "' , Latitue = " + latitude + " , Longtitude = " + longtitude;
        Connection conn = new DBContext().getConnection();
        conn.prepareStatement(query).executeUpdate();
        conn.close();
    }
    
    
     
     
}
