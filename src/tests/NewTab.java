package tests;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTab 
{
	
	public WebDriver driver;
	
//	@Test  
	public void Newtab() throws Exception 
	{
		driver.get("http://www.seleniumlearn.com"); 
		driver.findElement(By.linkText("Home")).sendKeys(Keys.CONTROL, "t");
		driver.navigate().to("http://in.linkedin.com/in/purushothamk"); 
//		driver.findElement(By.linkText("Personal Website")).sendKeys(Keys.CONTROL, "n");
		Set<String> window1=driver.getWindowHandles();
		Object s[]=window1.toArray();
		driver.switchTo().window(s[1].toString());
		driver.get("https://twitter.com/seleniumlearn"); 
	}
	
	@BeforeTest
	public void beforeTest() 
	{
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public static void main(String str[])
	{
		double d1 =   0.23;
		double d2 =  0.23;
		if(d1 == d2)
		{
			System.out.println("Success");
		}
		else
			System.out.println("Failed");
		
		Double dd1 = new Double( 0.23);
		Double dd2 = new Double( 0.23);
		
		if(dd1 == dd2)
		{
			System.out.println("Success");
		}
		else
			System.out.println("Failed");
		
		
		double ddd1 = dd1;
		double ddd2 = dd2;
		
		if(ddd1 == ddd2)
		{
			System.out.println("Success");
		}
		else
			System.out.println("Failed");
	}
}

