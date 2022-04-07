package BaseClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.CaptureScreenshot;
import Utilities.DateUtil;
import Utilities.ReadPropertiesFile;


public class PageBaseClass extends BaseTestClass {
	
	public ExtentTest logger;
	
	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;

	}
	
	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOn() {

		try {
		CaptureScreenshot.takeScreenShot(driver);
		logger.addScreenCaptureFromPath(
				System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOn();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}
	
	public void reportInfo(String reportString) {
		logger.log(Status.INFO, reportString);
	}
	
	
	
	/****************** Select DropDown  ***********************/
	
	public void selectDropDownValue(String xpathKey, String value){
		
		try {
			
			Select select = new Select(getElement(xpathKey));
			select.selectByVisibleText(value);

		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	
	
	/****************** Switch Window ***********************/
	
	public void switchWindow() {
		try {
			String originalWindow = driver.getWindowHandle();
			for (String windowHandle : driver.getWindowHandles()) 
			{
			    if(!originalWindow.contentEquals(windowHandle)) 
			    {
			        driver.switchTo().window(windowHandle);
			        break;
			    }
			}	
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** mouse hover  ***********************/	
	public  void mouseHover(String xpathKey) {
		try {
			reportInfo("Moving cursor to " + xpathKey);
			Actions action = new Actions(driver);
			action.moveToElement(getElement(xpathKey)).build().perform();
			reportPass("Moved cursor to "+ xpathKey);
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** Verify Element is Present ***********************/
	public boolean verifyElementIsDisplayed(String xpathKey){
		try {
			if(getElement(xpathKey).isDisplayed()){
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
		
	}

	
	/****************** Identify Element ***********************/
	public WebElement getElement(String locatorKey) {
		
		ReadPropertiesFile rf=new ReadPropertiesFile();
		prop = rf.readProperties();
		
		WebElement element = null;

		try {
			if (locatorKey.endsWith("_Id")) {
				element = driver.findElement(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				element = driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				element = driver.findElement(By.className(prop.getProperty(locatorKey)));
			logger.log(Status.INFO, "Locator Identidied : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				element = driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				element = driver.findElement(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				element = driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				element = driver.findElement(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
		}

		return element;
	}
	
	/****************** Click Element ***********************/
	public void elementClick(String xpathKey) {
		try {
			reportInfo("Clicking element " + xpathKey);
			getElement(xpathKey).click();
			reportPass("Clicked element " + xpathKey);
			
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** Explicitly Wait ***********************/
	public void explicitlyWait(String xpathKey) {
		try {
			
			WebDriverWait wait = new WebDriverWait(driver,40);
			wait.until(ExpectedConditions.visibilityOf(getElement(xpathKey)));
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** Send keys ***********************/
	public void elementSendKeys(String xpathKey, String value) {
		try {
			reportInfo("Entering " + value);
			getElement(xpathKey).sendKeys(value);
			reportPass("Entered " + value);
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** Get Text ***********************/
	public String elementGetText(String xpathKey) {
		String str = "";
		try {
			str = getElement(xpathKey).getText();
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
		return str;
	}
	
	/****************** Move To Element And Click ***********************/
	
	public void elementMoveToClick(String xpathKey) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(getElement(xpathKey)).click().build().perform();
		}
		catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	/****************** Return List of WebElements ***********************/
	public List<WebElement> getElements(String locatorKey){
		
		ReadPropertiesFile rf=new ReadPropertiesFile();
		prop = rf.readProperties();
		
		List<WebElement> elements = null;
		try {
			if (locatorKey.endsWith("_Id")) {
				elements = driver.findElements(By.id(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Xpath")) {
				elements = driver.findElements(By.xpath(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_ClassName")) {
				elements = driver.findElements(By.className(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_CSS")) {
				elements = driver.findElements(By.cssSelector(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_LinkText")) {
				elements = driver.findElements(By.linkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_PartialLinkText")) {
				elements = driver.findElements(By.partialLinkText(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else if (locatorKey.endsWith("_Name")) {
				elements = driver.findElements(By.name(prop.getProperty(locatorKey)));
				logger.log(Status.INFO, "Locator Identified : " + locatorKey);
			} else {
				reportFail("Failing the Testcase, Invalid Locator " + locatorKey);
			}
		} catch (Exception e) {

			// Fail the TestCase and Report the error
			reportFail(e.getMessage());
		}

		return elements;
		 
	}
	/***************** Wait Functions *****************/
	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			reportFail(e.getMessage());
		}
	}
	
	//write error
	public void writeError(String err,String filename)
	{
		try {
		FileWriter out=new FileWriter("Outputs\\"+filename+".txt");
		out.write(err);
		out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
