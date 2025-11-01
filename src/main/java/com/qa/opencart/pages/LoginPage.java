package com.qa.opencart.pages;

import org.openqa.selenium.By;
import static com.qa.opencart.constants.AppConstants.*;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private locators(By locators) of the page
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By forgotpasswordlink = By.linkText("Forgotten Password11");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By registerLink = By.linkText("Register");
	
	//2. public constructor of the class.(To provide the WebDriver driver values)
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	//3. public Actions/methods
	
	@Step("getting login page title")
	public String getLoginPageTitle() {
	String title=eleUtil.waitforTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("login page title is : "+title);
		
		return title;
	}

	@Step("getting login page url")
	public String getLoginPageUrl() {
	String url = eleUtil.waitforUrlContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("login page url is : "+url);
		return url;
	}
	
	@Step("checking forgot password link exist")
	public boolean isForgotPasswordLinkExist() {
		return eleUtil.IsElementDisplayed(forgotpasswordlink);
		
	}
	
	@Step("login with valid username: {0} and password: {1}")
	public AccountsPage Login(String username, String pwd) {
		System.out.println("user credentials  :" +username+ ":" +pwd);
		eleUtil.waitForElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.clickWithWait(loginBtn, MEDIUM_DEFAULT_TIMEOUT);
	//after clicking on login button-->landing on Accountspage
	//method responsibility to return Accountspage class objects	
		return new AccountsPage(driver);
		
		
	}
	
	@Step("navigating to the registration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWithWait(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
	
}
