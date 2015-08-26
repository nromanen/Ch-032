package com.softserveinc.orphanagemenu.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })

public class WarehouseItemDAOTest {
	@Autowired
	private WarehouseItemDao warehouseItemDAO;
		
	@Test
	public void initDbCount() {
			Long actual = warehouseItemDAO.getCount();
			Long expected = 0L;
	assertEquals(expected, actual);
	
	}

}
