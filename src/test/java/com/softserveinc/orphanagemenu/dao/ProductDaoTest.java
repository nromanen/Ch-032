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
	product.setProductWeight(productWeights);
	}

	@Test
	public void initDbCountTest() {
		Long actual = productDao.getCount();
		Long expected = 7L;
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
		product.setName(oldName+"Update");
		productDao.updateProduct(product);
		product = productDao.getProductById(1L);
		String newName = product.getName();
		assertNotEquals(newName, oldName);
	}
	
	@Test
	@Transactional
	public void getAllProductsTest() {
		List<Product> products = productDao.getAllProduct();
		String actual = products.get(0).getName()+products.get(1).getName();
		String expected = "БурякВівсяні пластівці";
		Long countExpected = productDao.getCount();
		Long countActual = (long) products.size();
		assertEquals(expected, actual);
		assertEquals(countExpected, countActual);
		products = productDao.getAllProduct("desc");
		actual = products.get(0).getName()+products.get(1).getName();
		expected = "Чай чорнийХліб";
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	public void getProductWithExceptionTest() {
		Product productActual = productDao.getProduct("Морозиво");
		assertEquals(null, productActual);
	}
	
	@Test
	@Transactional
	public void getProducsPageTest() {
		List<Product> products = productDao.getPage(2, 2);
		String actual = products.get(0).getName()+products.get(1).getName();
		String expected = "КартопляКурка";
		assertEquals(expected, actual);
	}
	
	@Test
	@Transactional
	public void getProducsSearchPageTest() {
		List<Product> products = productDao.getPage("к", 0, 2);
		String actual = products.get(0).getName()+products.get(1).getName();
		String expected = "БурякКартопля";
		Long countExpected = 2L;
		Long countActual = (long) products.size();
		assertEquals(expected, actual);
		assertEquals(countExpected, countActual);
		products = productDao.getPage("к", 2, 2);
		actual = products.get(0).getName()+products.get(1).getName();
		expected = "КуркаМолоко";
		countExpected = 2L;
		countActual = (long) products.size();
		assertEquals(expected, actual);
		assertEquals(countExpected, countActual);
	}
	
	@Test
	@Transactional
	public void getCountProductsInSearchTest() {
		Long countActual = productDao.getCount("к");
		Long countExpected = 4L;
		assertEquals(countExpected, countActual);
	}
}
