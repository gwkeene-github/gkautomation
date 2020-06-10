package testAutomationProject.webTests;

import org.testng.annotations.Test;

import testAutomationProject.webPages.GoogleHomePage;

public class WebTest  extends WebBaseTest {
	
	@Test
	public void searchTest() {
		System.out.println("What up dog?");
		navigate("https://www.google.com/");
		
		String lattitude = "40.891383";
		String longitude = "-92.885032";
		String location = (lattitude + ", " + longitude);
		
		GoogleHomePage googleHome = new GoogleHomePage(getDriver());
		
		googleHome.search(location);
	}

}
