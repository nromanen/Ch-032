package com.menu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
/**
 * JsonWritingAndReadingFile created to parse
 * all java object from project
 * 
 * @author Dima Khodan
 * @author Vlad Kokh
 * @version     1.5
 * @since       1.0
 */

public class JsonWritingAndReadingFile {
    
    /**
     * Method parsing java object to JSON format
     * @param list
     * @param filePath 
     */
    public void parsingJson(ArrayList list, String filePath){
        
        Gson gson = new Gson();
        
        //Convert java object to JSON format,
        //and return JSON format
        String json = gson.toJson(list);
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(json);
    }
    
    /**
     * Method read JSON file and convert him to java object
     * @param reference 
     */
    public void jsonReadingFromFile(String reference){
        
            Gson gson = new Gson();
       
            System.out.println("Reading JSON from file");
            System.out.println("____");
            
            Type type = new TypeToken<ArrayList<Object>>(){}.getType();
            try{
            BufferedReader br = new BufferedReader(new FileReader(reference));
            ArrayList<Object> inpList = gson.fromJson(String.valueOf(br), type);
            for(int i = 0; i < inpList.size();i++){
               Object x = inpList.get(i);
               System.out.println(x);
                 }
            }
            catch(IOException e){
                e.printStackTrace();
            }
    }
}
