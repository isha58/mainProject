package BaseClass;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Pages.LandingPage;
import Utilities.DriverSetup;
import Utilities.ExtentReportManager;
import Utilities.ReadPropertiesFile;


public class BaseTestClass {


	public WebDriver driver;
	public Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	
	//invoking browser
	@BeforeMethod
	public void invokeBrowser(){
		try {
			driver=DriverSetup.getWebDriver();
		}
		catch(Exception e) {
			logger.log(Status.FAIL, e.getMessage());
		}
	}
	//open application
	public LandingPage openApplication() {
		
		try {
			ReadPropertiesFile rf=new ReadPropertiesFile();
			prop = rf.readProperties();
			String url = prop.getProperty("baseurl");  
			logger.log(Status.INFO, "Navigating to URL "+ url);
			driver.get(url);
		}
		
		catch(Exception e) {
			logger.log(Status.FAIL, e.getMessage());
		}
		
		LandingPage landingPage = new LandingPage(driver, logger);
		PageFactory.initElements(driver, landingPage);
		return landingPage;

	}
	
	//flushing report
	@AfterMethod
	public void flushReports() {
		report.flush();
		driver.quit();
	}
	
//	public void closeBrowser() {
//		driver.quit();
//	}

}
