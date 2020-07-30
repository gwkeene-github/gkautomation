package testAutomationProject.webTests;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import testAutomationProject.core.User;
import testAutomationProject.core.Wrapper;
import testAutomationProject.report.Reporter;
import testAutomationProject.utils.LocalFileUtils;
import testAutomationProject.utils.MapUtil;

public class WebBaseTest{
	
	private static final User User = null;
	private static Map<String, String> testLoginData;
	protected static WebDriver driver;

	static String testName; 

	
	@BeforeSuite
	@Parameters({"loginDataFilePath"})
	public void beforeSuite(ITestContext context, String loginDataFilePath) {
		
		List<String> loginData = LocalFileUtils.readCsvFile(loginDataFilePath);
		
		testLoginData = MapUtil.hashEncryptedData(loginData);
		
		String testSuite = context.getName();
		
		Reporter.initialize("Chrome");
	        
		@SuppressWarnings("deprecation")
		String reportPath = Reporter.getReportPath(); 
		System.out.println(reportPath);
		ExtentHtmlReporter html = new ExtentHtmlReporter(reportPath + "\\"+testSuite+".html");
		
		Reporter.setExtent(html);

	}
	
    @BeforeClass
	protected void startDriver() {

        System.setProperty("webdriver.chrome.driver", "resources\\drivers\\chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Wrapper.setWebDriver(driver);
            
	}
    
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		
		// Notify the reporter that a test method block is going to be executed.
		testName = method.getName();
	
	}
	
	/**
	 * Called by TestNG framework when it is about to execute a "test" tag branch defined
	 * in the XML file. The test branch will contain one or more classes, each of which
	 * will be executed as part of the test branch.
	 */
	@BeforeTest
	public void beforeTest(ITestContext context) {
		// Notify the reporter that a TEST block is going to be executed.
		
	
	}

	/**
	 * Called after test runs, updates test count and writes results to reporter
	 */
	@AfterTest
	public void afterTest(ITestContext context) {
		
	}

	@AfterMethod
	public void afterMethod(Method method) throws InterruptedException {

	}
	
	/**
	 * Called by TestNG framework when all the test methods in the current a test class
	 * have been executed. 
	 */
	@AfterClass
	public void afterClass() {
		
		driver.quit();
		
	}
	/**
	 * Called after test suite finishes for teardown
	 */
	@AfterSuite
	public void afterSuite(ITestContext context) {

		Reporter.flushReport();
	}


    protected void navigate(String url) {
    	
    	driver.get(url);
    }
    


    protected User getUser(String id) {
    	
    	User.username = MapUtil.getHashMapValue(testLoginData, id+"name");
    	User.passwd = MapUtil.getHashMapValue(testLoginData, id+"passwd");
    	
    	return User;
    	
    	
    }
    

}
