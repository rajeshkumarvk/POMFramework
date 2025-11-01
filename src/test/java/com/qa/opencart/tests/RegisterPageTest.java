package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utilities.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registerSetup() {
	
	registerPage=loginPage.navigateToRegisterPage();
	}
	
	//MSExcel -->xlsx:read data using apache POI
	
	@DataProvider
	public Object[][] getUserRegData() {
		Object regData =(Object) ExcelUtil.getTestData(REGISTER_SHEET_NAME);
		return (Object[][]) regData;
	}
	
	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object [][] {
			{"Rajesh", "kumar", "9897654312", "test@123", "yes"},
			{"Ravesh", "kumar", "9897654315", "test@123", "yes"},
			{"Rakesh", "kumar", "9897654314", "test@123", "yes"},
		};
	}

	@Test(dataProvider ="getUserRegData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
	Assert.assertTrue(registerPage.userRegister(firstName, lastName, telephone, password, subscribe));
	}
	
	
}
