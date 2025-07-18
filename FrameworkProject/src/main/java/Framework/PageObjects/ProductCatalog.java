package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.utilities;

public class ProductCatalog extends utilities{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "mb-3")
	List<WebElement> products_list;
	
	@FindBy(css = ".ng-animating")
	WebElement loadingAnimation;

	
	By addToCart = By.xpath("//button[text()= ' Add To Cart']");
	By productsBy = By.className("mb-3");
	By toastMsg = By.cssSelector("#toast-container");
	
	
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products_list;
	}
	
	public WebElement getProductByName(String product_name) {
		WebElement product = getProductsList().stream().filter(prod ->
		prod.findElement(By.tagName("b")).getText().equalsIgnoreCase(product_name)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String product_name) {
		WebElement prod = getProductByName(product_name);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForWebElementToDisappear(loadingAnimation);
	}
	

}
