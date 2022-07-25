package Rexretail;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.Base;

public class LoginpageTest extends Base {
	public WebDriver driver;
	public Properties prop; 
	public static Logger log = LogManager.getLogger(Base.class.getName());
	private String vUsername = prop.getProperty("vUsername");
	private String vMerchantCode = prop.getProperty("vMerchantCode"); 
	private String validPassword = prop.getProperty("validPassword");
	private String invalidMerchantCode = prop.getProperty("invalidMerchantCode"); 
	private String invalidPassword = prop.getProperty("invalidPassword"); 
	private String invalidUser = prop.getProperty("invalidUser"); 
	private String invalidCondition = prop.getProperty("invalidCondition");
	
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
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	


	@Test (priority=0)
	public void verifyLoginPageAccess() {
		Landingpage ldp = new Landingpage(driver); 
		//Click Login Menu 
		Loginpage loginP = ldp.getLoginMenu();
		
		//Verify Brand Login is Displayed 
		Assert.assertTrue(loginP.getBrandLogo().isDisplayed()); 
		
		//Confirm Page title 
		Assert.assertEquals(loginP.getPageTitle().getText(), "Login");
		
	}
	
	@Test (priority=1)
	public void verifyUserCannotLoginWithInValidPassword() throws InterruptedException {
		Loginpage lgp = new Loginpage(driver);
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(vUsername);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(vMerchantCode);
				
		//Enter Password 
		lgp.getPassword().sendKeys(invalidPassword);

		Thread.sleep(2000);
		//Click Login Button 
		lgp.getLoginButton().click();
		
		WebElement passwordErrorMsg = driver.findElement(By.cssSelector("span[class='flag-message ng-tns-c37-0 ng-star-inserted']"));
		
		Assert.assertTrue(passwordErrorMsg.isDisplayed());						
	}

	@Test (priority=2)
	public void verifyUserCannotLoginWithInValidMerchantCode() throws InterruptedException {
		Loginpage lgp = new Loginpage(driver);
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(vUsername);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(invalidMerchantCode);
				
		//Enter Password 
		lgp.getPassword().sendKeys(validPassword);

		Thread.sleep(2000);
		//Click Login Button 
		lgp.getLoginButton().click();
		
		WebElement passwordErrorMsg = driver.findElement(By.cssSelector("span[class='flag-message ng-tns-c37-0 ng-star-inserted']"));
		
		Assert.assertTrue(passwordErrorMsg.isDisplayed());						
	}

	@Test (priority=3)
	public void verifyUserCannotLoginWithInValidUserName() throws InterruptedException {
		Loginpage lgp = new Loginpage(driver);
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(invalidUser);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(vMerchantCode);
				
		//Enter Password 
		lgp.getPassword().sendKeys(validPassword);

		Thread.sleep(2000);
		//Click Login Button 
		lgp.getLoginButton().click();
		
		WebElement passwordErrorMsg = driver.findElement(By.cssSelector("span[class='flag-message ng-tns-c37-0 ng-star-inserted']"));
		
		Assert.assertTrue(passwordErrorMsg.isDisplayed());						
	}
	@Test (priority=4)
	public void verifyUserCannotLoginWithInValidDetails() throws InterruptedException {
		Loginpage lgp = new Loginpage(driver);
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(invalidUser);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(invalidMerchantCode);
				
		//Enter Password 
		lgp.getPassword().sendKeys(invalidPassword);

		Thread.sleep(2000);
		//Click Login Button 
		lgp.getLoginButton().click();
		
		WebElement passwordErrorMsg = driver.findElement(By.cssSelector("span[class='flag-message ng-tns-c37-0 ng-star-inserted']"));
		
		Assert.assertTrue(passwordErrorMsg.isDisplayed());						
	}
	
	@Test (priority=5)
	public void verifyUserLoginWithValidDetails() throws InterruptedException {
		Loginpage lgp = new Loginpage(driver);
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(vUsername);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(vMerchantCode);
	
		//Enter Password 
		lgp.getPassword().sendKeys(validPassword);

		Thread.sleep(2000);
		
		//Scroll in order to login
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		//Click Login Button 
		lgp.getLoginButton().click();
		
		WebElement dashboardMenu = driver.findElement(By.cssSelector(".sidenav-nav__nav-item.ng-star-inserted.active"));
		
		Assert.assertTrue(dashboardMenu.isDisplayed());
		
								
	}	
	

	@AfterTest
	public void quit() {
		driver.quit();
	}
}
