package com.ui.ar.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.ui.ar.util.WaitTool;

public class CreateInstitution {

	public WebDriver driver;

	public CreateInstitution(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyCreateInstitutionPageLoads() {
		driver.findElement(By.linkText("Create Institution")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("save_institution"), 60);
		return isPresent;
	}
	
	public CreateInstitution enterInstitutionName() {
		String strInstitutionName = "TestInstitution22Nov13";
		driver.findElement(By.id("institutionName")).clear();
		driver.findElement(By.id("institutionName")).sendKeys(strInstitutionName);
		return this;
	}
	public CreateInstitution enterDescription() {
		String strDescription = "TestDescription22Nov13";
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys(strDescription);
		return this;
	}
	public CreateInstitution enterContactName() {
		String strContactName = "TestContactName22Nov13";
		driver.findElement(By.id("contactName")).clear();
		driver.findElement(By.id("contactName")).sendKeys(strContactName);
		return this;
	}
	
	public CreateInstitution enterPhone() {
		String strPhone = "1231231234";
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys(strPhone);
		return this;
	}
	
	public CreateInstitution enterEmail() {
		String strEmailAddress = "testemail21Nov@kaplan.com";
		driver.findElement(By.id("emailAddress")).clear();
		driver.findElement(By.id("emailAddress")).sendKeys(strEmailAddress);
		
		return this;
	}
	public CreateInstitution clickSave() {
		
		driver.findElement(By.id("save_institution")).click();
		return this;
	}
	
	public CreateInstitution createAndSaveInstitution()
	{
		enterInstitutionName();
		enterDescription();
		enterContactName();
		enterPhone();
		enterEmail();
		clickSave();
		return this;
	}
	
}
