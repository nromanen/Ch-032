package com.javagroup.restaurantmenu.dao.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javagroup.restaurantmenu.dao.DishDAO;
import com.javagroup.restaurantmenu.model.Dish;
import com.javagroup.restaurantmenu.util.Dishes;

public class DishDAOFile implements DishDAO {
	private final String FILENAME;
	
	public DishDAOFile(String fileName) {
		this.FILENAME = fileName;
	}

	/**
     * Returns all the possible dishes
     * @return a list of all the possible dishes
     */	
	public List<Dish> findAll() {
		byte[] fileData = null;
		try {
			fileData = Files.readAllBytes(Paths.get(FILENAME));
	    } catch (IOException ex) {
	    	System.out.println(ex);
	    }
		String json = new String(fileData);
		List<Dish> dishes = null;
		try {
			dishes = Dishes.asList(json);
		} catch (IOException ex) {
	    	System.out.println(ex);
		}
		return dishes;
	}
	
    /**
     * Persists list of all dishes
     * All previously existed dishes are deleted 
     * @param dishes list of dishes to persist
     */	
	public void persistAllDishes(List<Dish> dishes) {
		String json = "";
		try {
			json = Dishes.asJson(dishes);
		} catch (JsonProcessingException ex) {
			System.out.println(ex);
		}
		byte[] fileData = json.getBytes();
		try {
			Files.write(Paths.get(FILENAME), fileData, 
					StandardOpenOption.CREATE);
	    } catch (IOException ex) {
	    	System.out.println(ex);
	    }
	}

	@Override
	public void makePersistent(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeTransient(Dish dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dish findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
