package com.softserveinc.orphanagemenu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.softserveinc.orphanagemenu.dto.ReportProductQuantitiesDto;
import com.softserveinc.orphanagemenu.model.Product;

public interface DailyMenuReportService {
	List<ReportProductQuantitiesDto> buildReports(Date date);
	Map<Product, Double> buildOverallProductQuantities(Date date);
}
