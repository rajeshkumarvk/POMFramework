package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

@Feature("F 60: Open Cart-Account feature")
@Epic("Epic 100: design pages for open cart application")
@Story("US 201: implement Accounts page for open cart application")
public class AccountsPageTest extends BaseTest{
	
	//BT--->BC
	
	@BeforeClass
	public void AccountsPageSteup() {
	accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("pwd"));
	}
	
	@Description("checking open cart Account page title")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajesh")
	@Test
	public void AccountsPageTitleTest() {
	Assert.assertEquals(accPage.getAccountPageTitle(), ACCOUNT_PAGE_TITLE);
			
	}

	@Description("checking open cart Account page url")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Rajesh")
	@Test
	public void AccountsPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl().contains(ACCOUNT_PAGE_FRACTION_URL));
	}
	
	@Description("checking open cart Account page Headers")
	@Severity(SeverityLevel.MINOR)
	@Owner("Rajesh")
	@Test
	public void AccountsPageHeadersTest() {
		List<String>actHeadersList =accPage.getAccountsPageHeaders();
		Assert.assertEquals(actHeadersList, expectedAccPageHeadersList);
	}
}
