package testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.PO_Calendar;
import utility.BaseClass;


public class CalendarTest extends BaseClass{

	@BeforeMethod
	public void setUp() throws Exception 
	{
		initialisation();
	}

	@Test
	public void clickOnNewEvent() 
	{
		PO_Calendar po = PageFactory.initElements(driver, PO_Calendar.class);
		po.clickOnCalendar();
		
	}

	@AfterMethod
	public void tearDown() 
	{
		termination();
	}
}
