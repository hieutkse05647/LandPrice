package Controller;

import DAO.AreaDAO;
import adapter.GeoJSONAdapter;
import entity.Area;
import entity.Location;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.json.JSONObject;

@ManagedBean
@RequestScoped
public class searchPage {

    private String address;
    private int min, max;
    private String dataMap;
    private Integer activeIndex;
    private ArrayList<Area> areasAll,areas;
    

    public ArrayList<Area> getAreas() {
        return areas;
    }
    
    @PostConstruct
    public void init() {
        address = "Đại học FPT";
        min = 1;
        max = 100;
        areas = new ArrayList<>();
        activeIndex = null;
        //TODO: CALL BACKEND GET ALL DATA 
        // Call method : getAllAreaWithLocation();
        
        AreaDAO dao = new AreaDAO();
        ArrayList<Area> listFromDB = new ArrayList<>();
        try{
            listFromDB = dao.getAllAreaWithLocation();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
//        areas = createAreasArray();
        areas = setDataAreasArray(listFromDB);
        areasAll = areas;
        
        JSONObject json = createJSON(areas);
        
        dataMap = json.toString();
//        PrimeFaces.current().executeScript("alert('peek-a-boo');");
    }

    public void onSlideChange(){

        // TODO: CALL BACKEND GET DATA BY PRICE ( START - END )
        ArrayList<Area> areasTemp = new ArrayList<>();
        int countId=0;
        for (Area area: areasAll){
            if (min <= area.getCurrentPrice() && area.getCurrentPrice() <= max){
                Area tempElement = area;
                tempElement.setAreaID(countId++);
                areasTemp.add(area);
            }
        }
        areas = areasTemp;
        JSONObject json = createJSON(areas);
        
        dataMap = json.toString();
    }
    private JSONObject createJSON(ArrayList<Area> listAreas) {   
        return GeoJSONAdapter.createGeoJSON(listAreas);
    }

    private ArrayList<Area> setDataAreasArray(ArrayList<Area> listFromDB){
        ArrayList<Area> listAreas= new ArrayList<>();
        int countId=0;
        for (Area area: listFromDB){
            LinkedHashMap<Double, Double> map = new LinkedHashMap<>();
            Location firstLocation = null;
            for (Location location: area.getMapsl()){
                if (firstLocation == null){
                    firstLocation = location;
                }
                map.put(location.getLongtitude(), location.getLatitude());
            }
            map.put(firstLocation.getLongtitude(), firstLocation.getLatitude());
            listAreas.add(new Area(countId++, area.getAreaName(), area.getAreaDescription(), area.getCurrentPrice(), map));
        }
        return listAreas;
    }
    private ArrayList<Area> createAreasArray() {
        ArrayList<Area> listAreas= new ArrayList<>();
        listAreas.clear();
        LinkedHashMap<Double, Double> map = new LinkedHashMap<>();
        map.put(105.52751183509827, 21.017083778344993);
        map.put(105.52789807319641, 21.015711705459296);
        map.put(105.52908897399901, 21.013969054545914);
        map.put(105.5310308933258, 21.015601538475746);
        map.put(105.52902460098267, 21.0177848326108);
        map.put(105.52751183509827, 21.017083778344993);
        listAreas.add(new Area(0, "khu đất số 1"," chủ sở hữu: Tuấn",10, map));
        
        map = new LinkedHashMap<>();
        map.put(105.52904605865479, 21.017794847647856);
        map.put(105.5310308933258, 21.015621568842448);
        map.put(105.53257584571838, 21.01688347652086);
        map.put(105.53056955337524, 21.018686183252605);
        map.put(105.52904605865479, 21.017794847647856);
        listAreas.add(new Area(1, "khu đất số 2"," chủ sở hữu: Tuấn",50, map));
        
        map = new LinkedHashMap<>();
        map.put(105.53058028221129, 21.018686183252605);
        map.put(105.53258657455444, 21.016893491618458);
        map.put(105.53377747535706, 21.017864952888473);
        map.put(105.53356289863586, 21.018926542503593);
        map.put(105.53233981132507, 21.0197577819264);
        map.put(105.53058028221129, 21.018686183252605);
        listAreas.add(new Area(2, "khu đất số 3"," chủ sở hữu: Tuấn", 90, map));
        return listAreas;
    }
    
    public String getDataMap() {
        return dataMap;
    }

    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }
    
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
