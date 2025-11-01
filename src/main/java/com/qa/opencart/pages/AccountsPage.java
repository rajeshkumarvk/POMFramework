package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

import com.qa.opencart.utilities.ElementUtil;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.qameta.allure.Step;

public class AccountsPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	
	private final By header = By.cssSelector("div#content >h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		}

	@Step("getting Account page title")
	public String getAccountPageTitle() {
	String title = eleUtil.waitforTitleIs(ACCOUNT_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("account page title is : " +title);
		return title;
	}
	
	@Step("getting Account page url")
	public String getAccountPageUrl() {
	String url = eleUtil.waitforUrlContains(ACCOUNT_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("account page url is : " +url);
		return url;
	}
	
	@Step("getting Account page headers")
	public List<String> getAccountsPageHeaders() {
		return  eleUtil.getElementsTextList(header);
		
	}
	
	@Step("perform search: {0}")
	public SearchResultPage doSearch(String searchkey) {
		
		System.out.println("search key " +searchkey);
		eleUtil.dosendKeys(search, searchkey);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}
	

}
