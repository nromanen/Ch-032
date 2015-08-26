package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.dto.NormAndFactForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.Product;

public interface StatisticHelperService {
	 Map<Product, List<NormAndFactForAgeCategoryDto>> parseComponents(
				List<Component> components);

}
