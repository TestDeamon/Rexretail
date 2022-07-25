package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Reportpage {
	public WebDriver driver;
	
	public Reportpage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*MANAGE STAFF PAGE*/

	By BrandLogo = By.cssSelector("img[alt='logo']");
	By reportMenu = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(1) > lib-side-navigation:nth-child(1) > div:nth-child(1) > nav:nth-child(3) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(2)");
	By myReportTitle = By.cssSelector(".heading__4.no-margin");
	By openSalesReportBtn = By.cssSelector("a[href='/reports/sales-reports']");
	By openSettlementReportBtn = By.cssSelector("a[href='/reports/settlements']");
	By openBillingReportBtn = By.cssSelector("a[href='/reports/billings']");
	By openCashierReportBtn = By.cssSelector("a[href='/reports/cashiers']");
	
	public WebElement getBrandLogo() {
		return driver.findElement(BrandLogo);
	}
	public WebElement getReportMenu() {
		return driver.findElement(reportMenu);
	}
	public WebElement getMyReportPageTitle() {
		return driver.findElement(myReportTitle);
	}
	public WebElement openSalesReport() {
		return driver.findElement(openSalesReportBtn);
	}
	public WebElement openSettlementReport() {
		return driver.findElement(openSettlementReportBtn);
	}
	public WebElement openBillingReport() {
		return driver.findElement(openBillingReportBtn);
	}
	public WebElement openCashierReport() {
		return driver.findElement(openCashierReportBtn);
	}
	
	
	
	
	By filterBtn = By.cssSelector(".btn.btn-secondary.flexed.icon-append.filter-button");
	
	
	
	
	By cancelFilter = By.cssSelector(".btn.btn__sm.btn-transparent");
	By applyFilter = By.cssSelector(".btn.btn__sm.btn-primary");
	public WebElement getFilterButton() {
		return driver.findElement(filterBtn);
	}
	
	public WebElement getFilterCancelButton() {
		return driver.findElement(cancelFilter);
	}
	public WebElement getFilterApplyFilterButton() {
		return driver.findElement(applyFilter);
	}	
	

	
	
	By orderNoField = By.cssSelector("input[type='text']");
	By paymentStatusField = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-reoprts-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-sales-report:nth-child(2) > app-sales-report-table:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > select:nth-child(2)");
	By paymentTypeField = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-reoprts-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-sales-report:nth-child(2) > app-sales-report-table:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > select:nth-child(2)");
	By startDateField = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-reoprts-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-sales-report:nth-child(2) > app-sales-report-table:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > input:nth-child(2)");
	By endDateField = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-reoprts-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-sales-report:nth-child(2) > app-sales-report-table:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > input:nth-child(2)");
	public WebElement getOrderNoField() {
		return driver.findElement(orderNoField);
	}
	public void getPaymentStatusField(String value) {
		WebElement paymentStatus = driver.findElement(paymentStatusField);
		Select selectedRole = new Select(paymentStatus);
		selectedRole.selectByVisibleText(value);
	}
	public void getPaymentTypeField(String value) {
		WebElement paymentType = driver.findElement(paymentTypeField);
		Select selectedRole = new Select(paymentType);
		selectedRole.selectByVisibleText(value);
	}
	public WebElement getPaymentStartDate() {
		return driver.findElement(startDateField);
	}
	
	/*SETTLEMENT REPORT*/
	By dashboardTab = By.cssSelector(".tab-item:nth-child(1)");
	By fullBreakDownTab = By.cssSelector(".tab-item:nth-child(2)");
	
	By requestSettlementBtn = By.cssSelector(".btn-btn__sm.cashback__button.cursor-pointer");
	By walletValue = By.cssSelector("div.cashback-container p .text-bold");
	public WebElement getDashboardTab() {
		return driver.findElement(dashboardTab);
	}
	public WebElement getFullBreakDownTab() {
		return driver.findElement(fullBreakDownTab);
	}
	public WebElement getRequestSettlementBtn() {
		return driver.findElement(requestSettlementBtn);
	}
	public WebElement getWalletValue() {
		return driver.findElement(walletValue);
	}
}

