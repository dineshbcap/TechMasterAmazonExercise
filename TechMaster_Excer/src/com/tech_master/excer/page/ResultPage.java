package com.tech_master.excer.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class ResultPage {
	
	AndroidDriver driver;

    @FindBy(id="in.amazon.mShop.android.shopping:id/item_title")
    By productResultLink;

    public ResultPage(AndroidDriver driver){
    	this.driver = driver;
    	//This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    
    public String clickFirstProductInTheResult() throws InterruptedException{
    	WebElement item = (WebElement) ((driver.findElements(By.id("in.amazon.mShop.android.shopping:id/item_title"))).get(0));
 	   
 	    String itemDetails = item.getText();
 	    System.out.println("Item Details: " + itemDetails);
 	   
 	    item.click();
 	   
 	    return itemDetails;
    }
    

}
