package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Utils;

public class NSEPage {

	public String change_Percent = "(//table[@id='dataTable']//tr//td/a[contains(@href,'get_quote')]//parent::td//following-sibling::td[Constant0])[Constant1]";
	public String change_Percent_Header = "//table[@id='dataTable']//tr[@class='alt sel']//td[9]";
	public String color = "//table[@id='dataTable']//tr//td/a[contains(@href,'get_quote')]//parent::td//following-sibling::td[Constant0][@class='number Constant1']";
	public String total_Rows ="//table[@id='dataTable']//tr//td/a[contains(@href,'get_quote')]";
	public String advances = "//ul[@id='advDecData']//li[Constant0]";
	
	WebDriver driver;
	
	public NSEPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void fetchChanges()
	{
		
	}
	
	public void verifyPercentChange()
	{
		int no_Of_Rows = Utils.totalCount(driver, total_Rows);
		Utils.logDetails("Total No of Rows : " + no_Of_Rows);
		boolean mismatch = false;
		
		for(int i = 1; i <= no_Of_Rows; i++)
		{
			String open = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "3", i+""))).getText();
			open = open.replaceAll(",", "").trim();
			String change = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "7", i+""))).getText();
			String changePercentFromPage = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "8", i+""))).getText();
			change = change.replaceAll(",", "").trim();
			double changePercent = ( Double.parseDouble(change) * 100) / Double.parseDouble(open) ;
			double roundOff = Math.round(changePercent * 100.0) / 100.0;
			double doubleChangePercentage = Double.parseDouble(changePercentFromPage);
			Utils.logDetails("Open : " + open + " Change : " + change + " Calculated ChangePercent : " + roundOff + " ChangePercent From Page : " + doubleChangePercentage);
			if(roundOff == doubleChangePercentage)
			{
				Utils.logErrorDetails("There is no mismatch between Calculated ChangePercent : " + roundOff + " ChangePercentFromPage : " + doubleChangePercentage, false);
			}
			else
			{
				Utils.logErrorDetails("There is mismatch between Calculated ChangePercent : " + roundOff + " ChangePercent From Page : " + doubleChangePercentage, true);
				mismatch = true;
			}
				
		}
		
		if(mismatch)
			Assert.fail("There is mismatch between caluculated change% and change% rendered in page");
	}
	
	
	
	public void verifyPercentChangeOrder()
	{
		int no_Of_Rows = Utils.totalCount(driver, total_Rows);
		Utils.logDetails("Total No of Rows : " + no_Of_Rows);
		String intValue = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "8", "1"))).getText();
		double intialValue = Double.parseDouble(intValue);
		boolean mismatch = false;
		
		for(int i = 2; i <= no_Of_Rows; i++)
		{
			String value = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "8", i+""))).getText();
			Utils.logDetails("% Change : " + value);
			double val = Double.parseDouble(value);
			if(!(intialValue >= val))
			{
				int temp = i - 1;
				Utils.logErrorDetails("Value in row " + temp + " is greater than that in  " + temp, false);
				mismatch = true;
			}
			intialValue = val;
		}
		
		String headerPercentage = driver.findElement(By.xpath(change_Percent_Header)).getText();
		Utils.logDetails("% Change in NIFTY 50 : " + headerPercentage);
		
		if(mismatch)
			Assert.fail("The percentage change is not in decreasing order");
	}
	
	/****
	 * verify advances and declines count
	 */
	public void countMatch()
	{
		String locator = Utils.formatLocator(color, "8", "green");
		Utils.waitForElement(driver, locator, 10);
		int green_Count = Utils.totalCount(driver, locator);
		Utils.logDetails("Total no of green : " + green_Count);
		
		locator = Utils.formatLocator(color, "8", "red");
		Utils.waitForElement(driver, locator, 10);
		int red_Count = Utils.totalCount(driver, locator);
		Utils.logDetails("Total no of red : " + red_Count);
		
		locator = Utils.formatLocator(color, "8", "black");
//		Utils.waitForElement(driver, locator, 10);
		int black_Count = Utils.totalCount(driver, locator);
		Utils.logDetails("Total no of black : " + black_Count);
		
		System.out.println(green_Count + " " + red_Count + " " + black_Count);
		boolean mismatch = false;
		
		int totalAdvances = Integer.parseInt(Utils.patternMatcher(driver.findElement(By.xpath(Utils.formatLocator(advances, "1"))).getText(), "[0-9]+"));
		Utils.logDetails("Total no of Advances : " + totalAdvances);
		if(totalAdvances != green_Count)
		{
			Utils.logErrorDetails("There is mismatch between no. of advances and no. of +ve change", false);
			mismatch = true;
		}
		else
		{
			Utils.logErrorDetails("There is no mismatch between no. of advances and no. of +ve change", true);
		}
		
		int totalDeclines = Integer.parseInt(Utils.patternMatcher(driver.findElement(By.xpath(Utils.formatLocator(advances, "2"))).getText(), "[0-9]+"));
		Utils.logDetails("Total no of Declines : " + totalDeclines);
		if(totalDeclines != red_Count)
		{
			Utils.logErrorDetails("There is mismatch between no. of Declines and no. of -ve change", false);
			mismatch = true;
		}
		else
		{
			Utils.logErrorDetails("There is no mismatch between no. of Declines and no. of -ve change", true);
		}
		
		int totalUnchanged = Integer.parseInt(Utils.patternMatcher(driver.findElement(By.xpath(Utils.formatLocator(advances, "3"))).getText(), "[0-9]+"));
		Utils.logDetails("Total no of Unchanged : " + totalUnchanged);
		if(totalUnchanged != black_Count)
		{
			Utils.logErrorDetails("There is mismatch between no. of Unchanged and no. of items with no change", false);
			mismatch = true;
		}
		else
		{
			Utils.logErrorDetails("There is no mismatch between no. of Unchanged and no. of items with no change", true);
		}
		
		if(mismatch)
			Assert.fail("There is mismatch");
	}
	
}
