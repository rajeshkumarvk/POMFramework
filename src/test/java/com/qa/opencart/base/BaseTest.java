package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;

import io.qameta.allure.Description;



//@Listeners(ChainTestListener.class)

public class BaseTest {
	
	 WebDriver driver;
	 DriverFactory df;
	
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accPage;
	protected SearchResultPage searchResPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	@Description("initialize the driver and properties")
	@Parameters({"browser"})
	
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop= df.initProp();
		
		//browserName is coming from .xml file
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop); //call by reference //login  page
		loginPage = new LoginPage(driver);
		
	}
	
	@AfterMethod //this will be executed after every @test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) { //when the failed output is achieved
			ChainTestListener.embed(DriverFactory.getScreenshotBase64(), "image/png");
		}
	}
	
	@Description("closing the browser")
	@AfterTest
	public void tearDown() {
		if (driver != null)
		driver.quit();
		
	}

}
