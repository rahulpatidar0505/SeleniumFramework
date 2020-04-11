package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PracticeTest {
WebDriver driver;
	
	
	@Test
	public void authinticationPopUpTest() {
		
		System.setProperty("webdriver.chrome.driver", "E:\\BrowserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.bizjournals.com/seattle/maps/puget-sound-area-crane-watch");
		driver.findElement(By.xpath("//a[contains(@class,'wide')]//strong[contains(text(),' View the Map')]")).click();
		
	}
}
