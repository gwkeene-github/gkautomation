package testAutomationProject.webPages;


import org.openqa.selenium.WebDriver;

import testAutomationProject.core.Wrapper;

public class GoogleBasePage {
	
	protected static WebDriver webDriver;

	public GoogleBasePage() {
		
		webDriver = Wrapper.getWebDriver();
	}

}
