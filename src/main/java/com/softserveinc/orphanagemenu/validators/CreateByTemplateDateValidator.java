package com.softserveinc.orphanagemenu.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		String dateToday = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
		Date dateTo = format.parse(dateToday);
		Date inputDate = format.parse(newMenuDate);
		if (inputDate.before(dateTo)){
			return "pastDate";
		}
		if (dailyMenuService.exist(inputDate)) {
			return "true";
		}
		return null;
	}
}