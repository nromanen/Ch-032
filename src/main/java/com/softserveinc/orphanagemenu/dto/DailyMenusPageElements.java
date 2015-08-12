package com.softserveinc.orphanagemenu.dto;

import java.util.Date;
import java.util.GregorianCalendar;

public class DailyMenusPageElements {

	private String currentDay;
	private String dayRange;
	
	private String prevWeekDay;
	private String nextWeekDay;
	private String prevMonthDay;
	private String nextMonthDay;
	
	public DailyMenusPageElements() {
	}
	
	public DailyMenusPageElements(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
//		calendar.get();
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
	
	

}
