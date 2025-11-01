package com.qa.opencart.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;
	
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public void flash(WebElement element) {
		String bgColor= element.getCssValue("backgroundColor"); //blue
		for(int i =0;i<5;i++) {
			changeColor("rgb(0,200,0)",element);//green
			changeColor(bgColor, element); //blue
		}
	}

	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
		
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
	
		
	}

}
