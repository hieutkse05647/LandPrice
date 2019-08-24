/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AreaDAO;
import DAO.LocationDAO;
import entity.Area;
import entity.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tu Khac Hieu
 */
public class LocationController {

    public static boolean checkAreainMap(int AreaID, Double SELangtitude, Double NWLangtitude, Double NWLongitude, Double SELongitude) throws Exception {
        ArrayList<Location> listLocationbyID = new ArrayList<>();
        listLocationbyID = LocationDAO.getLocationByAreaID(AreaID);
        for (int i = 0; i < listLocationbyID.size(); i++) {
            if (checkLatLocation(listLocationbyID.get(i).getLatitude(), SELangtitude, NWLangtitude)
                    && checkngLocation(listLocationbyID.get(i).getLongtitude(), NWLongitude, SELongitude)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLatLocation(Double lat, Double SELangtitude, Double NWLangtitude) {
        if (SELangtitude < lat && lat < NWLangtitude) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkngLocation(Double lng, Double NWLongitude, Double SELongitude) {
        if (NWLongitude < lng && lng < SELongitude) {
            return true;
        } else {
            return false;
        }
    }

	//this method is used to get all Area in Current Map 
    public static List<Area> AreaInMap(Double SELangtitude, Double NWLangtitude, Double NWLongitude, Double SELongitude) throws Exception {
        List<Integer> AreaIDlist = AreaDAO.getAllAreaID();
        List<Area> area = new ArrayList();
        for (int i = 0; i < AreaIDlist.size(); i++) {
            if(checkAreainMap(AreaIDlist.get(i), SELangtitude, NWLangtitude, NWLongitude, SELongitude))
                area.add(AreaDAO.getAreaByID(AreaIDlist.get(i)));
        }
        return area;
    }
}
