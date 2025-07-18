package Framework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String product_name = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("harveyspector@pearsonhardman.com");
		driver.findElement(By.id("userPassword")).sendKeys("Harvey@123");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(5000);
		List<WebElement> products_list = driver.findElements(By.className("mb-3"));
		WebElement my_product = products_list.stream().filter(product ->
			product.findElement(By.tagName("b")).getText().equalsIgnoreCase(product_name)).findFirst().orElse(null);
		my_product.findElement(By.xpath("//button[text()= ' Add To Cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink = '/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class = 'cart'] //h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product_name));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text() = 'Checkout']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btnn.action__submit")));
		driver.findElement(By.xpath("//div[@class = 'form__cc']/div[2]/div[2]/input")).sendKeys("143");
		driver.findElement(By.xpath("//div[@class = 'form__cc']/div[3]/div/input")).sendKeys("Harvey Spector");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> country_suggestions = driver.findElements(By.cssSelector("button[class ='ta-item list-group-item ng-star-inserted' ] span"));
		WebElement country_loc = country_suggestions.stream().filter(country 
				-> country.getText().equalsIgnoreCase("british indian ocean territory")).findFirst().orElse(null);
		country_loc.click();
		
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText().toLowerCase(), "thankyou for the order.");
		driver.close();
	}

}
