/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.AreaDAO;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
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
    
    @PostConstruct
    public void init() {
        wantMinMax = true;
    }

    public void submitData(){
        int i = 1;
        //data collect here
        AreaDAO dao = new AreaDAO();
        System.out.println("adding to database");
        try{
            dao.addNewAreaWithForm(areaName, maxValueInput, minValueInput, currentPriceInput, areaDescription);
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
    
}
