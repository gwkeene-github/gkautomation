package testAutomationProject.webTests;

import org.testng.annotations.Test;

public class WebTest  extends WebBaseTest {
	
	@Test
	public void searchTest() {
		System.out.println("What up dog?");
		navigate("https://www.google.com/");
	}

}
