package com.qa.opencart.tests;
import static com.qa.opencart.constants.AppConstants.*;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.CSVUtil;
import com.qa.opencart.utilities.ExcelUtil;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.Login(prop.getProperty("username"), prop.getProperty("pwd"));
	}

	//AAA- Arrange Act Assert(1)
	
	//Data Provider method should be created and it returns 2 dimensional object array. 
	//Data should be maintained inside the script of the test class and it is not hard coded.
	//it maintained as Data provider and can be used for the test. 
	//It is easy to maintain and read. because in excel you need to write the parser and logic 
	//and also need to use Apache POI API which is external third party and chances of the excel getting corrupted
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	
	
	@Test(dataProvider ="getProductTestData")
	public void productHeaderTest(String searchKey, String productName) {
		searchResPage =accPage.doSearch(searchKey);
		productInfoPage= searchResPage.selectProduct(productName);
		String actHeader= productInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, productName);
	}
	@DataProvider
	public Object[][] getProductImageTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro", "4"},
			{"macbook","MacBook Air", "4"},
			{"imac","iMac", "3"},
			{"samsung","Samsung SyncMaster 941BW","1"},
			{"samsung","Samsung Galaxy Tab 10.1","7"}
			
		};
	}
	//ExcelData
	@DataProvider
	public Object getProductImageData() {
		return ExcelUtil.getTestData(PRODUCT_SHEET_NAME);
	
	}
	
	//CSVData
		@DataProvider
		public Object[][] getProductCSVData() {
			return CSVUtil.csvData(PRODUCT_SHEET_NAME);
		
		}

	
	@Test(dataProvider ="getProductImageTestData")
	public void productImagesCount(String searchKey, String productName, String expectedImageCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage= searchResPage.selectProduct(productName);
		int actImgCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(actImgCount), expectedImageCount);
	}
	//Use SoftAssert when multiple assertions are to be validated:
	//Hard Assert will terminate once the assertion is failed while softAssert continues the execution
	//and says which assertions failed when AssertAll is used.
	//SoftAssert is  a class and need to create the object of the class.
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("macbook");
		productInfoPage= searchResPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetailsMap();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productDetailsMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
}
