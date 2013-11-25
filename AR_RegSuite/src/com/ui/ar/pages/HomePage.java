package com.ui.ar.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public CreateAdmin navigateToCreateAdmin() {
		driver.findElement(By.linkText("Create Administrator")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return new CreateAdmin(driver);
	}
	
	public ListEditAdmin navigateToEditAdmin() {
		driver.findElement(By.linkText("List Administrator")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return new ListEditAdmin(driver);
	}
}
