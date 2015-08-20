package com.softserveinc.orphanagemenu.validators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.springframework.validation.Errors;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.forms.UserAccountForm;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Dimension;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.Role;
import com.softserveinc.orphanagemenu.model.UserAccount;
import com.softserveinc.orphanagemenu.service.ProductService;

public class ProductValidatorTest {

	private ProductDao productDao;
	private ProductService productService;
	private Errors errors;
	
	private Product product;
	private ProductForm productForm;
	
	@Before
	public void setUp() throws Exception {
		// create userAccount with passing validation fields
		product = new Product();
		product.setId(1L);
		product.setName("Буряк");
		Dimension dimension = new Dimension();
		dimension.setId(1L);
		dimension.setName("гр.");
		product.setDimension(dimension);
		Set<ProductWeight> productWeights = new HashSet<>();
		ProductWeight productWeight = new ProductWeight();
		productWeight.setId(1L);
		productWeight.setProduct(product);
		productWeight.setStandartProductQuantity(100.00);
		AgeCategory ageCategory = new AgeCategory();
		ageCategory.setId(1L);
		ageCategory.setIsActive(true);
		ageCategory.setName("3-5p.");
		productWeight.setAgeCategory(ageCategory);
		productWeights.add(productWeight);
		
//		Role role = new Role();
//		role.setId(1L);
//		role.setName("Operator");
//		Set<Role> rolesSet = new HashSet<>();
//		rolesSet.add(role);
//		userAccount.setRoles(rolesSet);
//		
//		// create userAccountForm corresponded with userAccount
//		userAccountForm = new UserAccountForm();
//		userAccountForm.setId("1");
//		userAccountForm.setLogin("login");
//		userAccountForm.setPassword("password");
//		userAccountForm.setFirstName("Firstname");
//		userAccountForm.setLastName("Lastname");
//		userAccountForm.setEmail("email@email.com");
//		Map<String,String> rolesMap = new HashMap<String, String>();
//		rolesMap.put("Operator", "on");
//		userAccountForm.setRoles(rolesMap);
	}
}
