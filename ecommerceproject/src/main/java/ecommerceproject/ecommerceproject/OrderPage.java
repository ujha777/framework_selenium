package ecommerceproject.ecommerceproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import ecommerceproject.ecommerceproject.AbstractComponent.AbstractComponents;

import org.openqa.selenium.support.FindBy;

public class OrderPage extends AbstractComponents{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames ;
	
	//@FindBy(xpath="button[contains(text(),'Checkout')]")
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public boolean VerifyOrderDispaly(String productName) {
		boolean match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
}
