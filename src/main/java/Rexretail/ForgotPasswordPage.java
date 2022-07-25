package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
	public WebDriver driver;
	
	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	By brand = By.cssSelector("div img[src$='rexretail.svg']");
	By pageTitle = By.xpath("//h1[contains(text(),'Forgot Pin?')]");
	By inputFields = By.cssSelector("div.row div app-dynamic-field div div div.field-container input");
	By sendButton = By.cssSelector("div.form-central__form + button");
//	By loginLink = By.cssSelector("a.login__signup span");
	
	public WebElement brandLogo() {
		return driver.findElement(brand);
	}
	public Landingpage getBrandLogo() {
		driver.findElement(brand);
		return new Landingpage(driver);
	}

	public WebElement getPageTitle() {
		return driver.findElement(pageTitle);
	}
	public List<WebElement> getPageInputFields() {
		return driver.findElements(inputFields);
	}
	public WebElement getPhoneNoField() {
		return getPageInputFields().get(0); 
	}
	public WebElement getMerchantCodeField() {
		return getPageInputFields().get(1); 
	}
	public WebElement getSendButton() {
		return driver.findElement(sendButton);
	}
	public Loginpage getLoginLink() {
		driver.findElement(By.linkText("Return to Login")).click();
		return new Loginpage(driver);
	}
}

