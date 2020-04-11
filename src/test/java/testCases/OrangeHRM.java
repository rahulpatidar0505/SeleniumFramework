package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class OrangeHRM {
	
	WebDriver driver;

	@Test
	public void login() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rahul.patidar\\Documents\\Learning\\FreeCRMFramework\\FreeCRMFramework\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://opensource.demo.orangehrmlive.com/");
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin");
	
		WebElement button1=driver.findElement(By.name("Submit"));
		Actions act=new Actions(driver);
		act.moveToElement(button1).build().perform();

		button1.click();
		/*JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+button1.getLocation().x+")");
		button1.click();
		*/
		
		
		
		
		
		

	}
}
