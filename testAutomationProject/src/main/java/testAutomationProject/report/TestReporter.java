package testAutomationProject.report;

import java.io.IOException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestReporter extends Reporter {
	
	private ExtentTest extentTest;
	
	public TestReporter(String testName){
		extentTest = extent.createTest(testName);
	}
    
	public void logPass(String description) {
		
		extentTest.log(Status.PASS, description);
	}
	
	public void logFail(String description) {
		
		extentTest.log(Status.FAIL, description);
		
	}
	
	public void logInfo(String description) {
		
		extentTest.info(description);
	}
	
	public void testPass(String description) {
		
		try {
			extentTest.pass(description,  MediaEntityBuilder.createScreenCaptureFromPath(Reporter.getScreenshot(description)).build());
					
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public void testFail(String description) {

		try {
			extentTest.fail(description,  MediaEntityBuilder.createScreenCaptureFromPath(Reporter.getScreenshot(description)).build());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void infoScreenShot(String description) {
		try {
			extentTest.info(description, MediaEntityBuilder.createScreenCaptureFromPath(Reporter.getScreenshot(description)).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
