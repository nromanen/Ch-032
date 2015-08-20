package com.softserveinc.orphanagemenu.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormAndFactList {
private List<ProductNorms> productsNormsAndFacts = new ArrayList<ProductNorms>();

public NormAndFactList() {
	super();
	
}

public List<ProductNorms> getProductsNormsAndFacts() {
		
	return productsNormsAndFacts;
}

public void add(ProductNorms productNormCompliance){
	if (productsNormsAndFacts.contains(productNormCompliance)) {

		int indexID = productsNormsAndFacts
				.indexOf(productNormCompliance);
		ProductNorms itemProductNormCompliance = productsNormsAndFacts
				.get(indexID);

		itemProductNormCompliance
				.addCategoryWithNormsAndFact(productNormCompliance
						.getCategoryWithNormsAndFact().get(0));

	} else {

		productsNormsAndFacts.add(productNormCompliance);

	}
	for (ProductNorms productNorm : productsNormsAndFacts) {
		Collections.sort(productNorm
				.getCategoryWithNormsAndFact());
	}
}

public void setProductsNormsAndFacts(
		List<ProductNorms> productsNormsAndFacts) {
	this.productsNormsAndFacts = productsNormsAndFacts;
}



}
