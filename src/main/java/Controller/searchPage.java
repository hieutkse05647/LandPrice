package Controller;

import adapter.GeoJSONAdapter;
import entity.Area;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@ManagedBean
@SessionScoped
public class searchPage {

    private String address;
    private int min, max;
    private String dataMap;
    

    public String getDataMap() {
        return dataMap;
    }

    public void setDataMap(String dataMap) {
        this.dataMap = dataMap;
    }

    @PostConstruct
    public void init() {
        address = "tuen";
        min = 1;
        max = 10;
        JSONObject json = createJSON();
        int i = 1;
        i++;
        dataMap = json.toString();
        i++;
        if (i == 2) {
            i = 1;
        }
//        PrimeFaces.current().executeScript("alert('peek-a-boo');");
    }

    private JSONObject createJSONGeometry(Map<Double, Double> map) {
        JSONObject json = new JSONObject();
        json.put("type", "Polygon");
//    	HashMap<Double, Double> map = new HashMap<>();
        JSONArray jsonAxis = new JSONArray();
        Double firstValueX = null, firstValueY = null;
        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            JSONArray jsonAxisElement = new JSONArray();
            Double x = entry.getKey();
            Double y = entry.getValue();
            if (firstValueX == null) {
                firstValueX = x;
                firstValueY = y;
            }
            jsonAxisElement.put(x);
            jsonAxisElement.put(y);
            jsonAxis.put(jsonAxisElement);
        }
        JSONArray jsonEndAxisElement = new JSONArray();
        jsonEndAxisElement.put(firstValueX);
        jsonEndAxisElement.put(firstValueY);
        jsonAxis.put(jsonEndAxisElement);

        //----------
        JSONArray jsonTemp = new JSONArray();
        jsonTemp.put((Object) jsonAxis);

        json.put("coordinates", jsonTemp);
        return json;
    }

    private JSONObject createJSONObjectArea(Map<Double, Double> map) {
        JSONObject json = new JSONObject();
        json.put("type", "Feature");
        JSONObject color = new JSONObject();
        color.put("color", "red");
        json.put("properties", (Object) color);

        JSONObject geometry = new JSONObject();
        geometry = createJSONGeometry(map);
        json.put("geometry", (Object) geometry);

        return json;
    }

    private JSONObject createJSON() {
        ArrayList<Area> areas = new ArrayList();

        LinkedHashMap<Double, Double> map = new LinkedHashMap<>();
        map.put(105.52751183509827, 21.017083778344993);
        map.put(105.52789807319641, 21.015711705459296);
        map.put(105.52908897399901, 21.013969054545914);
        map.put(105.5310308933258, 21.015601538475746);
        map.put(105.52902460098267, 21.0177848326108);
        map.put(105.52751183509827, 21.017083778344993);
        areas.add(new Area(0, map));
        
        map = new LinkedHashMap<>();
        map.put(105.52904605865479, 21.017794847647856);
        map.put(105.5310308933258, 21.015621568842448);
        map.put(105.53257584571838, 21.01688347652086);
        map.put(105.53056955337524, 21.018686183252605);
        map.put(105.52904605865479, 21.017794847647856);
        areas.add(new Area(0, map));
        
        map = new LinkedHashMap<>();
        map.put(105.53058028221129, 21.018686183252605);
        map.put(105.53258657455444, 21.016893491618458);
        map.put(105.53377747535706, 21.017864952888473);
        map.put(105.53356289863586, 21.018926542503593);
        map.put(105.53233981132507, 21.0197577819264);
        map.put(105.53058028221129, 21.018686183252605);
        areas.add(new Area(0, map));
        
        return GeoJSONAdapter.createGeoJSON(areas);
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

    private String name = "name";
    private String description = "this is description";
    private Date date = new Date();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
