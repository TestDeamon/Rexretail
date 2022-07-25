package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Devicespage {
	public WebDriver driver;
	
	public Devicespage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*MANAGE STAFF PAGE*/

	By BrandLogo = By.cssSelector("img[alt='logo']");
	By deviceMenu = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(1) > lib-side-navigation:nth-child(1) > div:nth-child(1) > nav:nth-child(3) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(2)");
	By myDeviceTitle = By.cssSelector(".heading__4.no-margin");
	
	By filterBtn = By.cssSelector(".btn.btn-secondary.flexed.icon-append.filter-button");
	
	By deviceTypeField = By.cssSelector("select[class='filter-group-input ng-untouched ng-pristine ng-valid']");
	
	By deviceIdField = By.cssSelector("input[type='text']");
	By cancelFilter = By.cssSelector(".btn.btn__sm.btn-transparent");
	By applyFilter = By.cssSelector(".btn.btn__sm.btn-primary");
	
	

	
	public WebElement getBrandLogo() {
		return driver.findElement(BrandLogo);
	}
	public WebElement getMyDevicePageTitle() {
		return driver.findElement(myDeviceTitle);
	}
	public WebElement getMyDeviceMenu() {
		return driver.findElement(deviceMenu);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterBtn);
	}
	
	public WebElement getDeviceTypeField() {
		return driver.findElement(deviceTypeField);
	}
	public WebElement getDeviceIdField() {
		return driver.findElement(deviceIdField);
	}
	
	By deviceElipsisMenu = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-devices-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-devices:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-dynamic-table:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(5) > img:nth-child(1)");
	By disableElipsisMenu = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-devices-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-devices:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-dynamic-table:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(5) > div:nth-child(2) > div:nth-child(2)");
	By deviceStatus = By.cssSelector("tbody tr:nth-child(1) td:nth-child(4)");
	By typeOfDevice = By.cssSelector("tbody tr:nth-child(1) td:nth-child(3)");
	public WebElement getDeviceElipsisMenu() {
		return driver.findElement(deviceElipsisMenu);
	}
	
	public WebElement getDisableElipsisMenu() {
		return driver.findElement(disableElipsisMenu);
	}

	public WebElement getDeviceStatus() {
		return driver.findElement(deviceStatus);
	}
	public WebElement getTypeOfDevice() {
		return driver.findElement(typeOfDevice);
	}

	public WebElement getFilterCancelButton() {
		return driver.findElement(cancelFilter);
	}
	public WebElement getFilterApplyFilterButton() {
		return driver.findElement(applyFilter);
	}	
	
}

