package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@Service
public class StatisticHelperServiceImpl implements StatisticHelperService {

	public Map<Product, List<NormstForAgeCategoryDto>> parseComponents(
			List<Component> components) {
		Map<Product, List<NormstForAgeCategoryDto>> productsWithNorms = new HashMap<Product, List<NormstForAgeCategoryDto>>();

		for (Component component : components) {

			for (ComponentWeight componentWeight : component.getComponents()) {

				NormstForAgeCategoryDto ageNormAndFact = createAgeNorms(
						componentWeight.getAgeCategory(),
						findStandartQuantity(componentWeight, component),
						componentWeight.getStandartWeight());

				put(productsWithNorms, component.getProduct(), ageNormAndFact);

			}

		}

		sortAgeCategorys(productsWithNorms);

		return productsWithNorms;
	}

	private NormstForAgeCategoryDto createAgeNorms(AgeCategory ageCategory,
			Double standartQuantity, Double factQuantity) {
		NormstForAgeCategoryDto ageStandartAndFact = new NormstForAgeCategoryDto();
		ageStandartAndFact.setAgeCategory(ageCategory);

		ageStandartAndFact.setStandartProductQuantity(standartQuantity);

		ageStandartAndFact.setFactProductQuantity(factQuantity);

		return ageStandartAndFact;
	}

	private Double findStandartQuantity(ComponentWeight componentWeight,
			Component component) {
		for (ProductWeight productWeight : component.getProduct()
				.getProductWeight()) {
			if (productWeight.getAgeCategory().getName()
					.equals(componentWeight.getAgeCategory().getName())) {

				return productWeight.getStandartProductQuantity();

			}
		}
		return 0D;
	}

	private void put(
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

				NormstForAgeCategoryDto itemNorms = ageNorms.get(ageNorms.indexOf(ageNormAndFact));

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
