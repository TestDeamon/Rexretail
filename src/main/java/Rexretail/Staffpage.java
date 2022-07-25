package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Staffpage {
	public WebDriver driver;
	
	public Staffpage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*MANAGE STAFF PAGE*/

	By BrandLogo = By.cssSelector("img[alt='logo']");
	By staffMenu = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(1) > lib-side-navigation:nth-child(1) > div:nth-child(1) > nav:nth-child(3) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(2)");

	By openManageStaff = By.cssSelector("a[href='/staff/permissions']");
	By myStaffTitle = By.cssSelector(".heading__4.no-margin");
	By openStaffRole = By.cssSelector("a[href='/staff/observe']");
	
	By filterTable = By.cssSelector(".btn.btn-secondary.flexed.icon-append.filter-button");
	By addStaff = By.cssSelector(".btn.btn-primary.no-margin-r");
	By openStaffDetails = By.cssSelector("tbody tr:nth-child(1) td:nth-child(7) button:nth-child(1)");
	By status = By.cssSelector("tbody tr:nth-child(1) td:nth-child(5)"); 
	By firstName = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > app-create-staff:nth-child(5) > lib-modal:nth-child(1) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
	By lastName = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > app-create-staff:nth-child(5) > lib-modal:nth-child(1) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
	By phoneNo = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > app-create-staff:nth-child(5) > lib-modal:nth-child(1) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(3) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
	By emailAdd = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > app-create-staff:nth-child(5) > lib-modal:nth-child(1) > section:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > lib-dynamic-form:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(1) > div:nth-child(4) > app-dynamic-field:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)");
	By staffRole = By.cssSelector("select[name='select']");
	
	//Bottom cancel button
	By cancelButton = By.cssSelector(".btn.btn-secondary.ng-star-inserted"); 
	By createStaffButton = By.cssSelector("button[class='btn btn-primary']"); 
	
	//Top cancel button (X)
	By cancel = By.cssSelector("img[alt='cancel']"); 
	
	
	//Filter fields 
	By fn = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > div:nth-child(4) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");
	By ln = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > div:nth-child(4) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(2)");
	By roleName = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > div:nth-child(4) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > input:nth-child(2)");
	By filterEmail = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > div:nth-child(4) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > input:nth-child(2)");
	By merchantCode = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-manage-staff:nth-child(2) > div:nth-child(4) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(5) > div:nth-child(1) > input:nth-child(2)");
	By cancelFilter = By.cssSelector(".btn.btn__sm.btn-transparent");
	By applyFilter = By.cssSelector(".btn.btn__sm.btn-primary");
	
	
	public WebElement getBrandLogo() {
		return driver.findElement(BrandLogo);
	}
	
	public WebElement getStaffMenu() {
		return driver.findElement(staffMenu);
	}
	
	public WebElement getOpenManageStaff() {
		return driver.findElement(openManageStaff);
	}
	public WebElement getMyStaffTitle() {
		return driver.findElement(myStaffTitle);
	}
	public WebElement getOpenStaffRole() {
		return driver.findElement(openStaffRole);
	}
	
	public WebElement getFilterButton() {
		return driver.findElement(filterTable);
	}
	
	public WebElement getAddStaff() {
		return driver.findElement(addStaff);
	}
	public WebElement getOpenStaffButton() {
		return driver.findElement(openStaffDetails);
	}
	public WebElement getStaffStatus() {
		return driver.findElement(status);
	}
	
	public WebElement getFirstName() {
		return driver.findElement(firstName);
	}

	public WebElement getLastName() {
		return driver.findElement(lastName);
	}

	public WebElement getPhoneNo() {
		return driver.findElement(phoneNo);
	}

	public WebElement getEmailAdd() {
		return driver.findElement(emailAdd);
	}

	public void getStaffRole(String value) {
		WebElement role = driver.findElement(staffRole);
		Select sel = new Select(role); 
		sel.selectByVisibleText(value);	
	}
	public WebElement getCancelButton() {
		return driver.findElement(cancelButton);
	}
	public WebElement getAddNewStaffButton() {
		return driver.findElement(createStaffButton);
	}
	public WebElement getCreateStaffModalCancelButton() {
		return driver.findElement(cancel);
	}
	
	public WebElement getFilterFirstName() {
		return driver.findElement(fn);
	}
	public WebElement getFilterLastName() {
		return driver.findElement(ln);
	}
	public WebElement getFilterRole() {
		return driver.findElement(roleName);
	}
	public WebElement getFilterEmailAddress() {
		return driver.findElement(filterEmail);
	}
	public WebElement getFilterMerchantCode() {
		return driver.findElement(merchantCode);
	}
	public WebElement getFilterCancelButton() {
		return driver.findElement(cancelFilter);
	}
	public WebElement getFilterApplyFilterButton() {
		return driver.findElement(applyFilter);
	}	
	

	/*STAFF ROLE PAGE*/
	By role = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-permissions:nth-child(2) > div:nth-child(2) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > input:nth-child(2)");
	By roleCat = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-permissions:nth-child(2) > div:nth-child(2) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(2)");
	
	By addRole = By.cssSelector(".btn.btn-primary.no-margin-r");
	By updateRoleButton = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-permissions:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-dynamic-table:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > button:nth-child(1)");
	By roleNameList = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-permissions:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-dynamic-table:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > button:nth-child(1)");
	By roleStatusList = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-staff-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > app-permissions:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > lib-dynamic-table:nth-child(1) > table:nth-child(1) > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(4) > button:nth-child(1)");
	
	
	public WebElement getStaffRoleName() {
		return driver.findElement(role);
	}
	public WebElement getStaffRoleCategory() {
		return driver.findElement(roleCat);
	}
	public WebElement getStaffAddRoleButton() {
		return driver.findElement(addRole);
	}
	public List<WebElement> getStaffRoleNames() {
		return driver.findElements(roleNameList);
	}
	
	public List<WebElement> getStaffStatusList() {
		return driver.findElements(roleStatusList);
	}
	
	/*Add New User Role page*/
	By rn = By.cssSelector("div.ng-star-inserted .field-container input");
	By sr = By.cssSelector(".ng-star-inserted:nth-child(2) .field-container label select");
	By activeBtn = By.id("active");
	By cancelRole = By.cssSelector(".btn.btn-white");
	By addUserRoleButton = By.cssSelector(".btn.btn-primary");
	
	public WebElement getAddStaffRoleName() {
		return driver.findElement(rn);
	}
	public void getSelectStaffRole(String value) {
		WebElement role = driver.findElement(sr);
		Select selectedRole = new Select(role);
		selectedRole.selectByVisibleText(value);
	}
	public WebElement getAddStaffActiveButton() {
		return driver.findElement(activeBtn);
	}
	public WebElement getAddStaffCancelButton() {
		return driver.findElement(cancelRole);
	}
	public WebElement getAddStaffAddUserRoleButton() {
		return driver.findElement(addUserRoleButton);
	}
	
	By roleAccessText = By.cssSelector(".ng-star-inserted .ng-star-inserted .checkbox-container label");
	By roleAccessCheckBoxes = By.cssSelector(".ng-star-inserted .ng-star-inserted .checkbox-container input");
	
	public List<WebElement> getRolesTextList() {
		return driver.findElements(roleAccessText);
	}
	public List<WebElement> getRolesCheckBoxes() {
		return driver.findElements(roleAccessCheckBoxes);
	}
	
}

