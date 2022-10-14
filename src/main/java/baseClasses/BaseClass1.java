package baseClasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilClasses.UtilClass;

public class BaseClass1 {
	static WebDriver driver;
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports reports; 
	static ExtentTest extentTest;
	
	
	
	
	
	public static WebDriver getDriver(String browser) throws IOException {
		if(driver==null) {
			
			if(browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
			//  HERE DIRECTLT TAKE CHROME DRIVER PATH ONLY CREATE BROWSER FOLDER 
			//	INTO MAIN RESOURES AND PASTE THE BROWSERS.
				
			//	System.setProperty("webdriver.chrome.driver","src\\main\\resources\\browsers\\chromedriver.exe");
			    driver = new ChromeDriver();
			}else {
				
				WebDriverManager.firefoxdriver().setup();
			//  HERE DIRECTLY TAKE THE FIREFOX DRIVER PATH	
				
			//	System.setProperty("webdriver.gecko.driver","src\\main\\resources\\browsers\\geckodriver.exe");
			    driver = new FirefoxDriver();
			}
			
			
//			if(UtilClass.getConfigData("dev").equals("qa")){
//				driver.get("https://www.amazonQA.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_1aeuamiuit_b&adgrpid=58746164597&hvpone=&hvptwo=&hvadid=617721280249&hvpos=&hvnetw=g&hvrand=1829455378145520102&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1007783&hvtargid=kwd-298741529014&hydadcr=5903_2362026&gclid=CjwKCAjws--ZBhAXEiwAv-RNLxfl2a-9y6oQt0CEyJXwtXUdrfAiDFjH_dKDRpMKnk20OB7UrfCuZRoCK38QAvD_BwE");
//			}else {
//				driver.get("https://www.amazonDEV.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_1aeuamiuit_b&adgrpid=58746164597&hvpone=&hvptwo=&hvadid=617721280249&hvpos=&hvnetw=g&hvrand=1829455378145520102&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1007783&hvtargid=kwd-298741529014&hydadcr=5903_2362026&gclid=CjwKCAjws--ZBhAXEiwAv-RNLxfl2a-9y6oQt0CEyJXwtXUdrfAiDFjH_dKDRpMKnk20OB7UrfCuZRoCK38QAvD_BwE");
//			}
			
			driver.get("https://www.amazon.in/?&ext_vrnc=hi&tag=googinhydr1-21&ref=pd_sl_1aeuamiuit_b&adgrpid=58746164597&hvpone=&hvptwo=&hvadid=617721280249&hvpos=&hvnetw=g&hvrand=1829455378145520102&hvqmt=b&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=1007783&hvtargid=kwd-298741529014&hydadcr=5903_2362026&gclid=CjwKCAjws--ZBhAXEiwAv-RNLxfl2a-9y6oQt0CEyJXwtXUdrfAiDFjH_dKDRpMKnk20OB7UrfCuZRoCK38QAvD_BwE");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		}  
		return driver;
	}
	
	public static void unloadDriver() {
		driver=null;
	}
	
	
	public static ExtentHtmlReporter getExtentHtmlreporter() {
		if(htmlReporter==null) {
			htmlReporter = new ExtentHtmlReporter("reports.html");
		}
		return htmlReporter;
	}
	
	public static ExtentReports getExtentReporter() {
		if(reports==null) {
			reports = new ExtentReports();
			reports.attachReporter(htmlReporter);
		}
		return reports;
	}
	
	public static ExtentTest getExtentTest(String testName) {
		extentTest = reports.createTest(testName);
		return extentTest;
	}
	

}
