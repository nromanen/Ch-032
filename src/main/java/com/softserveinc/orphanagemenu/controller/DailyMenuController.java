package com.softserveinc.orphanagemenu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserveinc.orphanagemenu.dto.DailyMenuDto;
import com.softserveinc.orphanagemenu.dto.DailyMenusPageElements;

import com.softserveinc.orphanagemenu.forms.SelectForm;

import com.softserveinc.orphanagemenu.dto.ProductNorms;

import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.service.AgeCategoryService;
import com.softserveinc.orphanagemenu.service.DailyMenuService;
import com.softserveinc.orphanagemenu.service.ProductService;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
@Controller
public class DailyMenuController {

	@Autowired
	@Qualifier("dailyMenuService")
	private DailyMenuService dailyMenuService;

	@Autowired
	private AgeCategoryService ageCategoryService;

	@Autowired
	private ProductService productService;

	/**
	 * @author Vladimir Perepeliuk
	 * @author Olexii Riabokon
	 */
	@RequestMapping({ "/", "/dailyMenus" })
	public String showDailyMenus(
			@RequestParam Map<String, String> requestParams,
			Map<String, Object> model, SelectForm selectForm, BindingResult result) {

		DateTime actualDateTime;

		if (requestParams.get("actualDate") == null
				|| "".equals(requestParams.get("actualDate"))) {

			actualDateTime = new DateTime();
		} else {
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yy");
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
		model.put("pageTitle", "dm.pageTitle");
		model.put("interfaceMessages", getInterfaceMessages());
		
		if(selectForm.getId()!=null){
		Long dailyMenuId = Long.parseLong(selectForm.getId());
		DailyMenu dm = dailyMenuService.getById(dailyMenuId);
		if(selectForm.getAccepted().equals("Затверджено")){
			boolean accept = true;
			dm.setAccepted(accept);
		}
		if(selectForm.getAccepted().equals("Не затверджено")){
			boolean accept = false;
			dm.setAccepted(accept);
		}
		dailyMenuService.updateDailyMenu(dm);
		}
		return "dailyMenus";
	}

	/**
	 * @author Vladimir Perepeliuk
	 * @author Olexii Riabokon
	 */
	@RequestMapping({ "/dailyMenuDelete" })
	public String testMenus(
			final RedirectAttributes redirectAttributes,
			@RequestParam("id") Long id,
			@RequestParam("actualDate") String date) {

		dailyMenuService.deleteByID(id);
		redirectAttributes.addFlashAttribute("infoMessage", "dm.deleteDailyMenuSuccessful");
		return "redirect:/dailyMenus?actualDate=" + date;
	}
	
	@RequestMapping (value="editMenu")
	public String editMenu (Map<String, Object> model)
	{
		return "editMenu";
	}
	

	@RequestMapping(value = "/dailyMenuUpdate")
	public String editDailyMenu(Map<String, Object> model,
			@RequestParam Map<String, String> requestParams, Model mdl, SelectForm selectForm, BindingResult result)
			throws ParseException {
		
		// DIMA PART 
		String param = requestParams.get("id");
		Long idi = Long.parseLong(param);
		Date date = dailyMenuService.getDateById(idi);
		DailyMenuDto dailyMenuDto = dailyMenuService.getDailyMenuDtoForDay(date);
		
		List<DailyMenuDto> dailyMenu = new ArrayList<DailyMenuDto>();
		List<String> acceptedList = new ArrayList<String>();
		acceptedList.add("Затверджено");
		acceptedList.add("Не затверджено");
		dailyMenu.add(dailyMenuDto);
		
		model.put("selectForm", selectForm);
		model.put("acceptedList", acceptedList);
		model.put("dailyMenu", dailyMenu);
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");

		// ANDRE PART
		
		List<ConsumptionType> consumptionTypes = dailyMenuService
				.getAllConsumptionType();
		String id = requestParams.get("id");
		Long i_d = Long.parseLong(id);
		System.out.println(i_d);
		
		model.put("ageCategoryList", ageCategoryService.getAllAgeCategory());
		List<ProductNorms> prodNormList = dailyMenuService
				.getProductWithStandartAndFactQuantityList(Long.parseLong(requestParams.get("id")) );

		model.put("norms", prodNormList);
		model.put("percent", 10);
		

		model.put("consumptionTypes", consumptionTypes);
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");


		dailyMenuService.getAllProductsWithQuantitiesForDailyMenu(Long.parseLong(requestParams.get("id")));

		return "dailyMenuUpdate";
	}

	public Set<String> getInterfaceMessages() {
		Set<String> messages = new HashSet<>();

		messages.add("yes");
		messages.add("no");
		messages.add("goNextConfirmation");
		return messages;
	}

}
