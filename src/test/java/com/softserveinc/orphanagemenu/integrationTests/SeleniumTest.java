package com.softserveinc.orphanagemenu.integrationTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;

public class SeleniumTest {
		  private WebDriver driver;
		  private String baseUrl;
		  private StringBuffer verificationErrors = new StringBuffer();

		  @Before
		  public void setUp() throws Exception {
//			  System.setProperty("webdriver.chrome.driver",
//						"/src/test/resources/chromedriver.exe");
//			  driver = new ChromeDriver();
			  
			driver = new FirefoxDriver();
		    
		    baseUrl = "http://localhost:8080/";
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  }

//		  @Test
//		  public void test125() throws Exception {
//		    driver.get(baseUrl + "orphanagemenu/");
//		    driver.findElement(By.xpath("//button[@type='submit']")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-forward")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-left")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-right")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-forward")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-forward")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-forward")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-forward")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-forward")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-triangle-left")).click();
//		    driver.findElement(By.cssSelector("a.glyphicon.glyphicon-edit")).click();
//		    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
//		  }

		  @After
		  public void tearDown() throws Exception {
		    driver.quit();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		  }
}