package testAutomationProject.webPages;



import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testAutomationProject.core.User;
import testAutomationProject.core.Wrapper;
import testAutomationProject.report.TestReporter;


public class GoogleSignInPage extends GoogleBasePage{

	private static final By GOOGLE_SIGN_IN = By.xpath("//*[text()='Sign in']");
	private static final By GOOGLE_USERNAME = By.xpath("//*[@id='identifierId']");
	private static final By GOOGLE_NEXT = By.xpath("//*[@id='identifierNext']/div/button");
	private static final By GOOGLE_PWD = By.xpath("//*[@id='password']/div[1]/div/div[1]/input");
	private static final By PASSWD_NEXT = By.xpath("//*[@id='passwordNext']/div/button");
	private static final By GOOGLE_ACCT = By.xpath("//*[@id='gbw']/div/div/div[2]/div[2]/div[1]/a");
	
	static TestReporter signInReporter; 
	
	public GoogleSignInPage(TestReporter reporter){
		signInReporter = reporter;		
	}
	
	public boolean signIn(User user) throws InterruptedException {

		boolean result = true;
		
		WebDriverWait wait = Wrapper.getWebDriverWait();
		
		try {
			// click the sign in button
			wait.until(ExpectedConditions.elementToBeClickable(GOOGLE_SIGN_IN));
			signInReporter.infoScreenShot("On sign in page");
			webDriver.findElement(GOOGLE_SIGN_IN).click();
			
			// wait for page transition and then enter username
			wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(GOOGLE_USERNAME)));
			
			WebElement userEntry = webDriver.findElement(GOOGLE_USERNAME);
			
			userEntry.sendKeys(User.username);
			
			signInReporter.infoScreenShot("Username entered");
			
			// click next button and then enter passwd and click next again. 	
			wait.until(ExpectedConditions.elementToBeClickable(GOOGLE_NEXT));
			webDriver.findElement(GOOGLE_NEXT).click();	
			
				
			wait.until(ExpectedConditions.elementToBeClickable(GOOGLE_PWD));
			
			WebElement passwdEntry = webDriver.findElement(GOOGLE_PWD);	
			
			passwdEntry.sendKeys(User.passwd);		
			signInReporter.infoScreenShot("Passwd entered");			
			webDriver.findElement(PASSWD_NEXT).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(GOOGLE_ACCT));
			String acctName = webDriver.findElement(GOOGLE_ACCT).getAttribute("title");
			result = acctName.contains("@gmail.com");
		    signInReporter.logInfo(acctName);
		
		}catch(NoSuchElementException e) {
			result = false;
		}
		
		return result;		
		
	}

}
