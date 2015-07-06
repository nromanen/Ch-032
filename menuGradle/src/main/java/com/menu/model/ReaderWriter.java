//package com.menu.model;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.type.TypeReference;
//
///**
// * Created by skaraltc on 6/19/2015.
// */
//public class ReaderWriter {
//
//	private ObjectMapper mapper = new ObjectMapper();
//	
//	
//	// HOW TO WRITE A LISTS:
//	
////	public static void main(String[] args) {
////		new PortionsOfMeals().initializeMealList();
////		ObjectMapper mapper = new ObjectMapper();
////		try {
////			mapper.writeValue(new File("E:/Git/Menu/Menu/Files/ingredients.txt"), ingredients);
////			
////			//List<PortionsOfMeals> myObjects 
////			ingredients= mapper.readValue(new File("E:/Git/Menu/Menu/Files/ingredients2.txt"), new TypeReference<List<Ingredient>>(){});
////			
////			//ingredients = mapper.readValue(new File("E:/Git/Menu/Menu/Files/ingredients2.txt"), );
////			
////			
////		} catch (JsonGenerationException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (JsonMappingException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println(ingredients);
////	}
//	
//	
//	
//	public void writeToFile(ComplexMealMenu object) {
//		try {
//			
//			
//			// convert user object to json string, and save to a file
//			mapper.writeValue(new File("E:/Git/Menu/Menu/Files/PriceSortMeals.txt"), object);
//			
//			
//
//		} catch (JsonGenerationException e) {
//
//			e.printStackTrace();
//
//		} catch (JsonMappingException e) {
//
//			e.printStackTrace();
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public ComplexMealMenu readFromFile(Class objectClass) {
//
//		ComplexMealMenu sortFromFile = new ComplexMealMenu();
//		
//		ObjectMapper mapper = new ObjectMapper();
//
//		try {
//
//			// read from file, convert it to user class
//			sortFromFile = mapper.readValue(new File("E:/Git/Menu/Menu/Files/PriceSortMeals.txt"), objectClass);
//
//		} catch (JsonGenerationException e) {
//
//			e.printStackTrace();
//
//		} catch (JsonMappingException e) {
//
//			e.printStackTrace();
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//		
//		return sortFromFile;
//
//	}
//}
