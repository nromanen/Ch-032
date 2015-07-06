package com.javagroup.restaurantmenu.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javagroup.restaurantmenu.model.Dish;

/**
 * Utility class for converting list of dishes
 * 
 * @author Oleksii Riabokon
 * @author Vova Perepelyuk
 */

public class Dishes {

	/**
     * Converts list of dishes to Json
     * @param dishes list of dishes to convert
     * @return list of dishes as Json
     */	
    public static String asJson(List<Dish> dishes) 
    		throws JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS);
        String json = objectMapper.writeValueAsString(dishes);
        return json;
    }
    
    /**
     * Converts Json to list of dishes
     * @param json to convert to the list of dishes
     * @return list of dishes
     */	
    public static List<Dish> asList(String json) 
    		throws IOException, JsonParseException, JsonMappingException {
        ArrayList<Dish> dishes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        dishes = objectMapper
        			.readValue(json, new TypeReference<ArrayList<Dish>>() { });
        return dishes;
    }
}
