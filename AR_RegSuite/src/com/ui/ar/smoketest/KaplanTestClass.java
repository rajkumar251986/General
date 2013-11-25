package com.ui.ar.smoketest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ui.ar.pages.CreateAdmin;
import com.ui.ar.pages.CreateInstitution;
import com.ui.ar.pages.ListEditAdmin;
import com.ui.ar.pages.ListEditInstitution;
import com.ui.ar.pages.Login;
import com.ui.ar.pages.MapClassesToInstitution;
import com.ui.ar.pages.StudentPerformanceReport;
import com.ui.ar.setup.DriverSetup;
import com.ui.ar.util.WaitTool;

public class KaplanTestClass {

	private WebDriver driver;
	private String baseUrl = "http://olpreporting-qa.kaplan.com/";
	public Login login;
	

	/**
	 * This method creates and loads the browser and open the AR portal.
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		// Create and load the browser driver
		driver = DriverSetup.setUp("Firefox");
		//open the application url
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//create an object of Login class
		login = new Login(driver);
	}

	/**
	 * This method is used to login with Institutional admin
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void testInstAdminLogin() throws Exception {

		// Institutional admin login
		Boolean isInstAdminLoggedin = login.adminLogin("COMLEX@kaplan.com",
				"kaplan13");
		Assert.assertTrue(isInstAdminLoggedin, "Unable to kaplan admin login");
		Thread.sleep(2000);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(priority = 2)
	public void testKplanAdminLogin() throws Exception {
		// Kaplan admin login
		Boolean isKaplanLoggedin = login.adminLogin("kaplan@kaplan.com",
				"kaplan");
		Assert.assertTrue(isKaplanLoggedin, "Unable to kaplan admin login");
		Thread.sleep(2000);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test(priority = 3)
	public void createAdmin() throws Exception {
		// Login to kaplan admin portal
		commonAdminLogin();
		// Create admin
		CreateAdmin cAdmin = new CreateAdmin(driver);
		boolean isPresent = cAdmin.verifyCreateAdminPageLoads();
		Assert.assertTrue(isPresent, "Create Admin page is not loaded");
		Thread.sleep(2000);
		
		// Enter All Values in all fields and Save 
		// Kaplan Admin 
		cAdmin.createAndSaveKaplanAdmin();
		Assert.assertTrue(true, "The Kaplan Admin was saved successfully.");
		Thread.sleep(3000);
		//Enter All Values in all fields and Save 
		
		// Kaplan Institution 
		cAdmin.createAndSaveKaplanInstitution();
		Assert.assertTrue(true, "The Institution Admin was saved successfully.");
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 4)
	public void listEditAdmin() throws Exception
	{
		// Login to kaplan admin portal
		commonAdminLogin();
		
		// List Admin
		ListEditAdmin listeditAdmin = new ListEditAdmin(driver);
		boolean isPresent = listeditAdmin.verifyListAdminPageLoads();
		Assert.assertTrue(isPresent, "List Admin page is not loaded");
		Thread.sleep(2000);
		
		//Search Kaplan Admin and Edit
		listeditAdmin.searchAdmin();
		Thread.sleep(3000);
		
		//Edit Admin 
		/*boolean isEditPresent = listeditAdmin.verifyEditAdminPageLoads();
		Assert.assertTrue(isEditPresent, "Edit Admin page is not loaded");
		Thread.sleep(2000);*/
		
		
		// Enter new values in all fields
		listeditAdmin.editAndSaveAdmin();
		Assert.assertTrue(true, "The user was updated successfully.");
		Thread.sleep(2000);
		
		
	}
	
	@Test(priority = 5)
	public void createInstitution() throws Exception {
		// Login to kaplan admin portal
		commonAdminLogin();

		// Create Institution
		CreateInstitution cInstitution = new CreateInstitution(driver);
		boolean isPresent = cInstitution.verifyCreateInstitutionPageLoads();
		Assert.assertTrue(isPresent, "Create Institution page is not loaded");
		Thread.sleep(2000);
		
		cInstitution.createAndSaveInstitution();
		Assert.assertTrue(true,"The Institution was saved successfully.");
		Thread.sleep(2000);
	}
	
	@Test(priority = 6)
	public void listEditInstitution() throws Exception
	{
		// Login to kaplan admin portal
		commonAdminLogin();
		
		// List Institution
		ListEditInstitution listeditInst = new ListEditInstitution(driver);
		boolean isPresent = listeditInst.verifyListInstitutionPageLoads();
		Assert.assertTrue(isPresent, "List Institution page is not loaded");
		Thread.sleep(2000);
		
				
		//Search Institutions
		listeditInst.searchInstitution();
		Thread.sleep(3000);
		
		//Edit Institution
		/*boolean isEditInstPresent = listeditInst.verifyEditInstitutionPageLoads();
		Assert.assertTrue(isEditInstPresent, "Edit Institution page is not loaded");
		Thread.sleep(2000);*/
		
		//Enter New Values in all  fields and Save
		listeditInst.editAndSaveInstitution();
		Assert.assertTrue(true, "The institution was updated successfully.");
		Thread.sleep(2000);
	}
	
	@Test(priority = 7)
	public void mapClassesToInst() throws Exception {
		// Login to kaplan admin portal
		commonAdminLogin();

		// Verifying Map Classes To Institution Page Loads
		MapClassesToInstitution mapClsToInst = new MapClassesToInstitution(driver);
		boolean isPresent = mapClsToInst.verifyMapClassesToInstitutionPageLoads();
		Assert.assertTrue(isPresent, "Map Classes To Institution page is not loaded");
		Thread.sleep(2000);
		
		//Map Classes To Institution
		mapClsToInst.mapClassToInstitution();
		Thread.sleep(2000);
		
	}
	
	@Test(priority = 8)
	public void StudentPerformance() throws Exception {
		// Login to kaplan admin portal
		commonAdminLogin();

		//Student Performance 
		StudentPerformanceReport studReport = new StudentPerformanceReport(driver);
		boolean isPresent = studReport.verifyStudPerfReportPageLoads();
		Assert.assertTrue(isPresent, "Student Performance page is not loaded");
		Thread.sleep(2000);
		
		//Generate Reports
		studReport.verifyStudentPerformanceReport();
		WaitTool.waitForElement(driver, By.id("excel_report"), 60);
		Thread.sleep(2000);
	}
	
	

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
	
	/**
	 * This is common method to login kaplan admin portal
	 * @return
	 */
	private boolean commonAdminLogin()
	{
		// Kaplan admin login
		Boolean isKaplanLoggedin = login.adminLogin("kaplan@kaplan.com",
				"kaplan");
		return isKaplanLoggedin;
	}
}
