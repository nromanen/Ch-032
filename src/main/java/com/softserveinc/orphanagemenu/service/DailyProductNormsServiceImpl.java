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
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.ProductWeight;

@Service
public class DailyProductNormsServiceImpl implements DailyProductNormsService {
	
	public Map<Product, List<NormstForAgeCategoryDto>> parseComponents(
			List<ComponentWeight> componentWeights) {

		Map<Product, List<NormstForAgeCategoryDto>> productsWithNorms = new HashMap<Product, List<NormstForAgeCategoryDto>>();

		for (ComponentWeight componentWeight : componentWeights) {

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

	private Double findStandartQuantity(AgeCategory ageCategory,
			Set<ProductWeight> productWeights) {

		for (ProductWeight productWeight : productWeights) {

			if (productWeight.getAgeCategory().equals(ageCategory)) {

				return productWeight.getStandartProductQuantity();

			}
		}
		return 0D;
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
