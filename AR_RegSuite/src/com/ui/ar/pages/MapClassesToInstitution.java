package com.ui.ar.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ui.ar.util.WaitTool;

public class MapClassesToInstitution {
	public WebDriver driver;

	public MapClassesToInstitution(WebDriver driver) {
		this.driver = driver;
	}

	public boolean verifyMapClassesToInstitutionPageLoads() {
		driver.findElement(By.linkText("Map Class to Institution")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("save_classtoinstitution"), 60);
		return isPresent;
	}

	public void mapClassToInstitution() {
		String classCode = null;
		try {
			if (isElementPresent(By.xpath("//div[@id='school_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='school_chzn']/a/span"))
						.click();
				// driver.findElement(By.id("school_chzn_o_25")).click();
				WebElement institution_element = driver.findElement(By
						.id("school_chzn"));
				List<WebElement> li_collection = institution_element
						.findElements(By.xpath("id('school_chzn')/div/ul/li"));

				System.out.println("Number of li Element = "
						+ li_collection.size());
				for (WebElement lielement : li_collection) {

					if (lielement.getText().equals("USMLE Step1 Institution")) {
						lielement.click();
						break;
					}
				}

				Thread.sleep(5000);

				WaitTool.waitForElementDisplay(driver, By.id("ClsTbl"), 30);
				List<WebElement> list = driver.findElement(By.id("ClsTbl"))
						.findElements(By.tagName("tr"));
				if (list.size() >= 2) {
					for (int i = 1; i < list.size(); i++) {
						List<WebElement> tdList = list.get(i).findElements(
								By.tagName("td"));
						classCode = tdList.get(0).getText();
						if (classCode.contains("NONE")) {
							Assert.fail("class code is not found");
						} else {
							// click on remove when class code is mapped.
							tdList.get(2).findElement(By.tagName("span")).click();
							Thread.sleep(2000);
							handleApplicationPopups(driver, "accept");
							break;
						}

					}
				}
				driver.findElement(By.name("ClassCode")).sendKeys(classCode);
				driver.findElement(By.id("save_classtoinstitution")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void handleApplicationPopups(WebDriver driver, String action) throws InterruptedException
	 {
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  wait.until(ExpectedConditions.alertIsPresent());
	  Alert alert = driver.switchTo().alert();
	  if(action.equalsIgnoreCase("accept"))
	  {
	   alert.accept();
	  }
	  else if(action.equalsIgnoreCase("cancel"))
	  {
	   alert.dismiss();
	  }
	  Thread.sleep(5000);
	 }

}
