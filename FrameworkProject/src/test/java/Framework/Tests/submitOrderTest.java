package Framework.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Framework.PageObjects.CartPage;
import Framework.PageObjects.CheckoutPage;
import Framework.PageObjects.ConfirmationPage;
import Framework.PageObjects.ProductCatalog;
import Framework.TestComponents.baseTest;
import junit.framework.Assert;

public class submitOrderTest extends baseTest {
	
	@Test
	public void submitOrder() throws InterruptedException, IOException {
		
		String product_name = "ZARA COAT 3";
		String user_full_name = "Harvey Spector";
		String user_email = "harveyspector@pearsonhardman.com";
		String user_pwd = "Harvey@123";
		String user_CNN = "143";
		String user_country = "India";
	
		ProductCatalog productCatalog = landingPage.loginApplication(user_email, user_pwd);
		productCatalog.addProductToCart(product_name);
		CartPage cartPage = productCatalog.goToCartPage();
		boolean match = cartPage.productPresentInCart(product_name);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		ConfirmationPage confirmationPage = checkoutPage.enterInfoAndPlaceOrder(user_CNN, user_full_name, user_country);
		Assert.assertEquals(confirmationPage.getConfirmationMsg(), "thankyou for the order.");
	}

}
