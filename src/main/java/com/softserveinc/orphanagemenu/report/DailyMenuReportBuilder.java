package com.softserveinc.orphanagemenu.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.dto.ProductQuantitiesReportColumn;
import com.softserveinc.orphanagemenu.dto.ReportProductQuantitiesDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Submenu;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;

@Component("dailyMenuReportBuilder")
@Transactional
public class DailyMenuReportBuilder {

	private final static int FIRST_JUNIOR_AGE_CATEGORY = 0;
	private final static int SECOND_JUNIOR_AGE_CATEGORY = 1;
	private final static int FIRST_SENIOR_AGE_CATEGORY = 2;
	private final static int SECOND_SENIOR_AGE_CATEGORY = 3;
	
	@Autowired
	@Qualifier("dailyMenuDao")
	private DailyMenuDao dailyMenuDao;
	
	@Autowired
	@Qualifier("consumptionTypeDao")
	private ConsumptionTypeDao consumptionTypeDao; 
	
	@Autowired
	@Qualifier("submenuDao")
	private SubmenuDao submenuDao;
	
	@Autowired
	private AgeCategoryService ageCategoryService;
	
	@Autowired
	@Qualifier("factProductQuantityDao")
	private FactProductQuantityDao factProductQuantityDao;
	
	public List<ReportProductQuantitiesDto> buildReports(Date date){
		List<ReportProductQuantitiesDto> reports = new ArrayList<>();
		List<AgeCategory> ageCategories = ageCategoryService.getAllAgeCategory();
		List<AgeCategory> juniorAgeCategories = new ArrayList<>();
		juniorAgeCategories.add(ageCategories.get(FIRST_JUNIOR_AGE_CATEGORY));
		juniorAgeCategories.add(ageCategories.get(SECOND_JUNIOR_AGE_CATEGORY));
		List<AgeCategory> seniorAgeCategories = new ArrayList<>();
		seniorAgeCategories.add(ageCategories.get(FIRST_SENIOR_AGE_CATEGORY));
		seniorAgeCategories.add(ageCategories.get(SECOND_SENIOR_AGE_CATEGORY));
		reports.add(buildReportForAgeCategories(date,juniorAgeCategories, "report.subtitleJuniors"));
		reports.add(buildReportForAgeCategories(date,seniorAgeCategories, "report.subtitleSeniors"));
		return reports;
	}
	
	public ReportProductQuantitiesDto buildReportForAgeCategories(
			Date date,
			List<AgeCategory> ageCategories,
			String subtitleMessageCode){
		ReportProductQuantitiesDto report = new ReportProductQuantitiesDto();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
		report.setDate(formatter.print(new DateTime(date)));
		report.setSubtitle(subtitleMessageCode);
		report.setConsumptionTypeAgeCategoryChildQuantities(createConsumptionTypeAgeCategoryChildQuantities(date));
		report.setConsumptionTypes(dailyMenuDao.getConsumptionTypesForDailyMenu(date));
		report.setAgeCategories(ageCategories);
		report.setProducts(dailyMenuDao.getProductsForDailyMenu(date));
		List<ProductQuantitiesReportColumn> columns = createProductQuantitiesReportColumns(date, ageCategories);
		report.setColumns(columns);
		report.setConsumptionTypeDishQuantities(createConsumptionTypeDishQuantities(columns));
		Mapper mapper = new DozerBeanMapper();
		ReportProductQuantitiesDto reportDto = mapper.map(report, ReportProductQuantitiesDto.class);
		return reportDto;
	}

	private Map<ConsumptionType, Map<AgeCategory, Integer>> createConsumptionTypeAgeCategoryChildQuantities(Date date) {
		DailyMenu dailyMenu = dailyMenuDao.getByDate(date);
		Map<ConsumptionType, Map<AgeCategory, Integer>> quantities = new HashMap<>();
		for (ConsumptionType consumptionType : consumptionTypeDao.getAll()) {
			Map<AgeCategory, Integer> ageCategoryChildQuantities = new HashMap<>();
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if (submenu.getConsumptionType().equals(consumptionType)) {
					ageCategoryChildQuantities.put(
							submenu.getAgeCategory(),
							submenu.getChildQuantity());
				}
			}
			quantities.put( consumptionType, ageCategoryChildQuantities);
		}
		return quantities;
	}
	
	private List<ProductQuantitiesReportColumn> createProductQuantitiesReportColumns(Date date, List<AgeCategory> ageCategories) {
		DailyMenu dailyMenu = dailyMenuDao.getByDate(date);
		List<ProductQuantitiesReportColumn> columns = new ArrayList<>();
		for (ConsumptionType consumptionType : consumptionTypeDao.getAll()) {
			for (Submenu submenu : dailyMenu.getSubmenus()) {
				if (submenu.getConsumptionType().equals(consumptionType)) {
					for (Dish dish : submenu.getDishes()) {
						ProductQuantitiesReportColumn column = new ProductQuantitiesReportColumn();
						column.setConsumptionType(consumptionType);
						column.setDish(dish);
						Map<Product, Map<AgeCategory, Double>> productQuantities = new HashMap<>();
						for (com.softserveinc.orphanagemenu.model.Component component : dish.getComponents()) {
							Map<AgeCategory, Double> quantitiesForAgeCategory = new HashMap<>();
							for (ComponentWeight componentWeight : component.getComponents()) {
								if (!ageCategories.contains(componentWeight.getAgeCategory())){
									continue;
								}
								Submenu submenuForComponentWeigth = submenuDao
										.getSubmenuByDailyMenuAndConsumptionTypeAndAgeCategory(
												dailyMenu.getId(), consumptionType.getId(),	componentWeight.getAgeCategory());
								Double quantityToFillReport = factProductQuantityDao
										.getBySubmenuAndComponentWeight(submenuForComponentWeigth,componentWeight)
										.getFactProductQuantity();
								quantitiesForAgeCategory.put(componentWeight.getAgeCategory(), quantityToFillReport);
							}
							productQuantities.put(component.getProduct(), quantitiesForAgeCategory);
						}
						column.setProductQuantities(productQuantities);
						columns.add(column);
					}
					break;
				}
			}
		}
		return columns;
	}
	
	private Map<ConsumptionType, Integer> createConsumptionTypeDishQuantities(
			List<ProductQuantitiesReportColumn> columns) {
		Map<ConsumptionType, Integer> quantities = new HashMap<>();
		for (ConsumptionType consumptionType: consumptionTypeDao.getAll()){
			quantities.put(consumptionType, 0);
		}
		for (ProductQuantitiesReportColumn column : columns){
			ConsumptionType consumptionType = column.getConsumptionType();
			int quantity = quantities.get(consumptionType) + 1;
			quantities.put(consumptionType, quantity);
		}
		return quantities;
	}

}
