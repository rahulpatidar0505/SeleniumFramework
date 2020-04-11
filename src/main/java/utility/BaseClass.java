package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import browserFactory.BrowserFactory;
import dataProvider.ConfigDataProvider;
import dataProvider.ExcelDataProvider;
import de.redsix.pdfcompare.PdfComparator;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class BaseClass {

	public static WebDriver driver;
	public static ExcelDataProvider reader;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String randStr = RandomStringUtils.randomAlphabetic(2);
	public String timeStamp = new SimpleDateFormat("dd_MMM_yy_HHmmss").format(Calendar.getInstance().getTime());

	@BeforeSuite
	public void reportSetup() {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "./Reports/MyReport" + "_" + randStr + "_" + timeStamp + ".html");
		htmlReporter.config().setDocumentTitle("AutomationTesting Demo Report");
		htmlReporter.config().setReportName("My Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Host Name", "Rahul");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Rahul Patidar");
		extent.attachReporter(htmlReporter);

	}

	@AfterSuite
	public void reportTeardown() {
		termination();
		extent.flush();
	}

	@BeforeTest
	public void testSetup() {

		initialisation();

	}

	@AfterTest
	public void testTeardown() throws IOException {

	}

	@AfterMethod
	public void attachSreenshot(ITestResult result) throws IOException, InterruptedException {

		String temp = captureScreenshot(driver, result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Failed " + test.addScreenCaptureFromPath(temp));
		} /*
			 * else if (result.getStatus() == ITestResult.SKIP) { test.log(Status.SKIP,
			 * "Test Skipped " + test.addScreenCaptureFromPath(temp)); } else if
			 * (result.getStatus() == ITestResult.SUCCESS) { test.log(Status.PASS,
			 * "Passed test " + test.addScreenCaptureFromPath(temp)); }
			 */
		/*
		 * if (result.getStatus() == ITestResult.FAILURE) { test.log(Status.FAIL,
		 * MarkupHelper.createLabel(result.getName() +
		 * " Test case FAILED due to below issues:", ExtentColor.RED)); String temp1 =
		 * captureScreenshot(driver, result.getName());
		 * test.fail(result.getThrowable().getMessage(),
		 * MediaEntityBuilder.createScreenCaptureFromPath(temp1).build()); } else if
		 * (result.getStatus() == ITestResult.SUCCESS) { test.log(Status.PASS,
		 * MarkupHelper.createLabel(result.getName() + " Test Case PASSED",
		 * ExtentColor.GREEN)); String temp1 = captureScreenshot(driver,
		 * result.getName()); test.pass(result.getName(),
		 * MediaEntityBuilder.createScreenCaptureFromPath(temp1).build()); } else {
		 * test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() +
		 * " Test Case SKIPPED", ExtentColor.ORANGE)); String temp1 =
		 * captureScreenshot(driver, result.getName());
		 * test.skip(result.getThrowable().getMessage(),
		 * MediaEntityBuilder.createScreenCaptureFromPath(temp1).build()); }
		 */

	}

	public static void initialisation() {
		try {
			driver = BrowserFactory.getBrowser("chrome");
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.get(ConfigDataProvider.getApplicationURL());
	}

	public static void termination() {
		BrowserFactory.closeBrowser();
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName
				+ System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (IOException e) {
			e.getMessage();
		}
		return destination;
	}

// This is for WebEventListener
	public static void captureScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");

		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}

	public static String captureScreenshotFullPage(WebDriver driver, String screenshotName) {
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(500)).takeScreenshot(driver);
		BufferedImage image = screenshot.getImage();

		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName
				+ System.currentTimeMillis() + ".png";

		try {
			ImageIO.write(image, "PNG", new File(destination));
		} catch (IOException e) {
			e.getMessage();
		}
		return destination;
	}

	public static void sendkeys(WebDriver driver, WebElement element, int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	public static void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public static void staleElementRefExceptionSolution(String xpath) {

		for (int i = 0; i <= 3; i++) {
			try {
				driver.findElement(By.id(xpath)).click();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static WebElement waitForElementPresent(WebElement element, int time) {
		for (int i = 0; i < time; i++) {
			try {
				new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated((By) element));
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					System.out.println("Waiting for element to appear on DOM");
				}
			}
		}
		return element;
	}

	public static void funcFieldValueSet(WebElement element, String typeOfElement, int timeout, String value) {
		performAction(element, typeOfElement, timeout, value);
	}

	private static void performAction(WebElement element, String typeOfElement, int timeout, String value) {
		if (typeOfElement.equalsIgnoreCase("text")) {
			waitForElementPresent(element, 5);
			sendkeys(driver, element, timeout, value);
		} else if (typeOfElement.equalsIgnoreCase("radio") || typeOfElement.equalsIgnoreCase("checkbox")) {
			if (element.isDisplayed() && !element.isSelected()) {
				waitForElementPresent(element, 5);
				clickOn(driver, element, timeout);
			}
		} else if (typeOfElement.equalsIgnoreCase("button") || typeOfElement.equalsIgnoreCase("link")
				|| typeOfElement.equalsIgnoreCase("hyperlink")) {
			if (element.isDisplayed() && element.isEnabled()) {
				waitForElementPresent(element, 5);
				clickOn(driver, element, timeout);
			}
		}
	}

	public static void selectByIndex(WebElement element, int timeout, int indexValue) {
		waitForElementPresent(element, timeout);
		Select sel = new Select(element);
		sel.selectByIndex(indexValue);
	}

	public static void selectByVisibleText(WebElement element, int timeout, String visibleText) {
		waitForElementPresent(element, timeout);
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}

	public static void selectByValue(WebElement element, int timeout, String value) {
		waitForElementPresent(element, timeout);
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void getAllOptionFromDropdown(WebElement element) {
		waitForElementPresent(element, 10);

		Select dropdown = new Select(element);

		List<WebElement> options = dropdown.getOptions();

		System.out.println("Total number of options available are ---->" + options.size());

		for (int i = 0; i < options.size(); i++) {
			System.out.println("OPtions are--->" + options.get(i).getText());
		}
	}

	/*
	 * How to check the Broken Links and images using Selenium:
	 ** 
	 * For checking the broken links, you will need to do the following steps.
	 * 
	 * 1. Collect all the links in the web page based on "a" and "img" tags. 2. Send
	 * HTTP request for the link and read HTTP response code. 3. Find out whether
	 * the link is valid or broken based on HTTP response code. 4. Repeat this for
	 * all the links captured.
	 */

	public static void getBrokenLinks() throws MalformedURLException, IOException {
		List<WebElement> linksList = driver.findElements(By.tagName("a"));
		linksList.addAll(driver.findElements(By.tagName("img")));
		System.out.println("Total number of links and images are---" + linksList.size());

		List<WebElement> activeLinks = new ArrayList<>();

		for (int i = 0; i < linksList.size(); i++) {
			System.out.println(linksList.get(i).getAttribute("href"));
			if (linksList.get(i).getAttribute("href") != null
					&& (!linksList.get(i).getAttribute("href").contains("javascript"))) {
				activeLinks.add(linksList.get(i));
			}
		}

		for (int i = 0; i < activeLinks.size(); i++) {
			HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(i).getAttribute("href"))
					.openConnection();
			connection.connect();
			String response = connection.getResponseMessage();
			connection.disconnect();
			System.out.println(activeLinks.get(i).getAttribute("href") + "---->" + response);
		}
	}

	public static void getAllLinks() {
		List<WebElement> alllinks = driver.findElements(By.tagName("a"));
		for (int i = 0; i < alllinks.size(); i++)
			System.out.println(alllinks.get(i).getText());
	}

	public static String randomStringGenerator(int length, boolean useLetters, boolean useNumbers) {

		return RandomStringUtils.random(length, useLetters, useNumbers);

	}

	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date); // 2016/11/16 12:08:43
	}

	public static void handlePagination() {

	}

	public static void acceptAlert(int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public static void dismissAlert(int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public static String getTextFromAlert(int timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public static void setTextToAlert(int timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		;
	}

	public void switchToFrameByIndex(int index, int timeOut) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void switchToFrameByName(String name, int timeOut) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
	}

	public void switchToFrameByElement(WebElement element, int timeOut) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public static void calendarSelectTest(String xpath_month, String xpath_year) {
		String date = "dd-mmm-yyyy";
		String dateArr[] = date.split("-");
		String day = dateArr[0];
		String month = dateArr[1];
		String year = dateArr[2];

		Select select = new Select(driver.findElement(By.xpath(xpath_month)));
		select.selectByVisibleText(month);

		Select select1 = new Select(driver.findElement(By.xpath(xpath_year)));
		select1.selectByVisibleText(year);

		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[1]
		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]
		// *[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[6]

		String beforeXpath = "//*[@id='crmcalendar']/table/tbody/tr[2]/td/table/tbody/tr[";
		String afterXpath = "]/td[";

		final int totalWeekDays = 7;

		// 2-1 2-2 2-3 2-4 2-5 2-6 2-7
		// 3-2 3-2 3-3 3-4 3-5 3-6 3-7
		boolean flag = false;
		String dayVal = null;
		for (int rowNum = 2; rowNum <= 7; rowNum++) {

			for (int colNum = 1; colNum <= totalWeekDays; colNum++) {
				try {
					dayVal = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();
				} catch (Exception e) {
					System.out.println("Please enter a correct date value");
					flag = false;
					break;
				}
				System.out.println(dayVal);
				if (dayVal.equals(day)) {
					driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}

		}

	}

	public static void dynamicWebTableHandle() {
		// Method-1:
		String before_xpath = "//*[@id='vContactsForm']/table/tbody/tr[";
		String after_xpath = "]/td[2]/a";

		for (int i = 4; i <= 7; i++) {
			String name = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(name);
			if (name.contains("test2 test2")) { // i=6
				// *[@id='vContactsForm']/table/tbody/tr[6]/td[1]/input
				driver.findElement(By.xpath("//*[@id='vContactsForm']/table/tbody/tr[" + i + "]/td[1]/input")).click();
			}
		}

		// Method-2:
		driver.findElement(By.xpath(
				"//a[contains(text(),'test2 test2')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
				.click();
		driver.findElement(By
				.xpath("//a[contains(text(),'ui uiii')]/parent::td//preceding-sibling::td//input[@name='contact_id']"))
				.click();

	}

	public static void handleWindowPopUp() throws Exception {
		Set<String> handler = driver.getWindowHandles();

		Iterator<String> it = handler.iterator();

		String parentWindowId = it.next();
		System.out.println("parent window id:" + parentWindowId);

		String childWindowId = it.next();
		System.out.println("Child window id:" + childWindowId);

		driver.switchTo().window(childWindowId);

		Thread.sleep(2000);

		System.out.println("child window pop up title" + driver.getTitle());

		driver.close();

		driver.switchTo().window(parentWindowId);

		Thread.sleep(2000);

		System.out.println("parent window title" + driver.getTitle());
	}

	// in DOM type='file'
	public static void fileUploadUsingSenkeys(WebElement element, int timeout, String FILE_NAME) {
		sendkeys(driver, element, timeout, FILE_NAME);

	}

	public static void downloadFile(WebElement element, int timeout) throws InterruptedException {
		clickOn(driver, element, timeout);
		Thread.sleep(5000);
		File[] listOfFiles = BrowserFactory.folder.listFiles();

		Assert.assertTrue(listOfFiles.length > 0);

		for (File file : listOfFiles) {
			Assert.assertTrue(file.length() > 0);
		}
	}

	// Selenium does not provide direct API to handle, so we have to handle using
	// Actions class
	public void handleIrritatingChatModelPopUp(String xpath1, String xpath2) throws InterruptedException {
		Thread.sleep(5000);
		// check the frame before mouse over
		Actions action = new Actions(driver);
		// xpath1 - xpath of chat box
		action.moveToElement(driver.findElement(By.xpath(xpath1))).build().perform();
		// xpath2 - xpath of cross button
		driver.findElement(By.xpath(xpath2)).click();
	}

	public void handlePagination(int timeOut) {
		new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.tagName("a")));
		List<WebElement> elements = driver.findElements(By.tagName("a"));
		for (int i = 0; i < elements.size(); i++) {
			String title = elements.get(i).getAttribute("title");
			if (title.equals("Next Page")) {
				elements.get(i).click();
				break;
			}
		}
	}

	public void handlePagination(String navXpath, String nextButtonXpath, String prevButtonXpath, String lastPage) {
		/*
		 * "//div[@class='nav-pages']//a"
		 * 
		 * "//*[@id='nextbutton id']"
		 * 
		 * "//*[@id='prevButtonid']"
		 * 
		 * "//*[@text='>>]/preceding::/span[1]"
		 */

		List<WebElement> pagination = driver.findElements(By.xpath(navXpath));

		WebElement NextButton = driver.findElement(By.xpath(nextButtonXpath));

		WebElement prevButton = driver.findElement(By.xpath(prevButtonXpath));

		int LastPageNumber = Integer.parseInt(driver.findElement(By.xpath(lastPage)).getText());

		// check if pagination link exists
		if (pagination.size() > 0) {
			System.out.println("pagination exists");

			// click on pagination link

			for (int i = 1; i < pagination.size(); i++) {

				// Check if nextbutton is enable or not.

				if (NextButton.isEnabled()) {

					NextButton.click();
				} else {
					System.out.println("pagination not exists");
				}
			}
		}
	}
}
