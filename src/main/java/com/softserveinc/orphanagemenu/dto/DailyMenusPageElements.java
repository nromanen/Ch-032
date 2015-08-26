package com.softserveinc.orphanagemenu.dto;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Vladimir Perepeliuk
 * @author Olexii Riabokon
 */
public class DailyMenusPageElements {

	private String currentDay;
	private String dayRange;
	
	private String prevWeekDay;
	private String nextWeekDay;
	private String prevMonthDay;
	private String nextMonthDay;
	
	public DailyMenusPageElements() {
	}
	
	public DailyMenusPageElements(DateTime actualDateTime) {
		DateTime dateTime = new DateTime();
		DateTimeFormatter dateTimeFormatter = DateTimeFormat
				.forPattern("dd.MM.yyyy, EEEE")
				.withLocale(new Locale("uk"));
		currentDay = dateTimeFormatter.print(dateTime); 

		DateTime monday = actualDateTime.withDayOfWeek(1);
		DateTime sunday = actualDateTime.withDayOfWeek(7);
		
		dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
		dayRange = dateTimeFormatter.print(monday)
				+ " - "
				+ dateTimeFormatter.print(sunday);
		
		dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");
		DateTime prevWeekMonday = monday.minusWeeks(1);
		prevWeekDay = dateTimeFormatter.print(prevWeekMonday); 
		DateTime nextWeekMonday = monday.plusWeeks(1);
		nextWeekDay = dateTimeFormatter.print(nextWeekMonday);
		DateTime prevMonthMonday = monday.minusWeeks(4);
		prevMonthDay = dateTimeFormatter.print(prevMonthMonday);
		DateTime nextMonthMonday = monday.plusWeeks(4);
		nextMonthDay = dateTimeFormatter.print(nextMonthMonday);
	}

	public String getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}

	public String getDayRange() {
		return dayRange;
	}

	public void setDayRange(String dayRange) {
		this.dayRange = dayRange;
	}

	public String getPrevWeekDay() {
		return prevWeekDay;
	}

	public void setPrevWeekDay(String prevWeekDay) {
		this.prevWeekDay = prevWeekDay;
	}

	public String getNextWeekDay() {
		return nextWeekDay;
	}

	public void setNextWeekDay(String nextWeekDay) {
		this.nextWeekDay = nextWeekDay;
	}

	public String getPrevMonthDay() {
		return prevMonthDay;
	}

	public void setPrevMonthDay(String prevMonthDay) {
		this.prevMonthDay = prevMonthDay;
	}

	public String getNextMonthDay() {
		return nextMonthDay;
	}

	public void setNextMonthDay(String nextMonthDay) {
		this.nextMonthDay = nextMonthDay;
	}

	@Override
	public String toString() {
		return "DailyMenusPageElements [currentDay=" + currentDay
				+ ", dayRange=" + dayRange + ", prevWeekDay=" + prevWeekDay
				+ ", nextWeekDay=" + nextWeekDay + ", prevMonthDay="
				+ prevMonthDay + ", nextMonthDay=" + nextMonthDay + "]";
	}
	
	

}
