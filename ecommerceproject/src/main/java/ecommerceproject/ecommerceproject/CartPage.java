package ecommerceproject.ecommerceproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

import ecommerceproject.ecommerceproject.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//div/div/h3")
	private List<WebElement> cartproducts ;
	
	//@FindBy(xpath="button[contains(text(),'Checkout')]")
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean verifyProductDislay(String productName) {
		boolean match = cartproducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckOutPage checkOut() {
		checkoutEle.click();
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
	}
}
