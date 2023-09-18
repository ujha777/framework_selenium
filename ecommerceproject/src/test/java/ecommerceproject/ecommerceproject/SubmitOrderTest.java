package ecommerceproject.ecommerceproject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecommerceproject.ecommerceproject.TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest  {
    @Test
	public void submitOrder() throws IOException {
		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		ProductCatalouge productcatalouge = landingpage.loginApplication("jhagangeshgunjan@gmail.com", "Jha@123gg");
		List<WebElement> products = productcatalouge.getProductList();
		productcatalouge.addProductToCart(productName);
		CartPage cartpage=productcatalouge.goToCartPage();
		boolean match = cartpage.verifyProductDislay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.checkOut();
		checkoutpage.selectCountryName("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMsg=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}

