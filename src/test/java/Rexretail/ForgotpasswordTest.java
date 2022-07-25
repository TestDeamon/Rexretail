package Rexretail;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.Base;

public class ForgotpasswordTest extends Base {
	public WebDriver driver;
	public Properties prop; 
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		prop = new Properties(); 

		String location = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		String driverLocation = System.getProperty("user.dir") + "\\src\\main\\java\\resources";
		FileInputStream fis = new FileInputStream(location);
       
		prop.load(fis);
		
		driver = initializeDriver();
		driver.manage().window().maximize();
		
		
		log.info("Browser launched and maximized");
		log.info("Go to " + prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		log.info(prop.getProperty("url").concat(" launched!"));
		
		//Universal Implicit Wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	


	@Test (priority=0)
	public void verifyUserAccessForgotPage() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver);
		
		//Click Login Menu 
		Loginpage loginP = ldp.getLoginMenu();
		
		
		Thread.sleep(2000);
		
		//Vertical scroll down by 600  pixels		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,200)");
		
		Thread.sleep(2000);
        //Click Forgot Password Link
		ForgotPasswordPage fpp = loginP.getForgotPasswordLink();
		
		Thread.sleep(2000);
		//Verify you can see forgot pin text
		Assert.assertEquals(fpp.getPageTitle().getText(), "Forgot Pin?");		
	}

	@Test(priority=1)
	public void userReturnsToLandingpageFromForgotPasswordPage() throws InterruptedException {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver); 
		Assert.assertTrue(fpp.brandLogo().isDisplayed());
		Thread.sleep(2000);
		Assert.assertEquals(fpp.getPageTitle().getText().trim(), "Forgot Pin?");
		
		
		Thread.sleep(2000);
		//Click Brand Logo 
		Landingpage ldp = fpp.getBrandLogo();
		
		Thread.sleep(2000);
		ldp.getFaqsMenu().isDisplayed(); 
		
	}
	@Test (priority=3)
	public void verifyForgotPassword() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver);
		
		//Click Login Menu 
		Loginpage loginP = ldp.getLoginMenu();
		
		
		Thread.sleep(2000);
		
		//Vertical scroll down by 600  pixels		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		Thread.sleep(2000);
        //Click Forgot Password Link
		ForgotPasswordPage fpp = loginP.getForgotPasswordLink();
		
		Thread.sleep(2000);
		
		//Verify you can see forgot pin text
		Assert.assertEquals(fpp.getPageTitle().getText(), "Forgot Pin?");
		
		//Enter phone no 
		fpp.getPhoneNoField().sendKeys(prop.getProperty("vUsername"));
		
		//Enter Password 
		fpp.getMerchantCodeField().sendKeys(prop.getProperty("vMerchantCode"));
		
		//Click Send Button 
		fpp.getSendButton().click();
		
		String mainWindow = driver.getWindowHandle();
	    System.out.println("Main window handle is " + mainWindow);
    
		if(driver.getCurrentUrl().equalsIgnoreCase("https://rexretail-dev.globalaccelerex.com:8080/reset-password")) {
		
			// Opens a new tab and switches to new tab
			driver.switchTo().newWindow(WindowType.TAB);
			
			driver.get("https://www.mailinator.com/"); 
			driver.findElement(By.id("search")).sendKeys("joyfulmerchant", Keys.ENTER); 
			
			//Open message
			List<WebElement> ls = driver.findElements(By.cssSelector("tbody tr.ng-scope:nth-child(1) td.ng-binding"));
			ls.get(0).click(); 
			

			//Switch to Iframe
			driver.switchTo().frame(0); 
			
			//Get OTP from mail
			String OTP = driver.findElement(By.cssSelector("tr td div div p:nth-child(3) > br:first-of-type + b")).getText();
			
			//Click reset link 
			driver.findElement(By.cssSelector("tr td div div p:nth-child(2) a")).click();		
			Thread.sleep(5000);
			
			//Switch to Default Content Mode
			driver.switchTo().defaultContent(); 
			
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> its = handles.iterator(); 
		    
			System.out.println("Handles: "+handles.size());
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			//IMPORT HASHTABLE PAGE
			HashtablePage htp = new HashtablePage(driver); 
			
			//STORE VARIABLE INTO HASH TABLE PAGE 
			htp.putElementInHashTable("firstWindow", its.next());
			htp.putElementInHashTable("secondWindow", its.next());
			htp.putElementInHashTable("thirdWindow", its.next());

			
			Thread.sleep(5000);
			
			//GET VARIABLE VALUE FROM HASHTABLE PAGE
			
			//Switch to Recover Password Page
			driver.switchTo().window(htp.getElementInHashTable("firstWindow"));

			//Wait for the new window or tab
			wait.until(ExpectedConditions.textToBe(By.cssSelector(".form_sub_header"), "Reset Your Pin"));
			
			Thread.sleep(10000);
			
			
			if(driver.findElement(By.cssSelector(".form_sub_header")).isDisplayed()) {
				//Enter OTP 
				driver.findElement(By.id("otp")).sendKeys(OTP);
				
				//Enter OTP 
				driver.findElement(By.id("newPassword")).sendKeys(prop.getProperty("newPin"));
							
				//Confirm OTP 
				driver.findElement(By.id("password_confirmation")).sendKeys(prop.getProperty("newPin"));
				
				//Click Reset PIN 
				driver.findElement(By.cssSelector(".form_button")).click();
		
				//Verify Password Reset was successful 
				Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://rexretail-dev.globalaccelerex.com/login")); 
			}
		}

	}
	
	@Test (priority=4)
	public void verifyCantSigninWithOldPassword() throws InterruptedException {
		//Switch back to First Handle 
		HashtablePage htp = new HashtablePage(driver); 
		driver.switchTo().window(htp.getElementInHashTable("firstWindow"));

				
		//Click Brand Logo on Forgot Password Page to Return to Landingpage  
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		Landingpage ldp = fpp.getBrandLogo();
		
		//Click Login Menu 
		Loginpage lgp = ldp.getLoginMenu();
		
		//Enter Username 
		lgp.getUsername().sendKeys(prop.getProperty("vUsername"));
		
		
		//Enter Merchant Code 
		lgp.getMerchantCode().sendKeys(prop.getProperty("vMerchantCode"));
		
		
		//Enter Old Password 
		lgp.getPassword().sendKeys(prop.getProperty("vPassword"));
		

		
		//Vertical scroll down by 600  pixels		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		
		//Click Login Button 
		lgp.getLoginButton().click();
		Thread.sleep(2000);
		
		//What you expect to see 
		
	}
	
	/*
	 * Verify Reset Password Match
	 *  
	 * */
	
	
	
	/*
	@Test (priority=3)
	public void verifyForgotPassword() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver);
		
		//Click Login Menu 
		Loginpage loginP = ldp.getLoginMenu();
		
		
		Thread.sleep(2000);
		
		//Vertical scroll down by 600  pixels		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		Thread.sleep(2000);
        //Click Forgot Password Link
		ForgotPasswordPage fpp = loginP.getForgotPasswordLink();
		
		Thread.sleep(2000);
		
		//Verify you can see forgot pin text
		Assert.assertEquals(fpp.getPageTitle().getText(), "Forgot Pin?");
		
		//Enter phone no 
		fpp.getPhoneNoField().sendKeys(prop.getProperty("vUsername"));
		
		//Enter Password 
		fpp.getMerchantCodeField().sendKeys(prop.getProperty("vMerchantCode"));
		
		//Click Send Button 
		fpp.getSendButton().click();
		
    
		if(driver.getCurrentUrl().equalsIgnoreCase("https://rexretail-dev.globalaccelerex.com:8080/reset-password")) {
			// Opens a new tab and switches to new tab
			driver.switchTo().newWindow(WindowType.TAB);
			
			driver.get("https://www.mailinator.com/"); 
			driver.findElement(By.id("search")).sendKeys("joyfulmerchant", Keys.ENTER); 
			
			//Open first message in Mailinator 
			driver.findElement(By.cssSelector("div.os-content table[class*='table-striped'] tbody tr:first-child")).click();
			
			
			//Open Reset your Password Link 
//			List<WebElement> ls = driver.findElements(By.cssSelector("tbody tr.ng-scope:nth-child(1) td.ng-binding"));
//			ls.get(0).click(); 
			
			
			//Switch to Iframe
			driver.switchTo().frame(0); 
			
			//Open Reset Password Link in Mail 
			driver.findElement(By.cssSelector("tbody tr p:nth-child(2) a")).click();
	
			
			//Get OTP 
			String OTP = driver.findElement(By.cssSelector("tr td div div p:nth-child(3) > br:first-of-type + b")).getText();
			
			//Click reset link 
			driver.findElement(By.cssSelector("tr td div div p:nth-child(2) a")).click();		
			
			//Switch to Default Content Mode
			driver.switchTo().defaultContent(); 
			
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> its = handles.iterator(); 

			String firstWindow = "";
			String secondWindow = "";
			String thirdWindow = "";
		    
			System.out.println("Handles: "+handles.size());
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			firstWindow = its.next(); 
			secondWindow = its.next(); 
			thirdWindow = its.next(); 

			System.out.println(driver.getCurrentUrl() + "Current Url");
			System.out.println(thirdWindow);
			
			Thread.sleep(5000);
			//Switch to Recover Password Page
			driver.switchTo().window(thirdWindow);

			System.out.println(driver.getCurrentUrl() + "New Url");
			//Wait for the new window or tab
			wait.until(ExpectedConditions.textToBe(By.cssSelector(".form_sub_header"), "Reset Your Pin"));
			
			if(driver.findElement(By.cssSelector(".form_sub_header")).isDisplayed()) {
				//Enter OTP 
				driver.findElement(By.id("otp")).sendKeys(OTP);
				
				//Enter OTP 
				driver.findElement(By.id("newPassword")).sendKeys(prop.getProperty("newPin"));
							
				//Confirm OTP 
				driver.findElement(By.id("password_confirmation")).sendKeys(prop.getProperty("newPin"));
				
				//Click Reset PIN 
				driver.findElement(By.cssSelector(".form_button")).click();
		
				//Verify Password Reset was successful 
				Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://rexretail-dev.globalaccelerex.com/login")); 
			}
		}
		
	}

	
	*/
	@AfterTest
	public void quit() {
		driver.quit();
	}
}
