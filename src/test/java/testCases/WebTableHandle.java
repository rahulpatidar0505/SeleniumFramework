package testCases;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebTableHandle {

	WebDriver driver;
	
	@Test
	public void handleWebTable() {
		System.setProperty("webdriver.chrome.driver", "E:\\BrowserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.w3schools.com/html/html_tables.asp");
		
		//*[@id="customers"]/tbody/tr[2]/td[1]
		//*[@id="customers"]/tbody/tr[3]/td[1]
		//*[@id="customers"]/tbody/tr[4]/td[1]
		
		String beforeXpath="//*[@id=\"customers\"]/tbody/tr[";
		String afterXpath="]/td[1]";
		
		List<WebElement>comany=driver.findElements(By.xpath("//*[@id='customers']/tbody/tr"));
		
		for (int i = 2; i <=comany.size(); i++) 
		{
			String absoluteXpath=beforeXpath+i+afterXpath;
			
		//	System.out.println(driver.findElement(By.xpath(absoluteXpath)).getText());
			String text=driver.findElement(By.xpath(absoluteXpath)).getText();
			if (text.equalsIgnoreCase("Ernst Handel")) {
				System.out.println(text);
				break;
			}
		}
		
		
		//*[@id="customers"]/tbody/tr[1]/th[2]
		//*[@id="customers"]/tbody/tr[2]/td[2]
		
		String afterXpath2="]/td[2]";
		
		
	List<WebElement>country=driver.findElements(By.xpath("//*[@id='customers']/tbody/tr"));
		
		for (int i = 2; i <=country.size(); i++) 
		{
			String absoluteXpath=beforeXpath+i+afterXpath2;
			
		//	System.out.println(driver.findElement(By.xpath(absoluteXpath)).getText());
			String text=driver.findElement(By.xpath(absoluteXpath)).getText();
			if (text.equalsIgnoreCase("Francisco Chang")) {
				System.out.println(text);
				break;
			}
		}
		
				
				
				
				
				
		
		
		
	}
}
