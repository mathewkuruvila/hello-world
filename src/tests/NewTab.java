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
	
	@Test  
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
	
}

