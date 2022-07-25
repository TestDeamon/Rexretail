package Rexretail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FaqPage {
	public WebDriver driver;
	
	public FaqPage(WebDriver driver) {
		this.driver = driver;
	}

	By faqTitle = By.cssSelector("div[class='faqs__article__scroll'] h2");
	By onboardingTab = By.cssSelector("[fragment='onboarding']");
	By featureTab = By.cssSelector("a[fragment='features']");
	By paymentTab = By.cssSelector("a[fragment='payments']");
	
	public WebElement getFaqTitle() {
		return driver.findElement(faqTitle);
	}
	public WebElement verifyUserSwitchToFeatureTab() {
		driver.findElement(featureTab).click();
		String featureTitle = driver.findElement(By.id("features")).getText(); 
		if(featureTitle.equalsIgnoreCase("Features")) {
//			log.info
		}
		return null;
	}
	
/*	
	By signup = By.cssSelector("div.header a:nth-child(4)");
		
	By start1 = By.cssSelector("button[class='btn btn-primary no-margin']");
	By demoBtn = By.cssSelector(".btn.no-margin.trans");
	By start2 = By.cssSelector("div[class='feature feature-management'] div a[class='feature__action']");
	By mobileStart = By.cssSelector("section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > button:nth-child(4)");
	By leaseStart = By.cssSelector(".subscription-options__btn.btn.btn-primary");
	By outrightStart = By.cssSelector("div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > button:nth-child(4)");
	By gogglePlayBtn = By.cssSelector("a[class='btn']");
//	By signup = By.cssSelector("div.header a:nth-child(4)");
	
	By aboutUs = By.cssSelector("a[href='https://www.globalaccelerex.com/about']"); 
	By blog = By.cssSelector("footer section:nth-child(2) ul li:nth-child(2) a");
	By ourServices = By.cssSelector("a[href$='com/services']");
	By ourSolutions = By.cssSelector("a[href$='com/solutions']");
	By ourProducts = By.cssSelector("a[href$='com/products']");
	
	By footerFaq = By.cssSelector("footer section:nth-child(4) li:nth-child(1)");
	By termsOfService = By.cssSelector("footer section:nth-child(4) li:nth-child(2)");
	By privacyPolicy = By.cssSelector("footer section:nth-child(4) li:nth-child(3)");
	
	By fb = By.cssSelector("a img[alt='facebook']");
	By twitter = By.cssSelector("a img[alt='twitter']");
	By ig = By.cssSelector("a img[alt='instagram']");
	
*/	
/*
	public WebElement getSignupMenu() {
		return driver.findElement(signup);
	}
	public WebElement getStartedBesideQuickDemo() {
		return driver.findElement(start1);
	}
	public WebElement getQuickDemoBtn() {
		return driver.findElement(demoBtn);
	}
	public WebElement getStartedInventorySection() {
		return driver.findElement(start2);
	}
	public WebElement getMobilePlan() {
		return driver.findElement(mobileStart);
	}
	public WebElement getLeasePlan() {
		return driver.findElement(leaseStart);
	}
	public WebElement getOutrightPlan() {
		return driver.findElement(outrightStart);
	}
	public WebElement getGooglePlay() {
		return driver.findElement(gogglePlayBtn);
	}
	
	
	
	public WebElement getAboutUs() {
		return driver.findElement(aboutUs);
	}
	public WebElement getBlog() {
		return driver.findElement(blog);
	}
	public WebElement getOurServices() {
		return driver.findElement(ourServices);
	}
	public WebElement getSolutions() {
		return driver.findElement(ourSolutions);
	}
	public WebElement getOurProducts() {
		return driver.findElement(ourProducts);
	}
	public WebElement getFooterFaqLink() {
		return driver.findElement(footerFaq);
	}
	public WebElement getTermsOfService() {
		return driver.findElement(termsOfService);
	}
	public WebElement getPrivacyPolicy() {
		return driver.findElement(privacyPolicy);
	}
	public WebElement getFacebook() {
		return driver.findElement(fb);
	}
	public WebElement getTwitter() {
		return driver.findElement(twitter);
	}
	public WebElement getInstragram() {
		return driver.findElement(ig);
	}
	
	
*/	
}

