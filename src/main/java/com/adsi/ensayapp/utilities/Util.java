package com.adsi.ensayapp.utilities;

public class Util {
    
    public String getBackgroundColor(){
                
        double x = Math.floor(Math.random()*256);
        double y = Math.floor(Math.random()*256);
        double z = Math.floor(Math.random()*256);
        
        String response = "rgba("+x+","+y+","+z+", 0.2)";
        //log.info("Background: "+response);
        return response;
    }
    
}
