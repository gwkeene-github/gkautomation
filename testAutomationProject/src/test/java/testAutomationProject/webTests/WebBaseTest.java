package testAutomationProject.webTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebBaseTest{
	
	private static WebDriver driver;
	
    @BeforeClass
	private void startDriver() {

        System.setProperty("webdriver.chrome.driver", "resources\\drivers\\chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
	}
    
    @AfterClass
    private void quitBrowser() {
    	
    	driver.quit();  	
    	
    }

    protected void navigate(String url) {
    	
    	driver.get(url);
    }
	

}
