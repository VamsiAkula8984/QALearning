package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.utilities;

public class LandingPage extends utilities {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement userPwd;
	
	@FindBy(id = "login")
	WebElement login_btn;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMsg;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalog loginApplication(String username, String pwd) {
		userEmail.sendKeys(username);
		userPwd.sendKeys(pwd);
		login_btn.click();
		return new ProductCatalog(driver);
	}
	
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}

}
