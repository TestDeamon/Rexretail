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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.Base;

public class HomepageTest extends Base {
	public WebDriver driver;
	public Properties prop; 
	public static Logger log = LogManager.getLogger(Base.class.getName());
	WebElement firstName;
	WebElement lastName;
	WebElement fName;
	WebElement lName;
	
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
	public void verifyCantUpdateAdminInfoWithWrongOTP() throws InterruptedException {
		String vUsername = prop.getProperty("vUsername");
		String vMerchantCode = prop.getProperty("vMerchantCode"); 
		String validPassword = prop.getProperty("vPassword");
		
		
		String newFirstName = prop.getProperty("newFirstName");
		String newLastName = prop.getProperty("newLastName");
		
		
		Thread.sleep(5000);
		Landingpage ldp = new Landingpage(driver);
		//CLICK LOGIN MENU 
		Loginpage lgp = ldp.getLoginMenu();
		
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(vUsername);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(vMerchantCode);
		Thread.sleep(2000);
				
		//Enter Password 
		lgp.getPassword().sendKeys(validPassword);

		Thread.sleep(2000);
		
		//Scroll in order to login
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		//Click Login Button 
		Homepage hp = lgp.clickLoginButton();
		
		Thread.sleep(3000);
		//DELETE TOP SECTION 
//		Homepage hp = new Homepage(driver);
		//CONFIRM USER IS ON HOMEPAGE
		//Welcome back Element 
		WebElement welcomeText = driver.findElement(By.cssSelector(".text__default.text-dashboard-gray.sub-text"));
		Assert.assertEquals(welcomeText.getText().trim(), "Welcome back!");
		
		Thread.sleep(2000);
	
		//HOVER ON USERNAME/ADMIN
		Actions action = new Actions(driver);
		action.moveToElement(hp.getProfileDiv()).build().perform();
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		Thread.sleep(2000);

		//Wait 5 seconds until Profile Option shows up and click 
		//CLICK MY PROFILE 
		wait.until(ExpectedConditions.visibilityOf(hp.getProfile())).click();
		
		Thread.sleep(2000);
		
		//First Name Field 
		firstName = driver.findElement(By.cssSelector(".left-details .detail:nth-child(1) h6"));
		//Last Name Field 
		lastName = driver.findElement(By.cssSelector(".right-details .detail:nth-child(1) h6"));
		
	
		//Click Edit Profile Button 
		WebElement editBtn = driver.findElement(By.cssSelector(".btn.btn-primary.profile-btn"));
		editBtn.click();
				
		//STORE PAGE HANDLE 
		String mainPage = driver.getWindowHandle(); 
		
		//CLEAR AND SEND NEW FIRST NAME 
		fName = driver.findElement(By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-profile-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > app-admin:nth-child(2) > section:nth-child(2) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
		lName = driver.findElement(By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-profile-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > app-admin:nth-child(2) > section:nth-child(2) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
		fName.clear();
		fName.sendKeys(newFirstName);
		
		//CLICK SAVE CHANGES BUTTON
		WebElement save = driver.findElement(By.cssSelector(".btn.btn-primary.profile-btn.mt-5"));
		save.click();

				
		Thread.sleep(2000);
		
		//OTP FIELD ON REXRETAIL PAGE
		WebElement otpField = driver.findElement(By.cssSelector("input[class='field four ng-untouched ng-pristine ng-invalid']")); 
		otpField.sendKeys("123455"); 
		
		//VALIDATE BUTTON ON OTP MODAL
		WebElement validateBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary']")); 
		validateBtn.click(); 
	
		Thread.sleep(2000);
		
		//VALIDATE MODAL DOESN'T CLOSE MEANING WRONG OTP WAS RENDERED
		Assert.assertTrue(validateBtn.isDisplayed());
	}
	
	@Test (priority=1)
	public void verifyCanCloseOTPModalWithX() throws InterruptedException {
		Homepage hp = new Homepage(driver);
		
		//TOP CLOSE (X)
		WebElement x = driver.findElement(By.cssSelector("img[alt='cancel']"));
	
		//CLICK X 
		x.click();
	
		Thread.sleep(2000);
		
		//CONFIRM MODAL CLOSED
		WebElement saveBtn = driver.findElement(By.cssSelector(".btn.btn-primary.profile-btn.mt-5"));
		Assert.assertTrue(saveBtn.isDisplayed());		
	}
	
	@Test (priority=2)
	public void verifyCanCloseOTPModalWithCancelBtn() throws InterruptedException {
		//CLICK SAVE CHANGES BUTTON 
		WebElement saveBtn = driver.findElement(By.cssSelector(".btn.btn-primary.profile-btn.mt-5"));
		saveBtn.click();
	
		Thread.sleep(2000);
		
		//CLICK CANCEL MODAL 
		WebElement cancelBtn = driver.findElement(By.cssSelector(".btn.btn-secondary.ng-star-inserted"));
		cancelBtn.click();
		
		Thread.sleep(2000);
		
		//CONFIRM MODAL CLOSED
		Assert.assertTrue(saveBtn.isDisplayed());	
		
		Thread.sleep(2000);
	}
	
	
	@Test (priority=3)
	public void verifyAdminInfoUpdate() throws InterruptedException {
		Homepage hp = new Homepage(driver);
		
		//CLICK BUSINESS INFO 
		driver.findElement(By.cssSelector("ul.tabs li:nth-child(2)")).click();
		Thread.sleep(2000);
		
		//CLICK ADMIN INFO
		driver.findElement(By.cssSelector("ul.tabs li:first-child")).click();
		
		Thread.sleep(2000);
		
		//Click Edit Profile Button 
		WebElement editBtn = driver.findElement(By.cssSelector(".btn.btn-primary.profile-btn"));
		editBtn.click();
				
		//STORE PAGE HANDLE 
		String mainPage = driver.getWindowHandle(); 
		
		//FETCH NEW FIRST & LAST NAME FROM DATA FILE 
		String newFirstName = prop.getProperty("newFirstName");
		String newLastName = prop.getProperty("newLastName");

		Thread.sleep(2000);
		//CLEAR AND SEND NEW FIRST NAME 
		WebElement fNameField = driver.findElement(By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-profile-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > app-admin:nth-child(2) > section:nth-child(2) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
		WebElement lNameField = driver.findElement(By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-profile-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > app-admin:nth-child(2) > section:nth-child(2) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));

		fNameField.clear();
		fNameField.sendKeys(newFirstName);
		lNameField.clear();
		lNameField.sendKeys(newLastName);
		
		//CLICK SAVE CHANGES BUTTON
		WebElement save = driver.findElement(By.cssSelector(".btn.btn-primary.profile-btn.mt-5"));
		save.click();
				
		// Opens a new tab and switches to new tab
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("https://www.mailinator.com/"); 
		Thread.sleep(3000);
		driver.findElement(By.id("search")).sendKeys("joyfulmerchant", Keys.ENTER); 
		
		Thread.sleep(2000);
		//Open message
		List<WebElement> ls = driver.findElements(By.cssSelector("tbody tr.ng-scope:nth-child(1) td.ng-binding"));
		ls.get(0).click(); 
		
		//Switch to Iframe
		driver.switchTo().frame(0); 
		
		//Get OTP from mail
		String OTP = driver.findElement(By.cssSelector("body div h1")).getText();
		
		
		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
		
		//SWITCH BACK TO REXRETAIL 
		driver.switchTo().window(mainPage); 
		
		Thread.sleep(2000);
		
		//OTP FIELD ON REXRETAIL PAGE
		WebElement otpField = driver.findElement(By.cssSelector("input[class='field four ng-untouched ng-pristine ng-invalid']")); 
		otpField.sendKeys(OTP); 
		
		//VALIDATE BUTTON ON OTP MODAL
		WebElement validateBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary']")); 
		validateBtn.click(); 
	
		Thread.sleep(2000);
		//CLICK BUSINESS INFO 
		driver.findElement(By.cssSelector("ul.tabs li:nth-child(2)")).click();
		Thread.sleep(2000);
		
		//CLICK ADMIN INFO
		driver.findElement(By.cssSelector("ul.tabs li:first-child")).click();
		
		Thread.sleep(2000);
		//CONFIRM FIRST AND LAST NAME CHANGED
		Assert.assertEquals(firstName.getText(), newFirstName);
		Assert.assertEquals(lastName.getText(), newLastName);
		
		Thread.sleep(2000);
	}
	
	
	@Test (priority=4)
	public void verifyLogout() throws InterruptedException {
		Homepage hp = new Homepage(driver);
		//CONFIRM USER IS ON HOMEPAGE
		//Welcome back Element 
		WebElement welcomeText = driver.findElement(By.cssSelector(".text__default.text-dashboard-gray.sub-text"));
		if(welcomeText.isDisplayed()) {
			System.out.println("TRUE");
			Thread.sleep(2000);
			
			//HOVER ON USERNAME/ADMIN
			Actions action = new Actions(driver);
			action.moveToElement(hp.getProfileDiv()).build().perform();
			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			Thread.sleep(2000);

			//Wait 5 seconds until Profile Option shows up and click 
			//CLICK LOGOUT 
			wait.until(ExpectedConditions.visibilityOf(hp.getLogout())).click();
			
			Thread.sleep(5000);
			
			Loginpage lp = new Loginpage(driver);
			
			//VERIFY LAND ON LOGIN PAGE
			Assert.assertEquals(lp.getPageTitle().getText(), "Login"); 
		} else {
			
			System.out.println("ELSE");
			//CLICK DASHBOARD MENU 
			hp.getDashMenu().click();
			
			Thread.sleep(2000);
			
			//HOVER ON USERNAME/ADMIN
			Actions action = new Actions(driver);
			action.moveToElement(hp.getProfileDiv()).build().perform();
			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			Thread.sleep(2000);

			//Wait 5 seconds until Profile Option shows up and click 
			//CLICK LOGOUT 
			wait.until(ExpectedConditions.visibilityOf(hp.getLogout())).click();
			
			Thread.sleep(5000);
			
			Loginpage lp = new Loginpage(driver);
			
			//VERIFY LAND ON LOGIN PAGE
			Assert.assertEquals(lp.getPageTitle().getText(), "Login"); 
		}
	}

	//UPDATE BUSINESS PROFILE TEST 
	
	//CHANGE PASSWORD TEST 
	

	@AfterTest
	public void quit() {
//		driver.quit();
	}
}
