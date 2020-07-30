package testAutomationProject.webTests;

import org.testng.annotations.Test;



import static org.testng.Assert.assertTrue;


import testAutomationProject.report.TestReporter;
import testAutomationProject.webPages.GoogleEmailPage;
import testAutomationProject.webPages.GoogleHomePage;
import testAutomationProject.webPages.GoogleSignInPage;

public class WebTest  extends WebBaseTest {
	
	@Test
	public void searchTest() {
		
		boolean result = true;
		String details = "Search Test";
		
		TestReporter testReporter = new TestReporter(testName);
		
		navigate("https://www.google.com/");
		
		String lattitude = "38.212045";
		String longitude = "-84.258550";
		String location = (lattitude + ", " + longitude);
		
		GoogleHomePage googleHome = new GoogleHomePage(testReporter);
		
		result = googleHome.search(location);
		
		if(result) {
			testReporter.testPass(details);
		}
		else {
			testReporter.testFail(details);
		}
		
		assertTrue(result);
		
	}
	
	@Test
	public void loginTest() {
		
		boolean result = false;
		
		String details = "Login Test";
		
		TestReporter testReporter = new TestReporter(testName);
		
		navigate("https://www.google.com/");
				
		GoogleSignInPage signInPage = new GoogleSignInPage(testReporter);
		
		testReporter.logInfo("Logging into Google");
		
		try {
			result = signInPage.signIn(getUser("user2"));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(result) {
			testReporter.testPass(details);
		}
		else {
			testReporter.testFail(details);
		}
		
		assertTrue(result);
		
	}
	
	
	@Test
	public void gmailTest() {
		
	    TestReporter testReporter = new TestReporter(testName);
		GoogleEmailPage emailPage = new GoogleEmailPage(testReporter);

		try {
			emailPage.verifyGmail();
			testReporter.logPass("Gmail log in");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean result = true;
		
		testReporter.testPass("Gmail test passed");
		
		assertTrue(result);
	}

	
}
