package com.softserveinc.orphanagemenu.validators;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.softserveinc.orphanagemenu.dao.ProductDao;
import com.softserveinc.orphanagemenu.forms.ProductForm;
import com.softserveinc.orphanagemenu.model.Product;

public class ProductValidatorTest {

	private ProductDao productDao;
	private Errors errors;

	private Product product;
	private ProductForm productForm;
	private ProductValidator productValidator;

	@Before
	public void setUp() throws Exception {
		// create userAccount with passing validation fields
		product = new Product();
		product.setId(1L);

		// create userAccountForm corresponded with userAccount
		productForm = new ProductForm();
		productForm.setId("1");
		productForm.setName("Буряк");
		productForm.setDimension("1");

		productDao = mock(ProductDao.class);
		when(productDao.getProduct("Буряк")).thenReturn(product);

		productValidator = new ProductValidator();
		ReflectionTestUtils
				.setField(productValidator, "productDao", productDao);
	}

	 @Test
	 public void emptyProductNameTest() {
	 errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
	 productForm.setName("");
	 productValidator.validate(productForm, errors);
	 Assert.assertEquals(1, errors.getErrorCount());
	 }
	
	 @Test
	 public void whiteSpacesProductNameTest() {
	 errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
	 productForm.setName("  ");
	 productValidator.validate(productForm, errors);
	 Assert.assertEquals(1, errors.getErrorCount());
	 }
	
	 @Test
	 public void tooShortProductNameTest() {
	 errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
	 productForm.setName("Б");
	 productValidator.validate(productForm, errors);
	 Assert.assertEquals(1, errors.getErrorCount());
	 }
	
	 @Test
	 public void tooLongProductNameTest() {
	 errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
	 productForm
	 .setName("Ббббббббббббббббббббббббббббббббббббббббббббббббббббб");
	 productValidator.validate(productForm, errors);
	 Assert.assertEquals(1, errors.getErrorCount());
	 }
	
	 @Test
	 public void illegalSymbolsProductNameTest() {
	 errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
	 productForm.setName("Буряк$");
	 productValidator.validate(productForm, errors);
	 Assert.assertEquals(1, errors.getErrorCount());
	 }

	@Test
	public void duplicateSymbolsIfNullObjectReturnProductNameTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		productForm.setId("2");
		productValidator.validate(productForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void duplicateSymbolsIfNotNullObjectReturnProductNameTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		productValidator.validate(productForm, errors);
		Assert.assertEquals(0, errors.getErrorCount());
	}

	@Test
	public void duplicateSymbolsSameIdProductNameTest() {
		when(productDao.getProduct("Буряк")).thenReturn(null);
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		productValidator.validate(productForm, errors);
		Assert.assertEquals(0, errors.getErrorCount());
	}

	@Test
	public void EmptyDimensionTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		productForm.setDimension(null);
		productValidator.validate(productForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void EmptyProductWeightTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		Map<Long, String> weightList = new TreeMap<>();
		weightList.put(3L, "");
		productForm.setWeightList(weightList);
		productValidator.validate(productForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void illegalSymbolsWeightTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		Map<Long, String> weightList = new TreeMap<>();
		weightList.put(3L, "a");
		productForm.setWeightList(weightList);
		productValidator.validate(productForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}

	@Test
	public void tooLongWeightStringTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		Map<Long, String> weightList = new TreeMap<>();
		weightList.put(3L, "33333333");
		productForm.setWeightList(weightList);
		productValidator.validate(productForm, errors);
		Assert.assertEquals(1, errors.getErrorCount());
	}
	@Test
	public void goodWeightStringTest() {
		errors = new BeanPropertyBindingResult(productForm, "noMatterParameter");
		Map<Long, String> weightList = new TreeMap<>();
		weightList.put(3L, "333333");
		productForm.setWeightList(weightList);
		productValidator.validate(productForm, errors);
		Assert.assertEquals(0, errors.getErrorCount());
	}
}
