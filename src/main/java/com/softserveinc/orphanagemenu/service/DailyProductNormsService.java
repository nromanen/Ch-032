package com.softserveinc.orphanagemenu.service;

import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Submenu;

public interface DailyProductNormsService {
    Map<Product, List<NormstForAgeCategoryDto>> parseComponents(
	    List<Submenu> submenus);

}
