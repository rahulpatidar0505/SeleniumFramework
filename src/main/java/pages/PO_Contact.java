package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BaseClass;

public class PO_Contact extends BaseClass {

	WebDriver driver;

	public PO_Contact(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//a[text()='Contacts']")
	WebElement contactsLink;

	public void clickOnContactsLink() {
		switchToFrameByIndex(1, 10);
		contactsLink.click();
	}
}
