package testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.PO_Contact;
import utility.BaseClass;

public class ContactTest extends BaseClass {

	@Test(priority = 0)
	public void clickOnNewEvent() throws Exception {

		PO_Contact po = PageFactory.initElements(driver, PO_Contact.class);
		po.clickOnContactsLink();
	}

	@Test(priority = 1)
	public void verifyPageTitle() throws Exception {

		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "CRMPRO");
	}

}
