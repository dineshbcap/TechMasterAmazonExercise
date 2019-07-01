package com.tech_master.excer.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class HomePage {
	
	AndroidDriver driver;

    @FindBy(id="in.amazon.mShop.android.shopping:id/action_bar_cart_count")
    WebElement initialCartCountLbl;

    @FindBy(id="in.amazon.mShop.android.shopping:id/rs_search_src_text")
    WebElement productSearchTxt;    
    
    public HomePage(AndroidDriver driver){
    	this.driver = driver;
    	//This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public int getCartCount(){
    	return Integer.parseInt(initialCartCountLbl.getText());
    }

    public void searchProduct(String productDetails) throws InterruptedException{
    	productSearchTxt.clear();
    	productSearchTxt.sendKeys(productDetails);
    }
    
}
