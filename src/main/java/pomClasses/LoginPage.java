package pomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilClasses.UtilClass;

public class LoginPage extends UtilClass{
	 static WebDriver driver;
	@FindBy(xpath="//a[@data-nav-role='signin']")
	private WebElement login;
	
	@FindBy(xpath="(//a[@data-nav-role='signin'])[2]")
	private WebElement loginBtn;
	
	@FindBy(xpath="//input[@type='email']")
	private WebElement mobileNo;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement mobileNoEnter;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement pass;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	private WebElement passEnter;
	
	@FindBy(xpath="//span[text()='Hello, PRASHANT']")
	private WebElement profileName;
	
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public void login() throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(login).perform();
		loginBtn.click();
	}
	
	public void enterMobNo() throws IOException {
		mobileNo.sendKeys(getConfigData("email"));
		mobileNoEnter.click();
	}
	
	public void enterPassword() throws IOException {
		pass.sendKeys(getConfigData("password"));
		passEnter.click();
	}
	
	public String getVerifyProfileName() {
		String pName =profileName.getText();
		return pName;
	}

}
