package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

import io.qameta.allure.Step;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static  ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight;
	
	public static Logger log = LogManager.getLogger(DriverFactory.class);
	
	// log- info, warn, error and fatal
	
	
	/**
	 * this method is used to initialize the driver on the basis of browser name 
	 * @param browserName
	 * @return
	 */
	
	@Step("initialize the driver with properties: {0}")
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		//System.out.println("Browser name is :" +browserName);
		log.info("Browser name is :" +browserName);
		
		optionsManager = new OptionsManager(prop);
		
		highlight =prop.getProperty("highlight");
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//run on remote Selenium grid server/aws/machine
				initRemoteDriver("chrome");
			
			}
			else
				//run it on local
			{
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			}
			break;
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//run on remote Selenium grid server/aws/machine
				initRemoteDriver("firefox");
			
			}
			else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));	
			}
			
			break;
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//run on remote Selenium grid server/aws/machine
				initRemoteDriver("edge");
			
			}
			else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			
			break;
			
		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
		//	System.out.println("please pass the correct browser " +browserName);
			log.error("please pass the correct browser " +browserName);
			throw new BrowserException("====INVALID BROWSER=========");
			
		}
		
		getDriver().get(prop.getProperty("url"));;
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	/**
	 * Initalize remoteWebdriver
	 */
	//run it on remote grid
	private void initRemoteDriver(String browserName) {
		switch (browserName) {
		case "chrome":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
					e.printStackTrace();
			}
			break;
			
		case "edge":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
					e.printStackTrace();
			}
			break;
			
		case "firefox":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
					e.printStackTrace();
			}
			break;

		default:
			log.error("this browser is not supported on selenium Grid server :" +browserName);
			throw new BrowserException("====INVALID BROWSER=====");
			
		}
		
	}
	/**
	 * this method is used to initialize the config properties
	 * @return
	 */
	
	//mvn clean install -D(envvarible)env ="qa"
	public Properties initProp() {
		
		String envName= System.getProperty("env");
		FileInputStream ip = null;
		prop = new Properties();
		try {
			if(envName==null) {
			//	System.out.println("env is null, hence running by default on qa env");
				log.warn("env is null, hence running by default on qa env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}
		
		
		else {
			
		//	System.out.println(" Running tests on env " +envName);
			log.info(" Running tests on env " +envName);
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;

			default:
				throw new FrameWorkException("===INVALID ENV=====");
				
			}
			
		}
	}catch(FileNotFoundException e) {
		e.printStackTrace();
		
	}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	
	/**
	 * getDriver: get the local thread copy of the driver
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	
	}
	
	/**
	 * takescreenshot
	 * @return 
	 */
	
	public static File getScreenshotFile() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE); //temp direction and attach to chaintest report
		return srcFile;
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES); //temp direction and attach to chaintest report
		
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64); //temp direction and attach to chaintest report
		
	}
}