package utility;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	public static void main(String[] args) {

		TestNG runner = new TestNG();
		List<String> list = new ArrayList<String>();
		list.add("D:\\SELENIUM\\Selenium_Project\\Selenium\\Selenium\\test-output\\Default suite\\testng-failed.xml");

		runner.setTestSuites(list);
		runner.run();
	}

}
