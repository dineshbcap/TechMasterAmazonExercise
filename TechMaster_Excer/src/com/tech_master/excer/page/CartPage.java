package com.tech_master.excer.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.android.AndroidDriver;

public class CartPage {
	
	AndroidDriver driver;

    @FindBy(xpath="//View[starts-with(text(),'Cart Subtotal (')]")
    WebElement cartTotalLbl;

    @FindBy(name="Proceed to Buy")
    WebElement proceedToBuyBtn;    
    

    public CartPage(AndroidDriver driver){
    	this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }   
    
    public Boolean verifyItemAvailabilityInCartPage(String itemDetails){
    	String productItemDetails = (itemDetails.length()>64)?itemDetails.substring(0, 63)+"...":itemDetails;
 	    System.out.println(productItemDetails);
 	   
 	    String cartItemDetails = driver.findElement(By.xpath("//View[@text='" + productItemDetails + "']")).getText();
 	    System.out.println(cartItemDetails);
 	    return productItemDetails.equals(cartItemDetails);
    }
    
    public int getCartItemCount(){
 	   String totalItemInCartCheckout = cartTotalLbl.getText();
 	   return Integer.parseInt(totalItemInCartCheckout.split("(")[1].split(" ")[0]);
    }
    
    public void proceedToCheckout(){
    	proceedToBuyBtn.click();
    }

}
