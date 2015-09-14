package com.softserveinc.orphanagemenu.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
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
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class ComponentDaoTest {
	
	Component component = new Component();
	
	@Autowired
	private ComponentDao componentDao;
	
	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private ProductDao productDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp (){
		Dish dish = dishDao.getDishById(2L);
		Product product = productDao.getProductById(1L);
		component.setDish(dish);
		component.setProduct(product);
		Set<ComponentWeight> componentWeightMapTest = new HashSet<ComponentWeight>();
		componentWeightMapTest.add(new ComponentWeight());
		component.setComponents(componentWeightMapTest);
	}

	@Test
	public void initDbCountTest() throws Exception {
		Long actual = componentDao.getCount();
		Long expected = 7L;
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	public void getAllComponentTest() throws Exception {
		Long actual  = (long) componentDao.getAllComponents().size();
		Long expected = 7L;
		assertEquals(actual, expected);
	}
	
	@Test
	@Transactional
	public void getComponentByIdTest() throws Exception {
		component.setId(1L);
		Component actualComponent = componentDao.getComponentById(1L);
		assertEquals(component, actualComponent);
	}
	
	@Test
	@Transactional
	public void updateComponentTest() throws Exception {
		component = componentDao.getComponentById(1L);
		component.setId(10L);
		componentDao.updateComponent(component);
	}
}
