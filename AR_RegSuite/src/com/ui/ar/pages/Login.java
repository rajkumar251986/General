package com.ui.ar.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ui.ar.util.WaitTool;

public class Login {
	private String baseUrl;
	 public WebDriver driver;
	 
	 public Login(WebDriver driver)
	 {
		 this.driver = driver;
	 }
	 /**
	  * This method is used to login with Kaplan Admin and Institutional admin credentials.
	  * @param userName
	  * @param password
	  * @return
	  */
	 public boolean adminLogin(String userName, String password)
	 {		 
		  driver.findElement(By.id("Username")).clear();
		  driver.findElement(By.id("Username")).sendKeys(userName); //strusername  "admin@kaplan.com"
		  
		  driver.findElement(By.id("Password")).clear();
		  driver.findElement(By.id("Password")).sendKeys(password); // "kaplan"
		  
		  driver.findElement(By.id("login_button")).click();
		  Boolean isAdminLoggedin = WaitTool.waitForTextPresent(driver, By.xpath("//div[@id='Main']/div/h3"), "Welcome to the Reporting Portal", 60);
		 return isAdminLoggedin;
	 }
}
