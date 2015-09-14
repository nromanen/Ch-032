package com.softserveinc.orphanagemenu.controller;

import static java.util.Arrays.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.ModelAndViewAssert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.softserveinc.orphanagemenu.exception.LastAdministratorException;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;
import com.softserveinc.orphanagemenu.validators.UserAccountValidator;

public class UserAccountControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void showAllUserAccountsTest() {
		List<UserAccount> userAccounts = asList (new UserAccount(), new UserAccount());
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
	public void deleteUserAccountTest() {
		UserAccountService userAccountService = mock(UserAccountService.class);
		UserAccountController accountController = new UserAccountController();
		ReflectionTestUtils.setField(accountController, "userAccountService", userAccountService);
		Long idToDeleteUserAccount = 1L;
		Map<String, Object> model = new HashMap<>();
		
		accountController.deleteUserAccount(new RedirectAttributesModelMap(),
											idToDeleteUserAccount, 
											model);

		try {
			verify(userAccountService).deleteByID(idToDeleteUserAccount);
		} catch (LastAdministratorException e) {
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void saveUserAccountTestDontSaveOnValidateFailUserAccount() throws LastAdministratorException {
		UserAccountService userAccountService = mock(UserAccountService.class);
		UserAccountValidator userAccountValidator = mock(UserAccountValidator.class);
		Map<String, String> requestParams = mock(Map.class);
		Map<String, Object> model = mock(Map.class);
		UserAccountForm userAccountForm = mock(UserAccountForm.class);
		BindingResult bindingResult = mock(BindingResult.class);

		when(bindingResult.hasErrors()).thenReturn(true);
		
		UserAccountController accountController = new UserAccountController();
		ReflectionTestUtils.setField(accountController, "userAccountService", userAccountService);
		ReflectionTestUtils.setField(accountController, "userAccountValidator", userAccountValidator);
				
		accountController.saveUserAccount(null, requestParams, model, userAccountForm, bindingResult);

		verify(userAccountValidator).validate(userAccountForm, bindingResult);
		verify(userAccountService, never()).save(any(UserAccount.class));
	}

}
