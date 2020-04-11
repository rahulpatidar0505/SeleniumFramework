package testCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import utility.BaseClass;

public class Login extends BaseClass{

	@Test
	public void login() {

		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("rahul");
		driver.findElement(By.xpath("//input[contains(@name,'lastname')]")).sendKeys("patidar");
		driver.findElement(By.xpath("//input[contains(@name,'reg_email__')]")).sendKeys("9902490505");
		driver.findElement(By.xpath("//input[contains(@name,'reg_passwd__')]")).sendKeys("rahulpatidar123");
		
		WebElement element1=driver.findElement(By.xpath("//select[contains(@name,'birthday_day')]"));
		Select sel1=new Select(element1);
		sel1.selectByIndex(21);
		
		WebElement element2=driver.findElement(By.xpath("//select[contains(@name,'birthday_month')]"));
		Select sel2=new Select(element2);
		sel2.selectByVisibleText("Sept");
		
		WebElement element3=driver.findElement(By.xpath("//select[contains(@name,'birthday_year')]"));
		Select sel3=new Select(element3);
		sel3.selectByVisibleText("1991");
		
		List<WebElement>list=driver.findElements(By.xpath("//input[@type='radio']"));
		
		/*for (WebElement webElement : list) {
			String sex=webElement.getAttribute("innerHTML");
			if (sex.contains("Male")) {
				System.out.println("-------------------------------");
				webElement.click();
			}
		}*/
		/*for (int i = 0; i < list.size(); i++) {
			String sex=list.get(i).getAttribute("value");
			if (sex.equalsIgnoreCase("Male")) {
				System.out.println("---------------------------------------------------------");
				list.get(i).click();
				break;
			}
		}*/

		// Create a boolean variable which will hold the value (True/False)
		boolean bValue = false;
		
		// This statement will return True, in case of first Radio button is selected
		bValue = list.get(0).isSelected();

		// This will check that if the bValue is True means if the first radio button is
		// selected
		if (bValue == true) {
			// This will select Second radio button, if the first radio button is selected
			// by default
			list.get(1).click();
		} else {
			// If the first radio button is not selected by default, the first will be
			// selected
			list.get(0).click();
		}
	}
}
