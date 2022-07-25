package Rexretail;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class Onboardingpage {
	public WebDriver driver;
	
	public Onboardingpage(WebDriver driver) {
		this.driver = driver;
	}

	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	By brandLogo = By.cssSelector(".onboarding__logo.cursor-pointer");
	By mobileGetStarted = By.cssSelector("div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(4)");
	By leaseGetStarted = By.cssSelector(".subscription-options__btn.btn.btn-primary");
	By outrightGetStarted = By.cssSelector("div:nth-child(3) > div:nth-child(1) > button:nth-child(4)");
	By changePlanLink = By.cssSelector(".text__default.no-margin.cursor-pointer");
	By decrease = By.cssSelector("body app-root span:nth-child(1)");
	By month = By.cssSelector("body app-root span:nth-child(2)");
	By increase = By.cssSelector("body app-root span:nth-child(3)");
	By previous = By.cssSelector(".btn.plan__prev-button"); 
	By next = By.cssSelector(".btn.btn-primary.plan__next-button");
	By sign_inBtn = By.cssSelector(".btn.btn__sm.btn-outline-primary.no-margin");

	
	By contact_email = By.cssSelector("body > app-root:nth-child(1) > app-onboarding-container:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > app-business-information:nth-child(1) > div:nth-child(2) > div:nth-child(2) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(3) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
	By taxNo = By.cssSelector(".field-input.ng-untouched.ng-pristine.ng-valid");
	By dialCode = By.cssSelector("form div div:nth-child(7) div.ng-star-inserted div.field-container label.field.three select");
	By currencySelect = By.cssSelector("form div div:nth-child(9) div.ng-star-inserted div.field-container label.field.three select");
	By cacNo = By.cssSelector("input[class='field four ng-untouched ng-pristine ng-valid']");
	By natureOfBusiness = By.cssSelector("form div div:nth-child(6) div.ng-star-inserted div.field-container label.field.three select");
	
	By accountNo = By.cssSelector(".field-input.ng-untouched.ng-pristine.ng-invalid");
	By bankField = By.cssSelector("div.onboarding div.onboarding__stage div.business-information div.bank-info + .form-central__form .ng-star-inserted form div.row > div:nth-child(2) select");
	By back = By.cssSelector(".btn.business-information__prev-button");
	By proceed = By.cssSelector("button[class='btn btn-primary']");
	
	
	//Secondpage
	By agreeToTerms = By.cssSelector("label[for='checked']");
	By submit = By.cssSelector("div[class='terms-of-use__footer'] button[class='btn btn-primary']");
	
	
	//DetailsPage

    By nameLookupField = By.cssSelector("div.onboarding div.onboarding__stage div.business-information div.bank-info + .form-central__form  .ng-star-inserted form div.row > div:nth-child(3) input");
	//	Select sec = new Select(driver.findElement(natureOfBusiness));
    
    // Lease Plan
    By radioButton1 = By.cssSelector("label:nth-child(1) span:nth-child(1) input:nth-child(1)");
	By radioButton2 = By.cssSelector("label:nth-child(2) span:nth-child(1) input:nth-child(1)");
	By radioButton3 = By.cssSelector("label:nth-child(3) span:nth-child(1) input:nth-child(1)");
	By radioButton4 = By.cssSelector("label:nth-child(4) span:nth-child(1) input:nth-child(1)");
	By proceedLease = By.cssSelector(".btn.btn-primary.plan__next-button");
	By previousLease = By.cssSelector(".btn.plan__prev-button");
	
	//Second Page
	By businessName = By.cssSelector("body > app-root:nth-child(1) > app-onboarding-container:nth-child(3) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > app-business-information:nth-child(1) > div:nth-child(2) > div:nth-child(2) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
	By businessAddress = By.cssSelector("div[class='form-central__form'] div:nth-child(2) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)");
	By contactEmail = By.cssSelector("div.onboarding__stage div.admin-information form div:nth-child(5) input");
	//	By taxNo = By.cssSelector("']");
	
	By inputs = By.cssSelector("input[type='text']");
	By selects = By.cssSelector("label > select"); 
	
	public List<WebElement> getSelects() {
		return driver.findElements(selects);
	}
	
	public List<WebElement> getInputs() {
		return driver.findElements(inputs);
	}
	public WebElement getBrandLogo() {
		return driver.findElement(brandLogo);
	}
	
	public WebElement getMobileButton() {
		return driver.findElement(mobileGetStarted);
	}
	
	public WebElement getLeaseButton() {
		return driver.findElement(leaseGetStarted);
	}
	
	public WebElement getOutrightButton() {
		return driver.findElement(outrightGetStarted);
	}
	
	public WebElement getChangePlan() {
		return driver.findElement(changePlanLink);
	}
	
	public WebElement getDecreaseButton() {
		return driver.findElement(decrease);
	}
	
	public WebElement getMonth() {
		return driver.findElement(month);
	}
	
	public WebElement getIncreaseButton() {
		return driver.findElement(increase);
	}
	
	public WebElement getPreviousButton() {
		return driver.findElement(previous);
	}
	
	public WebElement getProceedButton() {
		return driver.findElement(next);
	}
	
	public WebElement getSigningButton() {
		return driver.findElement(sign_inBtn);
	}
	
	public WebElement getContactEmail() {
		return driver.findElement(contact_email);
	}
	public WebElement getTaxNo() {
		return driver.findElement(taxNo);
	}
	public void selectDialCode() {
		WebElement dial = driver.findElement(dialCode);
		Select dialCode= new Select(dial);
		dialCode.selectByVisibleText("+234");
	}
	public void selectCurrency() {
		WebElement currencyElement = driver.findElement(currencySelect);
		Select currency= new Select(currencyElement);
		currency.selectByVisibleText("NGN");
	}
	public WebElement getCacNo() {
		return driver.findElement(cacNo);
	}
	
	public void selectNatureOfBusiness(String value) {
		WebElement nOB = driver.findElement(natureOfBusiness);
		Select nature_of_Business = new Select(nOB);
		nature_of_Business.selectByValue(value);
	}
	
	public WebElement getAccountNo() {
		return driver.findElement(accountNo);
	}
	
	public void selectBankName(String value) throws InterruptedException {
		WebElement bank = driver.findElement(bankField);
		Select bankBtn = new Select(bank);
		bankBtn.selectByValue(value);
		
		Thread.sleep(3000);
	}
	
	public void verifyAccountNo() {
		WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(3))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary")));
		driver.findElement(By.cssSelector(".btn.btn-primary.no-margin")).click();	

	}
	
	public String verifyLookupSuccessful() {
		return driver.findElement(nameLookupField).getText(); 
	}
	public WebElement getBackButton() {
		return driver.findElement(back);
	}
	public WebElement saveAndProceed() {
		return driver.findElement(proceed);
	}
	public WebElement agreeToTerms() {
		return driver.findElement(agreeToTerms);
	}
	
	public WebElement getSubmitBtn() {
		return driver.findElement(submit);
	}

	public void selectradio1(String value) {
		WebElement rad1 = driver.findElement(radioButton1);
		WebElement rad2 = driver.findElement(radioButton2);
		WebElement rad3 = driver.findElement(radioButton3);
		WebElement rad4 = driver.findElement(radioButton4);	
// Radio Button1 is selected.		
		rad1.click();
// Radio Button2 is selected.		
		rad2.click();
// Radio Button3 is selected.
		rad3.click();
// Radio Button4 is selected.
		rad4.click();
	}
	public WebElement leaseProceed() {
		return driver.findElement(proceedLease);
	}
	public WebElement leasePrevious() {
		return driver.findElement(previousLease);
	}
	public WebElement getBusiness() {
		return driver.findElement(businessName);
	}
	public WebElement getBusAddress() {
		return driver.findElement(businessAddress);
	}
	public WebElement getcontactEmail() {
		return driver.findElement(contactEmail);
	}
	
	public NgWebDriver getNGDriver(){
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		return (new NgWebDriver(js)); 
	}

	public void waitForAngularRequestsToFinish(){
		try{
			getNGDriver().waitForAngularRequestsToFinish(); 
		}catch(Exception e){
			System.out.println("Error while waiting for Angular requests to finish: "+e.getMessage());
	//		Assert.fail("Error while waiting for Angular requests to finish: "+e.getMessage()); 
		}
	}
	
	//PAGE TWO (PERSONAL DETAILS
	By foneNo = By.cssSelector("div.onboarding__stage div.admin-information form div:nth-child(4) input");
	By bvn = By.cssSelector("div.onboarding__stage div.admin-information form div:nth-child(6) input");
	By firstName = By.cssSelector("input[class='field four ng-pristine ng-invalid ng-touched']");
	By lastName = By.cssSelector("div[class='form-central__form'] div:nth-child(2) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)");
    By email = By.cssSelector("div:nth-child(3) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)");
    By phoneNumber = By.cssSelector("form div div:nth-child(8) input");
    By countryCode = By.cssSelector("select[name='select']");
    By saveProceed = By.cssSelector(".btn.btn-primary"); 
    By getStarted = By.cssSelector(".btn.btn-primary.btn__sm");
    
    
	public WebElement getPage2PhoneNo() {
		return driver.findElement(foneNo);
	}
	public WebElement getPage2BVN() {
		return driver.findElement(bvn);
	}
	/*PERSONAL DETAILS */
	
	public WebElement getFirstName() {
		return driver.findElement(firstName);
	}
	public WebElement getLastName() {
		return driver.findElement(lastName);
	}
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	public WebElement getPhoneNumber() {
		return driver.findElement(phoneNumber);
	}
	public void selectCountryCode() {
		WebElement cc = driver.findElement(countryCode);
		Select country_Code = new Select(cc);
		country_Code.selectByVisibleText("+234");
	}
	public WebElement saveNdProceed() {
		return driver.findElement(saveProceed);
	}
	public WebElement getStart() {
		return driver.findElement(getStarted);
	}
}
