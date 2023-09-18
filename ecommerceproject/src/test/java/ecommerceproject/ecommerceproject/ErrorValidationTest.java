package ecommerceproject.ecommerceproject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import ecommerceproject.ecommerceproject.CartPage;
import ecommerceproject.ecommerceproject.CheckOutPage;
import ecommerceproject.ecommerceproject.ConfirmationPage;
import ecommerceproject.ecommerceproject.ProductCatalouge;
import ecommerceproject.ecommerceproject.TestComponent.BaseTest;



public class ErrorValidationTest extends BaseTest{

	 @Test(groups = {"ErrorHandling"})
		public void loginErrorValidation() throws IOException {
			// TODO Auto-generated method stub
	        landingpage.loginApplication("jhagangeshgunjan@gmail.co", "Jha@123gg");
			Assert.assertEquals("Incorrect email  password.",landingpage.getErrorMessage());
	
			
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
