package com.softserveinc.orphanagemenu.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:unit-test-context/test-context.xml", 
									"classpath:unit-test-context/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserAccountControllerIntegrationTest {
	
	private MockMvc mockMvc;

	@Autowired
	private UserAccountService userAccountService;
	 
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() throws Exception {
		
		Mockito.reset(userAccountService);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void showAllUserAccountsTest() throws Exception {
		
		when(userAccountService.getAllDto()).thenReturn(Arrays.asList(
				new UserAccount(), new UserAccount()));

		mockMvc.perform(get("/userAccountList"))
			.andExpect(status().isOk())
			.andExpect(view().name("userAccountList"))
			.andExpect(model().attributeExists("userAccounts"))
			.andExpect(model().attribute("userAccounts", hasSize(2)));
	}
}
