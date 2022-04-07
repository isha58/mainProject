package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptureScreenshot {
	
	
	/****************** Capture Full Screen Shot ***********************/
	public static void takeScreenShot(WebDriver driver) {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		try {
			
			FileUtils.copyFile(sourceFile, destFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/****************** Capture Element Screen Shot  ***********************/
	public static void takeScreenShot(WebElement element) {
		File sourceFile = element.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		try {
			
			FileUtils.copyFile(sourceFile, destFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
