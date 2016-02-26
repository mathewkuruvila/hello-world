package tests;

import org.testng.annotations.Test;

import pages.NSEPage;

public class NSETestCases extends BaseTest {
	
	/***
	 * Check if %Chng is correctly calculated based on opening value and the change
	 */
	@Test
	public void changeTest()
	{
		
		String pageURL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/equities_stock_watch.htm#";
		driver.get(pageURL );
//		new NSEPage(driver).verifyPercentChangeOrder();
//		new NSEPage(driver).countMatch();
		new NSEPage(driver).verifyPercentChange();
	}

}
