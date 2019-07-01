package com.tech_master.excer.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class ProductDetailsPage {
	
	AndroidDriver driver;
	GenericReusable genericReusableObj;
	
    @FindBy(id="add-to-cart-button")
    WebElement addToCartBtn;

    @FindBy(xpath="//*[starts-with(@text,'Cart')]")
    WebElement navigateCartBtn;
    
    public ProductDetailsPage(AndroidDriver driver){
    	this.driver = driver;
    	genericReusableObj = new GenericReusable(driver);
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }   
    
    public void addToCart(){
    	genericReusableObj.scrollToViewByVisibleText("Add to Cart");
    	addToCartBtn.click();
    }
    
    public void goToCart(){
    	navigateCartBtn.click();
    }
    
}
