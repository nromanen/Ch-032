package com.softserveinc.orphanagemenu;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


import com.softserveinc.orphanagemenu.service.WarehouseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-context.xml" })
public class ServiceTest {
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	ApplicationContext context;
	
	@Test
	public void initDbCount() {
			Long testVar = warehouseService.getCount();
			
	assertEquals(new Long(0),  testVar);
	
	}

}
