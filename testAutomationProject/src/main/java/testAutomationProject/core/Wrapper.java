package testAutomationProject.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrapper {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
	
	public static WebDriver getWebDriver() {
		
		return driver;
		
	}
	
	public static void setWebDriver(final WebDriver newDriver) {
		
		driver = newDriver;
		wait = new WebDriverWait(driver, 120);
		
	}	
	
	public static WebDriverWait getWebDriverWait() {
		
		return wait;
		
	}
	
	
	
    
    
    
    
	

}
