package com.softserveinc.orphanagemenu.controller;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.service.UserAccountService;


public class ProductCintrollerTest {
	
	@InjectMocks
	ProductController controller;
	
	@Mock
	ProductService mockProductService;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
//	@Test
//	public void testListProducts() {
//		List<Product> expectedProducts = asList (new Product());
//		when(mockProductService.getPage(1, 15)).thenReturn(expectedProducts);
//		
//		Model model = (Model) new ModelMap();
//		String viewName = controller.getProductsPage(model, currentPage)
//	}
//	
//	@Test
//	public void showAllUserAccountsTest() {
//		List<Product> products = asList (new Product(), new Product());
//		UserAccountService userAccountService = mock(UserAccountService.class);
//		when(userAccountService.getAllDto()).thenReturn(userAccounts);
//		UserAccountController accountController = new UserAccountController();
//		ReflectionTestUtils.setField(accountController, "userAccountService",
//				userAccountService);
//
//		ModelAndView modelAndView = accountController.showAllUserAccounts();
//
//		assertViewName(modelAndView, "userAccountList");
//		assertModelAttributeValue(modelAndView, "userAccounts", userAccounts);
//	}
	
}