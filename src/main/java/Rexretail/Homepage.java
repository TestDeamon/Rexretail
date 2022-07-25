package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Homepage {
	public WebDriver driver;
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
	}
	
	By brandLogo = By.cssSelector("img[alt='logo']");

	By dashMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(1)");
	By productMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(2)");
	By customerMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(3)");
	By staffMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(4)");
	By devicesMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(5)");
	By reportsMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(6)");
	By payNowMenu = By.cssSelector("nav.sidenav-nav ul li:nth-child(7)");
	By supportMenu = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(1) > lib-side-navigation:nth-child(1) > div:nth-child(1) > nav:nth-child(3) > ul:nth-child(1) > li:nth-child(9) > a:nth-child(2)");

	By profileDiv = By.cssSelector(".topnav-container");
	By profileLink = By.cssSelector("div[class='layout-container-content'] a:nth-child(1)");
	By logoutLink = By.cssSelector("div[class='layout-container-content'] a:nth-child(2)");
	
	By filter = By.cssSelector("img[alt='dropdown arrow']");
	
	public WebElement getBrandLogo() {
		return driver.findElement(brandLogo);
	}
	
	
	public WebElement getProfileDiv() {
		return driver.findElement(profileDiv);
	}
	
	public WebElement getProfile() {
		return driver.findElement(profileLink);
	}
	public WebElement getLogout() {
		return driver.findElement(logoutLink);
	}
	//GET FILTER DROPDOWN (CARET)
	public WebElement getFilterDropdown() {
		return driver.findElement(filter);
	}
	
	public WebElement getFilterSelected() {
		return driver.findElement(By.cssSelector(".label-container label"));
	}
	//GET FILTER OPTIONS (24 HRS|7 DAYS|30 DAYS)
	public List<WebElement> getFilterOptions() {
		return driver.findElements(By.cssSelector("ul.dropdown-content li"));
	}
	
	public void clickProfileMenu() {
		WebElement profileMenu = driver.findElement(By.cssSelector("label[for='content']"));
		Actions action = new Actions(driver);
		action.moveToElement(profileMenu).build().perform();
		driver.findElement(By.cssSelector("div[class='topnav-container-dropdown'] a:nth-child(1)")).click();
		
	}
	public Loginpage clickLogoutMenu() {
		WebElement profileMenu = driver.findElement(By.cssSelector("label[for='content']"));
		Actions action = new Actions(driver);
		action.moveToElement(profileMenu).build().perform();
		driver.findElement(By.cssSelector("div[class='topnav-container-dropdown'] a:nth-child(2)")).click();
		return new Loginpage(driver); 
	}
	public WebElement getDashMenu() {
		return driver.findElement(dashMenu);
	}
	public WebElement getProductMenu() {
		return driver.findElement(productMenu);
	}
	public WebElement getCustomerMenu() {
		return driver.findElement(customerMenu);
	}
	public WebElement getStaffMenu() {
		return driver.findElement(staffMenu);
	}
	public WebElement getDevicesMenu() {
		return driver.findElement(devicesMenu);
	}
	public WebElement getReportsMenu() {
		return driver.findElement(reportsMenu);
	}
	public WebElement getPayNowMenu() {
		return driver.findElement(payNowMenu);
	}
	public WebElement getSupportMenu() {
		return driver.findElement(supportMenu);
	}
	
	
	
	
	

}

