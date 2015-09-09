package com.softserveinc.orphanagemenu.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:unit-test-context/test-context.xml" })
public class DailyProductNormsServiceTest {
	@Autowired
	private DailyProductNormsService productNormService;

	@Mock
	private List<ComponentWeight> componentWeights;
	@Mock
	private List<ProductWeight> productWeightsInsideProduct;
	@Mock
	private ProductWeight productWeight;
	@Mock
	private ComponentWeight componentWeight;
	@Mock
	private AgeCategory ageCategory;
	@Mock
	private Component component;
	@Mock
	private Product product;

	public void setUPTest() {

		MockitoAnnotations.initMocks(this);

		doReturn(10D).when(productWeight).getStandartProductQuantity();

		ageCategory.setId(1L);
		ageCategory.setName("Standart");

		doReturn(ageCategory).when(productWeight).getAgeCategory();

		doReturn(product).when(component).getProduct();

		productWeightsInsideProduct = Arrays.asList(productWeight,
				productWeight, productWeight, productWeight);

		doReturn(ageCategory).when(componentWeight).getAgeCategory();
		doReturn(component).when(componentWeight).getComponent();

		componentWeights = Arrays.asList(componentWeight, componentWeight,
				componentWeight);
	}

	@Test
	public void testCount() {
		setUPTest();
		Map<Product, List<NormstForAgeCategoryDto>> productNorms = productNormService
				.parseComponents(componentWeights);
		Integer expected = 1;
		Integer actual = productNorms.size();
		assertEquals(expected, actual);

	}

}
