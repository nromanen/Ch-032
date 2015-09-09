package com.softserveinc.orphanagemenu.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.Submenu;

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
    private Submenu submenu;
    @Mock
    private Dish dish;
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
    @Mock
    private List<Submenu> submenus;

    private Set<Dish> dishes;
    @Mock
    private Set<Component> components;

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
	components = new HashSet<Component>();
	components.add(component);

	doReturn(components).when(dish).getComponents();
	dishes = new HashSet<Dish>();
	dishes.add(dish);

	doReturn(dishes).when(submenu).getDishes();

	submenu.setAgeCategory(ageCategory);

	submenus = Arrays.asList(submenu);

    }

    @Test
    public void testCount() {
	setUPTest();
	Map<Product, List<NormstForAgeCategoryDto>> productNorms = productNormService
		.parseComponents(submenus);
	Integer expected = 0;
	Integer actual = productNorms.size();
	assertEquals(expected, actual);

    }

}
