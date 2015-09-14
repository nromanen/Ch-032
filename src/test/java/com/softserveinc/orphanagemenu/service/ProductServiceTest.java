package com.softserveinc.orphanagemenu.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.softserveinc.orphanagemenu.dao.DimensionDaoImpl;
import com.softserveinc.orphanagemenu.dao.ProductDaoImpl;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
@WebAppConfiguration
public class ProductServiceTest {
	
	Product product = new Product();
	List<Product> products = new ArrayList<>();
	Dimension dimension = new Dimension();
	ProductForm productForm = new ProductForm();
	AgeCategory ageCategory = new AgeCategory();
	ProductWeight productWeight = new ProductWeight();

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	private ProductDaoImpl mockProductDaoImpl;
	
	@Mock
	private DimensionDaoImpl mockDimensionDaoImpl;
	
	@Mock
	private ProductServiceImpl mockProductServiceImpl;

	@Before
	public void setUPTest() {
		dimension.setId(1L);
		dimension.setName("гр");
		productForm.setId("1");
		ageCategory.setId(1L);
		MockitoAnnotations.initMocks(this);
		doReturn(6L).when(mockProductDaoImpl).getCount();
		doReturn(6L).when(mockProductDaoImpl).getCount("бу");
		doReturn(products).when(mockProductDaoImpl).getAllProduct();
		doReturn(product).when(mockProductDaoImpl).getProductById(1L);
		doReturn(product).when(mockProductDaoImpl).getProduct("Сік");
		doReturn(products).when(mockProductDaoImpl).getPage("бу", 1, 15);
		doReturn(products).when(mockProductDaoImpl).getPage(1, 15);
		doReturn(dimension).when(mockDimensionDaoImpl).getDimension("1");
		doReturn(product).when(mockProductServiceImpl).getProductById(1L);
	}
	
	@Test
	public void saveProductTest() {
		productServiceImpl.saveProduct(product);
	}
	
	@Test
	public void getAllProductDtoSortedNullListTest() {
		List<Product> expected = products;
		List<Product> actual = productServiceImpl.getAllProductDtoSorted();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllProductDtoSortedTest() {
		products.add(product);
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
	
	@Test
	public void getAllProductsSearchCountTest() {
		Long expected = 6L;
		Long actual = productServiceImpl.getCount("бу");
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSearchPageCountTest() {
		Long expected = 6L;
		Long actual = productServiceImpl.getCount("бу");
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSearchPageNullListTest() {
		List<Product> expected = products;
		List<Product> actual = productServiceImpl.getPage("бу", 1, 15);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getSearchPageListTest() {
		products.add(product);
		List<Product> expected = products;
		List<Product> actual = productServiceImpl.getPage("бу", 1, 15);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPageNullListTest() {
		List<Product> expected = products;
		List<Product> actual = productServiceImpl.getPage(1, 15);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getPageListTest() {
		products.add(product);
		List<Product> expected = products;
		List<Product> actual = productServiceImpl.getPage(1, 15);
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateProductByProductFormNullWeightListTest() {
		product.setDimension(dimension);
		Product expected = product;
		Product actual = productServiceImpl.updateProductByProductForm(productForm);
		assertEquals(expected, actual);
	}
	
	@Test
	public void updateProductByProductFormWithWeightListTest() {
		product.setDimension(dimension);
		productWeight.setAgeCategory(ageCategory);
		Set<ProductWeight> productWeights = new HashSet<>();
		productWeights.add(productWeight);
		product.setProductWeight(productWeights);
		Map<Long, String> weight = new HashMap<>();
		weight.put(1L, "150.2");
		productForm.setWeightList(weight);
		Product expected = product;
		Product actual = productServiceImpl.updateProductByProductForm(productForm);
		assertEquals(expected, actual);
		Map<Long, String> weight1 = new HashMap<>();
		weight1.put(2L, "150.2");
		productForm.setWeightList(weight1);
		Product expected1 = product;
		Product actual1 = productServiceImpl.updateProductByProductForm(productForm);
		assertEquals(expected1, actual1);
	}
}
