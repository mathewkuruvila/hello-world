package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

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
	
	public static void logDetails(String s)
	{
		Reporter.log(s);
		System.out.println(s);
	}
	
	public static void logErrorDetails(String s, boolean status)
	{
		System.out.println(s);
		if(status)
		{
			s = "<FONT COLOR=\"GREEN\">" + s + "</FONT>";
		}
		else
		{
			s = "<FONT COLOR=\"RED\">" + s + "</FONT>";
		}
		
		Reporter.log(s);
		
	}
	
	public static String patternMatcher(String input, String regex)
	{
		String str = "";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		
		if(m.find())
		{
			str = m.group();
		}
		
		return str;
	}

}
