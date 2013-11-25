package com.ui.ar.pages;



import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.ui.ar.util.WaitTool;

public class ListEditAdmin {

	public WebDriver driver;

	public ListEditAdmin(WebDriver driver) {
		this.driver = driver;
	}

	public ListEditAdmin enterFirstName() {
		String strFirstName = "Newkap12";
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys(strFirstName);
		return this;
	}

	public ListEditAdmin enterLastName() {
		String strLastName = "Newkap12";
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys(strLastName);
		return this;
	}
	
	public ListEditAdmin enterPassword() {
		String strPassword = "kaplan";
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(strPassword);
		return this;
	}
	
	public ListEditAdmin clickSave() {
		driver.findElement(By.id("save_administrator")).click();
		return this;
	}
	
    public boolean verifyListAdminPageLoads() {
		driver.findElement(By.linkText("List Administrator")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("editUser"), 60);
		return isPresent;
	}
	
	public boolean verifyEditAdminPageLoads()
	{
		driver.findElement(By.id("editUser")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("firstName"), 60);
		return isPresent;
		
	}
		
	public ListEditAdmin editAndSaveAdmin()
	{
		enterFirstName();
		enterLastName();
		enterPassword();
		clickSave();
		return this;
	}
	
	public ListEditAdmin searchAdmin() throws InterruptedException
	{
		boolean isFound = false;
		int rowCount = 0;
		WebElement table = driver.findElement(By.id("table1"));
		List<WebElement> allrows = table.findElements(By.xpath("//tbody/tr"));
		List<WebElement> invisbleRows = table.findElements(By.xpath("//tbody/tr[@style='display: none;']"));
		List<WebElement> allpages = driver.findElements(By.xpath("//div[@id='Extended']/div[2]/ul/li"));
		
		int visibleRows = allrows.size()-invisbleRows.size();
		System.out.println("Invisible rows :" +invisbleRows.size());
		System.out.println("Total pages :" +allpages.size());
		System.out.println("Total allrows :" +allrows.size());
		
		for(int i=1; i<=allpages.size();i++)
		{
			driver.findElement(By.xpath("//div[@id='Extended']/div[2]/ul/li/a[@id='"+(i)+"']")).click();
			Thread.sleep(10000);
			System.out.println("VisibleRows: "+visibleRows);
			System.out.println("rowCount: "+rowCount);
			for(int row=rowCount;row<visibleRows;row++)
			{
	
				List<WebElement> colmnList = allrows.get(row).findElements(By.tagName("td"));
				System.out.println("Colmnlist size: "+colmnList.size());
				System.out.println("Elemnet: "+colmnList.get(2).getText());
				if(colmnList.get(2).getText().equals("kap12@kaplan.com"))
				{
					System.out.println("Found the element");
					colmnList.get(5).findElement(By.id("editUser")).click();
					Thread.sleep(5000);
					isFound = true;
					break;
				}
			}
			if(isFound)
			{
				break;
			}
			else
			{
				rowCount+=20;
				visibleRows+=20;
			}	
		}
		return this;
	}
}

