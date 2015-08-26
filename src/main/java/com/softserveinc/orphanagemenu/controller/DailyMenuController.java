package com.softserveinc.orphanagemenu.controller;

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
import com.softserveinc.orphanagemenu.dto.ProductWithLackAndNeededQuantityDto;
import com.softserveinc.orphanagemenu.forms.SelectForm;
import com.softserveinc.orphanagemenu.dto.ProductNormsAndFact;
import com.softserveinc.orphanagemenu.dto.NormAndFactForAgeCategoryDto;
import com.softserveinc.orphanagemenu.model.ConsumptionType;
import com.softserveinc.orphanagemenu.model.DailyMenu;
import com.softserveinc.orphanagemenu.model.Product;
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
			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
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

	@RequestMapping(value = "/redirect")
	   public String redirect(SelectForm selectForm, BindingResult result) {
		
		Long dailyMenuIde = Long.parseLong(selectForm.getId());
		DailyMenu daily = dailyMenuService.getById(dailyMenuIde);
		Boolean accept = Boolean.parseBoolean(selectForm.getAccepted());
		daily.setAccepted(accept);
		dailyMenuService.updateDailyMenu(daily);
		String redirectDate = selectForm.getDate();
	    return "redirect:dailyMenus?actualDate="+redirectDate;
	}

	@RequestMapping({ "/dailyMenuDelete" })
	public String testMenus(final RedirectAttributes redirectAttributes,
			@RequestParam("id") Long id, @RequestParam("actualDate") String date) {

		dailyMenuService.deleteByID(id);
		redirectAttributes.addFlashAttribute("infoMessage",
				"dm.deleteDailyMenuSuccessful");
		return "redirect:/dailyMenus?actualDate=" + date;
	}

	@RequestMapping(value = "editMenu")
	public String editMenu(Map<String, Object> model) {
		return "editMenu";
	}

	@RequestMapping(value = "/dailyMenuUpdate")
	public String editDailyMenu(Map<String, Object> model,
			@RequestParam("id") String id, Model mdl, SelectForm selectForm,
			BindingResult result) {

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
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");

		// ANDRE PART
		List<ConsumptionType> consumptionTypes = dailyMenuService
				.getAllConsumptionType();
		model.put("ageCategoryList", ageCategoryService.getAllAgeCategory());
		Map<Product, List<NormAndFactForAgeCategoryDto>> productsWithNorms = dailyMenuService
				.getProductsWithNorms(menuId);
		model.put("norms", productsWithNorms);
		model.put("percent", 10);
		model.put("consumptionTypes", consumptionTypes);
		model.put("pageTitle", "dm.edit");
		model.put("action", "save");
		model.put("canceled", "cancel");
		
	//PASHA PART
		List<ProductWithLackAndNeededQuantityDto> listOfProductsWithLack = dailyMenuService.getAllProductNeededQuantityAndLack(menuId);
		model.put("listOfProductsWithLackAndNeeded", listOfProductsWithLack);
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
