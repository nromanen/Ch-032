package com.softserveinc.orphanagemenu.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class ProductDaoTest {
	
	Product product = new Product();

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private DimensionDao dimensionDao;
	
	@PersistenceContext
	private EntityManager em;

	@Before
	public void setUp() {
	product.setName("сік");
	Dimension dimension = dimensionDao.getDimension(1L);
	product.setDimension(dimension);
	Set<ProductWeight> productWeights = new HashSet<>();
//	ProductWeight productWeight = new ProductWeight();
//	productWeights.add(productWeight);
	product.setProductWeight(productWeights);
	}

	@Test
	public void initDbCountTest() {
		Long actual = productDao.getCount();
		Long expected = 6L;
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	public void saveProductTest() {
		Long actual = productDao.getCount();
		Long expected = actual+1;
		productDao.saveProduct(product);
		actual = productDao.getCount();
		assertEquals(expected, actual);
		em.remove(productDao.getProduct(product.getName()));
	}
	
	@Test
	@Transactional
	public void updateProductTest() {
		product = productDao.getProductById(1L);
		String oldName = product.getName();
		product.setName("");
//		productDao
//		assertNotEquals(expected, oldName);
		em.remove(productDao.getProduct(product.getName()));
	}
	
	@After
	public void delete() {
//		em.remove(productDao.getProduct(product.getName()));
	}
}