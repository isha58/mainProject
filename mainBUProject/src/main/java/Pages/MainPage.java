package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import BaseClass.PageBaseClass;

public class MainPage extends PageBaseClass {

	public MainPage(WebDriver driver) {
		super(driver);
	}
	public String alpha;

	public void clickAllAppsandTools() 
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"appSlide-0\"]/a")).click();
		driver.findElement(By.xpath("//input[starts-with(@id,'genericSearch')]")).sendKeys("i");
		driver.findElement(By.xpath("//span[text()='Search']")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

