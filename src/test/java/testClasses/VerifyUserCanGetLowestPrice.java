package testClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.BaseClass1;
import pomClasses.HomePage;
import pomClasses.LoginPage;


public class VerifyUserCanGetLowestPrice {
	static WebDriver driver;
	LoginPage lp;
	HomePage hp;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	@BeforeClass
	@Parameters("browser")
	
	public void beforeClass(String browser) throws IOException {
		htmlReporter =BaseClass1.getExtentHtmlreporter();
		reports = BaseClass1.getExtentReporter();
		extentTest = BaseClass1.getExtentTest("VerifyUserCanGetLowestPrice");
		
		driver=BaseClass1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
	lp = new LoginPage(driver);
	hp = new HomePage(driver);	
	}
	
	@Test(priority=1)
	public void getLowestPriceOfProduct() throws InterruptedException{
		hp.getLowestPrice();
		hp= new HomePage(driver);
	//	List<String> getLowestPriceFrom5Page = new ArrayList<>();
		Map <Integer,String> lowPriceOfEachPage = new HashMap<>();	
		for(int i=1; i<=4; i++) {
			if(i!=1) {
			hp.switchToPage(i);
			}
	  //    	getLowestPriceFrom5Page.add(hp.getLowestPrice());
			lowPriceOfEachPage.put(i,hp.getLowestPrice());		
		}
	//	System.out.println(getLowestPriceFrom5Page);
		System.out.println(lowPriceOfEachPage);
	}
		
	@Test
	public void s() {
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(Status.PASS,"Test :"+result.getName());
		}else if(result.getStatus()==ITestResult.FAILURE) {
			String path = hp.getScreenshot(driver,"Test :"+result.getTestName());
			extentTest.log(Status.PASS,"Test :"+result.getName(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(Status.SKIP,"Test :"+result.getName());
		}
	}
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}

}
