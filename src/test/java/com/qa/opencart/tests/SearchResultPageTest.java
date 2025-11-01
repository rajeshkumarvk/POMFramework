package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class SearchResultPageTest extends BaseTest{
	
	@BeforeClass
	public void searchResultPageSetup() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("pwd"));
	}

	
	@DataProvider
	public Object[][] searchProductTestData() {
		return new Object[][] {
			{"macbook", "3"},
			
			
		};
	}

	@Description("checkin search feature test with product: {0} and count: {1}")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Rajesh")
	@Test(dataProvider ="searchProductTestData")
	public void searchTest(String searchkey, String expProductCount ) {
	searchResPage = accPage.doSearch(searchkey);
	int accResultCount =searchResPage.getResultsProductCount();
	Assert.assertEquals(String.valueOf(accResultCount), expProductCount);
		
	}
}
