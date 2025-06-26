import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class frameHandling {

	public static void main(String[] args) {
		
		//invoking webdriver and entering url in maximizing mode
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
				
		// setting a global implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//a[text()='Nested Frames']")).click();
		
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		
		driver.switchTo().frame(1);
		
		System.out.println(driver.findElement(By.tagName("div")).getText());
		
		driver.close();

	}

}
