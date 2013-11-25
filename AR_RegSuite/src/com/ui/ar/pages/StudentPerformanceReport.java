package com.ui.ar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.ui.ar.util.WaitTool;

public class StudentPerformanceReport {
	public WebDriver driver;

	public StudentPerformanceReport(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean verifyStudPerfReportPageLoads() {
		driver.findElement(By.linkText("Student Performance")).click();
		boolean isPresent = WaitTool.waitForElementDisplay(driver,
				By.id("generate_report"), 60);
		return isPresent;
	}
	
	public StudentPerformanceReport verifyStudentPerformanceReport()
	{
		selectInstitution();
		selectProductLine();
		selectClass();
		selectTestType();
		selectAllstudents();
		selectReportType();
		selectReportDetails();
		selectTime();
		clickGenerate();
		return this;
	}
	
	//Select Institution 
	public StudentPerformanceReport selectInstitution()
	{
		try {
			if (isElementPresent(By.xpath("//div[@id='school_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='school_chzn']/a/span"))
						.click();
				driver.findElement(By.id("school_chzn_o_5")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	//Select Product Line
	public StudentPerformanceReport selectProductLine()
	{
		try {
			if (isElementPresent(By.xpath("//div[@id='productline_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='productline_chzn']/a/span"))
						.click();
				driver.findElement(By.id("productline_chzn_o_1")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
		
	}
	
	//Select Class
	public StudentPerformanceReport selectClass()
	{
		try {
			if (isElementPresent(By.xpath("//div[@id='cohort_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='cohort_chzn']/a/span"))
						.click();
				driver.findElement(By.id("cohort_chzn_o_1")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
		
	}
	
	//Select Test Type
	public StudentPerformanceReport selectTestType()
	{
		try {
			if (isElementPresent(By.xpath("//div[@id='testType_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='testType_chzn']/a/span"))
						.click();
				driver.findElement(By.id("testType_chzn_o_1")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	//Select Students
	public StudentPerformanceReport selectAllstudents()
	{/*
		try {
			if (isElementPresent(By.xpath("//input[@value='All Student']"))) {
				driver.findElement(By.xpath("//div[@id='student_chzn']/a/span"))
						.click();
				driver.findElement(By.id("testType_chzn_o_1")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return this;
	}
	
	//Select Report Type
	public StudentPerformanceReport selectReportType()
	{
		try {
			if (isElementPresent(By.xpath("//div[@id='reporttype_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='reporttype_chzn']/a/span"))
						.click();
				driver.findElement(By.id("reporttype_chzn_o_1")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	// Select Report Details
	public StudentPerformanceReport selectReportDetails()
	{
		//reportDetail_chzn
		return this;
		
	}
	
	// Select Time
	public StudentPerformanceReport selectTime()
	{

		try {
			if (isElementPresent(By.xpath("//div[@id='timeframe_chzn']/a/span"))) {
				driver.findElement(By.xpath("//div[@id='timeframe_chzn']/a/span"))
						.click();
				driver.findElement(By.id("timeframe_chzn_o_4")).click();
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	//Generate Report
	public StudentPerformanceReport clickGenerate()
	{
		driver.findElement(By.id("generate_report")).click();
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
