package com.ui.ar.setup;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import com.opera.core.systems.OperaDriver;

/**
 * This class is designed to create and load the drivers for Firefox, IE,
 * Chrome, Safari and Opera browsers
 * 
 * @author fl907 Anil Borse
 * 
 */
public class DriverSetup {

	private static Robot rbt = null;
	private static WebDriver driver;
	private static Actions builder = null;
	
	/**
	 * This method is used to setup standalone driver for different browsers and os combination.
	 * @param Browser
	 * @return
	 */
	public static WebDriver setUp(final String BROWSER) throws Exception {

		System.out.println("Start of DriverSetup->setUp");
		System.out.println("Browser name: " + BROWSER);
		
		if (BROWSER.equalsIgnoreCase("Firefox")) {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver(firefoxProfile);
			driver.manage().window().maximize();

		} else if (BROWSER.contentEquals("Internet Explorer")) {

			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capabilities.setCapability("ignoreProtectedModeSettings", true);
			File file = new File("C:\\Drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();

		} else if (BROWSER.contentEquals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (BROWSER.contentEquals("Safari")) {
			// Add safari driver extensions file into safari browser to load and open the browser(Latest
			// extension build is SafariDriver2.32.0)
			driver = new SafariDriver();
			driver.manage().window().maximize();
		} else if (BROWSER.equalsIgnoreCase("Opera")) {
			DesiredCapabilities capabilities = DesiredCapabilities.opera();
			capabilities.setCapability("opera.binary", "C:\\Drivers\\opera.exe");

			driver = new OperaDriver(capabilities);
			rbt.keyPress(KeyEvent.VK_ALT);
			rbt.keyPress(KeyEvent.VK_SPACE);
			rbt.keyRelease(KeyEvent.VK_ALT);
			rbt.keyRelease(KeyEvent.VK_SPACE);
			rbt.keyPress(KeyEvent.VK_X);
			rbt.keyRelease(KeyEvent.VK_X);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("End of DriverSetup->setUp");
		return driver;
	}

	public static Actions setupActions() {
		builder = new Actions(driver);
		return builder;
	}

	public static Robot setupRobot() throws AWTException {
		rbt = new Robot();
		return rbt;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Robot getRobot() {
		return rbt;
	}
}
