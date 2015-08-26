package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dto.StandartAndFactForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@Service
public class StatisticHelperServiceImpl implements StatisticHelperService {
	private Map<Product, List<StandartAndFactForAgeCategoryDto>> productsWithNorms;

	public Map<Product, List<StandartAndFactForAgeCategoryDto>> parseComponents(
			List<Component> components) {
		productsWithNorms = new HashMap<Product, List<StandartAndFactForAgeCategoryDto>>();

		for (Component component : components) {

			for (ComponentWeight componentWeight : component.getComponents()) {

				StandartAndFactForAgeCategoryDto ageNormAndFact = new StandartAndFactForAgeCategoryDto();

				ageNormAndFact.setAgeCategory(componentWeight.getAgeCategory());

				Double standartProductQuantity = 0D;

				for (ProductWeight productWeight : component.getProduct()
						.getProductWeight()) {
					if (productWeight.getAgeCategory().getName()
							.equals(componentWeight.getAgeCategory().getName())) {

						standartProductQuantity = productWeight
								.getStandartProductQuantity();

					}
				}
				ageNormAndFact
						.setStandartProductQuantity(standartProductQuantity);

				ageNormAndFact.setFactProductQuantity(componentWeight
						.getStandartWeight());

				put(component.getProduct(), ageNormAndFact);

			}

		}

		sort();

		return productsWithNorms;
	}

	private void put(Product product,
			StandartAndFactForAgeCategoryDto ageNormAndFact) {

		if (productsWithNorms.get(product) == null) {
			List<StandartAndFactForAgeCategoryDto> ageNorms = new ArrayList<StandartAndFactForAgeCategoryDto>();
			ageNorms.add(ageNormAndFact);
			productsWithNorms.put(product, ageNorms);
		} else {
			List<StandartAndFactForAgeCategoryDto> ageNormsList = new ArrayList<StandartAndFactForAgeCategoryDto>();

			ageNormsList = productsWithNorms.get(product);
			if (ageNormsList.contains(ageNormAndFact)) {

				int indexID = ageNormsList.indexOf(ageNormAndFact);
				StandartAndFactForAgeCategoryDto itemNorms = ageNormsList
						.get(indexID);

				itemNorms.setFactProductQuantity(itemNorms
						.getFactProductQuantity()
						+ ageNormAndFact.getFactProductQuantity());

			} else {
				ageNormsList.add(ageNormAndFact);
			}

		}

	}

	private void sort() {
		for (Entry<Product, List<StandartAndFactForAgeCategoryDto>> entry : productsWithNorms
				.entrySet()) {
			Collections.sort(entry.getValue());
		}
	}
}
