package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.utilities;

public class CheckoutPage extends utilities{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'form__cc']/div[2]/div[2]/input")
	WebElement cvv;
	
	@FindBy(xpath = "//div[@class = 'form__cc']/div[3]/div/input")
	WebElement name_on_card;
	
	@FindBy(css = ".btnn.action__submit")
	WebElement placeOrder_btn;
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country_dropdown;
	
	@FindBy(css = "button[class ='ta-item list-group-item ng-star-inserted' ] span")
	List<WebElement> country_suggestions;
	
	public ConfirmationPage enterInfoAndPlaceOrder(String user_cvv, String user_card_name, String country) {
		enterPersonalInfo(user_cvv, user_card_name);
		enterShippingInfo(country);
		placeOrder();
		return new ConfirmationPage(driver);
	}
	
	private void placeOrder() {
		placeOrder_btn.click();
	}

	private void enterShippingInfo(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(country_dropdown, country).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		WebElement country_loc = country_suggestions.stream().filter(country_ 
				-> country_.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		country_loc.click();
	}

	public void enterPersonalInfo(String user_cvv, String user_card_name) {
		waitForWebElementToAppear(placeOrder_btn);
		cvv.sendKeys(user_cvv);
		name_on_card.sendKeys(user_card_name);
	}

}
