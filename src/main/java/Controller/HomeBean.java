/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AreaDAO;
import DAO.LocationDAO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author tuans
 */
@ManagedBean
@SessionScoped
public class HomeBean {
    private boolean wantMinMax;
    private int maxValueInput;
    private int minValueInput;
    private int currentPriceInput;
    private String textAreaCoordinates;
    private String northWestValue, northEastValue, southEastValue, southWestValue;
    private String areaName;
    private String areaDescription;
    private String textAreaJSON;

    @PostConstruct
    public void init() {
        wantMinMax = true;
    }

    public void submitData(){
        int i = 1;
        //data collect here
        AreaDAO dao = new AreaDAO();
        LocationDAO LocateDao = new LocationDAO();
        System.out.println("adding to database");
        JSONArray jsonArray = new JSONArray(textAreaJSON);
        LinkedHashMap<Double, Double> map = new LinkedHashMap<>();
        for (Object element: jsonArray){
            JSONArray arrayElement = (JSONArray)element;
            Double coorX = arrayElement.getDouble(0);
            Double coorY = arrayElement.getDouble(1);
            map.put(coorX, coorY);
        }
        i++;
        try{
            i++;
            dao.addNewAreaWithForm(areaName, maxValueInput, minValueInput, currentPriceInput, areaDescription);
            // get ID của thằng vừa thêm
            int lastID = dao.getLastIDArea();
            for (Map.Entry<Double, Double> entry : map.entrySet()) {
                Double CorX = entry.getKey();
                Double CorY = entry.getValue();
                LocateDao.addNewLocationWithForm(lastID, CorX, CorY);
            }
            //todo: add data to location table
            // using linkedhashmap : map
            
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }   
        //TODO: submit data to database
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    
    public String getNorthWestValue() {
        return northWestValue;
    }

    public void setNorthWestValue(String northWestValue) {
        this.northWestValue = northWestValue;
    }

    public String getNorthEastValue() {
        return northEastValue;
    }

    public void setNorthEastValue(String northEastValue) {
        this.northEastValue = northEastValue;
    }

    public String getSouthEastValue() {
        return southEastValue;
    }

    public void setSouthEastValue(String southEastValue) {
        this.southEastValue = southEastValue;
    }

    public String getSouthWestValue() {
        return southWestValue;
    }

    public void setSouthWestValue(String southWestValue) {
        this.southWestValue = southWestValue;
    }
    
    public String getTextAreaCoordinates() {
        return textAreaCoordinates;
    }

    public void setTextAreaCoordinates(String textAreaCoordinates) {
        this.textAreaCoordinates = textAreaCoordinates;
    }
    
    
    public int getCurrentPriceInput() {
        return currentPriceInput;
    }

    public void setCurrentPriceInput(int currentPriceInput) {
        this.currentPriceInput = currentPriceInput;
    }
    
    
    public int getMaxValueInput() {
        return maxValueInput;
    }

    public void setMaxValueInput(int maxValueInput) {
        this.maxValueInput = maxValueInput;
    }

    public int getMinValueInput() {
        return minValueInput;
    }

    public void setMinValueInput(int minValueInput) {
        this.minValueInput = minValueInput;
    }
    
    
    
    public boolean isWantMinMax() {
        return wantMinMax;
    }

    public void setWantMinMax(boolean wantMinMax) {
        this.wantMinMax = wantMinMax;
    }
    
    public String getTextAreaJSON() {
        return textAreaJSON;
    }

    public void setTextAreaJSON(String textAreaJSON) {
        this.textAreaJSON = textAreaJSON;
    }
}
