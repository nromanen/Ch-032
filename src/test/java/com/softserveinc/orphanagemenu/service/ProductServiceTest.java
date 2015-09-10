package com.softserveinc.orphanagemenu.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.softserveinc.orphanagemenu.dao.ProductDaoImpl;
import com.softserveinc.orphanagemenu.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
@WebAppConfiguration
public class ProductServiceTest {
	
	Product product = new Product();
	List<Product> products = new ArrayList<>();

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	private ProductDaoImpl mockProductDaoImpl;

	@Before
	public void setUPTest() {
		MockitoAnnotations.initMocks(this);
		doReturn(6L).when(mockProductDaoImpl).getCount();
		doReturn(products).when(mockProductDaoImpl).getAllProduct();
		doReturn(product).when(mockProductDaoImpl).getProductById(1L);
		doReturn(product).when(mockProductDaoImpl).getProduct("Сік");
	}
	
	@Test
	public void getAllProductDtoSortedTest() {
		List<Product> expected = products;
		List<Product> actual = productServiceImpl.getAllProductDtoSorted();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getProductByIdTest() {
		Product expected = product;
		Product actual = productServiceImpl.getProductById(1L);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getProductByNameTest() {
		Product expected = product;
		Product actual = productServiceImpl.getProductByName("Сік");
		assertEquals(expected, actual);
	}

	@Test
	public void getAllProductsCountTest() {
		Long expected = 6L;
		Long actual = productServiceImpl.getCount();
		assertEquals(expected, actual);
	}
	
	
}
