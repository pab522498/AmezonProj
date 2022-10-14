package testClasses;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.BaseClass1;
import pomClasses.AddressPage;
import pomClasses.HomePage;
import pomClasses.LoginPage;


public class VerifyUserCanAddTheAddress {
	static WebDriver driver;
	LoginPage lp;
	HomePage hp;
	AddressPage ap;
	String newAddressCount;
	String oldAddressCount;
	
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		htmlReporter =BaseClass1.getExtentHtmlreporter();
		reports = BaseClass1.getExtentReporter();
		extentTest =BaseClass1.getExtentTest("VerifyUserCanAddTheAddress");
		driver=BaseClass1.getDriver(browser);	
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp= new LoginPage(driver);
		hp= new HomePage(driver);
		ap= new AddressPage(driver); 
	}
	
	@Test(priority=1)
	public void verifyAddressPageIsOpened() {
		ap.hOverOnProfile();
		ap.clickOnAccountBtn();
		ap.clickOnAddressBtn();
//		boolean onPage = ap.addressBtn();
//		System.out.println(onPage);
//		Assert.assertTrue(onPage);
	}
	
	@DataProvider(name="addressData")
	public Object[][] getData(){
		Object k [][] = {{"prashant borade","8329131619","416119","flat no-1","guarishankar nagar,mudshingi","near khat karkhana"},{"satish patil","9119486162","416005","flat no-2","maratha chauk,mudshingi","near khat karkhana"}};
		return k;
	}
	
	@Test(priority=2,dataProvider ="addressData")
	public void addTheAddress(String name,String phone,String pin,String flatNo,String area,String landmark) throws InterruptedException {
		boolean onPage = ap.addressBtn();
		System.out.println(onPage);
		Assert.assertTrue(onPage);
		oldAddressCount = String.valueOf(ap.getAddressCount());
		List<String> addressDetails = Arrays.asList(name,phone,pin,flatNo,area,landmark);
		ap.enterTheTotalAddress(addressDetails);
		newAddressCount = String.valueOf(ap.getAddressCount());
		boolean newcount = newAddressCount.equals(oldAddressCount);
		Assert.assertFalse(newcount);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(Status.PASS,"Test :"+result.getName());
		}else if(result.getStatus()==ITestResult.FAILURE) {
			String path = hp.getScreenshot(driver, result.getName());
			extentTest.log(Status.FAIL,"Test :"+result.getName(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(Status.SKIP,"Test :"+result.getName());
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		reports.flush();
		BaseClass1.unloadDriver();
	}

}
