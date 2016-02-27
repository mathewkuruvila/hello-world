package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import utils.Utils;

public class BaseTest {
	
	WebDriver driver;
	
	@BeforeTest(alwaysRun = true)
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
		driver = new ChromeDriver();
		
		String pageURL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm#";
		Utils.logDetails("Open Page : " + pageURL);
		driver.get(pageURL );
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}

}
