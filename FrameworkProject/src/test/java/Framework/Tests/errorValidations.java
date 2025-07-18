package Framework.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Framework.PageObjects.ProductCatalog;
import Framework.TestComponents.baseTest;
import junit.framework.Assert;

public class errorValidations extends baseTest {
	
	@Test
	public void unRegisteredUserLogin() throws InterruptedException, IOException {

		String user_email = "mikeRoss@pearsonhardman.com";
		String user_pwd = "Rachel@143";
	
		ProductCatalog productCatalog = landingPage.loginApplication(user_email, user_pwd);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}

}
