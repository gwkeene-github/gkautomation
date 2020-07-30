package testAutomationProject.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testAutomationProject.core.Wrapper;
import testAutomationProject.report.TestReporter;

public class GoogleEmailPage extends GoogleBasePage {
	
	private static final By GOOGLE_GMAIL = By.xpath("//*[text()='Gmail']");
	private static final By SEARCH_EMAIL = By.xpath("//*[@id='gs_lc50']/input[1]");
	private static final By SEARCH_BUTTON = By.xpath("//*[@id='aso_search_form_anchor']/button[4]");
	private static final By TEST_EMAIL = By.xpath("//*[@id=':6c']");
	
	static TestReporter emailReporter; 
	
	public GoogleEmailPage(TestReporter reporter) {
		emailReporter = reporter;
	}


	public void verifyGmail() throws Exception {
		
		boolean result = true;
		
		WebDriverWait wait = Wrapper.getWebDriverWait();
		
		try {
			
			wait.until(ExpectedConditions.elementToBeClickable(GOOGLE_GMAIL));
			
			webDriver.findElement(GOOGLE_GMAIL).click();
					
			wait.until(ExpectedConditions.elementToBeClickable(SEARCH_EMAIL));
			emailReporter.infoScreenShot("Email home displayed");
			
			WebElement searchBox = webDriver.findElement(SEARCH_EMAIL);
			
			System.out.println(searchBox.getText());
			
			searchBox.sendKeys("Test Email");
			emailReporter.infoScreenShot("Search for Test Email subject");
			
			webDriver.findElement(SEARCH_BUTTON).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(TEST_EMAIL));
			
			emailReporter.infoScreenShot("Test Email displayed");		
			webDriver.findElement(TEST_EMAIL).click();
		
		}catch(NoSuchElementException e) {
			result = false;
		}
		
		
	}

}
