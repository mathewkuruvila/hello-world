package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Utils {
	
	/***
	 * Check if element is visible
	 * @param timeout
	 * @param locator
	 * @param driver
	 */
	public static void waitForElement(WebDriver driver, String locator, int timeout)
	{
		for(int i = 0; i < timeout; i++)
		{
			if(driver.findElement(By.xpath(locator)).isDisplayed())
			{
				return;
			}
			
			waitFor(timeout);
		}
		
		Assert.assertFalse(true, "Element " + locator + " is not visible after timeout of " + timeout + " seconds");
	}
	
	/***
	 * Wait for n seconds
	 * @param timeout
	 */
	public static void waitFor(int timeout)
	{
		try
		{
			Thread.sleep(1000 * timeout);
		}
		catch(Exception e)
		{
			
		}
	}
	
	/***
	 * Format locator
	 * @param locator
	 * @param values
	 * @return
	 */
	public static String formatLocator(String locator, String... values)
	{
		for(int i = 0; i < values.length; i++)
		{
			locator = locator.replaceAll("Constant"+ i, values[i]);
		}
		
		return locator;
	}
	
	/***
	 * get the webelement
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static WebElement getWebElement(WebDriver driver, String locator)
	{
		return driver.findElement(By.xpath(locator));
	}
	
	/***
	 * Fetch total count of locators
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static int totalCount(WebDriver driver, String locator)
	{
		waitForElement(driver, locator, 10);
		int no_Of_Rows = driver.findElements(By.xpath(locator)).size();
		return no_Of_Rows;
	}

}
