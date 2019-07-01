package com.tech_master.excer.test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.tech_master.excer.page.CartPage;
import com.tech_master.excer.page.HomePage;
import com.tech_master.excer.page.LoginPage;
import com.tech_master.excer.page.ProductDetailsPage;
import com.tech_master.excer.page.ResultPage;

import io.appium.java_client.android.AndroidDriver;


public class AmazonProductCheckoutExcer2 {
	AndroidDriver driver;
	LoginPage loginPageObj;
	HomePage homePageObj;
	ResultPage resultPageObj;
	ProductDetailsPage productDetailsPageObj;
	CartPage cartPageObj;
	
	
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
	@Parameters({ "username", "password", "productDetailForSearch" })
	public void testProductCheckout( String username, String password, String productDetailForSearch) throws Exception {
		
		int expectedCartCount = 1;
		
		// Initialize login page elements and methods
		loginPageObj = new LoginPage(driver);
		
		// Sign in using existing customer details
		loginPageObj.alreadyHaveAccount();
		loginPageObj.enterUsername(username);
		loginPageObj.enterPassword(password);
		Assert.assertTrue(loginPageObj.checkLoggedIn(), "Login Failed. Please check logs for more details");
    	System.out.println("Successfully logged into the application!");

		// Initialize home page elements and methods
		homePageObj = new HomePage(driver);
		// Get the initial cart count
		int initialCartValue = homePageObj.getCartCount();
		// If count greater than zero then failing the case for simplicity purpose. Because after adding product in later step validating cart count accuracy
	    if(initialCartValue>0){
		   //Assert.fail("For Simplicity purpose failing the case. Please clear cart manually for further execution. Current Cart count: " + initialCartValue);
		   System.out.println("Will uncomment fail");
	    }
	    System.out.println("Initial cart value: " + initialCartValue);
	    // Search the product with the search string mentioned
	    homePageObj.searchProduct(productDetailForSearch+"\n");
	   

	    // Initialize results page page elements and methods
	    resultPageObj = new ResultPage(driver);
	    // Click the first item in the search result and get the details of the product
	    String itemDetails = resultPageObj.clickFirstProductInTheResult();
	    System.out.println("Successfully clicked first product in the search result page!");
	    
	   
	    // Initialize product details page elements and methods
	    productDetailsPageObj = new ProductDetailsPage(driver);
	    productDetailsPageObj.addToCart();

	    
	    productDetailsPageObj.goToCart();
	    
	    
    	// Initialize cart page elements and methods
	    cartPageObj = new CartPage(driver);
	    Boolean itemInCartSame = cartPageObj.verifyItemAvailabilityInCartPage(itemDetails);
	    Assert.assertTrue(itemInCartSame, "Item in the cart is not same as added");
	    
	    int cartItemCount = cartPageObj.getCartItemCount();
	    Assert.assertEquals(cartItemCount, expectedCartCount, "Cart count mismatch. Please check");
	    
	    // After all verification proceeding to checkout
	    cartPageObj.proceedToCheckout();
	    
	    Thread.sleep(20000);
	}
	
	@AfterClass
	public void teardown(){
		driver.quit();
	}
}