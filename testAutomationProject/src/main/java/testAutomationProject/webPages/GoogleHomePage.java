package testAutomationProject.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import testAutomationProject.report.TestReporter;

public class GoogleHomePage extends GoogleBasePage {
	
	private static final By GOOGLE_SEARCH_BUTTON = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]");
	private static final By GOOGLE_SEARCH = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input");
	static TestReporter googleReporter; 
	
	public GoogleHomePage(TestReporter reporter){
		googleReporter = reporter;		
	}
	

	public boolean search(String location) {
		
		boolean result = true;
		
		try {
			
			WebElement searchBox = webDriver.findElement(GOOGLE_SEARCH);
			searchBox.sendKeys(location);
			
			googleReporter.infoScreenShot("Search for coordinates");
			
			webDriver.findElement(GOOGLE_SEARCH_BUTTON).click();
		
		}catch(NoSuchElementException e) {
			googleReporter.logFail(e.getMessage());
			result = false;
		}
		
		return result;
	
	}


}
