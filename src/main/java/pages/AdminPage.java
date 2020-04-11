package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AdminPage {
	WebDriver driver;

	public AdminPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(xpath = "//a[@id='menu_admin_viewAdminModule']")
	WebElement adminLink;
	@FindBy(xpath = "//input[@id='searchSystemUser_userName']")
	WebElement username;
	@FindBy(xpath = "//select[@id='searchSystemUser_userType']")
	WebElement userroleoption;
	@FindBy(xpath = "//input[@id='searchSystemUser_employeeName_empName']")
	WebElement employeename;
	@FindBy(xpath = "//select[@id='searchSystemUser_status']")
	WebElement status;

	public void clickonAdminLink() {
		Actions act=new Actions(driver);
		act.moveToElement(adminLink).build().perform();
		adminLink.click();
	}

	public void enterUserName() {
		username.sendKeys("rahul");
	}

	public void selectRole() {
		Select sel = new Select(userroleoption);
		sel.selectByIndex(1);
	}

	public void enterEmpName() {
		employeename.sendKeys("patidar");
	}

	public void selectStatus() {
		Select sel = new Select(status);
		sel.selectByIndex(1);
	}

}
