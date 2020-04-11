package testCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import utility.BaseClass;

public class VerifyOrangeHRMHomePageFail extends BaseClass {
	@Test
	public void testHomePage() throws InterruptedException {

		HomePage home = PageFactory.initElements(driver, HomePage.class);
		home.login();
		String title = home.getApplicationTitle();
		try {
			Assert.assertTrue(title.contains("google"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

	}
	/*@Test
	public void testAdminPage() {
		AdminPage admin=PageFactory.initElements(driver, AdminPage.class);
		admin.clickonAdminLink();
		admin.enterUserName();
		admin.selectRole();
		admin.enterEmpName();
		admin.selectStatus();
	}*/
}
