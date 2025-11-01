package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utilities.ElementUtil;
import com.qa.opencart.utilities.StringUtils;

public class RegisterPage {
	
	 private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confPassword = By.id("input-confirm");
	private final By subsribeYes = By.xpath("//label[@class='radio-inline'][1]/input[@type='radio']");
	private final By subsribeNo = By.xpath("//label[@class='radio-inline'][2]/input[@type='radio']");
	private final By agreeCheckBox = By.name("agree");
	private final By continueButton = By.xpath("//input[@value='Continue']");
	
	private final By successMesg = By.cssSelector("div#content h1");
	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");
	

	public RegisterPage(WebDriver driver) {
	this.driver = driver;
	eleUtil = new ElementUtil(driver);
	}
	
	public boolean userRegister(String firstName, String lastName, 
								 String telephone, String password, String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.dosendKeys(this.lastName, lastName);
		eleUtil.dosendKeys(this.email, StringUtils.getRandomEmailId());
		eleUtil.dosendKeys(this.telephone, telephone);
		eleUtil.dosendKeys(this.password, password);
		eleUtil.dosendKeys(this.confPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subsribeYes);
		}
		else {
			eleUtil.doClick(subsribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		if(eleUtil.waitForElementVisible(successMesg, DEFAULT_TIMEOUT).getText().contains(REGISTER_SUCCESS_MESG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else {
			return false;
		}
	}
	

}
