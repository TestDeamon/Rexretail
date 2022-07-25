package Rexretail;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import resources.Base;

public class OnboardingpageTest extends Base {
	public WebDriver driver;
	public Properties prop; 
	public static Logger log = LogManager.getLogger(Base.class.getName());
	int duration;

	
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
		Onboardingpage op = new Onboardingpage(driver);
		op.waitForAngularRequestsToFinish();
	}

	@Test(priority=0)
	public void verifyMonthIncrease() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		
		Thread.sleep(2000);
		//CLICK SIGNUP BUTTON 
		Onboardingpage op = ldp.getSignupMenu();
		
		Thread.sleep(2000);
		//CLICK MOBILE GET START BUTTON
		op.getMobileButton().click();

		Thread.sleep(2000);
		duration = 2; 
		if(duration==1) {
			String month = op.getMonth().getText();
			Assert.assertEquals(Integer.parseInt(month), 1);
			Thread.sleep(2000);			
		}else if(duration>1) {
			for(int i = 0; i<duration; i++) {
				Thread.sleep(3000);
				op.getIncreaseButton().click();
				Thread.sleep(1000);
			}
			String month = op.getMonth().getText();
			Assert.assertEquals(Integer.parseInt(month), duration+1);
		}else {
			System.out.println("Month doesn't increase");
		}

	}


	@Test(priority=1)
	public void verifyMonthDecrease() throws InterruptedException {
		Onboardingpage op = new Onboardingpage(driver); 

		//CONFIRM DECREASE 
		Thread.sleep(2000);
		for(int i = duration; i>0; i--) {
			Thread.sleep(2000);
			op.getDecreaseButton().click();
			Thread.sleep(1000);
			Assert.assertEquals(Integer.parseInt(op.getMonth().getText()), i);
			Thread.sleep(1000);
		}	
	}
	

	@Test(priority=2) 
	public void confirmPreviousBtnFromMobilePage() throws InterruptedException {
		Thread.sleep(2000);
		Onboardingpage op = new Onboardingpage(driver); 
		
		//CLICK PREVIOUS BUTTON 
		op.getPreviousButton().click();
		
		//CONFIRM LAND ON PREVIOUS PAGE 
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading__3.text-bold.highlight.no-margin-b")).getText().trim(), "Get You Started");
		
		Thread.sleep(2000);
	}
	@Test(priority=3) 
	public void verifySignInBtnOnMobilePageActive() throws InterruptedException {
		Thread.sleep(2000);
		//CLICK SIGNUP BUTTON 
		Onboardingpage op = new Onboardingpage(driver);
		
		Thread.sleep(2000);
		
		//CLICK MOBILE GET START BUTTON
		op.getMobileButton().click();
		
		Thread.sleep(2000);
		
		//CLICK SIGNIN BUTTON 
		op.getSigningButton().click();
		
		Thread.sleep(2000);
		
		Loginpage lp = new Loginpage(driver);
		
		//CONFIRM LAND ON SIGNIN PAGE
		Assert.assertEquals(lp.getPageTitle().getText(), "Login"); 
		Thread.sleep(2000);
	}
	
	@Test(priority=4) 
	public void verifyChangePlanBtnActive() throws InterruptedException {
		Loginpage lp = new Loginpage(driver);
		
		//CLICK SIGNUP LINK TO LAND ON ONBOARDING PAGE
		Thread.sleep(2000);
		lp.getSignupLink().click();
		
		Onboardingpage op = new Onboardingpage(driver); 
		
		//CLICK MOBILE GET START BUTTON
		op.getMobileButton().click();
		
		Thread.sleep(3000);
		
		//CLICK CHANGE PLAN LINK ON MOBILE PLAN PAGE
		op.getChangePlan().click();
		
		Thread.sleep(2000);
		
		
		//CONFIRM LAND ON ONBOARDING PAGE 
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text__default")).isDisplayed());
		
		Thread.sleep(2000);
	}
	
	@Test (priority=5)
	public void confirmBackBtnOnMobileOnboardingPage1() throws InterruptedException {		
		Onboardingpage op = new Onboardingpage(driver); 		
		Thread.sleep(2000);

		//CLICK MOBILE GET STARTED BUTTON
		op.getMobileButton().click();
		Thread.sleep(2000);

		//CLICK PROCEED BUTTON 
		op.getProceedButton().click();
		
		Thread.sleep(2000);

		//CLICK BACK BUTTON ON MOBILE ONBOARDING BUSINESS PAGE
		op.getBackButton().click();
		
		Thread.sleep(2000);
		
		//CONFIRM LAND ON PREVIOUS PAGE 
		Assert.assertTrue(op.getChangePlan().isDisplayed());
		
		Thread.sleep(2000);
	}

	@Test (dataProvider="getData", priority=6)
	public void onboardMobileUser(String bizName, String contactEmail, String taxNo, String bizAddress, String cacNo, String natureOfBusiness, String phoneNo, String accNo, String bank) throws InterruptedException {
		
		
		Landingpage ldp = new Landingpage(driver); 
		
		Thread.sleep(2000);
		//CLICK SIGNUP BUTTON 
		Onboardingpage op = ldp.getSignupMenu();
		
		Thread.sleep(2000);
		//CLICK MOBILE GET START BUTTON
		op.getMobileButton().click();

		Thread.sleep(3000);

		op.getProceedButton().click();
		
		
		
		
		
		
		
		
		
		//START HERE
//		Onboardingpage op = new Onboardingpage(driver); 		
		Thread.sleep(2000);

		//CLICK PROCEED BUTTON 
		
		//ENTER BUSINESS NAME
		op.getInputs().get(0).sendKeys(bizName);
		Thread.sleep(2000);

		//ENTER EMAIL ADDRESS
		op.getInputs().get(2).sendKeys(contactEmail);
		Thread.sleep(2000);
		
		//ENTER TAX NO 
		op.getTaxNo().sendKeys(taxNo);
		Thread.sleep(2000);
		
		//ENTER BUSINESS ADDRESS
		op.getBusAddress().sendKeys(bizAddress);
		Thread.sleep(2000);
		
		//ENTER CAC NO 
		op.getCacNo().sendKeys(cacNo);
		Thread.sleep(2000);
		
		//SELECT NATURE OF BUSINESS 
		op.selectNatureOfBusiness(natureOfBusiness);
		Thread.sleep(2000);
		
		//SELECT DIAL CODE 
		op.selectDialCode();
		Thread.sleep(2000);
		
		//ENTER PHONE NO 
		op.getPhoneNumber().sendKeys(phoneNo);
		Thread.sleep(2000);
		
		//SELECT CURRENCY
		op.selectCurrency();
		
		//SCROLL DOWN TO BANK INFO SECTION 
		WebElement bankInfoHeader = driver.findElement(By.cssSelector("div[class='bank-info'] h4"));		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", bankInfoHeader);
		Thread.sleep(2000);
		
		
		//ENTER ACCOUNT NO 
		op.getAccountNo().sendKeys(accNo);
		Thread.sleep(2000);
		
		//SELECT BANK NAME
		op.selectBankName(bank); 
		Thread.sleep(2000);	
		
		Assert.assertEquals(op.verifyLookupSuccessful(), "ALOLA DAVID FEMI"); 
		
		//CLICK VERIFY BUTTON 
		op.verifyAccountNo();
		Thread.sleep(2000);	
		
		
		/*SECOND PAGE*/
		//CHECK AGREE CHECKBOX
		op.agreeToTerms().click();
		Thread.sleep(2000);
		
		
		// Initialize and wait till element(link) became clickable - timeout in 10 seconds
		WebElement submitBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
		.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='terms-of-use__footer'] button[class='btn btn-primary']")));
		
		//CLICK SUBMIT BUTTON 
		op.getSubmitBtn().click();
		
		Thread.sleep(3000);
		/*PERSONAL DETAILS*/
		
		//ENTER FIRSTNAME 
		op.getFirstName().sendKeys(null);
		Thread.sleep(1000);
		
		//ENTER LAST NAME 
		op.getLastName().sendKeys(null);
		Thread.sleep(1000);
		
		//SELECT COUNTRY CODE 
		op.selectCountryCode();
		Thread.sleep(2000);
		
		//ENTER PHONE NO 
		op.getPage2PhoneNo().sendKeys(null);
		Thread.sleep(2000);
		
		//ENTER EMAIL ADDRESS 
		op.getcontactEmail();
		Thread.sleep(2000);

		//ENTER BVN NO 
		op.getPage2BVN().sendKeys(null);
		Thread.sleep(2000);

		//CLICK SAVE AND PROCEED BUTTON 
		op.saveAndProceed().click();
		Thread.sleep(2000);
		
		//CLICK GET STARTED BUTTON 
		op.getStart().click();;
		Thread.sleep(2000);
		
		
		//VERIFY USER LANDS ON LANDING PAGE
//		Landingpage ldp = new Landingpage(driver);
		Assert.assertTrue(ldp.getPricing().isDisplayed());
	}


	@DataProvider(name="getData")
	public Object[][] getData(){
		return new Object[][] {
			{"544 World", "544world@mailinator.com", "12345678901234", "Ikeja", "CN12345689", "Fashion", "7063213451", "0167571532", "058"}
		};
	}
	
	@AfterTest
	public void quit() {
		driver.quit();
	}
}
