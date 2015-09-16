package com.softserveinc.orphanagemenu.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.dto.AppProperties;
import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DailyMenusPageElements;
import com.softserveinc.orphanagemenu.dto.ProductWithLackAndNeededQuantityDto;
import com.softserveinc.orphanagemenu.forms.SelectForm;
import com.softserveinc.orphanagemenu.dto.NormstForAgeCategoryDto;
import com.softserveinc.orphanagemenu.json.DailyMenuJson;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Product;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DailyMenuReportService;
import com.softserveinc.orphanagemenu.service.DailyMenuService;
import com.softserveinc.orphanagemenu.service.ProductService;
import com.softserveinc.orphanagemenu.validators.CreateByTemplateDateValidator;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Controller
public class DailyMenuController {
	
	private static final Logger logger =  LogManager.getLogger(DailyMenuController.class);

	private static final String DD_MM_YYYY = "dd.MM.yyyy";

	private static final String PAGE_TITLE = "pageTitle";

	private static final String REDIRECT_DAILY_MENU_UPDATE = "redirect:dailyMenuUpdate";

	@Autowired
	private DailyMenuService dailyMenuService;

	@Autowired
	private AgeCategoryService ageCategoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private DailyMenuReportService dailyMenuReportService;
	
	@Autowired
	private CreateByTemplateDateValidator createByTemplateValidator;

	@RequestMapping({ "/", "/dailyMenus" })
	public String showDailyMenus(
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, SelectForm selectForm,
			BindingResult result) {
		DateTime actualDateTime;

		if (requestParams.get("actualDate") == null
				|| "".equals(requestParams.get("actualDate"))) {

			actualDateTime = new DateTime();
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(DD_MM_YYYY);
			actualDateTime = formatter.parseDateTime(requestParams
					.get("actualDate"));
		}
		DailyMenusPageElements dailyMenusPageElements = new DailyMenusPageElements(
				actualDateTime);
		model.put("pageElements", dailyMenusPageElements);

		List<DailyMenuDto> dailyMenuDtos = dailyMenuService
				.getDailyMenuDtoForWeek(actualDateTime.toDate());

		model.put("dailyMenuDtos", dailyMenuDtos);

		List<ConsumptionType> consumptionTypes = dailyMenuService
				.getAllConsumptionType();
		model.put("consumptionTypes", consumptionTypes);
		model.put(PAGE_TITLE, "dm.pageTitle");
		model.put("interfaceMessages", getInterfaceMessages());
		model.put("datapickerStrings", getDatapickerStrings());

		return "dailyMenus";
	}

	@RequestMapping(value = "/redirect")
	public String updateDailyMenuStatus(SelectForm selectForm,
			BindingResult result) {

		Long dailyMenuIde = Long.parseLong(selectForm.getId());
		DailyMenu daily = dailyMenuService.getById(dailyMenuIde);
		Boolean accept = Boolean.parseBoolean(selectForm.getAccepted());
		daily.setAccepted(accept);
		dailyMenuService.updateDailyMenu(daily);
		String redirectDate = selectForm.getDate();
		return "redirect:dailyMenus?actualDate=" + redirectDate;
	}

	@RequestMapping({ "/dailyMenuDelete" })
	public String testMenus(final RedirectAttributes redirectAttributes,
			@RequestParam("id") Long id, @RequestParam("actualDate") String date) {

		dailyMenuService.deleteByID(id);
		redirectAttributes.addFlashAttribute("infoMessage",
				"dm.deleteDailyMenuSuccessful");
		return "redirect:/dailyMenus?actualDate=" + date;
	}

	@RequestMapping(value = "/dailyMenuUpdate")
	public String editDailyMenu(Map<String, Object> model,
			@RequestParam("id") String id, SelectForm selectForm) {

		// DIMA PART

		Long menuId = Long.parseLong(id);
		Date date = dailyMenuService.getDateById(menuId);
		DailyMenuDto dailyMenuDto = dailyMenuService
				.getDailyMenuDtoForDay(date);
		List<DailyMenuDto> dailyMenu = new ArrayList<DailyMenuDto>();
		Boolean acceptMenu = dailyMenuService.getDailyMenuAccepted(menuId);
		dailyMenu.add(dailyMenuDto);

		model.put("acceptMenu", acceptMenu);
		model.put("selectForm", selectForm);
		model.put("dailyMenu", dailyMenu);
		model.put("id", id);
		model.put(PAGE_TITLE, "dm.edit");
		model.put("action", "save");
		model.put("canceled", "back");

		// ANDRE PART

		model.put("ageCategoryList", ageCategoryService.getAllAgeCategory());
		Map<Product, List<NormstForAgeCategoryDto>> productsWithNorms = dailyMenuService
				.getProductsWithNorms(menuId);
		model.put("norms", productsWithNorms);
		model.put("percent", AppProperties.DEVATION);

		List<ConsumptionType> consumptionTypes = dailyMenuService
				.getAllConsumptionType();
		model.put("consumptionTypes", consumptionTypes);

		// PASHA PART
		List<ProductWithLackAndNeededQuantityDto> listOfProductsWithLack = dailyMenuService
				.getAllProductNeededQuantityAndLack(menuId);
		model.put("listOfProductsWithLackAndNeeded", listOfProductsWithLack);
		return "dailyMenuUpdate";
	}

	public Set<String> getInterfaceMessages() {
		Set<String> interfaceMessages = new HashSet<>();

		interfaceMessages.add("yes");
		interfaceMessages.add("no");
		interfaceMessages.add("goNextConfirmation");
		interfaceMessages.add("rewriteByTemplateConfirmation");
		interfaceMessages.add("doYouWantToRewrite");
		return interfaceMessages;
	}

	@RequestMapping(value = "dailyMenuAdd")
	public String dailyMenuAdd(@RequestParam("date") String dateParam,
			Map<String, Object> model) throws ParseException {
		Date date;
		DateFormat format = new SimpleDateFormat(DD_MM_YYYY);
		date = format.parse(dateParam);
		Long id = dailyMenuService.create(date);
		model.put("id", id);
		return REDIRECT_DAILY_MENU_UPDATE;
	}

	@RequestMapping(value = "/dailyMenuPreview")
	public String dailyMenuPreview(Map<String, Object> model,
			@RequestParam("id") Long id) {
		DateTime reportDateTime = new DateTime(dailyMenuService.getById(id)
				.getDate());
		model.put("reports",
				dailyMenuReportService.buildReports(reportDateTime.toDate()));
		model.put("overallProductQuantities",
				dailyMenuReportService.buildOverallProductQuantities(reportDateTime.toDate()));
		return "dailyMenuPreview";
	}

	@RequestMapping(value = "/dailyMenuPrint")
	public String dailyMenuPrint(Map<String, Object> model,
			@RequestParam("id") Long id) {
		DateTime reportDateTime = new DateTime(dailyMenuService.getById(id)
				.getDate());
		model.put("reports",
				dailyMenuReportService.buildReports(reportDateTime.toDate()));
		model.put("overallProductQuantities",
				dailyMenuReportService.buildOverallProductQuantities(reportDateTime.toDate()));
		return "dailyMenuPrint";
	}

	@RequestMapping(value = "/dailyMenuСreateByTemplate", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String createByTemplate(
			@RequestBody DailyMenuJson dailyMenuJson, Map<String, Object> model)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date date = format.parse(dailyMenuJson.getData());
		Long id = dailyMenuJson.getDailyMenuId();
		String newDailyMenuId = dailyMenuService.createByTemplate(id, date)
				.toString();
		return newDailyMenuId;
	}

	@RequestMapping(value = "/dailyMenuExist", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String checkIfMenuExist(
			@RequestBody DailyMenuJson dailyMenuJson, Map<String, Object> model)
			throws ParseException {
		return createByTemplateValidator.validate(dailyMenuJson.getData());
	}

	@RequestMapping(value = "/printLackList")
	public String printLackList(Map<String, Object> model,
			@RequestParam("id") String id) {
		Long menuId = Long.parseLong(id);
		model.put("id", id);
		model.put("listOfProductsWithLackAndNeeded",dailyMenuService.getLackList(menuId));
		return "productListWithLack";
	}

	private Set<String> getDatapickerStrings() {
		Set<String> messages = new HashSet<>();
		messages.add("day1");
		messages.add("day2");
		messages.add("day3");
		messages.add("day4");
		messages.add("day5");
		messages.add("day6");
		messages.add("day7");
		messages.add("month1");
		messages.add("month2");
		messages.add("month3");
		messages.add("month4");
		messages.add("month5");
		messages.add("month6");
		messages.add("month7");
		messages.add("month8");
		messages.add("month9");
		messages.add("month10");
		messages.add("month11");
		messages.add("month12");
		return messages;
	}

}
