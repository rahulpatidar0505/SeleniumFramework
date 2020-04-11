package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    WebDriver driver;

	public HomePage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	@FindBy(xpath="//input[@name='txtUsername']")WebElement email;
	@FindBy(xpath="//input[@name='txtPassword']")WebElement password;
	@FindBy(xpath="//input[@name='Submit']")WebElement loginButton;
	
	public void login() throws InterruptedException {
		email.sendKeys("admin");
		password.sendKeys("admin");
		
		Thread.sleep(3000);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+loginButton.getLocation().y+")"); 
		loginButton.click();
	}
	public String getApplicationTitle() {
		String applicationTitle = driver.getTitle();
		return applicationTitle;
	}
	/*@FindBy(xpath = "//a[text()='Sign In']")
	WebElement signInLink;
	@FindBy(xpath = "//a[text()='My Account']")
	WebElement myAccountLink;
	@FindBy(xpath = "//a[text()='My cart']")
	WebElement myCartLink;
	@FindBy(xpath = "//a[text()='Checkout']")
	WebElement checkoutLink;

	public void clickOnSignInLink() {
		signInLink.click();

	}

	public void clickOnMyAccountLink() {
		myAccountLink.click();

	}

	public void clickOnMyCartLink() {
		myCartLink.click();

	}

	public void clickOnCheckotLink() {
		checkoutLink.click();

	}

	public String getApplicationTitle() {
		String applicationTitle = driver.getTitle();
		return applicationTitle;
	}*/
}
