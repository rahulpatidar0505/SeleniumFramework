package testCases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utility.BaseClass;

public class AuthinticationTest extends BaseClass {

	@Test
	public void authinticationPopUpTest() {

		driver.get("https://admin:admin@the-internet.herokuapp.com/");
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();

	}
}
