package ecommerceproject.ecommerceproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecommerceproject.ecommerceproject.AbstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(xpath="//input[@value='Login']")
	WebElement submit;
	//.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	public ProductCatalouge loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalouge productcatalouge = new ProductCatalouge(driver);
		return productcatalouge;
	}
	public String getErrorMessage() {
		waitForWebelementToAppear(errorMessage);
		return errorMessage.getText();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
