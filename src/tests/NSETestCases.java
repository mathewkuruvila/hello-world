package tests;

import org.testng.annotations.Test;



import pages.NSEPage;
import utils.Utils;


public class NSETestCases extends BaseTest {
	
	/***
	 * Check if %Chng is correctly calculated based on opening value and the change
	 */
	@Test(groups = {"nseTest"})
	public void verifyPercentChangeOrder()
	{
		new NSEPage(driver).verifyPercentChangeOrder();
	}
	
	@Test(groups = {"nseTest"})
	public void countMatch()
	{
		new NSEPage(driver).countMatch();
	}
	
	@Test(groups = {"nseTest"})
	public void verifyPercentChange()
	{
		new NSEPage(driver).verifyPercentChange();
	}

}
