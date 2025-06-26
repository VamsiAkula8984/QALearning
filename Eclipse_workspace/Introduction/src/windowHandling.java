import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class windowHandling {

	public static void main(String[] args) {
		
		//invoking webdriver and entering url in maximizing mode
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		
		// setting a global implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//clicking on multiple windows link
		driver.findElement(By.xpath("//a[text()='Multiple Windows']")).click();
		
		//clicking on Click here in parent window
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		//now a child window gets opened. Extracting window handles
		Set<String> window_ids = driver.getWindowHandles();
		Iterator<String> it = window_ids.iterator();
		String parent_id = it.next();
		String child_id = it.next();
		
		//switching scope of driver to that child window.
		driver.switchTo().window(child_id);
		System.out.println(driver.findElement(By.tagName("h3")).getText());
		
		//swiching back to parent window.
		driver.switchTo().window(parent_id);
		System.out.println(driver.findElement(By.tagName("h3")).getText());
		
		driver.quit();
	}

}
