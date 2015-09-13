package com.softserveinc.orphanagemenu.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Dish;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class DishDaoTest {

	Dish dish = new Dish();
	
	@Autowired
	private DishDao dishDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp (){
		dish.setName("Каша Вівсяна");
		dish.setIsAvailable(true);
		Set<Component> componentSet = new HashSet<Component>();
		componentSet.add(new Component());
		dish.setComponents(componentSet);
	}
	
	@Test
	public void initDbCountTest() throws Exception {
		Long actual = dishDao.getCount();
		Long expected = 4L;
		assertEquals(expected, actual);
	}
	
//	@Test
//	@Transactional
//	public void addDishTest() throws Exception {
//		Long actual = dishDao.getCount();
//		Long expected = actual+1;
//		dishDao.addDish(dish);
//		actual = dishDao.getCount();
//		assertEquals(expected,actual);
//		em.remove(dishDao.getDish(dish.getName()));
//	}
	
	@Test
	@Transactional
	public void getAllDishTest() throws Exception {
		List<Dish> dishList = dishDao.getAllDish();
		Integer actual = dishList.size();
		Integer expected = 4;
		assertEquals(actual, expected);
	}
	
	@Test
	@Transactional
	public void getDishByIdTest() throws Exception {
		dish.setId(1L);
		Dish expectedDish = dishDao.getDishById(1L);
		assertEquals(dish, expectedDish);
	}
	
	@Test
	@Transactional
	public void getDishByIdExpectedExceptionTest() throws Exception {
		Dish dishActual = dishDao.getDishById(6L);
		assertEquals(null, dishActual);
	}
	
	@Test
	@Transactional
	public void getDishByNameExpectedExceptionTest() throws Exception {
		Dish dishActual = dishDao.getDish("NULL");
		assertEquals(null, dishActual);
	}
	
	@Test
	@Transactional
	public void updateDishTest() throws Exception {
		dish = dishDao.getDishById(1L);
		String oldName = dish.getName();
		dish.setName(oldName+"Update");
		dishDao.updateDish(dish);
		dish = dishDao.getDishById(1L);
		String newName = dish.getName();
		assertNotEquals(oldName, newName);
	}
	
	@Test
	@Transactional
	public void isAvailableTest() throws Exception {
		dish = dishDao.getDishById(1L);
		boolean actualAvailable = dish.getIsAvailable();
		boolean expectedAvailable  = dishDao.getAvailable(1L);
		assertEquals(actualAvailable, expectedAvailable);
	}
	
}
