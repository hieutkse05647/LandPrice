/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Phong
 */
public class ColorDrawer {
    
    public static String IdentifyColor(double currentPrice) {
        if (currentPrice < 50){
            return "blue";
        }
        if (currentPrice < 70){
            return "orange";
        }
        return "red";
    }
}
