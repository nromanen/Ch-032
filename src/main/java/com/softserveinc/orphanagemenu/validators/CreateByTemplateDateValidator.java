package com.softserveinc.orphanagemenu.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserveinc.orphanagemenu.service.DailyMenuService;

@Component
public class CreateByTemplateDateValidator {
	
	@Autowired
	private DailyMenuService dailyMenuService;
	
	public String validate(String newMenuDate) throws ParseException {
		if (!newMenuDate.matches(
				"^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$")) {
			return "validDateFalse";
		}
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date inputDate = format.parse(newMenuDate);
		if (dailyMenuService.exist(inputDate)) {
			return "true";
		}
		return null;
	}
}