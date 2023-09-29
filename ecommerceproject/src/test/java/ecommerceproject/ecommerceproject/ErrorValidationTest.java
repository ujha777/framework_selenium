package ecommerceproject.ecommerceproject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ecommerceproject.ecommerceproject.TestComponent.BaseTest;
import ecommerceproject.ecommerceproject.TestComponent.Retry;



public class ErrorValidationTest extends BaseTest{

	 @Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException {
			// TODO Auto-generated method stub
	        landingpage.loginApplication("jhagangeshgunjan@gmail.co", "Jha@123gg");
			Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());			
		}
	  @Test
		public void productErrorValidation() throws IOException {
			// TODO Auto-generated method stub
			String productName="ZARA COAT 3";
			ProductCatalouge productcatalouge = landingpage.loginApplication("ujha777@gmail.com", "G@g8961jha");
			List<WebElement> products = productcatalouge.getProductList();
			productcatalouge.addProductToCart(productName);
			CartPage cartpage=productcatalouge.goToCartPage();
			boolean match = cartpage.verifyProductDislay("ZARA COAT 33");
			Assert.assertFalse(match);	
		}
}
