package com.softserveinc.orphanagemenu.validators;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.test.util.ReflectionTestUtils;

import com.softserveinc.orphanagemenu.dao.UserAccountDao;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.UserAccountService;

public class UserAccountValidatorTest {

	private UserAccountDao userAccountDao;
	private UserAccountService userAccountService;
	private Errors errors;
	
	private UserAccount userAccount;
	private UserAccountForm userAccountForm;
		
	@Before
	public void setUp() throws Exception {
		// create userAccount with passing validation fields
		userAccount = new UserAccount();
		userAccount.setId(1L);
		userAccount.setLogin("login");
		userAccount.setPassword("password");
		userAccount.setFirstName("Firstname");
		userAccount.setLastName("Lastname");
		userAccount.setEmail("email@email.com");
		Role role = new Role();
		role.setId(1L);
		role.setName("Operator");
		Set<Role> rolesSet = new HashSet<>();
		rolesSet.add(role);
		userAccount.setRoles(rolesSet);
		
		// create userAccountForm corresponded with userAccount
		userAccountForm = new UserAccountForm();
		userAccountForm.setId("1");
		userAccountForm.setLogin("login");
		userAccountForm.setPassword("password");
		userAccountForm.setFirstName("Firstname");
		userAccountForm.setLastName("Lastname");
		userAccountForm.setEmail("email@email.com");
		Map<String,String> rolesMap = new HashMap<String, String>();
		rolesMap.put("Operator", "on");
		userAccountForm.setRoles(rolesMap);
	}

	@Test
	public void validateTestWithWrongLogin() {
		userAccountDao = mock(UserAccountDao.class);
		when(userAccountDao.getByLogin("login")).thenReturn(userAccount);
		userAccountService = mock(UserAccountService.class);
		when(userAccountService.isLastAdministrator(1L)).thenReturn(false);
		
		UserAccountValidator userAccountValidator =	new UserAccountValidator();
		ReflectionTestUtils.setField(userAccountValidator, "userAccountDao", userAccountDao);
		ReflectionTestUtils.setField(userAccountValidator, "userAccountService", userAccountService);

		// test for empty login
		errors = new BeanPropertyBindingResult(userAccountForm, "noMatterParameter");
		userAccountForm.setLogin("");
		userAccountValidator.validate(userAccountForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());

		// test for login consists of white spaces only
		errors = new BeanPropertyBindingResult(userAccountForm, "noMatterParameter");
		userAccountForm.setLogin("  ");
		userAccountValidator.validate(userAccountForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
		
		// test for login less than 3 symbols
		errors = new BeanPropertyBindingResult(userAccountForm, "noMatterParameter");
		userAccountForm.setLogin("lo");
		userAccountValidator.validate(userAccountForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
		
		// test for login with illegal symbols
		errors = new BeanPropertyBindingResult(userAccountForm, "noMatterParameter");
		userAccountForm.setLogin("login$");
		userAccountValidator.validate(userAccountForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
		
		// test for duplicate login
		errors = new BeanPropertyBindingResult(userAccountForm, "noMatterParameter");
		userAccountForm.setLogin("login");
		userAccountForm.setId("2");
		userAccountValidator.validate(userAccountForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}
	
	@Test
	public void validateTestWithWrongLastAdministratorUnassign() {
		userAccountDao = mock(UserAccountDao.class);
		when(userAccountDao.getByLogin("login")).thenReturn(userAccount);
		userAccountService = mock(UserAccountService.class);
		when(userAccountService.isLastAdministrator(1L)).thenReturn(true);
		UserAccountValidator userAccountValidator =	new UserAccountValidator(userAccountDao,
															userAccountService);
		
		// Test for last 'Administrator' unassign.
		// Send to validate() userAccountForm with unsigned 'Administrator' role
		// while there is the last Administrator with corresponding ID in DB.
		errors = new BeanPropertyBindingResult(userAccountForm, "noMatterParameter");
		userAccountValidator.validate(userAccountForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

}
