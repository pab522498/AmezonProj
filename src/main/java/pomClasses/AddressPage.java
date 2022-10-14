package pomClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilClasses.UtilClass;

public class AddressPage extends UtilClass {
	
	static WebDriver driver;
	@FindBy(xpath="//a[@id='nav-link-accountList']")
	private WebElement onProfilePage;
	
	@FindBy(xpath="//span[text()='Your Account']")
	private WebElement clickonAccount;
	
	
	@FindBy(xpath="(//div[@class=\"a-column a-span9 a-span-last\"])[4]")
	private WebElement clickOnAddress;
	
	@FindBy(xpath="//div[@id='ya-myab-plus-address-icon']")
	private WebElement addAddress;
	
	@FindBy(xpath="//h2[text()='Add a new address']")
	private WebElement addressPage;
	
	@FindBy(xpath="//input[@class='a-input-text address-ui-widgets-desktop-form-field-full-width addrui-form-text-input']")
	private List<WebElement> listOfTotalAddress;
	
	@FindBy(xpath="//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//div[@class='a-box a-spacing-none normal-desktop-address-tile']")
	private List<WebElement> addressElemenetList;
	
	@FindBy(xpath="(//a[text()='Remove'])[2]")
	private WebElement deleteAddress;
	

	
	@FindBy(xpath="//input[@aria-labelledby='deleteAddressModal-1-submit-btn-announce']")
	private WebElement deleteFinal;
	
	
	public AddressPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public void hOverOnProfile() {
		Actions act = new Actions(driver);
		act.moveToElement(onProfilePage).perform();
	}
	
	public void clickOnAccountBtn() {
		clickonAccount.click();
	}
	
	public void clickOnAddressBtn() {
		clickOnAddress.click();
	}
	
	public boolean addressBtn() {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(addAddress)).click();;
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public void enterTheTotalAddress(List<String>addressDetails) throws InterruptedException {
	
		for(int i=1; i<=6; i++) {
			driver.findElement(By.xpath("(//input[@class='a-input-text address-ui-widgets-desktop-form-field-full-width addrui-form-text-input'])["+i+"]")).sendKeys(addressDetails.get(i-1));
		}
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(saveBtn)).click();;
	}
	
	public int getAddressCount() {
		return addressElemenetList.size();
	}


}
