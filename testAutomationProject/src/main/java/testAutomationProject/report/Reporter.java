package testAutomationProject.report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import testAutomationProject.core.Wrapper;
import testAutomationProject.utils.LocalFileUtils;


public class Reporter {
	
	public static final String REPORTS_FOLDER_PATH = "test_reports";
	public static final String SCREENSHOTS_FOLDER = "Screenshots";
	public static File file = null;
	
	protected static WebDriver webDriver;
	protected static String browserT; 
	
	private static File reportFolder;
	private static File screenshotsFolder;
	protected static ExtentReports extent;

	
	/**
	 * TODO: Suggestion: Each Test Instance (each Class in each *bundle package) "HAS A" reporter
	 * Class constructor	
	 */
	public Reporter() {}
	
	/**
	 * Initialize array list to hold results
	 */
	public static void initialize(String browser) {
	
		initFolders();
		
		setBrowserType(browser);	
		

	}
	
	public static void setExtent(ExtentHtmlReporter html) {
		
        extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	
	
	private static void initFolders() {
		final String reportPath = String.format("%s", REPORTS_FOLDER_PATH);
		final String screenshotsPath = String.format("%s\\%s", reportPath, SCREENSHOTS_FOLDER);
	
		try {
			reportFolder = new File(reportPath);
			boolean  result = reportFolder.canWrite();
			//System.out.println(result);
			if (!reportFolder.exists()) {
				result = reportFolder.mkdirs();
			
			}
			
			screenshotsFolder = new File(screenshotsPath);
			if (!screenshotsFolder.exists()) {
			//	System.out.println(screenshotsFolder.getAbsolutePath());
				screenshotsFolder.mkdirs();
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	private static String getFileTimestamp() {
		
		return new SimpleDateFormat("MM-dd-yyyy@HHmmss", Locale.US).format(new Date());
	}

	/**
	 * Takes a screenshot of the currently displayed page in the web application
	 * 
	 * @param testName Name the test for which a screenshot is to be captured.
	 * @return
	 */
	public static String getScreenshot(String testName) {
	
		final String browserType = browserT;
		String timeStamp = getFileTimestamp();
		String screenshotFilename = String.format("%s(%s)%s.png", testName, browserType, timeStamp);
		
		String relativePath = String.format(".\\%s\\%s", SCREENSHOTS_FOLDER, screenshotFilename);
		
		// System.out.println(relativePath);
		
		WebDriver driver = Wrapper.getWebDriver();
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
        // build the filepath and filename
		String filepath = String.format("%s\\%s", screenshotsFolder.getPath(), screenshotFilename);
        File screenshotFile = new File(filepath);
        
        // Save to the Screenshots Directory
        try {
        	LocalFileUtils.copyFile(screenshot.toString(), screenshotFile.toString());
        	// System.out.println(screenshotFile.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
      
        return relativePath;
	}
	
		
	public static void setBrowserType(String browser) {
		
		browserT = browser;
	}
		
	public static String getReportPath() {
		return reportFolder.getPath();
	}
	
	public static void flushReport() {
		 extent.flush();
	}
	
	
}
