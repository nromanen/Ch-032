package com.softserveinc.orphanagemenu.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.WarehouseItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class WarehouseItemDAOTest {

	@Autowired
	private WarehouseItemDao warehouseItemDAO;
	@Autowired
	private DimensionDao dimensionDAO;
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void saveitemTest(){
		WarehouseItem warehouseItem = new WarehouseItem();
		Dimension dimension = dimensionDAO.getAllDimension().get(0);
		Product product = new Product();
		product.setName("жир мутанта");
		product.setDimension(dimension);
		warehouseItem.setProduct(product);
		assertNull(warehouseItem.getId());
		productDao.saveProduct(product);
		warehouseItem.setQuantity(0d);
		warehouseItemDAO.saveItem(warehouseItem);
		assertNotNull(warehouseItem.getId());
	}

	@Test
	public void getItemTest() {
		WarehouseItem warehouseItem = warehouseItemDAO.getAll().get(0);
		assertEquals(warehouseItem, warehouseItemDAO.getItem(warehouseItem.getId()));
	}

	@Test
	public void getItemByProductTest() {
		WarehouseItem warehouseItem = warehouseItemDAO.getAll().get(0);
		assertEquals(warehouseItem, warehouseItemDAO.getItemByProduct(warehouseItem.getProduct()));
	}
	
	@Test
	public void getAllTest(){
		Long expected = warehouseItemDAO.getCount();
		Long actual = (long) warehouseItemDAO.getAll().size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getCountByName(){
		Long expectedCount = warehouseItemDAO.getCount("молоко");
		WarehouseItem warehouseItem = new WarehouseItem();
		warehouseItem.setQuantity(50d);
		Dimension dimension = dimensionDAO.getAllDimension().get(0);
		Product product = new Product();
		product.setName("молоко свіже");
		product.setDimension(dimension);
		warehouseItem.setProduct(product);
		productDao.saveProduct(product);
		warehouseItemDAO.saveItem(warehouseItem);
		Long actualCount = warehouseItemDAO.getCount("молоко") - 1l;
		assertEquals(expectedCount, actualCount);
		
	}
	@Test
	public void getPageTest(){
		Long actual =(long) warehouseItemDAO.getPage(0, 1000).size();
		Long expected = 2l;
		assertEquals(expected,actual);
	}
	
	@Test
	public void getPageByNameTest(){
		Long actual =(long) warehouseItemDAO.getPage("молоко", 0, 1000).size();
		Long expected = 2l;
		assertEquals(expected,actual);
	}
	
	@Test
	public void updateItemTest(){
		WarehouseItem warehouseItem = warehouseItemDAO.getAll().get(0);
		Double expected = warehouseItem.getQuantity();
		warehouseItem.setQuantity(expected + 1);
		warehouseItemDAO.updateItem(warehouseItem);
		Double actual = warehouseItemDAO.getItem(warehouseItem.getId()).getQuantity();
		assertNotEquals(expected, actual);
	}
	
	@Test
	public void getNewProductsTest(){
		List<Product> productList = warehouseItemDAO.getNewProducts();
		for (Product product : productList){
			if (warehouseItemDAO.getItemByProduct(product).getQuantity() != 0){
				Assert.fail();
			}
		}
	}
}