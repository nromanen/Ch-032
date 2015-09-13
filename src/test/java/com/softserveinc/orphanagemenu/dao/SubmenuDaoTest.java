package com.softserveinc.orphanagemenu.dao;

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

import static org.junit.Assert.*;

import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.Submenu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class SubmenuDaoTest {
	
	@Autowired
	private SubmenuDao submenuDao;
	
	@Autowired
	private AgeCategoryDao ageCategoryDao;
	
	
	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void godModTest() {
		int sizeActual = submenuDao.getAll().size();
		int childsEplected = 15;
		int submenuCount = submenuDao.getAll().size();
		AgeCategory ageCatExp = ageCategoryDao.getAllAgeCategory().get(0);
		Submenu submenuExpected = new Submenu();
		submenuExpected.setAgeCategory(ageCatExp);
		submenuExpected.setChildQuantity(childsEplected);
		em.persist(submenuExpected);
		assertEquals(submenuCount, submenuDao.getAll().size()-1);
		Submenu submenuActual = submenuDao.getById(submenuExpected.getId());
		assertSame(submenuExpected, submenuActual);
		submenuExpected.setChildQuantity(childsEplected+1);
		em.merge(submenuExpected);
		assertEquals( childsEplected+1 , (int) submenuDao.getById(submenuExpected.getId()).getChildQuantity());
		em.remove(submenuExpected);
		assertEquals(sizeActual ,submenuDao.getAll().size());
		assertNull(submenuDao.getById(submenuExpected.getId()));
	}
}
