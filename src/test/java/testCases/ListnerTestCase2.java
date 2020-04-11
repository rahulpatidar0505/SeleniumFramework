package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ListnerTestCase2 {
	WebDriver driver;
	@Test
	public void googleTitleVerify() {
		System.setProperty("webdriver.chrome.driver", "D:\\SELENIUM\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://google.com");
		System.out.println(driver.getTitle());
		driver.quit();
	}
	@Test
	public void Title() {
		String title=driver.getTitle();
		Assert.assertEquals(title, "abc");
		
	}
}
