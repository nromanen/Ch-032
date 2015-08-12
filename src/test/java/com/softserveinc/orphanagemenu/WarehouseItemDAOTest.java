package com.softserveinc.orphanagemenu;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import com.softserveinc.orphanagemenu.dao.WarehouseItemDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-context.xml" })

public class WarehouseItemDAOTest {
	@Autowired
	private WarehouseItemDao warehouseDAO;
	
	
	@Test
	public void initDbCount() {
			Long actual = warehouseDAO.getCount();
			Long expected = 0L;
	assertEquals(expected, actual);
	
	}

}
