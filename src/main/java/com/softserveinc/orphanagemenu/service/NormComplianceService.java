package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dto.AgeCategoryNormsAndFactDto;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.ProductNormComplianceDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Submenu;

@Service("StandartAndFactQuantityService")
public class NormComplianceService {

	@Autowired
	private DailyMenuDao dailyMenuDao;
	
	
	
	public void getCompliance(DailyMenuDto dailyMenu){
		
	}
	public List<ProductNormComplianceDto> getProductWithStandartAndFactQuantityList(Long id){
		List<ProductNormComplianceDto> prodNormList = new ArrayList<ProductNormComplianceDto>();
		
		for(Submenu subMenu:dailyMenuDao.getById(id).getSubmenus()){
			System.out.println("*** subMenu:"+subMenu);
			
			for(Dish dish:subMenu.getDishes()){
				for(Component component:dish.getComponents()){
					

					for(ComponentWeight componentWeight:component.getComponents()){
					
						AgeCategoryNormsAndFactDto ageCategoryNormsAndFact = new AgeCategoryNormsAndFactDto();  
						ageCategoryNormsAndFact.setAgeCategory(componentWeight.getAgeCategory());               //set Age Category
						ageCategoryNormsAndFact.setFactQuantity(componentWeight.getStandartWeight());           //set FactQuantity
						ageCategoryNormsAndFact.setNorma(0D);                                                   //TODO setNorma
						
						ProductNormComplianceDto productNormCompliance = new ProductNormComplianceDto();
						productNormCompliance.setName(component.getProduct().getName());
						productNormCompliance.setCategoryWithNormsAndFact(ageCategoryNormsAndFact);
						
						prodNormList.add(productNormCompliance);                                             //adding to list
						System.out.println("*** prodNormList:"+prodNormList);
					}
					
					
					
				}
				
			}
			
		}
		
		
		return prodNormList;
	}
}
