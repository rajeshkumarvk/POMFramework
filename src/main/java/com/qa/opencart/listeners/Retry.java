package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private int count =0;
	private static int maxtry =3;
	
	
	public boolean retry(ITestResult iTestresult) {
		
		if(!iTestresult.isSuccess()) { //check if test result is not succeed
			if(count<maxtry) { //check if maxtry count is reached
				count++;  //increase the count by 1
				iTestresult.setStatus(ITestResult.FAILURE); //Mark test as failed
				return true; //Tells TestNG to re-run the test
			}
			else {
				iTestresult.setStatus(ITestResult.FAILURE); //Mark test failed when the maxtry count is reached
			}
		}
		else {
			iTestresult.setStatus(ITestResult.SUCCESS); //Mark pass when the test pass
		}
		return false;
		
	}
	
}
