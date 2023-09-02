package ecommerceproject.ecommerceproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceproject.ecommerceproject.AbstractComponent.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {
	
	WebDriver driver;
	public ProductCatalouge(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toatMessage=By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {
		
		waitForelementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForelementToAppear(toatMessage);
		waitForelementToDisAppear(spinner);
	}

}
