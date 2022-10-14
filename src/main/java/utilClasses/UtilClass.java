package utilClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilClass {
	static WebDriver driver;
	public static void waitTillElementAppear(WebDriver driver, WebElement searchElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(searchElement));
	}
	
	public static void waitTillElementAppear(WebDriver driver, WebElement searchElement,int time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(searchElement));
	}
	
	public static WebElement waitTillElementAppear(WebDriver driver, By searchElement,int time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(searchElement));
	}
	
	
	public static String getConfigData(String key) throws IOException {
		FileInputStream file = new FileInputStream("configuration\\config.properties");
		Properties prop = new Properties();
		prop.load(file);
		return prop.getProperty(key);
		
	}
	
	public static String getScreenshot(WebDriver driver,String methodName) throws IOException {
		
		String path =""+methodName+".png";
		TakesScreenshot k = (TakesScreenshot)driver;
		File source = k.getScreenshotAs(OutputType.FILE);
		File destination = new File(path);
		FileHandler.copy(source, destination);
		return path;
	}
	

	
	
	
	

}
