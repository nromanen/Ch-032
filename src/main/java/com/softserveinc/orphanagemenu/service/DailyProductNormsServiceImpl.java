package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;
import com.softserveinc.orphanagemenu.model.Submenu;

@Service
public class DailyProductNormsServiceImpl implements DailyProductNormsService {

    public Map<Product, List<NormstForAgeCategoryDto>> parseComponents(
	    List<Submenu> submenus) {

	Map<Product, List<NormstForAgeCategoryDto>> productsWithNorms = new HashMap<Product, List<NormstForAgeCategoryDto>>();

	for (ComponentWeight componentWeight : parseSubMenu(submenus)) {

	    NormstForAgeCategoryDto ageStandartAndFact = new NormstForAgeCategoryDto();
	    Product product = componentWeight.getComponent().getProduct();
	    AgeCategory ageCategory = componentWeight.getAgeCategory();

	    Double standartQuantity = findStandartQuantity(ageCategory,
		    product.getProductWeight());
	    Double factQuantity = componentWeight.getStandartWeight();
	    ageStandartAndFact.setAgeCategory(ageCategory);
	    ageStandartAndFact.setStandartProductQuantity(standartQuantity);
	    ageStandartAndFact.setFactProductQuantity(factQuantity);

	    insertIntoProductWithNorms(productsWithNorms, product,
		    ageStandartAndFact);

	}

	sortAgeCategorys(productsWithNorms);

	return productsWithNorms;
    }

    private List<ComponentWeight> parseSubMenu(List<Submenu> submenus) {
	List<ComponentWeight> compontWeights = new ArrayList<ComponentWeight>();

	for (Submenu subMenu : submenus) {

	    AgeCategory subMenuAgeCategory = subMenu.getAgeCategory();
	    for (Dish dish : subMenu.getDishes()) {
		compontWeights.addAll(getProductWeightFromDish(dish,
			subMenuAgeCategory));

	    }

	}

	return compontWeights;
    }

    private List<ComponentWeight> getProductWeightFromDish(Dish dish,
	    AgeCategory subMenuAgeCategory) {
	List<ComponentWeight> dishCompontWeights = new ArrayList<ComponentWeight>();
	for (Component component : dish.getComponents()) {
	    for (ComponentWeight componentWeight : component.getComponents()) {
		if (componentWeight.getAgeCategory().equals(subMenuAgeCategory)) {
		    dishCompontWeights.add(componentWeight);
		}
	    }
	}
	return dishCompontWeights;
    }

    private Double findStandartQuantity(AgeCategory ageCategory,
	    Set<ProductWeight> productWeights) {
	Double localProductWeight = 0D;
	for (ProductWeight productWeight : productWeights) {

	    if (productWeight.getAgeCategory().equals(ageCategory)) {

		localProductWeight = productWeight.getStandartProductQuantity();

	    }
	}
	return localProductWeight;
    }

    private void insertIntoProductWithNorms(
	    Map<Product, List<NormstForAgeCategoryDto>> productsWithNorms,
	    Product product, NormstForAgeCategoryDto ageNormAndFact) {
	List<NormstForAgeCategoryDto> ageNorms;
	if (productsWithNorms.get(product) == null) {
	    ageNorms = new ArrayList<NormstForAgeCategoryDto>();
	    ageNorms.add(ageNormAndFact);
	    productsWithNorms.put(product, ageNorms);
	} else {

	    ageNorms = productsWithNorms.get(product);

	    if (ageNorms.contains(ageNormAndFact)) {

		NormstForAgeCategoryDto itemNorms = ageNorms.get(ageNorms
			.indexOf(ageNormAndFact));

		itemNorms.setFactProductQuantity(itemNorms
			.getFactProductQuantity()
			+ ageNormAndFact.getFactProductQuantity());
	    } else {
		ageNorms.add(ageNormAndFact);
	    }

	}

    }

    private void sortAgeCategorys(
	    Map<Product, List<NormstForAgeCategoryDto>> productsWithNorms) {
	for (Entry<Product, List<NormstForAgeCategoryDto>> entry : productsWithNorms
		.entrySet()) {
	    Collections.sort(entry.getValue());
	}
    }
}
