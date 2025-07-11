import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class automationTechniques {

	public static void main(String[] args) throws InterruptedException {
		//Handling Static dropdowns with select as tagname
		
		//loading chrome driver and maximizing window
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();
		
		//providing static dropdown web element to select class
		WebElement static_dropdown = driver.findElement(By.cssSelector("*[id*='DropDownListCurrency']"));
		Select dropdown = new Select(static_dropdown);
		
		//selects INR first, then AED and then USD from static dropdown
		static_dropdown.click();
		Thread.sleep(1500);
		dropdown.selectByIndex(1);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByVisibleText("AED");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("USD");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		
		//trying to enter 2 adults, 2 child, 1 infant in passengers dropdown
		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(1500);
		
		int[] pass_count = {1, 2, 1};
		WebElement[] pass_webaddr = {
				driver.findElement(By.id("hrefIncAdt")),
				driver.findElement(By.id("hrefIncChd")),
				driver.findElement(By.id("hrefIncInf"))
				};
		
		for(int i = 0; i < pass_count.length; i++) {
			int count = pass_count[i];
			while(count > 0) {
				pass_webaddr[i].click();
				count--;
			}
		}
		
		//to click done in passengers drop down
		driver.findElement(By.id("btnclosepaxoption")).click();
		
		
		//selecting BLR as departure city and MAA as arrival city
		driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTXT")).click();
		Thread.sleep(1500);
		WebElement blr = driver.findElement(By.xpath("//a[@value = 'BLR']"));	
		blr.click();
		Thread.sleep(1000);
		WebElement maa = driver.findElement(By.xpath("(//a[@value = 'MAA'])[2]"));
		maa.click();
		
		
		//Handling auto suggestive dropdowns -try to select India
		// We usually select the entire suggestion box and iterate through the values with given target value
		WebElement as_dropdown = driver.findElement(By.id("autosuggest"));
		as_dropdown.sendKeys("ind");
		Thread.sleep(2000);
		driver.findElement(By.linkText("India")).click();
		
		//selecting checkbox
		driver.findElement(By.cssSelector("*[id*='friendsandfamily']")).click();
		//selecting radio button
		driver.findElement(By.cssSelector("#travelOptions input[value = 'OneWay']")).click();
		
		
		

	}

}
