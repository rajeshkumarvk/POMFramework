package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

	public class SearchResultPage {
	WebDriver driver;
	ElementUtil eleUtil;
	
	private final By resultsProducts = By.cssSelector("div.product-thumb");

	public SearchResultPage(WebDriver driver) {
	this.driver= driver;
	eleUtil = new ElementUtil(driver);
	}
	
	@Step("getting the product count on the searchresult page ")
	public int getResultsProductCount() {
		int searchCount =eleUtil.waitForAllElementsVisibility(resultsProducts, MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("total number of search products: " +searchCount);
		return searchCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("product name is : "+productName);
		eleUtil.clickWithWait(By.linkText(productName), MEDIUM_DEFAULT_TIMEOUT);
		return new ProductInfoPage(driver);
	}

}
