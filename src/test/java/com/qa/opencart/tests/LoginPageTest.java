package com.qa.opencart.tests;

import org.testng.Assert;
import static com.qa.opencart.constants.AppConstants.*;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Feature("F 50: Open Cart-login feature")
@Epic("Epic 100: design pages for open cart application")
@Story("US 101: implement login page for open cart application")
public class LoginPageTest extends BaseTest{
	
	@Description("checking open cart login page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajesh")
	@Test(description="checking login page title")
	public void LoginPageTitleTest() {
		String actTitle =loginPage.getLoginPageTitle();
		ChainTestListener.log("page title is "+actTitle);
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}
	
	@Description("checking open cart login page url")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Rajesh")
	@Test(description="checking login page url")
	public void LoginPageUrlTest() {
		String actUrl =loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("checking open cart login page has forgot pwd link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Rajesh")
	@Test(description="forgotPasswordLinkExist")
	public void LoginPageForgotPasswordLinkTest() {
		
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Description("check user is able to login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Rajesh")
	@Test(priority = Short.MAX_VALUE, description="login with valid user credentials")
	public void LoginTest()  {
	accPage=loginPage.Login(prop.getProperty("username"), prop.getProperty("pwd"));
	Assert.assertEquals(accPage.getAccountPageTitle(), ACCOUNT_PAGE_TITLE);
		
	
	}
	
//	@Test(enabled = false)
//	public void forgotPassword()  {
//	accPage=loginPage.Login(prop.getProperty("username"), prop.getProperty("pwd"));
//	Assert.assertEquals(accPage.getAccountPageTitle(), ACCOUNT_PAGE_TITLE);
		
	
//	}
}
