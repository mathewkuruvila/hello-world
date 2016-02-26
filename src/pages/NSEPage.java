package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Utils;

public class NSEPage {

	public String change_Percent = "(//table[@id='dataTable']//tr//td/a[contains(@href,'get_quote')]//parent::td//following-sibling::td[Constant0])[Constant1]";
	public String color = "//table[@id='dataTable']//tr//td/a[contains(@href,'get_quote')]//parent::td//following-sibling::td[Constant0][@class='number Constant1']";
	public String total_Rows ="//table[@id='dataTable']//tr//td/a[contains(@href,'get_quote')]";
	public String advances = "//ul[@id='advDecData']//li[Constant1]";
	
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
		System.out.println(no_Of_Rows);
		
		for(int i = 1; i <= no_Of_Rows; i++)
		{
			String open = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "3", i+""))).getText();
			open = open.replaceAll(",", "").trim();
			String change = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "7", i+""))).getText();
			change = change.replaceAll(",", "").trim();
			Double changePercent = ( Double.parseDouble(change) * 100) / Double.parseDouble(open) ;
			double roundOff = Math.round(changePercent * 100.0) / 100.0;
			System.out.println("Open : " + open + " Change : " + change + " ChangePercent : " + roundOff);
			
		}
	}
	
	
	
	public void verifyPercentChangeOrder()
	{
		int no_Of_Rows = Utils.totalCount(driver, total_Rows);
		System.out.println(no_Of_Rows);
		
		String intValue = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "8", "1"))).getText();
		Double intialValue = Double.parseDouble(intValue);
		
		for(int i = 2; i <= no_Of_Rows; i++)
		{
			String value = driver.findElement(By.xpath(Utils.formatLocator(change_Percent, "8", i+""))).getText();
			System.out.println(value);
			Double val = Double.parseDouble(value);
			if(!(intialValue >= val))
			{
				System.out.println("Mismatch between row " + i + " and above one");
			}
			intialValue = val;
		}
	}
	
	/****
	 * verify advances and declines count
	 */
	public void countMatch()
	{
		String locator = Utils.formatLocator(color, "8", "green");
		Utils.waitForElement(driver, locator, 10);
		int green_Count = Utils.totalCount(driver, locator);
		
		locator = Utils.formatLocator(color, "8", "red");
		Utils.waitForElement(driver, locator, 10);
		int red_Count = Utils.totalCount(driver, locator);
		
		locator = Utils.formatLocator(color, "8", "black");
//		Utils.waitForElement(driver, locator, 10);
		int black_Count = Utils.totalCount(driver, locator);
		
		System.out.println(green_Count + " " + red_Count + " " + black_Count);
		
		for(int i = 1; i < 4; i++)
		{
			String value = driver.findElement(By.xpath(Utils.formatLocator(advances, i+""))).getText();
			System.out.println(value);
		}
	}
	
}
