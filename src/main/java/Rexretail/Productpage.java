package Rexretail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Productpage {
	public WebDriver driver;
	
	public Productpage(WebDriver driver) {
		this.driver = driver;
	}

	By manageProduct = By.cssSelector("ul.tabs.ng-star-inserted li:nth-child(1)");
	By shops = By.cssSelector("ul.tabs.ng-star-inserted li:nth-child(2)");
	By productCategories = By.cssSelector("ul.tabs.ng-star-inserted li:nth-child(3)");
	By filter = By.cssSelector(".btn.btn-secondary.flexed.icon-append.filter-button");
	
	By productUpdate = By.cssSelector("tbody tr:first-child td button");
	By perPage = By.cssSelector(".labelled-dropdown label");
	
	By productName = By.cssSelector("form > div > div:first-child > div input");
	By productCode = By.cssSelector("form > div > div:nth-child(2) > div input");
	By productCategory = By.cssSelector("form > div > div:nth-child(3) select");
	By cancelFilterBtn = By.cssSelector(".btn.btn__sm.btn-transparent");
	By applyFilterBtn = By.cssSelector(".btn.btn__sm.btn-primary");
	
	public String getPageName() {
		return driver.findElement(By.cssSelector(".heading__4.no-margin")).getText();
	}

	public WebElement getManageProduct() {
		return driver.findElement(manageProduct);
	}
	public WebElement getShops() {
		return driver.findElement(shops); 
	}
	public WebElement getFilter() {
		return driver.findElement(filter);
	}
	public WebElement getProductName() {
		return driver.findElement(productName);
	}
	public WebElement getProductCode() {
		return driver.findElement(productCode);
	}
	public void getProductCategory(String categoryName) throws InterruptedException {
		WebElement options = driver.findElement(productCategory);
		Select sel = new Select(options);
		Thread.sleep(2000);
		sel.selectByVisibleText(categoryName);
		Thread.sleep(2000);
	}
	public WebElement getCancelFilterBtn() {
		return driver.findElement(cancelFilterBtn);
	}
	public WebElement getApplyFilterBtn() {
		return driver.findElement(applyFilterBtn);
	}

	public void filterProductByName(String productName) throws InterruptedException {
		//CLICK FILTER BUTTON 
		getFilter().click();
		
		Thread.sleep(2000);
		
		//SEND PRODUCT NAME TO FILTER BY 
		getProductName().sendKeys(productName);
		
		Thread.sleep(1000);
		
		//CLICK APPLY FILTER 
		getApplyFilterBtn().click();
		
		
	}
	
	public void filterProductByCode(String productCode) throws InterruptedException {
		//CLICK FILTER BUTTON 
		getFilter().click();
		
		Thread.sleep(2000);
		
		//SEND PRODUCT NAME TO FILTER BY 
		getProductCode().sendKeys(productCode);
		
		Thread.sleep(1000);
		
		//CLICK APPLY FILTER 
		getApplyFilterBtn().click();
		
	}
	
	public void closeFilterModal() throws InterruptedException {
		//CLICK FILTER BUTTON 
		getFilter().click();
		
		Thread.sleep(2000);
		
		//CLICK APPLY FILTER 
		getCancelFilterBtn().click();
	
		//NEED TO ASSERT TO CONFIRM CLOSE OF MODAL 
	}
	
	/*PRODUCT VARIABLES*/
//	By newProductBtn = By.cssSelector("form > div > div:first-child > div input");
	By newProduct = By.cssSelector(".btn.btn-primary.no-margin-r.ng-star-inserted");
	By productDescriptions = By.cssSelector("div.modal-container .modal-content .injected-content  div.option-description ");
	By cancelX = By.cssSelector("img.cancel-icon");
	
	
	public WebElement getNewProductButton() {
		return driver.findElement(newProduct);
	}
	public List<WebElement> getSingleAndBulkElements() {
		return driver.findElements(productDescriptions);
	}
	public WebElement getCancelModalX(){
		return driver.findElement(cancelX);
	}
	
	public void closeNewProductModal() throws InterruptedException{
		//CLICK NEW PRODUCT BUTTON 
		getNewProductButton().click();
		
		Thread.sleep(2000);
		
		//CLICK CLOSE X TO CLOSE NEW PRODUCT MODAL 
		getCancelModalX().click();
		
		//ASSERT TO CONFIRM MODAL CLOSED 
	}
	
	/*SINGLE PRODUCTS FIELD*/
	By productNameField = By.cssSelector("input[class='field four ng-untouched ng-pristine ng-invalid']");
	By skuField = By.cssSelector("input[class='field four ng-untouched ng-pristine ng-valid']");
	By productCategoryField = By.cssSelector("form div:nth-child(3) div.field-container select"); //CHECK
	By productPriceField = By.cssSelector("div.ng-star-inserted:nth-child(4) .field-container .field.three input");
	By stockQuantityField = By.cssSelector("div.ng-star-inserted:nth-child(5) .field-container .field.three input");
	By lowStockField = By.cssSelector("div.ng-star-inserted:nth-child(6) .field-container .field.three select");
	By minStockField = By.cssSelector("div.ng-star-inserted:nth-child(7) .field-container .field.three input");
	By descriptionField = By.cssSelector("div.ng-star-inserted:nth-child(8) .field-container .field textarea");
	By checkboxesStockCanExpire = By.cssSelector("div.ng-star-inserted > div.checkbox-container input");
	
	
	By cancelProductBtn = By.cssSelector(".btn.btn-white");
	By createProductBtn = By.cssSelector(".form-central__content-actions button[class*='btn btn-primary']");
	By uploadImage = By.cssSelector(".btn.btn-secondary.btn__lg");
	
	
	public WebElement getProductNameField(){
		return driver.findElement(productNameField);
	}
	public WebElement getSKUField(){
		return driver.findElement(skuField);
	}
	public WebElement getProductCategoryField(){
		return driver.findElement(productCategoryField);
	}
	public Select selectProductCategory(){
		Select sel = new Select(getProductCategoryField());
		return sel;
	}
	public WebElement getProductPriceField(){
		return driver.findElement(productPriceField);
	}

	public WebElement getProductStockQuantityField(){
		return driver.findElement(stockQuantityField);
	}
	public Select selectLowStockActiion(){
		Select sel = new Select(driver.findElement(lowStockField));
		return sel;
	}
	public WebElement getProductMinStockField(){
		return driver.findElement(minStockField);
	}
	public WebElement getProductDescriptionField(){
		return driver.findElement(descriptionField);
	}
	public List<WebElement> getProductCheckBoxes(){
		return driver.findElements(checkboxesStockCanExpire);
	}
	public WebElement getCancelProductButton(){
		return driver.findElement(cancelProductBtn);
	}
	public WebElement getCreateProductButton(){
		return driver.findElement(createProductBtn);
	}
	public void uploadProductImage(String path){
		driver.findElement(createProductBtn).sendKeys(path);;
	}
	public WebElement getBulkUploadField(){
		return driver.findElement(By.cssSelector(".multiple__btn"));
	}
	public void createBulkProduct(String FilePath) {
		//CLICK NEW PRODUCT BUTTON 
		getNewProductButton().click();
		
		//GET MANY PRODUCT OPTIONS ON NEW PRODUCT MODAL 
		getSingleAndBulkElements().get(1).click();
		
		getBulkUploadField().sendKeys(FilePath);
		
		/*
		 * 
		 * Verify products successfully uploaded 
		 * WorkBook traversing 
		 * */
	}
	public String convertToSentenceCase(String value) {
        // get First letter of the string
        String firstLetStr = value.substring(0, 1);
        
        // Get remaining letter using substring
        String remLetStr = value.substring(1);
 
        // concantenate the first letter and remaining string
        return firstLetStr.toUpperCase() + remLetStr;
	}


	//SHOPS 
	By shopNameField = By.cssSelector("form div div:nth-child(1) div:first-child input");
	By shopID = By.cssSelector("form div div:nth-child(2) div:first-child input"); 
	By addShopBtn = By.cssSelector(".btn.btn-primary.no-margin-r");
	By shopName = By.cssSelector("table tbody tr:first-child td:nth-child(1)");
	By storeId = By.cssSelector("table tbody tr:first-child td:nth-child(2)"); 
	By status = By.cssSelector("table tbody tr:first-child td:nth-child(5)"); 
	By openBtn = By.cssSelector("table tbody tr:first-child td:nth-child(6) button"); 

	public WebElement getShopNameField() {
		return driver.findElement(shopNameField);
	}

	public WebElement getShopID() {
		return driver.findElement(shopID);
	}

	public WebElement getAddShopBtn() {
		return driver.findElement(addShopBtn);
	}

	public WebElement getShopName() {
		return driver.findElement(shopName);
	}
	public WebElement getStoreId() {
		return driver.findElement(storeId);
	}
	public WebElement getStatus() {
		return driver.findElement(status);
	}

	public WebElement getOpenBtn() {
		return driver.findElement(openBtn);
	}

	
	
}