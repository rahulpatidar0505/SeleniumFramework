package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BaseClass;

public class PO_Calendar extends BaseClass {

	@FindBy(linkText = "CALENDAR")
	WebElement calendarLink;

	public void clickOnCalendar() {
		switchToFrameByIndex(1, 10);
		calendarLink.click();
	}

}
