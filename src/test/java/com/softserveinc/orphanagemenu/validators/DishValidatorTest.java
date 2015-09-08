package com.softserveinc.orphanagemenu.validators;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.softserveinc.orphanagemenu.dao.DishDao;
import com.softserveinc.orphanagemenu.forms.DishForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

public class DishValidatorTest {

	private DishDao dishDao;
	private Errors errors;
	
	private Dish dish;
	private DishForm dishForm;
	private DishValidator dishValidator;
	
	@Before
	public void setUp() throws Exception {
		
		//create Dish
		dish = new Dish();
		dish.setId(1L);
		Map<Long, String> testMap = new HashMap<Long, String>();
		Map<Long, Double> quantityTestMap = new HashMap<Long, Double>();
		testMap.put(1L, "first");
		quantityTestMap.put(1L, 12.2);
		
		//create DishForm
		dishForm = new DishForm();
		dishForm.setAgeCategoryId(new AgeCategory());
		dishForm.setCategory(testMap);
		dishForm.setComp_id("1");
		dishForm.setComponent(new Component());
		dishForm.setDishName("Каша Вівсяна");
		dishForm.setId(1L);
		dishForm.setIsAvailable(true);
		dishForm.setNotValid(true);
		dishForm.setProduct(new Product());
		dishForm.setStandartComponentQuantity("10.2");
		dishForm.setWeight(quantityTestMap);
		
		dishDao = mock(DishDao.class);
		when(dishDao.getDish("Каша Вівсяна")).thenReturn(dish);
		dishValidator = new DishValidator();
		ReflectionTestUtils.setField(dishValidator, "dishDao", dishDao);
	}
	
	@Test
	public void dishNameTooShortTest(){
		errors = new BeanPropertyBindingResult(dishForm, "noMatterParameter");
		dishValidator.validate(dishForm, errors);
		dishForm.setDishName("");
		if((dishForm.getDishName().length())==0){
			Assert.assertEquals(1, errors.getErrorCount());
		}
		if((dishForm.getDishName().length())==0){
		}
	}
	
	@Test
	public void dishAlreayExistTest() {
		errors = new BeanPropertyBindingResult(dishForm, "noMatterParameter");
		dishValidator.validate(dishForm, errors);
		Dish dish = new Dish("Каша Вівсяна", true);
		
		if(dishForm.getDishName().equals(dish.getName())){
			Assert.assertEquals(1, errors.getErrorCount());
		}
	}
}
