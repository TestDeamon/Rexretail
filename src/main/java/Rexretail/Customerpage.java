package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Customerpage {
	public WebDriver driver;
	
	public Customerpage(WebDriver driver) {
		this.driver = driver;
	}
	
	By brandLogo = By.cssSelector("img[alt='logo']");

	By title = By.cssSelector("div.intro.flex-1 h3");
	By filter = By.cssSelector(".btn.btn-secondary.flexed.icon-append.filter-button");
	
	By addCustomerButton = By.cssSelector(".btn.btn-primary.no-margin-r");
	
	
	By openCustomerButton = By.cssSelector("tbody tr:nth-child(1) td:nth-child(5) button");
	By perPage = By.cssSelector(".labelled-dropdown label");
	
	By customerName = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-customers-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-all-customers:nth-child(2) > div:nth-child(2) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) input");
	By email = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-customers-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-all-customers:nth-child(2) > div:nth-child(2) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) input");
	By phoneNo = By.cssSelector("body > app-root:nth-child(1) > app-layout:nth-child(3) > main:nth-child(1) > div:nth-child(2) > div:nth-child(2) > app-customers-container:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > app-all-customers:nth-child(2) > div:nth-child(2) > div:nth-child(1) > lib-filters:nth-child(1) > div:nth-child(1) > div:nth-child(2) > form:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) input");
	
	By clearField = By.cssSelector(".btn.btn__sm.btn-transparent");
	By applyFilter = By.cssSelector("nav.sidenav-nav ul li:nth-child(7)");
	
	By suspendCustomerBtn = By.cssSelector(".btn.btn-primary.no-margin-r"); 

	
	public WebElement getBrandLogo() {
		return driver.findElement(brandLogo);
	}
	public WebElement getPageTitle() {
		return driver.findElement(title);
	}
	
	public WebElement getClearField() {
		return driver.findElement(clearField);
	}
	public WebElement openCustomerInfo() {
		driver.findElement(openCustomerButton).click();
		
		
		return null; 
	}
	
	public WebElement suspendCustomer(String toBeSuspended) {
		List<WebElement> customerNames = driver.findElements(By.cssSelector("tbody tr td:nth-child(1)")); 
		int customerSize = customerNames.size();
		int i = 0; 
		for(WebElement customer : customerNames) {
			i++; 
			if(customer.getText().equalsIgnoreCase(toBeSuspended)) {
				//Click Open Customer Button to view details 
				driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(5) > button:nth-child(1)")).click(); 
				
				//Click Suspend Button
				driver.findElement(suspendCustomerBtn).click();
				
				//Click Customer Menu 
				driver.findElement(By.cssSelector("li[class='sidenav-nav__nav-item ng-star-inserted active'] a[class='sidenav-nav__nav-item-link ng-star-inserted']")).click();
				
				//Get Status 
				String text = driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child("+i+") > td:nth-child(4) > button:nth-child(1)")).getText();
				if(text.equalsIgnoreCase("Inactive")) {
					System.out.println("Set successfully");
				}
			}
		}
		
		return driver.findElement(clearField);
	}
	
	public WebElement getAddCustomerButton() {
		return driver.findElement(addCustomerButton);
	}
	
	public void searchCustomerByName(String cName) {
		WebElement filterButton = driver.findElement(filter);
		//Click Filter table button 
		filterButton.click();
		
		//Enter search query into customer name field 
		driver.findElement(customerName).sendKeys(cName);
		
		//Click Apply button 
		driver.findElement(applyFilter).click();
		
		//Verify search query is entered
		driver.findElement(By.cssSelector(".tag-text")).isDisplayed(); 
		
		String text = driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(1)")).getText();
		if(text.contains(cName)) {
			System.out.println("Search query found!");
		}else {
			driver.findElement(By.cssSelector("lib-no-table-data[class='ng-star-inserted'] div h3")).isDisplayed();		
		}	
	}
	
	public void searchCustomerByEmail(String cEmail) {
		WebElement filterButton = driver.findElement(filter);
		//Click Filter table button 
		filterButton.click();
		
		//Enter search query into customer name field 
		driver.findElement(email).sendKeys(cEmail);
		
		//Click Apply button 
		driver.findElement(applyFilter).click();
		
		//Verify search query is entered
		driver.findElement(By.cssSelector(".tag-text")).isDisplayed(); 
		
		String text = driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(1)")).getText();
		if(text.contains(cEmail)) {
			System.out.println("Search query found!");
		}else {
			driver.findElement(By.cssSelector("lib-no-table-data[class='ng-star-inserted'] div h3")).isDisplayed();		
		}	
	}
	public void searchCustomerByPhoneNo(String cPhone) {
		WebElement filterButton = driver.findElement(filter);
		//Click Filter table button 
		filterButton.click();
		
		//Enter search query into customer name field 
		driver.findElement(phoneNo).sendKeys(cPhone);
		
		//Click Apply button 
		driver.findElement(applyFilter).click();
		
		//Verify search query is entered
		driver.findElement(By.cssSelector(".tag-text")).isDisplayed(); 
		
		String text = driver.findElement(By.cssSelector("tbody tr:nth-child(1) td:nth-child(1)")).getText();
		if(text.contains(cPhone)) {
			System.out.println("Search query found!");
		}else {
			driver.findElement(By.cssSelector("lib-no-table-data[class='ng-star-inserted'] div h3")).isDisplayed();		
		}	
	}
	
	public WebElement changeItemsPerPage(String value) {
		driver.findElement(perPage).click();
		List<WebElement> perPageLists = driver.findElements(By.cssSelector("ul.dropdown-content.visible li"));
		
		String [] perPageText = null;
		int i = 0; 
		String select = value; 
		for(WebElement perPageList : perPageLists) {
			perPageText[i] = perPageList.getText();
			if(perPageText[i].equalsIgnoreCase(value)) {
				perPageList.click();
			}	
			
		}
		
		//Verify expected filter value is selected
		String returnTitle = driver.findElement(By.cssSelector("div.label-container label")).getAttribute("title");
		if(returnTitle.equalsIgnoreCase(select)) {
			//kdkdl
			System.out.println("Correct selection made");
		}else {
			//dkdkk
			
		}
		return  null;
	}

	public WebElement getCancelAddButton() {
		//Click close modal 
		return driver.findElement(By.cssSelector("img[alt='cancel']"));
	}
	
	public WebElement getAddButton() {
		//Add Customer Button 
		return driver.findElement(addCustomerButton);
	}

	public WebElement getCustomerModalFirstName() {
		return driver.findElement(By.cssSelector("div[class='row'] div:nth-child(1) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)"));
	}
	
	public WebElement getCustomerModalLastName() {
		return driver.findElement(By.cssSelector("div[class='row'] div:nth-child(1) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)"));
	}
	public WebElement getCustomerModalPhoneNo() {
		return driver.findElement(By.cssSelector("div[class='row'] div:nth-child(1) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)"));
	}
	public WebElement getCustomerModalEmail() {
		return driver.findElement(By.cssSelector("div[class='row'] div:nth-child(1) app-dynamic-field:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) input:nth-child(1)"));
	}
	public WebElement getCustomerEReceipt() {
		WebElement eReceipt = driver.findElement(By.id("receiveEReceipt"));
		return eReceipt; 
	}
	
	public WebElement getCustomercancelCustomerButton() {
		WebElement cancelCustomerButton = driver.findElement(By.cssSelector(".btn.btn-secondary.ng-star-inserted"));
		return cancelCustomerButton; 
	}
	
	public WebElement getAddCustomerButtonModal() {
		//Add Customer Button
		WebElement addCustomerButton = driver.findElement(By.cssSelector("button[class='btn btn-primary']"));
		return addCustomerButton; 
	}
	

}

