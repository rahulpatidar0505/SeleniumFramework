package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDDT {
	WebDriver driver;

	@Test(dataProvider = "testdata")
	public void TestChrome(String uname, String password) {

		System.setProperty("webdriver.chrome.driver", "D:\\SELENIUM\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.facebook.com");

		driver.findElement(By.id("email")).clear();

		driver.findElement(By.id("email")).sendKeys(uname);

		driver.findElement(By.id("pass")).clear();

		driver.findElement(By.id("pass")).sendKeys(password);
	}

	// this is DataProvider which actually feed data to our test cases here I have
	// taken 2 D //array with 2 rows and 2 column it means. It will run our test
	// case two times because we have taken 2 rows. While first iteration this
	// will pass username and password to test case and in second iteration
	// perform the same for second rows
	
	@DataProvider(name = "testdata")
	public Object[][] TestDataFeed() {

		Object[][] facebookdata = new Object[2][2];

		facebookdata[0][0] = "Selenium1@gmail.com";

		// Enter data to row 0 column 1
		facebookdata[0][1] = "Password1";

		// Enter data to row 1 column 0
		facebookdata[1][0] = "Selenium2@gmail.com";

		// Enter data to row 1 column 0
		facebookdata[1][1] = "Password2";

		// return arrayobject to testscript
		return facebookdata;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
