package Rexretail;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

public class HashtablePage {
	public WebDriver driver;
	// Generic type twice
    Hashtable<String, String> hashtable = new Hashtable<>();
	public HashtablePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void putElementInHashTable(String key, String value) {
        hashtable.put(key, value);  
 	}
	
	public String getElementInHashTable(String key) {
		return hashtable.get(key);  
 	}	

}
