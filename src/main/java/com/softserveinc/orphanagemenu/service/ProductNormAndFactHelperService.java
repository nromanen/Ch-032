package com.softserveinc.orphanagemenu.service;

import java.util.List;

import com.softserveinc.orphanagemenu.dto.ProductNormsAndFact;
import com.softserveinc.orphanagemenu.model.Component;

public interface ProductNormAndFactHelperService {
	List<ProductNormsAndFact> parseComponents(List<Component> components);
}