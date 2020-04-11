package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

public class TestListener implements ITestListener{


	  WebDriver driver;


	  @AfterTest
		@Override
		public void onTestFailure(ITestResult result) {
		  if (ITestResult.FAILURE == result.getStatus()) {
				try {
					
					TakesScreenshot ts = (TakesScreenshot) driver;

					File source = ts.getScreenshotAs(OutputType.FILE);
					
					FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + ".png"));

					System.out.println("Screenshot taken");
				} catch (Exception e) {

					System.out.println("Exception while taking screenshot " + e.getMessage());
				}

			}
			// close application
			driver.quit();
			
		}

		@Override
		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void onTestSuccess(ITestResult result) {
			// TODO Auto-generated method stub
			
		}



		@Override
		public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void onStart(ITestContext context) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void onFinish(ITestContext context) {
			// TODO Auto-generated method stub
			
		}
}
