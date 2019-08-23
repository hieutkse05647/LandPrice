package DAO;

/**
 *
 * @author Tu Khac Hieu
 */
import entity.Area;
import entity.Location;
import DBConnect.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    public static List<Area> getArea(String query) throws Exception {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Area> area = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int AreaID = rs.getInt("AreaID");
            String AreaName = rs.getString("AreaName");
            double AreaMaxPrice = rs.getDouble("AreaMaxPrice");
            double AreaMinPrice = rs.getDouble("AreaMinPrice");
            double CurrentPrice = rs.getDouble("Price");
            String description = rs.getString("AreaDescription");
            area.add(new Area(AreaID, AreaName, AreaMaxPrice, AreaMinPrice, CurrentPrice,description));
        }
        rs.close();
        conn.close();
        return area;
    }

    public static List<Area> getAllArea(String AreaName) throws Exception {
        String query = "Select * From Area";
        return getArea(query);
    }

    public static List<Integer> getAllAreaID() throws Exception {
        String query = "Select AreaID From Area";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        List<Integer> areaID = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int AreaID = rs.getInt("AreaID");
            areaID.add(AreaID);
        }
        rs.close();
        conn.close();
        return areaID;
    }

    public static int getLastIDArea() throws Exception {
        String query = "Select Max (AreaID) as LastIDArea from Area";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        int lastID = 0;
        while (rs.next()) {
            lastID = rs.getInt("LastIDArea");
        }
        rs.close();
        conn.close();
        return lastID;
    }

    public static List<Area> getAreaByPrice(double maxPriceSearch, double minPriceSearch) throws Exception {
        String query = "Select * From Area Where Price Beetween " + maxPriceSearch + " AND " + minPriceSearch;
        return getArea(query);
    }

    public static List<Area> getAreaByName(String AreaName) throws Exception {
        String query = "Select * From Area Where AreaName=" + AreaName;
        return getArea(query);
    }

    public static Area getAreaByID(int AreaID) throws Exception {
        String query = "Select * From Area Where AreaID = " + AreaID;
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        String AreaName = rs.getString("AreaName");
        String AreaDescription = rs.getString("AreaDescription");
        double AreaMaxPrice = rs.getDouble("AreaMaxPrice");
        double AreaMinPrice = rs.getDouble("AreaMinPrice");
        double CurrentPrice = rs.getDouble("Price");
        Area area = new Area(AreaID, AreaName, AreaMaxPrice, AreaMinPrice, CurrentPrice,AreaDescription);
        rs.close();
        conn.close();
        return area;
    }

    // add object
    public static void addNewArea(Area a) throws Exception {
        String query = "insert into Area values(?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, a.getAreaID());
        ps.setString(2, a.getAreaName());
        ps.setDouble(3, a.getAreaMaxPrice());
        ps.setDouble(4, a.getAreaMinPrice());
        ps.setDouble(5, a.getCurrentPrice());
        ps.executeUpdate();
        conn.close();
    }

    //when submit form after add marker
    public void addNewAreaWithForm(String areaName, double maxPrice, double minPrice, double curentPrice, String description) throws Exception {
        String query = "insert into Area (AreaName, AreaMaxPrice, AreaMinPrice, Price, AreaDescription) values(?,?,?,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, areaName);
        ps.setDouble(2, maxPrice);
        ps.setDouble(3, minPrice);
        ps.setDouble(4, curentPrice);
        ps.setString(5, description);
        ps.executeUpdate();
        conn.close();
    }

    //update Area
    public void updateArea(int areaID, String name, double Maxprice, double Minprice, double price) throws Exception {
        String query = "UPDATE Area SET AreaName = '" + name + "' , AreaMaxPrice = " + Maxprice + " , AreaMinPrice = " + Minprice + " , Price = '" + price + "' WHERE AreaID = " + areaID;
        Connection conn = new DBContext().getConnection();
        conn.prepareStatement(query).executeUpdate();
        conn.close();
    }

    // when delete Area, so delete All Location - Marker in this Area
    // Click Area -> get ID of this Area & delete By ID
    public void deleteArea(int areaID) throws Exception {
        String queryArea = "Delete From Area Where AreaID = '" + areaID;
        String queryLocation = "Delete From Location Where AreaID = '" + areaID;
        Connection conn = new DBContext().getConnection();
        conn.prepareStatement(queryArea).executeUpdate();         // delete Area by ID
        conn.prepareStatement(queryLocation).executeUpdate();     // delete All Location - Marker in this Area
        conn.close();
    }

    // when map load init and Location Follow AreaID
    public static ArrayList<Area> getAllAreaWithLocation() throws Exception {
        String query = "Select a.AreaID,AreaName,AreaMaxPrice,AreaMinPrice,AreaDescription"
                + "Price,l.Latitude,l.Longtitude From Area a Inner Join Location l on l.AreaID = a.AreaID";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ArrayList<Area> areaWithLocation = new ArrayList<>();
        ArrayList<Area> areaList = new ArrayList<>();
        ArrayList<Location> mapsl = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int areaID = rs.getInt("AreaID");
            Double latitude = rs.getDouble("Latitude");
            Double longtitude = rs.getDouble("Longtitude");
            // add Location follow AreaID
            mapsl.add(new Location(areaID, latitude, longtitude));
            String areaName = rs.getString("AreaName");
            String areaDes = rs.getString("AreaDescription");
            Float areaMaxprice = rs.getFloat("AreaMaxPrice");
            Float areaMinprice = rs.getFloat("AreaMinPrice");
            Float currentPrice = rs.getFloat("Price");
            areaList.add(new Area(areaID, areaName, areaMaxprice, areaMinprice, currentPrice, areaDes));
        }

        ArrayList<Location> mapsltemp = new ArrayList<>();
        for (int i = 0; i < areaList.size(); i++) {
            for (int j = 0; j < mapsl.size(); j++) {
                if (areaList.get(i).getAreaID() == mapsl.get(j).getAreaID()) {
                    mapsltemp.add(mapsl.get(j));
                }
            }
            areaWithLocation.add(new Area(areaList.get(i).getCurrentPrice(), mapsltemp, areaList.get(i).getAreaID(),
                     areaList.get(i).getAreaName(), areaList.get(i).getAreaMaxPrice(),
                    areaList.get(i).getAreaMinPrice(), areaList.get(i).getAreaDescription()));
        }
        rs.close();
        conn.close();
        return areaWithLocation;
    }
}
