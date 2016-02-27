package tests;

import org.testng.annotations.Test;



import pages.NSEPage;
import utils.Utils;


public class NSETestCases extends BaseTest {
	
	/***
	 * Check if %Chng is correctly calculated based on opening value and the change
	 */
	@Test
	public void verifyPercentChangeOrder()
	{
		
		String pageURL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm#";
		Utils.logDetails("Open Page : " + pageURL);
		driver.get(pageURL );
		new NSEPage(driver).verifyPercentChangeOrder();
	}
	
	@Test
	public void countMatch()
	{
		
		String pageURL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm#";
		Utils.logDetails("Open Page : " + pageURL);
		driver.get(pageURL );
		new NSEPage(driver).countMatch();
	}
	
	@Test
	public void verifyPercentChange()
	{
		
		String pageURL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm#";
		Utils.logDetails("Open Page : " + pageURL);
		driver.get(pageURL );
		new NSEPage(driver).verifyPercentChange();
	}

}
