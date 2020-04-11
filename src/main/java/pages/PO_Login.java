package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BaseClass;
import utility.JavaScriptExecutorHelper;

public class PO_Login extends BaseClass {

	@FindBy(xpath = "//input[@name='username']")
	WebElement userName;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit' and @value='Login']")
	WebElement loginButton;

	JavaScriptExecutorHelper js = new JavaScriptExecutorHelper();

	public void loginToApplication(String user, String pass) {
		try {
			sendkeys(driver, userName, 20, user);
			sendkeys(driver, password, 20, pass);
			js.clickElementByJS(loginButton, driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
