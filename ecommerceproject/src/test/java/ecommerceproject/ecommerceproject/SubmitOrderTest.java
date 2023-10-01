package ecommerceproject.ecommerceproject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerceproject.ecommerceproject.TestComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest  {
	//String productName="ZARA COAT 3";
    @Test(dataProvider = "getData", groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
	
		ProductCatalouge productcatalouge = landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productcatalouge.getProductList();
		productcatalouge.addProductToCart(input.get("product"));
		CartPage cartpage=productcatalouge.goToCartPage();
		boolean match = cartpage.verifyProductDislay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.checkOut();
		checkoutpage.selectCountryName("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMsg=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
    //To verify ZARA COAT 3 Page displayed in Orders Page 
    @Test(dataProvider = "getData",dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest(HashMap<String,String> input) {
		ProductCatalouge productcatalouge = landingpage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderpage=productcatalouge.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDispaly(input.get("product")));
	
		
    }
    
  
    //ExtentReport
    
    @DataProvider
    public Object[][] getData() throws IOException {
    List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ecommerceproject//data//PurchaseOrder.json");
  	return new Object[][] {{data.get(0)},{data.get(1)}};
    		
    	
    }
//    @DataProvider
//    public Object[][] getData() throws IOException {
////    	HashMap<String, String> map=new HashMap<>();
////    	map.put("email", "jhagangeshgunjan@gmail.com");
////    	map.put("password", "Jha@123gg");
////    	map.put("productName","ZARA COAT 3");
////    	
////    	HashMap<String, String> map1=new HashMap<>();
////    	map1.put("email", "ujha777@gmail.com");
////    	map1.put("password", "G@g8961jha");
////    	map1.put("productName","ADIDAS ORIGINAL");
//    		return new Object[][] {{map},{map1}};
//    	
//    }
}

