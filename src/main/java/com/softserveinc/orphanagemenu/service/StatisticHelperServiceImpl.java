package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dto.NormAndFactForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@Service
public class StatisticHelperServiceImpl implements StatisticHelperService {
	private Map<Product, List<NormAndFactForAgeCategoryDto>> productsWithHorms = new HashMap<Product, List<NormAndFactForAgeCategoryDto>>();

	public Map<Product, List<NormAndFactForAgeCategoryDto>> parseComponents(
			List<Component> components) {

		for (Component component : components) {

			for (ComponentWeight componentWeight : component.getComponents()) {

				NormAndFactForAgeCategoryDto ageNormAndFact = new NormAndFactForAgeCategoryDto();

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

		return productsWithHorms;
	}

	private void put(Product product,
			NormAndFactForAgeCategoryDto ageNormAndFact) {

		if (productsWithHorms.get(product) == null) {
			List<NormAndFactForAgeCategoryDto> ageNorms = new ArrayList<NormAndFactForAgeCategoryDto>();
			ageNorms.add(ageNormAndFact);
			productsWithHorms.put(product, ageNorms);
		} else {
			List<NormAndFactForAgeCategoryDto> ageNormsList = new ArrayList<NormAndFactForAgeCategoryDto>();

			ageNormsList = productsWithHorms.get(product);
			if (ageNormsList.contains(ageNormAndFact)) {

				int indexID = ageNormsList.indexOf(ageNormAndFact);
				NormAndFactForAgeCategoryDto itemNorms = ageNormsList
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
		for (Entry<Product, List<NormAndFactForAgeCategoryDto>> entry : productsWithHorms
				.entrySet()) {
			Collections.sort(entry.getValue());
		}
	}
}
