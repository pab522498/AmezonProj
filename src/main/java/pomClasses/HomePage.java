package pomClasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilClasses.UtilClass;

public class HomePage extends UtilClass {
	
	static WebDriver driver;
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	private WebElement searchElement;
	
	@FindBy(xpath="//span[text()='OnePlus']")
	private WebElement filterMobile;
	
	@FindBy(xpath="(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]")
	private WebElement verifySearchProduct;
	
	@FindBy(xpath="//span[@class='a-price-whole']")
	private List<WebElement> productPriceList;
	
	
	
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver,this);
		this.driver=driver;	
	}
	
	public void searchMobile() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(searchElement));
		searchElement.sendKeys("Oneplus mobile");
		searchElement.sendKeys(Keys.ENTER);
		filterMobile.click();
	}
	
	public String verifyUserSearchTheProduct() throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(verifySearchProduct));
		String searchProduct = element.getText();
		return searchProduct;
	}
	
	public String getLowestPrice() {
		
		List<Integer> priceList = new ArrayList<>();
		for(WebElement priceElement : productPriceList) {
			try {
				priceList.add(Integer.parseInt(priceElement.getText().replace(",", "")));
			}catch(Exception e) {

			}
			
		}
		System.out.println(priceList);
		Collections.sort(priceList);
		
		return (priceList.get(0).toString());
		
	}
	
	public void switchToPage(int a) throws InterruptedException {
		
		driver.findElement(By.xpath("//a[@aria-label='Go to page "+a+"']")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='Current page, page "+a+"']")));
	
	}

}
