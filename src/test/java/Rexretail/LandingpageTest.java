package Rexretail;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.Base;

public class LandingpageTest extends Base {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.manage().window().maximize();
		
		
		log.info("Browser launched and maximized");
		log.info("Go to " + prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		log.info(prop.getProperty("url").concat(" launched!"));

		//Universal Implicit Wait 
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@Test(priority=0)
	public void verifyBrandLogoPresent() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		Assert.assertTrue(ldp.getBrandLogo().isDisplayed()); 
	}
	
	@Test(priority=1)
	public void verifyPricingMenuPresent() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		Assert.assertTrue(ldp.getPricing().isDisplayed()); 
	}
	
	@Test(priority=2)
	public void verifyPricingMenuClickable() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		ldp.getPricing().click();
		
		//Verify I land on Pricing Section 
		String title = driver.findElement(By.cssSelector(".plans__header__title")).getText();
		Assert.assertEquals(title, "Plans for all business sizes");
 
	}
	
	@Test(priority=3)
	public void verifyFAQMenuPresent() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		Assert.assertTrue(ldp.getFaqsMenu().isDisplayed()); 
	}
	
	@Test(priority=4)
	public void verifyFAQMenuClickable() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		
		//Click FAQ
		FaqPage fqp = ldp.clickFaqsMenu();
		
		//Verify I land on FAQ Page
		Assert.assertTrue(fqp.getFaqTitle().isDisplayed()); 
	}
	
	@Test(priority=5)
	public void verifyReturnToLandingPageFromFAQPage() throws InterruptedException {
		//RETURN TO LANDINGPAGE 
		driver.navigate().back();
		
		//VERIFY LAND ON LANDING PAGE 
		Assert.assertTrue(driver.findElement(By.cssSelector("button[class='btn btn-primary no-margin']")).isDisplayed()); 
	}
	
	
	@Test(priority=6)
	public void verifySignUpButtonPresent() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		Assert.assertTrue(ldp.getSignupMenuElement().isDisplayed()); 
	}
	@Test(priority=7)
	public void verifySignUpButtonClickable() throws InterruptedException {
		Landingpage ldp = new Landingpage(driver); 
		
		//CLICK SIGNUP BUTTON 
		ldp.getSignupMenuElement().click();
	
		//VERIFY LAND ON SIGNUP 
		String currentUrl = driver.getCurrentUrl(); 
		if(currentUrl.equalsIgnoreCase("https://rexretail-dev.globalaccelerex.com:8080/onboarding")) {
			Assert.assertTrue(driver.findElement(By.cssSelector(".heading__3.text-bold.highlight.no-margin-b")).isDisplayed());
		}else {
			System.out.println("Failed to reach onboarding page!");
		}
	}
	
	@Test(priority=8)
	public void verifyReturnToLandingpageFromSignupPage() throws InterruptedException {
		driver.navigate().back();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://rexretail-dev.globalaccelerex.com:8080/");
		
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}
}
