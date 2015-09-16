package com.softserveinc.orphanagemenu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserveinc.orphanagemenu.dao.ConsumptionTypeDao;
import com.softserveinc.orphanagemenu.dao.DailyMenuDao;
import com.softserveinc.orphanagemenu.dao.FactProductQuantityDao;
import com.softserveinc.orphanagemenu.dao.SubmenuDao;
import com.softserveinc.orphanagemenu.dto.ProductQuantitiesReportColumn;
import com.softserveinc.orphanagemenu.dto.ReportProductQuantitiesDto;
import com.softserveinc.orphanagemenu.model.AgeCategory;
import com.softserveinc.orphanagemenu.model.Component;
import com.softserveinc.orphanagemenu.model.ComponentWeight;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Dish;
import com.softserveinc.orphanagemenu.model.FactProductQuantity;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.model.Submenu;

@Service("dailyMenuReportBuilder")
@Transactional
public class DailyMenuReportServiceImpl implements DailyMenuReportService{

	private final static int FIRST_JUNIOR_AGE_CATEGORY = 0;
	private final static int SECOND_JUNIOR_AGE_CATEGORY = 1;
	private final static int FIRST_SENIOR_AGE_CATEGORY = 2;
	private final static int SECOND_SENIOR_AGE_CATEGORY = 3;
	
	@Autowired
	private DailyMenuDao dailyMenuDao;
	
	@Autowired
	private ConsumptionTypeDao consumptionTypeDao; 
	
	@Autowired
	private SubmenuDao submenuDao;
	
	@Autowired
	private AgeCategoryService ageCategoryService;
	
	@Autowired
	private FactProductQuantityDao factProductQuantityDao;
	
	public Map<Product, Double> buildOverallProductQuantities(Date date) {
		Map<Product, Double> overallProductQuantities = new HashMap<>();
		List<Object[]> matrix = dailyMenuDao.getMatrixSubmenuProductFactProductQuantity(date);
		for (Object[] entity : matrix){
			// Parse ResultSet Matrix
			Submenu submenu = (Submenu) entity[0];
			Product product = (Product) entity[1];
			FactProductQuantity factProductQuantity = (FactProductQuantity) entity[2];

			// Construct overallProductQuantities from Parsed ResultSet Matrix
			if (!overallProductQuantities.containsKey(product)) {
				overallProductQuantities.put(product, submenu.getChildQuantity() * factProductQuantity.getFactProductQuantity());
			} else {
				Double tempOverallProductQuantities = 
						overallProductQuantities.get(product);
				overallProductQuantities.put(product, 
						tempOverallProductQuantities 
						+ submenu.getChildQuantity() * factProductQuantity.getFactProductQuantity());
			}
		}
		return overallProductQuantities;
	}
	
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
	
	private ReportProductQuantitiesDto buildReportForAgeCategories(
			Date date,
			List<AgeCategory> ageCategories,
			String subtitleMessageCode){
		ReportProductQuantitiesDto report = new ReportProductQuantitiesDto();
		report.setDate(DateTimeFormat.forPattern("dd.MM.yyyy").print(new DateTime(date)));
		report.setYear(DateTimeFormat.forPattern("yyyy").print(new DateTime(date)));
		report.setSubtitle(subtitleMessageCode);
		report.setConsumptionTypeAgeCategoryChildQuantities(createConsumptionTypeAgeCategoryChildQuantities(date));
		report.setConsumptionTypes(dailyMenuDao.getConsumptionTypesForDailyMenu(date));
		report.setAgeCategories(ageCategories);
		report.setProducts(dailyMenuDao.getProductsForDailyMenu(date));
		report.setProductSums(createProductSums(date, ageCategories));
		List<ProductQuantitiesReportColumn> columns = createProductQuantitiesReportColumns(date, ageCategories);
		report.setColumns(columns);
		report.setConsumptionTypeDishQuantities(createConsumptionTypeDishQuantities(columns));
		
		Mapper mapper = new DozerBeanMapper();
		ReportProductQuantitiesDto reportDto = mapper.map(report, ReportProductQuantitiesDto.class);
		return reportDto;
	}

	private Map<Product, Map<AgeCategory, Double>> createProductSums(
			Date date,
			List<AgeCategory> ageCategories) {
	
		List<Object[]> matrix = dailyMenuDao.getMatrixProductAgeCategoryFactProductQuantity(date, ageCategories);
		Map<Product, Map<AgeCategory, Double>> productSums = new HashMap<>();
		for (Object[] entity : matrix){
			// Parse ResultSet Matrix
			Product product = (Product) entity[0];
			AgeCategory ageCategory = (AgeCategory) entity[1]; 
			FactProductQuantity factProductQuantity = (FactProductQuantity) entity[2];
		
			// Construct ProductSums from Parsed ResultSet Matrix
			if (!productSums.containsKey(product)) {
				productSums.put(product, new HashMap<AgeCategory, Double>());
			}
			if (!productSums.get(product).containsKey(ageCategory)){
				productSums.get(product).put(ageCategory, factProductQuantity.getFactProductQuantity());
			} else {
				Double tempSum = productSums.get(product).get(ageCategory);
				productSums.get(product).put(ageCategory, 
											tempSum + factProductQuantity.getFactProductQuantity());
			}
		}
		return productSums;
	}

	private Map<ConsumptionType, Map<AgeCategory, Integer>> createConsumptionTypeAgeCategoryChildQuantities(Date date) {
		DailyMenu dailyMenu = dailyMenuDao.getByDate(date);
		Map<ConsumptionType, Map<AgeCategory, Integer>> quantities = new HashMap<>();
		for (ConsumptionType consumptionType : consumptionTypeDao.getAll()) {
			Map<AgeCategory, Integer> ageCategoryChildQuantities = new HashMap<>();
			for (Submenu submenu : submenuDao.getSubmenuListByDailyMenuAndConsumptionTypeId(
							dailyMenu.getId(), consumptionType.getId())) {
				ageCategoryChildQuantities.put(submenu.getAgeCategory(), submenu.getChildQuantity());
			}
			quantities.put(consumptionType, ageCategoryChildQuantities);
		}
		return quantities;
	}
	
	private List<ProductQuantitiesReportColumn> createProductQuantitiesReportColumns(Date date, List<AgeCategory> ageCategories) {
		List<Object[]> matrix = dailyMenuDao.getMatrixConsumptionTypeDishProductAgeCategoryFactProductQuantity(date, ageCategories);
		Map<ProductQuantitiesReportColumn, Map <Product, Map <AgeCategory, Double>>> columnsMap = new HashMap<>();
		for (Object[] entity : matrix){
			// Parse ResultSet Matrix
			ConsumptionType consumptionType = (ConsumptionType) entity[0];  
			Dish dish = (Dish) entity[1];
			Product product = (Product) entity[2];
			AgeCategory ageCategory = (AgeCategory) entity[3]; 
			FactProductQuantity factProductQuantity = (FactProductQuantity) entity[4];

			// Construct ProductQuantitiesReportColumn from Parsed ResultSet Matrix
			ProductQuantitiesReportColumn column = new ProductQuantitiesReportColumn();
			column.setConsumptionType(consumptionType);
			column.setDish(dish);
			if (!columnsMap.containsKey(column)){
				columnsMap.put(column, new HashMap<Product, Map <AgeCategory, Double>>());
			}
			if (!columnsMap.get(column).containsKey(product)){
				columnsMap.get(column).put(product, new HashMap <AgeCategory, Double>());
			}
			columnsMap.get(column).get(product)
					.put(ageCategory, factProductQuantity.getFactProductQuantity());
		}
		List<ProductQuantitiesReportColumn> columns = new ArrayList<>();
		for (ProductQuantitiesReportColumn column : columnsMap.keySet()){
			column.setProductQuantities(columnsMap.get(column));
			columns.add(column);
		}
		Collections.sort(columns, new Comparator<ProductQuantitiesReportColumn>() {
			public int compare(ProductQuantitiesReportColumn first, ProductQuantitiesReportColumn second) {
				int result = first.getConsumptionType().getOrderby().compareTo(second.getConsumptionType().getOrderby());
				if (result != 0) {
					return result;
				} else {
					return first.getDish().getName().compareTo(second.getDish().getName());
				}
			}
		});
		return columns;
	}
	
	// Old inefficient implementation of createProductQuantitiesReportColumns(...)
	// with multiple "for" with multiple queries to DB
	private List<ProductQuantitiesReportColumn> createProductQuantitiesReportColumnsOld(Date date, List<AgeCategory> ageCategories) {
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
						for (Component component : dish.getComponents()) {
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
			quantities.put(consumptionType, quantities.get(consumptionType) + 1);
		}
		return quantities;
	}


}
