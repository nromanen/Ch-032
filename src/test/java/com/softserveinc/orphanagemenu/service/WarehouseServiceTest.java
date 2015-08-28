package com.softserveinc.orphanagemenu.service;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softserveinc.orphanagemenu.dao.WarehouseItemDaoImpl;
import com.softserveinc.orphanagemenu.service.WarehouseServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class WarehouseServiceTest {

	@InjectMocks
	WarehouseServiceImpl warehouseService;

	@Mock
	private WarehouseItemDaoImpl mockDAO;

	public void setUPTest() {

		MockitoAnnotations.initMocks(this);
		doReturn(15L).when(mockDAO).getCount();
	}

	@Test
	public void testCount() {
		setUPTest();

		Long expected = 15L;

		Long actual = warehouseService.getCount();

		assertEquals(expected, actual);
	}

}
