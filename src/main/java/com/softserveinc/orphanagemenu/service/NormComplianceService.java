package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.ProductNormComplianceDto;

@Service("StandartAndFactQuantityService")
public class NormComplianceService {

	@Autowired
	private DailyMenuDao dailyMenuDao;
	
	
	
	public void getCompliance(DailyMenuDto dailyMenu){
		
	}
	public List<ProductNormComplianceDto> getProductWithStandartAndFactQuantityList(){
		List<ProductNormComplianceDto> prodNormList = new ArrayList<ProductNormComplianceDto>();
		
		
		
		return null;
	}
}
