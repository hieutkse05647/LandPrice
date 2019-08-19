/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import common.ColorDrawer;
import entity.Area;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Phong
 */
public class GeoJSONAdapter {

    public static JSONObject parseStringToJSON(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject createGeoJSON(ArrayList<Area> areas) {
        JSONObject json = new JSONObject();
        json.put("type", "FeatureCollection");
        JSONArray jsonArray = new JSONArray();

        for (Area area : areas) {
            JSONObject elementInArray = new JSONObject();
            LinkedHashMap<Double, Double> map = area.getMap();
            String color = ColorDrawer.IdentifyColor(area.getCurrentPrice());
            elementInArray = createJSONObjectArea(map, color, area);
            jsonArray.put(elementInArray);
        }

        json.put("features", (Object) jsonArray);
        return json;
    }

    private static JSONObject createJSONGeometry(Map<Double, Double> map) {
        JSONObject json = new JSONObject();
        json.put("type", "Polygon");
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

    private static JSONObject createJSONProperty(Area area, String color){
        JSONObject json = new JSONObject();
        json.put("color", color);
        json.put("name", area.getAreaName());
        json.put("id", area.getAreaID());
        return json;
    }
    
    private static JSONObject createJSONObjectArea(Map<Double, Double> map,
            String areaColor, Area area) {
        JSONObject json = new JSONObject();
        json.put("type", "Feature");
        
        JSONObject property = new JSONObject();
        property = createJSONProperty(area, areaColor);
        json.put("properties", (Object) property);
        
        JSONObject geometry = new JSONObject();
        geometry = createJSONGeometry(map);
        json.put("geometry", (Object) geometry);

        return json;
    }
}
