package com.softserveinc.orphanagemenu.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;
import com.softserveinc.orphanagemenu.dao.WarehouseItemDaoImpl;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.WarehouseItem;
import com.softserveinc.orphanagemenu.service.WarehouseServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class WarehouseServiceTest {

	Dimension dimension = new Dimension();
	Set<ProductWeight> productWeight = new HashSet<ProductWeight>();
	Product product1 = new Product();
	Product product2 = new Product();
	WarehouseItem warehouseItem1 = new WarehouseItem();
	WarehouseItem warehouseItem2 = new WarehouseItem();
	List<WarehouseItem> warehouseItemsList = new ArrayList<WarehouseItem>();

	@InjectMocks
	WarehouseServiceImpl warehouseService;

	@Mock
	private WarehouseItemDao warehouseItemDAO;

	@Mock
	private ProductDao productDAO;

	@Before
	public void setUPTest() {
		dimension.setId(15l);
		dimension.setName("kg");
		product1.setDimension(dimension);
		product2.setDimension(dimension);
		product1.setId(1l);
		product2.setId(2l);
		product1.setName("Potato");
		product2.setName("Meat");
		product1.setProductWeight(productWeight);
		product2.setProductWeight(productWeight);
		warehouseItem1.setProduct(product1);
		warehouseItem1.setQuantity(55.21);
		warehouseItem1.setId(666l);
		warehouseItemsList.add(warehouseItem1);
		warehouseItemsList.add(warehouseItem2);

		MockitoAnnotations.initMocks(this);

		doReturn(product1).when(productDAO).getProduct("Potato");
		doReturn(product2).when(productDAO).getProduct("Meat");
		doReturn(warehouseItem1).when(warehouseItemDAO).getItemByProduct(product1);
		doReturn(null).when(warehouseItemDAO).getItemByProduct(product2);
		doReturn(warehouseItem1).when(warehouseItemDAO).getItem(any(Long.class));
		doReturn(warehouseItemsList).when(warehouseItemDAO).getAll();
		doReturn(warehouseItemsList).when(warehouseItemDAO).getNewProducts();
		doReturn(warehouseItemsList).when(warehouseItemDAO).getPage(any(Integer.class),any(Integer.class));
		doReturn(warehouseItemsList).when(warehouseItemDAO)
			.getPage(any(String.class), any(Integer.class), any(Integer.class));
	}

	@Test
	public void saveItemNotNullTest() {
		warehouseService.saveItem(product1.getName(), 14d);
		Mockito.verify(warehouseItemDAO, atLeastOnce()).updateItem(warehouseItem1);
	}

	@Test
	public void saveItemNullTest() {
		warehouseService.saveItem(product2.getName(), 10d);
		Mockito.verify(warehouseItemDAO, atLeastOnce()).saveItem(any(WarehouseItem.class));
	}

	@Test
	public void getItemByNameTest() {
		assertEquals(warehouseItem1, warehouseService.getItem("Potato"));
		Mockito.verify(productDAO, atLeastOnce()).getProduct(any(String.class));
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getItemByProduct(any(Product.class));
	}

	@Test
	public void getItemTest() {
		assertEquals(warehouseItem1, warehouseService.getItem(666l));
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getItem(warehouseItem1.getId());
	}

	@Test
	public void getCountTest() {
		warehouseService.getCount();
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getCount();
	}

	@Test
	public void getCountByNameTest() {
		warehouseService.getCount("lol");
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getCount(any(String.class));
	}
	
	@Test
	public void getAllTest(){
		assertEquals(warehouseService.getAll().size(),2);
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getAll();
	}
	
	@Test
	public void getNewProductsTest(){
		assertEquals(warehouseService.getNewProducts().size(),2);
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getNewProducts();
	}
	
	@Test
	public void getPageTest(){
		assertEquals(warehouseService.getPage(1,1).size(),2);
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getPage(any(Integer.class),any(Integer.class));
	}
	
	@Test
	public void getPageWithNameTest(){
		assertEquals(warehouseService.getPage("u are not prepared!!", 1,1).size(),2);
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getPage(any(String.class),any(Integer.class),any(Integer.class));
	}
	
	@Test
	public void getFormTest(){
		assertEquals("Potato",warehouseService.getForm(666l).getItemName());
		Mockito.verify(warehouseItemDAO, atLeastOnce()).getItem(any(Long.class));
	}
	
}
