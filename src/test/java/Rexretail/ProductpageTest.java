package Rexretail;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.Base;

public class ProductpageTest extends Base {
	public WebDriver driver;
	public Properties prop; 
	public static Logger log = LogManager.getLogger(Base.class.getName());

	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void initialize() throws IOException {
		prop = new Properties(); 

		String location = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		String driverLocation = System.getProperty("user.dir") + "\\src\\main\\java\\resources";
		FileInputStream fis = new FileInputStream(location);
       
		prop.load(fis);
		
		driver = initializeDriver();
		driver.manage().window().maximize();
		
		
		log.info("Browser launched and maximized");
		log.info("Go to " + prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		log.info(prop.getProperty("url").concat(" launched!"));
		
		//Universal Implicit Wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Onboardingpage op = new Onboardingpage(driver);
		op.waitForAngularRequestsToFinish();
	}
	

	@Test (priority=0)
	public void verifyProductMenuIsActive() throws InterruptedException {
		String vUsername = prop.getProperty("vUsername");
		String vMerchantCode = prop.getProperty("vMerchantCode"); 
		String validPassword = prop.getProperty("vPassword");
				
		Landingpage ldp = new Landingpage(driver);
		//CLICK LOGIN MENU 
		Loginpage lgp = ldp.getLoginMenu();
		
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(vUsername);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(vMerchantCode);
		Thread.sleep(2000);
				
		//Enter Password 
		lgp.getPassword().sendKeys(validPassword);

		Thread.sleep(2000);
		
		//Scroll in order to login
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		//Click Login Button 
		Homepage hp = lgp.clickLoginButton();
		
		Thread.sleep(3000);

		//CLICK PRODUCTS MENU  
		hp.getProductMenu().click();
		
		Productpage pp = new Productpage(driver);
		
		Assert.assertEquals(pp.getManageProduct().getText(), "Manage Products"); 
		
	}
	
	
	@Test(priority=1)
	public void filterProductByName() throws InterruptedException {
		Productpage pp = new Productpage(driver);
		String productName = prop.getProperty("product");
		//CLICK FILTER BUTTON 
		pp.filterProductByName(productName);
		
		Thread.sleep(2000);
		
		//CONFIRM SEARCH QUERY ENTERED
		Assert.assertEquals(driver.findElement(By.cssSelector(".tag-text")).getText(), "Name: ".concat(productName));
		
		Thread.sleep(2000);
		
		//CONFIRM RESPONSE 
		Assert.assertEquals(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), productName); 
		
		Thread.sleep(2000);
	}

	
	//VERIFY USER CAN FILTER PRODUCT BY NAME AND USING CONTAINS
	@Test(priority=2)
	public void filterProductByNameContains() throws InterruptedException {
		Productpage pp = new Productpage(driver);
		//CLEAR OLD SELECTIONS BY CLICKING CANCEL APPLY AND GET FILTER
		pp.getFilter().click();
		Thread.sleep(2000);
		pp.getCancelFilterBtn().click();
		Thread.sleep(2000);
		pp.getFilter().click();
		Thread.sleep(2000);

		String productName = prop.getProperty("partProductName");
		//CLICK FILTER BUTTON 
		pp.filterProductByName(productName);
		
		Thread.sleep(2000);

		//CONFIRM SEARCH QUERY ENTERED
		Assert.assertEquals(driver.findElement(By.cssSelector(".tag-text")).getText(), "Name: ".concat(pp.convertToSentenceCase(productName)));
		
		Thread.sleep(2000);
		List<WebElement> results = driver.findElements(By.cssSelector("table tbody tr td:nth-child(1)"));
		for(WebElement result : results) {
			String text = result.getText();
			System.out.println(text + " Main");
			System.out.println();
			if(text.contains(pp.convertToSentenceCase(productName))) {
				System.out.println(text + " Found in true");
				Assert.assertTrue(text.contains(pp.convertToSentenceCase(productName)));
			}else {
				System.out.println(text + " Found in false");
				Assert.assertFalse(text.contains(pp.convertToSentenceCase(productName)));
			}
		}
		//CONFIRM RESPONSE 
		
		Thread.sleep(2000);
	}
	
	@Test(priority=3)
	public void filterProductByCode() throws InterruptedException {
		Productpage pp = new Productpage(driver);

		//CLEAR OLD SELECTIONS BY CLICKING CANCEL APPLY AND GET FILTER
		pp.getFilter().click();
		Thread.sleep(2000);
		pp.getCancelFilterBtn().click();
		Thread.sleep(2000);
		pp.getFilter().click();
		Thread.sleep(2000);

		
		//CLEAR FILTER AND COLLAPSE FILTER MODAL
		pp.closeFilterModal(); //CLICK CLEAR FILTER
		pp.getFilter().click(); //CLICK FILTER TABLE BUTTON 
		
		
		String productCode= prop.getProperty("code");
		//CLICK FILTER BUTTON 
		pp.filterProductByCode(productCode);
		
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".tag-text")).getText(), "Code: ".concat(productCode));
		
		Thread.sleep(2000);
		
		String actual = driver.findElement(By.cssSelector("td:nth-child(2)")).getText();
		//CONFIRM RESPONSE 
		Assert.assertEquals(pp.convertToSentenceCase(actual), productCode); 
			
		Thread.sleep(2000);
	}
	
	@Test(priority=4)
	public void filterProductByCategory() throws InterruptedException {
		
		Productpage pp = new Productpage(driver);
		//CLEAR OLD SELECTIONS BY CLICKING CANCEL APPLY AND GET FILTER
		pp.getFilter().click();
		Thread.sleep(2000);
		pp.getCancelFilterBtn().click();
		Thread.sleep(2000);
		pp.getFilter().click();
		Thread.sleep(2000);
		
		
		String productCategory= prop.getProperty("category");
	
		Thread.sleep(2000);
		
		//CLEAR FILTER AND COLLAPSE FILTER MODAL
		pp.closeFilterModal(); //CLICK CLEAR FILTER
		pp.getFilter().click(); //CLICK FILTER TABLE BUTTON
	
		Thread.sleep(2000);
		
		//CLICK FILTER BUTTON 
		pp.getFilter().click();
		
		//SELECT PRODUCT CATEGORY 
		pp.selectProductCategory().selectByVisibleText(productCategory);
		
		//CLICK APPLY FILTER BUTTON 
		pp.getApplyFilterBtn().click();
		
		Thread.sleep(2000);

		//CONFIRM RESPONSE 
		Assert.assertEquals(driver.findElement(By.cssSelector("table tbody tr:first-child td:nth-child(3)")).getText(), productCategory); 
		
		Thread.sleep(2000);
		
	}
	
	
	@Test(priority=5)
	public void verifyNewProductModalCloses() throws InterruptedException {
		Productpage pp = new Productpage(driver);
		//CLEAR OLD SELECTIONS BY CLICKING CANCEL APPLY AND GET FILTER
		pp.getFilter().click();
		Thread.sleep(2000);
		pp.getCancelFilterBtn().click();
		Thread.sleep(2000);
		pp.getFilter().click();
		Thread.sleep(2000);
		
		//CLICK NEW PRODUCT BUTTON 
		pp.getNewProductButton().click();
		
		Thread.sleep(2000);
		
		//CLICK CANCEL(X) BUTTON 
		pp.closeNewProductModal();
		
		Thread.sleep(2000);
	}
	
	@Test(priority=6)
	public void verifyCancelSingleProductCreation() throws InterruptedException {
		Productpage pp = new Productpage(driver);
		
		Thread.sleep(2000);
		
		//CLICK NEW PRODUCT BUTTON 
		pp.getNewProductButton().click();
		
		Thread.sleep(2000);
		
		//CLICK SINGLE PRODUCT 
		pp.getSingleAndBulkElements().get(0).click();
		
		Thread.sleep(2000);
		
		//CLICK CANCEL BUTTON 
		pp.getCancelProductButton().click();
		
		Thread.sleep(2000);
	}
	
	
	@Test(dataProvider="getData", priority=7)
	public void createSingleProductWithoutStockTracking(
			String name, String sku, String productcategory, 
			String price, String stockQuantity, 
			String stockAction, String minStockQty, 
			String desc
			) throws InterruptedException {

		Productpage pp = new Productpage(driver);
		
		Thread.sleep(2000);
		
		//CLICK NEW PRODUCT BUTTON 
		pp.getNewProductButton().click();
		
		Thread.sleep(2000);
		
		//CLICK SINGLE PRODUCT 
		pp.getSingleAndBulkElements().get(0).click();
		
		Thread.sleep(2000);

		//SEND PRODUCT NAME
		pp.getProductNameField().sendKeys(name);
		Thread.sleep(1000);
		
		//SEND SKU 
		pp.getSKUField().sendKeys(sku); 
		Thread.sleep(1000);
		
		//SELECT PRODUCT CATEGORY 
		pp.selectProductCategory().selectByVisibleText(productcategory);
		
		Thread.sleep(1000);
		//SEND PRICE 
		pp.getProductPriceField().sendKeys(price);
		
		Thread.sleep(1000);
		//SEND QUANTITY 
		pp.getProductStockQuantityField().sendKeys(stockQuantity);
		
		Thread.sleep(1000);
		//SELECT LOW STOCK ACTION 
		pp.selectLowStockActiion().selectByVisibleText(stockAction);
		
		Thread.sleep(1000);
		//ENTER MINIMUM STOCK VALUE 
		pp.getProductMinStockField().sendKeys(minStockQty);
		
		Thread.sleep(1000);
		//ENTER DESCRIPTION 
		pp.getProductDescriptionField().sendKeys(desc);
	
		Thread.sleep(2000);

		pp.getCreateProductButton().click();
	
		Thread.sleep(2000);
	}
	
	@Test(dataProvider="getData", priority=8)
	public void createSingleProductWithStockTracking(
			String name, String sku, String productcategory, 
			String price, String stockQuantity, 
			String stockAction, String minStockQty, 
			String desc,
			String stockTracking, String canExpire, String imagePath
			) throws InterruptedException {
		
		Productpage pp = new Productpage(driver);
		
		Thread.sleep(2000);
		
		//CLICK NEW PRODUCT BUTTON 
		pp.getNewProductButton().click();
		
		Thread.sleep(2000);
		
		//CLICK SINGLE PRODUCT 
		pp.getSingleAndBulkElements().get(0).click();
		
		Thread.sleep(2000);

		//SEND PRODUCT NAME
		pp.getProductNameField().sendKeys(name);
		Thread.sleep(1000);
		
		//SEND SKU 
		pp.getSKUField().sendKeys(sku); 
		Thread.sleep(1000);
		
		//SELECT PRODUCT CATEGORY 
		pp.selectProductCategory().selectByVisibleText(productcategory);
		
		Thread.sleep(1000);
		//SEND PRICE 
		pp.getProductPriceField().sendKeys(price);
		
		Thread.sleep(1000);
		//SEND QUANTITY 
		pp.getProductStockQuantityField().sendKeys(stockQuantity);
		
		Thread.sleep(1000);
		//SELECT LOW STOCK ACTION 
		pp.selectLowStockActiion().selectByVisibleText(stockAction);
		
		Thread.sleep(1000);
		//ENTER MINIMUM STOCK VALUE 
		pp.getProductMinStockField().sendKeys(minStockQty);
		
		Thread.sleep(1000);
		//ENTER DESCRIPTION 
		pp.getProductDescriptionField().sendKeys(desc);
	
		Thread.sleep(2000);
	
		//CHECK STOCK TRACKING 
		if(Boolean.parseBoolean(stockTracking)==true) {
			pp.getProductCheckBoxes().get(0).click();
		}
		Thread.sleep(2000);
		
		//CHECK CAN EXPIRE
		if(Boolean.parseBoolean(canExpire)==true) {
			pp.getProductCheckBoxes().get(1).click();
		}
		
		
		Thread.sleep(2000);
		
		if(imagePath!=null) {
			pp.uploadProductImage(imagePath);
		}

		//CLICK CREATE PRODUCT BUTTON 
		pp.getCreateProductButton().click();
	
		Thread.sleep(2000);
	}
	
	@Test(dataProvider="getData", priority=9)
	public void createSingleProductWithImage(
			String name, String sku, String productcategory, 
			String price, String stockQuantity, 
			String stockAction, String minStockQty, 
			String desc,
			String stockTracking, String canExpire, String imagePath
			) throws InterruptedException {
		Productpage pp = new Productpage(driver);
		
		Thread.sleep(2000);
		
		//CLICK NEW PRODUCT BUTTON 
		pp.getNewProductButton().click();
		
		Thread.sleep(2000);
		
		//CLICK SINGLE PRODUCT 
		pp.getSingleAndBulkElements().get(0).click();
		
		Thread.sleep(2000);

		//SEND PRODUCT NAME
		pp.getProductNameField().sendKeys(name);
		Thread.sleep(1000);
		
		//SEND SKU 
		pp.getSKUField().sendKeys(sku); 
		Thread.sleep(1000);
		
		//SELECT PRODUCT CATEGORY 
		pp.selectProductCategory().selectByVisibleText(productcategory);
		
		Thread.sleep(1000);
		//SEND PRICE 
		pp.getProductPriceField().sendKeys(price);
		
		Thread.sleep(1000);
		//SEND QUANTITY 
		pp.getProductStockQuantityField().sendKeys(stockQuantity);
		
		Thread.sleep(1000);
		//SELECT LOW STOCK ACTION 
		pp.selectLowStockActiion().selectByVisibleText(stockAction);
		
		Thread.sleep(1000);
		//ENTER MINIMUM STOCK VALUE 
		pp.getProductMinStockField().sendKeys(minStockQty);
		
		Thread.sleep(1000);
		//ENTER DESCRIPTION 
		pp.getProductDescriptionField().sendKeys(desc);
	
		Thread.sleep(2000);
	
		//CHECK STOCK TRACKING 
		if(Boolean.parseBoolean(stockTracking)==true) {
			pp.getProductCheckBoxes().get(0).click();
		}
		Thread.sleep(2000);
		
		//CHECK CAN EXPIRE
		if(Boolean.parseBoolean(canExpire)==true) {
			pp.getProductCheckBoxes().get(1).click();
		}
		
		
		Thread.sleep(2000);
		
		if(imagePath!=null) {
			pp.uploadProductImage(imagePath);
		}

		//CLICK CREATE PRODUCT BUTTON 
		pp.getCreateProductButton().click();
	
		Thread.sleep(2000);
	}
//	@Parameters({"Halogen Bulb 70WATT"})
	@Test(priority=10)
	public void verifyCreatedProducts(@Optional("Halogen Bulb 70WATT") String name) throws InterruptedException {
		Productpage pp = new Productpage(driver);
		//CLICK FILTER BUTTON 
		pp.filterProductByName(name);
		
		Thread.sleep(2000);
		
		//CONFIRM SEARCH QUERY ENTERED
		Assert.assertTrue(driver.findElement(By.cssSelector(".tag-text")).isDisplayed());
		
		Thread.sleep(2000);
	}
	
	
	
	//WRITE TEST CASE TO VERIFY PRODUCT UPDATE 
	/*
	 * VERIFY USER CAN UPDATE PRODUCT NAME 
		VERIFY USER CAN UPDATE SKU 
		VERIFY USER CAN UPDATE PRODUCT CATEGORY 
		VERIFY USER CAN UPDATE PRICE 
		VERIFY USER CAN UPDATE STOCK QUANTITY 
		VERIFY USER CAN UPDATE LOW STOCK ACTION 
		VERIFY USER CAN UPDATE MINIMUM STOCK UPDATE 
		VERIFY USER CAN UPDATE DESCRIPTION 
		VERIFY USER CAN UPDATE STOCK TRACKING UNCHECK 
		VERIFY USER CAN UPDATE CAN EXPIRE UNCHECK 
		VERIFY USER CAN UPDATE CAN EXPIRE IS ACTIVE
	 * */
	
	@DataProvider(name="getData")
	public Object[][] getData(){
		Object[][] data=new Object[1][11];
/*		//0th row
		data[0][0]="Halogen Bulb 50WATT";
		data[0][1]="HAL04";
		data[0][2]="Phones";
		data[0][3]="50000";
		data[0][4]="10";
		data[0][5]="Invisible";
		data[0][6]="30";
		data[0][7]="Original Halogen Bulb";
		data[0][8]="true"; //STOCK TRACKING 
		data[0][9]="false"; //CAN EXPIRE 
		data[0][10]="null"; //IMAGE PATH
*/		
		//1st row
		data[0][0]="Halogen Bulb 70WATT";
		data[0][1]="HAL07";
		data[0][2]="Phones";
		data[0][3]="10000";
		data[0][4]="30";
		data[0][5]="Invisible";
		data[0][6]="10";
		data[0][7]="Original Halogen Bulb";
		data[0][8]="true"; //STOCK TRACKING
		data[0][9]="true"; //CAN EXPIRE
		data[0][10]="C:\\Users\\David Alola\\Desktop\\halogen1.jpg"; //IMAGE PATH
		
		return data;
	}
	
	/*SHOPS SECTION*/
	@Test(priority=11)
	public void verifyCanAccessShopMenu() throws InterruptedException {
		Productpage pp = new Productpage(driver); 
		pp.getShops().click();
		Thread.sleep(2000);
		
		Assert.assertTrue(pp.getAddShopBtn().isDisplayed());
		Thread.sleep(2000);	
	}
	
	/*SHOPS SECTION*/
	@Test(priority=11)
	public void verifyFilterShopByName() throws InterruptedException {
		String vUsername = prop.getProperty("vUsername");
		String vMerchantCode = prop.getProperty("vMerchantCode"); 
		String validPassword = prop.getProperty("vPassword");
				
		Landingpage ldp = new Landingpage(driver);
		//CLICK LOGIN MENU 
		Loginpage lgp = ldp.getLoginMenu();
		
		//Enter Username 
		Thread.sleep(2000);
		lgp.getUsername().sendKeys(vUsername);

		Thread.sleep(2000);
		//Enter merchantCode 
		lgp.getMerchantCode().sendKeys(vMerchantCode);
		Thread.sleep(2000);
				
		//Enter Password 
		lgp.getPassword().sendKeys(validPassword);

		Thread.sleep(2000);
		
		//Scroll in order to login
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,600)");
		
		//Click Login Button 
		Homepage hp = lgp.clickLoginButton();
		
		Thread.sleep(2000);
		hp.getProductMenu().click();
		Thread.sleep(2000);
		
		//START HERE 	
		Productpage pp = new Productpage(driver); 
		
		//CLICK FILTER BUTTON 
		pp.getFilter().click();
		Thread.sleep(2000);
		
		//ENTER SHOP NAME 
		pp.getShopName().sendKeys(null);
		Thread.sleep(2000);
		
		
		
		
	}
	@AfterTest
	public void quit() {
		driver.quit();
	}
}
