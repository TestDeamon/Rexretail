package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.google.common.io.Files;

public class Base {
	public WebDriver driver; 
	public Properties prop; 
	

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties(); 

		String location = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
		String driverLocation = System.getProperty("user.dir") + "\\src\\main\\java\\resources";
		FileInputStream fis = new FileInputStream(location);
		
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
        FirefoxOptions fireFoxOptions = new FirefoxOptions();
        fireFoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        
		prop.load(fis);
		
		String browser = prop.getProperty("browser"); 
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverLocation + "\\chromedriver.exe");
			driver = new ChromeDriver(chromeOptions);
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverLocation + "\\geckodriver.exe");
			driver = new FirefoxDriver(fireFoxOptions);
			
		}else if(browser.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}else {
			System.out.println("Please enter a valid browser name");
		}
		
		return driver;
	}
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException{
		TakesScreenshot shot = (TakesScreenshot) driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\" +testCaseName+ ".png";
		Files.copy(source, new File(destinationFile));
		return destinationFile;
	}
	
}
