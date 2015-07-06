package crud;

import engine.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReaderWriter {
    private ObjectMapper mapper = new ObjectMapper();

    public void WriteToFile(String path, Object object) {
        try {

            // convert user object to json string, and save to a file
            mapper.writeValue(new File(path), object);

            // display to console
            System.out.println(mapper.writeValueAsString(object));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public Products ReadProductsFromFile(String path) {
        Products products = null;
        try {
            // convert user object from json string
            products = mapper.readValue(new File(path), Products.class);

            // display to console
            System.out.println(mapper.writeValueAsString(products));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return products;
    }

    public Categories ReadCategiruesFromFile(String path) {
        Categories categories = null;
        try {
            // convert user object from json string
            categories = mapper.readValue(new File(path), Categories.class);

            // display to console
            System.out.println(mapper.writeValueAsString(categories));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return categories;
    }

    public Meals ReadMealsFromFile(String path) {
        Meals meals = null;
        try {
            // convert user object from json string
            meals = mapper.readValue(new File(path), Meals.class);

            // display to console
            System.out.println(mapper.writeValueAsString(meals));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return meals;
    }

    public ComplexMeals ReadComplexFromFile(String path) {
        ComplexMeals complexMeals = null;
        try {
            // convert user object from json string
            complexMeals = mapper.readValue(new File(path), ComplexMeals.class);

            // display to console
            System.out.println(mapper.writeValueAsString(complexMeals));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return complexMeals;
    }

    public Menus ReadMenusFromFile(String path) {
        Menus menus = null;
        try {
            // convert user object from json string
            menus = mapper.readValue(new File(path), Menus.class);

            // display to console
            System.out.println(mapper.writeValueAsString(menus));

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return menus;
    }

    public void WriteAllToFile(Products allProducts, Categories allCategories,
                               Meals allMeals, ComplexMeals complexMeals, Menus allMenus) {
        System.out.println("Writing to file");
        this.WriteToFile("d:\\prod.json", allProducts);
        this.WriteToFile("d:\\cat.json", allCategories);
        this.WriteToFile("d:\\meals.json", allMeals);
        this.WriteToFile("d:\\complex.json", complexMeals);
        this.WriteToFile("d:\\menus.json", allMenus);
    }

    public void ReadAllFromFile(Products allProducts, Categories allCategories,
                                Meals allMeals, ComplexMeals complexMeals, Menus allMenus) {
        System.out.println("\n" + "Writing to objects");
        allProducts = this.ReadProductsFromFile("d:\\prod.json");
        allCategories = this.ReadCategiruesFromFile("d:\\cat.json");
        allMeals = this.ReadMealsFromFile("d:\\meals.json");
        complexMeals = this.ReadComplexFromFile("d:\\complex.json");
        allMenus = this.ReadMenusFromFile("d:\\menus.json");
    }
}

