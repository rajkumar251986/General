package com.ui.ar.pages;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ui.ar.util.WaitTool;

public class CreateAdmin {

	public WebDriver driver;

	public CreateAdmin(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyCreateAdminPageLoads() {
		driver.findElement(By.linkText("Create Administrator")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("adminLevel_chzn"), 60);
		return isPresent;
	}

	public CreateAdmin enterFirstName() {

		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("TestFirstName");
		return this;
	}

	public CreateAdmin enterLastName() {
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("TestLastName");
		return this;
	}

	public CreateAdmin enterEmail() {

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("sphadnis@kaplan.com");

		return this;
	}

	public CreateAdmin enterPassword() {
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("kaplan");
		return this;
	}

	public CreateAdmin selectKaplanAdminLevel() {

		String strAdminLevel = "Kaplan Admin";

		try {
			if (isElementPresent(By
					.xpath("//div[@id='adminLevel_chzn']/a/span"))) {
				driver.findElement(
						By.xpath("//div[@id='adminLevel_chzn']/a/span"))
						.click();
				// driver.findElement(By.id("adminLevel_chzn_o_1")).click();

				WebElement institution_element = driver.findElement(By
						.id("adminLevel_chzn"));
				List<WebElement> li_collection = institution_element
						.findElements(By
								.xpath("id('adminLevel_chzn')/div/ul/li"));

				System.out.println("Number of li Element = "
						+ li_collection.size());
				for (WebElement lielement : li_collection) {

					if (lielement.getText().equals(strAdminLevel)) {
						lielement.click();
						break;
					}
				}
			}
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	public CreateAdmin selectKaplanInstLevel() {
		String strInstitutionLevel = "Institutional Admin";
		try {
			if (isElementPresent(By
					.xpath("//div[@id='adminLevel_chzn']/a/span"))) {
				driver.findElement(
						By.xpath("//div[@id='adminLevel_chzn']/a/span"))
						.click();
				// driver.findElement(By.id("adminLevel_chzn_o_2")).click();
				WebElement institution_element = driver.findElement(By
						.id("adminLevel_chzn"));
				List<WebElement> li_collection = institution_element
						.findElements(By
								.xpath("id('adminLevel_chzn')/div/ul/li"));

				System.out.println("Number of li Element = "
						+ li_collection.size());
				for (WebElement lielement : li_collection) {

					if (lielement.getText().equals(strInstitutionLevel)) {
						lielement.click();
						break;
					}
				}

			}
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	public CreateAdmin selectInstitution() {
		String strInstitution = "USMLE Step1 Institution";
		try {
			if (isElementPresent(By.xpath("//div[@id='school_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='school_chzn']/a/span"))
						.click();
				// driver.findElement(By.id("school_chzn_o_1")).click();
				WebElement institution_element = driver.findElement(By
						.id("school_chzn"));
				List<WebElement> li_collection = institution_element
						.findElements(By.xpath("id('school_chzn')/div/ul/li"));

				System.out.println("Number of li Element = "
						+ li_collection.size());
				for (WebElement lielement : li_collection) {

					if (lielement.getText().equals(strInstitution)) {
						lielement.click();
						break;
					}
				}

			}
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public CreateAdmin clickbtnSave() {
		driver.findElement(By.id("save_administrator")).click();
		return this;
	}

	public CreateAdmin createAndSaveKaplanAdmin() {
		enterFirstName();
		enterLastName();
		enterEmail();
		enterPassword();
		selectKaplanAdminLevel();
		clickbtnSave();
		return this;
	}

	public CreateAdmin createAndSaveKaplanInstitution() {
		enterFirstName();
		enterLastName();
		enterEmail();
		enterPassword();
		selectKaplanInstLevel();
		selectInstitution();
		clickbtnSave();
		return this;
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
