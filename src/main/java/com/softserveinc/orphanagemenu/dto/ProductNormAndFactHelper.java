package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;

public class ProductNormAndFactHelper {
	private List<ProductNormsAndFact> productsNormsAndFacts = new ArrayList<ProductNormsAndFact>();

	public ProductNormAndFactHelper() {

	}

	public List<ProductNormsAndFact> parseComponents(List<Component> components) {
		for (Component component : components) {

			for (ComponentWeight componentWeight : component.getComponents()) {

				NormAndFactForAgeCategory ageNormAndFact = new NormAndFactForAgeCategory();

				ageNormAndFact.setAgeCategory(componentWeight.getAgeCategory());

				ageNormAndFact
						.setStandartProductQuantityFromComponent(component);

				ageNormAndFact.addFactProductQuantity(componentWeight
						.getStandartWeight());

				ProductNormsAndFact productNorms = new ProductNormsAndFact();

				productNorms.setProductName(component.getProduct().getName());

				productNorms.setDimension(component.getProduct().getDimension().getName());

				productNorms.addNormsAndFact(ageNormAndFact);

				add(productNorms);

			}

		}

		sortList();
		return productsNormsAndFacts;

	}

	private void add(ProductNormsAndFact productNorm) {
		if (productsNormsAndFacts.contains(productNorm)) {

			int indexID = productsNormsAndFacts.indexOf(productNorm);
			ProductNormsAndFact itemProductNormCompliance = productsNormsAndFacts
					.get(indexID);

			itemProductNormCompliance.addNormsAndFact(productNorm
					.getCategoryWithNormsAndFact().get(0));

		} else {
			productsNormsAndFacts.add(productNorm);
		}

	}

	private void sortList() {
		for (ProductNormsAndFact productNormItem : productsNormsAndFacts) {
			Collections.sort(productNormItem.getCategoryWithNormsAndFact());
		}
	}

}
