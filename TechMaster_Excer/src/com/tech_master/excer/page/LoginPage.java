package com.tech_master.excer.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
	
	AndroidDriver driver;

    @FindBy(id="in.amazon.mShop.android.shopping:id/sign_in_button")
    WebElement alreadyHaveAccountBtn;

    @FindBy(id="ap_email_login")
    WebElement usernameTxt;    
    
    @FindBy(id="continue")
    WebElement continueBtn;
    
    @FindBy(id="ap_password")
    WebElement passwordTxt;
    
    @FindBy(id="signInSubmit")
    WebElement signinBtn;
    
    @FindBy(xpath="//*[starts-with(@text,'Incorrect phone number')]")
    WebElement mobileInvalidError;
    
    @FindBy(xpath="//*[starts-with(@text,'Enter your email or mobile phone number')]")
    WebElement blankFieldError;
    
    @FindBy(id="in.amazon.mShop.android.shopping:id/rs_search_src_text")
    WebElement productSearchTxt;
    
    /** 
     * 
     * @param driver
     */
    public LoginPage(AndroidDriver driver){
    	this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }   
    
    public void alreadyHaveAccount(){
    	alreadyHaveAccountBtn.click();
    }
   
    public void enterUsername(String userName){
    	usernameTxt.clear();
    	usernameTxt.sendKeys(userName);
    	continueBtn.click();
    }
    
    public void enterPassword(String password) throws InterruptedException{
    	passwordTxt.clear();
    	passwordTxt.sendKeys(password);
    	signinBtn.click();
    }
    
    public Boolean checkLoggedIn(){
    	return productSearchTxt.isDisplayed();
    }
    
    public String getInvalidLoginErrorMessage(){
    	return mobileInvalidError.getText();
    }
    
    public String getBlankFieldErrorMessage(){
    	return blankFieldError.getText();
    }
    

}
