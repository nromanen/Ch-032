package com.softserveinc.orphanagemenu.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softserveinc.orphanagemenu.model.AgeCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class AgeCategoryDaoTest {

	AgeCategory ageCategory = new AgeCategory();
	
	@Autowired
	private AgeCategoryDao ageCategoryDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Before
	public void setUp (){
		ageCategory.setName("3-5р.");
		ageCategory.setIsActive(true);
	}
	
	@Test
	public void initDbCountTest() throws Exception {
		Long actual = ageCategoryDao.getCount();
		Long expected = 4L;
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	public void getAllAgeCategoryTest() throws Exception {
		Long actual = (long) ageCategoryDao.getAllAgeCategory().size();
		Long expected = 4L;
		assertEquals(actual, expected);
	}
}
