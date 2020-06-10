package testAutomationProject.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage extends GoogleBasePage {
	
	private static final By GOOGLE_SEARCH_BUTTON = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]");
	private static final By GOOGLE_SEARCH = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input");
	
	
	public GoogleHomePage(WebDriver driver) {
		
		webDriver = driver;
	}
	
	public void search(String location) {
		
		WebElement searchBox = webDriver.findElement(GOOGLE_SEARCH);
		searchBox.sendKeys(location);
		
		webDriver.findElement(GOOGLE_SEARCH_BUTTON).click();
		
	}


}
