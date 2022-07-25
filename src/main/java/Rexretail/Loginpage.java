package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Loginpage {
	public WebDriver driver;
	
	public Loginpage(WebDriver driver) {
		this.driver = driver;
	}

	By brandLogo = By.cssSelector(".auth-layout__logo.cursor-pointer");
	By username = By.cssSelector(".field-container > input[css='1']");
	By merchantCode = By.cssSelector("input[class='field four ng-untouched ng-pristine ng-invalid']");
	By pass = By.id("pin");
	By pineye = By.cssSelector("#pineye");
	By forgotpass = By.cssSelector(".ml-auto.mb-4.text-primary");
	By loginBtn = By.cssSelector(".btn.btn-primary.btn__lg.login__button");
	By signupLink = By.cssSelector("a[class='login__signup'] span");
	
	By pageTitle = By.cssSelector("div[class='login'] h1");
	
	
	By form = By.cssSelector(".form-central__form div.ng-star-inserted > form div.row div div div div input[type='text']"); 
	public List<WebElement> getUsernameAndMerchantCodeFields() {
		return driver.findElements(form);
	}
	public WebElement getBrandLogo() {
		return driver.findElement(brandLogo);
	}

	public WebElement getUsername() {
		return getUsernameAndMerchantCodeFields().get(0);
	}
	
	public WebElement getMerchantCode() {
		return getUsernameAndMerchantCodeFields().get(1);
	}
	
	public WebElement getPassword() {
		return driver.findElement(pass);
	}
	
	public WebElement getPasswordVisibility() {
		return driver.findElement(pineye);
	}
	
	public ForgotPasswordPage getForgotPasswordLink() {
		driver.findElement(forgotpass).click();
		return new ForgotPasswordPage(driver);
	}
	
	public WebElement getLoginButton() {
		return driver.findElement(loginBtn);
	}
	public Homepage clickLoginButton() {
		driver.findElement(loginBtn).click();
		return new Homepage(driver);
	}
	
	public WebElement getSignupLink() {
		return driver.findElement(signupLink);
	}
	
	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}
	
	
	
}
