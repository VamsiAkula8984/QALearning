package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.utilities;

public class CartPage extends utilities{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class = 'cart'] //h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//button[text() = 'Checkout']")
	WebElement checkout_btn;
	
	public boolean productPresentInCart(String product_name) {
		waitForWebElementToAppear(checkout_btn);
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product_name));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkout_btn.click();
		return new CheckoutPage(driver);
	}

}
