package Pages;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import BaseClass.PageBaseClass;


public class LoginPage extends PageBaseClass {

	public String otpcode;
	Scanner sc=new Scanner(System.in);
	public LoginPage(WebDriver driver) {//, ExtentTest logger) {
		super(driver);//, logger);
	}

	public void enterUsername()
	{

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@name='loginfmt']")).sendKeys(" ");
		
		driver.findElement(By.id("idSIButton9")).click();				
	}
	
	public void enterPassword()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("passwd")).sendKeys(" ");
		driver.findElement(By.id("idSIButton9")).click();
	}
	public void fetchOTP()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[contains(text(),\"Text +XX \")]")).click();
		System.out.println("Enter the OTP :");
		otpcode=sc.next();
		driver.findElement(By.name("otc")).sendKeys(otpcode);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	public void clickNotSignedIn()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("idBtn_Back")).click();
	}
	public void chkUsername()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String uname=driver.findElement(By.id("user-name")).getText();
		uname.replaceAll("[\\n\\t ]", "");
		if(uname.equalsIgnoreCase(" "))
		//if(uname.equalsIgnoreCase("Verma, Isha (Contractor)")) ********* in excel sheet
			System.out.println("Accepted");
		else
			System.out.println("Rejected");
	//	Assert.assertEquals(uname, "Verma, Isha (Contractor)");//in excel sheet
	}
	
	//separate page
	public void clickAllAppsandTools() 
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id=\"appSlide-0\"]/a")).click();
		WebElement inbox=driver.findElement(By.xpath("//input[starts-with(@id,'genericSearch')]"));
		inbox.sendKeys("i");
		inbox.sendKeys(Keys.ENTER);
		//driver.findElement(By.xpath("//span[text()='Search']")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
