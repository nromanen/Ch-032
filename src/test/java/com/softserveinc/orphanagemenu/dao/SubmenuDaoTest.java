package com.softserveinc.orphanagemenu.dao;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.Submenu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
@Transactional
public class SubmenuDaoTest {

	@Autowired
	private SubmenuDao submenuDao;

	@Autowired
	private AgeCategoryDao ageCategoryDao;

	@Autowired
	private DailyMenuDao dailyMenuDao;

	@Autowired
	private ConsumptionTypeDao consumptionTypeDao;

	Random rnd = new Random();

	@Before
	public void setUpTest() {
	}

	@Test
	public void submenuSaveTest() {
		int submenuCount = submenuDao.getAll().size();
		Submenu submenu = new Submenu();
		submenu.setChildQuantity((Integer) rnd.nextInt(999));
		submenu.setDailyMenu(dailyMenuDao.getAll().get(0));
		submenu.setAgeCategory(ageCategoryDao.getAllAgeCategory().get(0));
		submenu.setConsumptionType(consumptionTypeDao.getAll().get(0));
		assertNull(submenu.getId());
		submenuDao.save(submenu);
		Long submenuId = submenu.getId();
		assertNotNull(submenuId);
		assertEquals("oops!something happend!", submenuCount, submenuDao.getAll().size() - 1);
	}

	@Test
	public void submenuUpdateTest() {
		Submenu submenu = submenuDao.getAll().get(0);
		Integer childExpected = submenu.getChildQuantity();
		submenu.setChildQuantity(rnd.nextInt(999) + 1000);
		submenuDao.update(submenu);
		assertNotEquals(childExpected, submenuDao.getById(submenu.getId()).getChildQuantity());
	}

	@Test
	public void submenuDeleteTest() {
		int submenuQuantity = submenuDao.getAll().size();
		submenuDao.delete(submenuDao.getAll().get(submenuQuantity - 1));
		assertEquals(submenuQuantity - 1, submenuDao.getAll().size());
	}

	@Test
	public void submenuGetByIdTest() {
		Submenu submenuExpected = submenuDao.getAll().get(0);
		Submenu submenuActual = submenuDao.getById(submenuExpected.getId());
		assertEquals(submenuActual, submenuExpected);
	}

	@Test
	public void getSubmenuListTest() {
		Submenu submenu = new Submenu();
		submenu.setChildQuantity((Integer) rnd.nextInt(999));
		submenu.setDailyMenu(dailyMenuDao.getAll().get(0));
		submenu.setAgeCategory(ageCategoryDao.getAllAgeCategory().get(0));
		submenu.setConsumptionType(consumptionTypeDao.getAll().get(0));
		assertNull(submenu.getId());
		submenuDao.save(submenu);
		
		Submenu randomSubmenu = submenuDao.getAll().get(0);
		Long dailyMenuId = randomSubmenu.getId();
		Long consumptionTypeId = randomSubmenu.getConsumptionType().getId();
		List<Submenu> submenuList = submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(dailyMenuId, consumptionTypeId);
		for (Submenu submenuX : submenuList) {
			assertEquals(dailyMenuId, submenuX.getDailyMenu().getId());
			assertEquals(consumptionTypeId, submenuX.getConsumptionType().getId());
		}
	}

	@Test
	public void getSubmenuByParamsTest() {
		Submenu randomSubmenu = submenuDao.getAll().get(0);
		Submenu actualSubmenu = submenuDao.getSubmenuByDailyMenuAndConsumptionTypeAndAgeCategory(
				randomSubmenu.getDailyMenu().getId(),
				randomSubmenu.getConsumptionType().getId(), 
				randomSubmenu.getAgeCategory());
		assertEquals(randomSubmenu, actualSubmenu);
	}
	
	@Test
	public void gelAtllByDailyMenuTest(){
		Long dailyMenuId = submenuDao.getAll().get(0).getId();
		for(Submenu submenu : submenuDao.getAllByDailyMenuId(dailyMenuId)){
			assertEquals(dailyMenuId, submenu.getDailyMenu().getId());
		}
	}

}
