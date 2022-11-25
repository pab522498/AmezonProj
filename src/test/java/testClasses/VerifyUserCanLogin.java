package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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


public class VerifyUserCanLogin {

	static WebDriver driver;
	LoginPage lp;
	HomePage hp;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	@BeforeClass
    @Parameters("browser")
	
	public void beforeClass(String browser) throws IOException {
		htmlReporter = BaseClass1.getExtentHtmlreporter();
		reports = BaseClass1.getExtentReporter();
		extentTest = BaseClass1.getExtentTest("VerifyUserCanLogin");
		driver=BaseClass1.getDriver(browser);
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
	lp = new LoginPage(driver);
	hp = new HomePage(driver);	
	}
	
	@Test(priority=1)
	public void login() throws InterruptedException, IOException{
		lp.login();
		lp.enterMobNo();
		lp.enterPassword();
		String profileName = lp.getVerifyProfileName();
		Assert.assertNotEquals(profileName, "Hello, PRASHANT","profileName not matching");
	}
	
	@Test(priority=2)
	public void searchProduct() throws InterruptedException {
		hp.searchMobile();
		hp= new HomePage(driver);
		String searchProduct = hp.verifyUserSearchTheProduct();
		Assert.assertTrue(searchProduct.contains("1-24"));
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
		extentTest.log(Status.PASS, "Test :"+result.getName());
		}else if(result.getStatus()==ITestResult.FAILURE) {
			String path = lp.getScreenshot(driver,result.getName());
			extentTest.log(Status.FAIL, "Test :"+result.getName(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test :"+result.getName());
		}
			
		
		
	}
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}

}
