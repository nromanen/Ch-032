package com.softserveinc.orphanagemenu.controller;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;

public class UserAccountControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShowAllUserAccounts() {
		List<UserAccount> userAccounts = asList(new UserAccount(),
				new UserAccount());
		UserAccountService userAccountService = mock(UserAccountService.class);
		when(userAccountService.getAllDto()).thenReturn(userAccounts);
		UserAccountController accountController = new UserAccountController();
		ReflectionTestUtils.setField(accountController, "userAccountService",
				userAccountService);

		ModelAndView modelAndView = accountController.showAllUserAccounts();

		assertViewName(modelAndView, "userAccountList");
		assertModelAttributeValue(modelAndView, "userAccounts", userAccounts);
	}

	@Test
	public void testDeleteUserAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowUserAccountCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowUserAccountUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveUserAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllValidationMessages() {
		fail("Not yet implemented");
	}

}
