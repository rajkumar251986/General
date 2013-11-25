package com.ui.ar.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ui.ar.util.WaitTool;

public class ListEditInstitution {

	public WebDriver driver;

	public ListEditInstitution(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyListInstitutionPageLoads() {
		driver.findElement(By.linkText("List Institution")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("editInstitution"), 60);
		return isPresent;
	}

	public boolean verifyEditInstitutionPageLoads() {
		driver.findElement(By.id("editInstitution")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("save_institution"), 60);
		return isPresent;

	}

	// Search Institution
	public ListEditInstitution searchInstitution() throws InterruptedException {
		boolean isFound = false;
		int rowCount = 0;
		WebElement table = driver.findElement(By.id("table1"));
		List<WebElement> allrows = table.findElements(By.xpath("//tbody/tr"));
		List<WebElement> invisbleRows = table.findElements(By
				.xpath("//tbody/tr[@style='display: none;']"));
		List<WebElement> allpages = driver.findElements(By
				.xpath("//div[@id='Extended']/div[2]/ul/li"));

		int visibleRows = allrows.size() - invisbleRows.size();
		System.out.println("Invisible rows :" + invisbleRows.size());
		System.out.println("Total pages :" + allpages.size());
		System.out.println("Total allrows :" + allrows.size());

		for (int i = 1; i <= allpages.size(); i++) {
			driver.findElement(
					By.xpath("//div[@id='Extended']/div[2]/ul/li/a[@id='" + (i)
							+ "']")).click();
			Thread.sleep(10000);
			System.out.println("VisibleRows: " + visibleRows);
			System.out.println("rowCount: " + rowCount);
			for (int row = rowCount; row < visibleRows; row++) {

				List<WebElement> colmnList = allrows.get(row).findElements(
						By.tagName("td"));
				System.out.println("Colmnlist size: " + colmnList.size());
				System.out.println("Elemnet: " + colmnList.get(0).getText());
				if (colmnList.get(0).getText().equals("TestInstitution")) {
					System.out.println("Found the element");
					colmnList.get(5).findElement(By.id("editInstitution"))
							.click();
					Thread.sleep(5000);
					isFound = true;
					break;
				}
				
			}
			if (isFound) {
				break;
			} else {
				rowCount += 20;
				visibleRows += 20;
			}
		}
		return this;
	}

	// Enter New Institution Name
	public ListEditInstitution enterInstitutionName() {
		String strInstitutionName = "TestInstitutionName22Nov";
		driver.findElement(By.id("institutionName")).clear();
		driver.findElement(By.id("institutionName")).sendKeys(
				strInstitutionName);
		return this;
	}

	// Enter New Description
	public ListEditInstitution enterDescription() {
		String strDescription = "TestDescription";
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys(strDescription);
		return this;
	}

	// Enter New Contact Name
	public ListEditInstitution enterContactName() {
		String strContactName = "TestContactName";
		driver.findElement(By.id("contactName")).clear();
		driver.findElement(By.id("contactName")).sendKeys(strContactName);
		return this;
	}

	// Enter New Phone Number
	public ListEditInstitution enterPhone() {
		String strPhone = "1234561231";
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys(strPhone);
		return this;
	}

	// Enter New Email Address
	public ListEditInstitution enterEmail() {
		String strEmailAddress = "testselinst@kaplan.com";
		driver.findElement(By.id("emailAddress")).clear();
		driver.findElement(By.id("emailAddress")).sendKeys(strEmailAddress);
		return this;
	}

	// Click on Save button
	public ListEditInstitution clickSave() {
		driver.findElement(By.id("save_institution")).click();

		try {
			Thread.sleep(2000);
			handleApplicationPopups(driver, "accept");
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		return this;
	}

	public void handleApplicationPopups(WebDriver driver, String action)
			throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		if (action.equalsIgnoreCase("accept")) {
			alert.accept();
		} else if (action.equalsIgnoreCase("cancel")) {
			alert.dismiss();
		}
		Thread.sleep(5000);
	}

	public ListEditInstitution editAndSaveInstitution() {
		enterInstitutionName();
		enterDescription();
		enterContactName();
		enterPhone();
		enterEmail();
		clickSave();
		return this;
	}

}
