package ecommerceproject.ecommerceproject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceproject.ecommerceproject.AbstractComponent.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countrydropdown;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results= By.cssSelector(".ta-results ");
	
	
	public void selectCountryName(String countryName) {
		
		Actions action = new Actions(driver);
		action.sendKeys(countrydropdown,"India").build().perform();
		waitForelementToAppear(results);
		selectCountry.click();
		
		
	}
	
	public ConfirmationPage submitOrder() {
		//submit.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
	}
}
