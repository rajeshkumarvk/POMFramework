package com.qa.opencart.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver= driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
public String waitforTitleIs(String title, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	try{
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}catch(TimeoutException e) {
		return null;
	}
	
}

public String waitforUrlContains(String fractionUrl, int timeOut) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	try{
		wait.until(ExpectedConditions.urlContains(fractionUrl));
		return driver.getCurrentUrl();
	}catch(TimeoutException e) {
		return null;
	}
	
}



public boolean IsElementDisplayed(By locator) {
	try{
		return getElement(locator).isDisplayed();
	}catch(NoSuchElementException e) {
		System.out.println("element is not present " +locator);
		return false;
	}
}


public void nullCheck(CharSequence... value) {
	if(value==null) {
		throw new RuntimeException("===Value cannot be null===");
	}
}
	public void dosendKeys(By locator, CharSequence...value ) {
	nullCheck(value);
	getElement(locator).clear();
	getElement(locator).sendKeys(value);
}

	@Step("entering value: {1} into element: {0}")
	public void dosendKeys(By locator, String value ) {
	nullCheck(value);
	getElement(locator).clear();
	getElement(locator).sendKeys(value);
}

	public void dosendKeys(String locatorType, String locatorValue, CharSequence... value ) {
	nullCheck(value);
	getElement(locatorType, locatorValue).clear();
	getElement(locatorType, locatorValue).sendKeys(value);
}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

private By getBy(String locatorType, String locatorValue) {
		// TODO Auto-generated method stub
		return null;
	}


public void clickWithWait(By locator, int timeOut) {
	waitForElementVisible(locator, timeOut).click();
	
}

	@Step("clicking on element using : {0}")
	public void doClick(By locator) {
	getElement(locator).click();
	
}
	@Step("waiting for element and clicking on it using : {0} and timeout: {1}")
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}
	
	public int getElementsCount(By locator) {
	int eleCount =getElements(locator).size();
	System.out.println("element count "+eleCount);
	return eleCount;
		
	}
	
	@Step("fetching the element text using : {0}")
	public String getElementText(By locator) {
		String eleText =getElement(locator).getText();
			System.out.println(eleText);
			return eleText;
			}
	
	@Step("finding the element using: {0}")
	public WebElement getElement(By locator) {
		
		WebElement element = driver.findElement(locator);
		ChainTestListener.log("locator : "+locator.toString());
		highlightElement(element);
		return element;
}
	
	
	public void highlightElement(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
	}
		
	}
	
	@Step("waiting for element using : {0} and timeout: {1}")
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(element);
		return element;

	}
	
	public List<WebElement> waitForAllElementsVisibility(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try{
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}catch(TimeoutException e) {
			return Collections.EMPTY_LIST; //0
		}
	}

public List<String> getElementsTextList(By locator) {
	List<WebElement>eleList =getElements(locator);
	List<String>eleTextList = new ArrayList<String>();
	for(WebElement e:eleList) {
		String text = e.getText();
		if(text.length()!=0) {
		System.out.println(text);
			eleTextList.add(text);
		}
		
	}
	return eleTextList;
}

public List<WebElement> getElements(By locator) {
	return driver.findElements(locator);
	
}
}
