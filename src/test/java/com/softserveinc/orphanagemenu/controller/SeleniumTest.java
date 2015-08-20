package com.softserveinc.orphanagemenu.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) {

		// Create Chrome driver to drive the browser
		System.setProperty("webdriver.chrome.driver",
				"C://Users/skaraltc/git/Ch-032/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// Open OrphanageMenu editFactComponentQuantity page
		driver.get("http://localhost:8080/orphanagemenu/e");
		//Log in into system
//		driver.findElement(By.(""));
	}

}
