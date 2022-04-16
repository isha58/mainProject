package testCase_mainScenarios;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import BaseClass.BaseTestClass;
import BaseClass.PageBaseClass;
import Pages.LoginPage;
import Pages.MainPage;

public class loginUsernameTest extends BaseTestClass {

//	public loginUsernameTest(WebDriver driver, ExtentTest logger) {
//		super(driver, logger);
//	}
	LoginPage loginPage;
	MainPage mpage;
	@Test
	public void loginUserTest() 
	{	
		loginPage = openApplication();
		loginPage.enterUsername();
		loginPage.enterPassword();
		loginPage.fetchOTP();
		loginPage.clickNotSignedIn();
		loginPage.chkUsername();
		loginPage.clickAllAppsandTools();

	}

}
