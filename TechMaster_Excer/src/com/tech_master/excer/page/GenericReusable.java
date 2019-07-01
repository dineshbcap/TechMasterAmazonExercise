package com.tech_master.excer.page;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class GenericReusable {
	
	AndroidDriver driver;
	
	public GenericReusable(AndroidDriver driver){
    	this.driver = driver;
    	//This initElements method will create all WebElements
	    PageFactory.initElements(driver, this);
    }   
	
	public void scrollToViewByVisibleText(String scrollToText){
		((AppiumDriver) driver).scrollToExact(scrollToText);
	}

}
