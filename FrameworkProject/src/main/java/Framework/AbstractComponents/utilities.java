package Framework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.PageObjects.CartPage;

public class utilities {
	
	WebDriver driver;
	
	public utilities(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(css = "button[routerlink = '/dashboard/cart']")
	WebElement cart_page_btn;

	//contains common methods used across pages
	
	public void waitForElementToAppear(By byElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
	}
	
	public void waitForWebElementToAppear(WebElement webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webelement));
	}
	
	public void waitForWebElementToDisappear(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(webElement));
	}
	
	public CartPage goToCartPage() {
		cart_page_btn.click();
		return new CartPage(driver);
	}
	
	public void closeDriver() {
		driver.quit();
	}

}
