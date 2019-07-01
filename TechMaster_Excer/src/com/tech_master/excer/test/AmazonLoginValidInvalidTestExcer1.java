package com.tech_master.excer.test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tech_master.excer.page.LoginPage;

import io.appium.java_client.android.AndroidDriver;


public class AmazonLoginValidInvalidTestExcer1 {
	AndroidDriver driver;
	LoginPage loginPageObj;
	
	@BeforeClass
	@Parameters({ "browser name", "device name", "platform", "host", "port" })
	public void setUp(String browserName, String deviceName, String platform, String host, String port) throws MalformedURLException{
		//Set desired capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", browserName);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", platform);
	 
		//Set the package and activity details of the app 
	    capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
	    capabilities.setCapability("appActivity","com.amazon.mShop.home.HomeActivity"); // This is Launcher activity of your app (you can get it from apk info app)
		
		//Initialize the driver
	    driver = new AndroidDriver(new URL("http://"+ host + ":" + port +"/wd/hub"), capabilities);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	@Parameters({ "username", "password"})
	public void testProductCheckout( String username, String password) throws Exception {
		
		// Initialize login page elements and methods
		loginPageObj = new LoginPage(driver);
		
		// Sign in using existing customer details
		loginPageObj.alreadyHaveAccount();
		loginPageObj.enterUsername(username);
		loginPageObj.enterPassword(password);
		Assert.assertTrue(loginPageObj.checkLoggedIn(), "Login Failed. Please check logs for more details");
    	System.out.println("Successfully logged into the application!");
	}
	
	@Test
	public void testBlankLoginValidation() throws Exception {
		
		// Initialize login page elements and methods
		loginPageObj = new LoginPage(driver);
		String expectedBlankLoginErrorMessage = "Enter your email or mobile phone number";
		
		loginPageObj.alreadyHaveAccount();
		
		loginPageObj.enterUsername("");
		String actualErrorMessage = loginPageObj.getBlankFieldErrorMessage();
		if(!(actualErrorMessage.equals(expectedBlankLoginErrorMessage))){
			Assert.fail("Invalid Blank login validation failed.. Expected Result: "+ expectedBlankLoginErrorMessage
					+  " ... But Actual Result is : " +actualErrorMessage );
		}
	    
	}

	@Test
	public void testInvalidLoginValidation() throws Exception {
		
		HashMap<String, String> invalidMobileNumbersList = new HashMap<String, String>();
		invalidMobileNumbersList.put("98941929671", "Incorrect phone number"); // Test mobile number with 11 digits
		invalidMobileNumbersList.put("98941", "Incorrect phone number"); // Test mobile number with less than 10 digits
		// Initialize login page elements and methods
		loginPageObj = new LoginPage(driver);
		
		// Locators, possible combinations and expected result suppose to get from excel and use generic one method. But due to time constrain could not complete
		loginPageObj.alreadyHaveAccount();
		
		for (Entry<String, String> invalidMobileNumberDetails : invalidMobileNumbersList.entrySet()) {
			loginPageObj.enterUsername(invalidMobileNumberDetails.getKey());
			String actualErrorMessage = loginPageObj.getInvalidLoginErrorMessage();
			if(!(actualErrorMessage.equals(invalidMobileNumberDetails.getValue()))){
				Assert.fail("Invalid login validation failed. Input Data: " + invalidMobileNumberDetails.getKey() + " ... And Expected Result: "+ invalidMobileNumberDetails.getValue()
						+  " ... But Actual Result is : " +actualErrorMessage );
			}
		}
	    
	}
	
	@AfterClass
	public void teardown(){
		//close the app
		driver.quit();
	}
}