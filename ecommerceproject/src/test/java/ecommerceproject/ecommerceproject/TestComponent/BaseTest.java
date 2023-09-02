package ecommerceproject.ecommerceproject.TestComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ecommerceproject.ecommerceproject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ecommerceproject//ecommerceproject//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*"); 
		driver = new ChromeDriver(options);
	
		
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.out.println("Lanch firefox");
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			System.out.println("Edge");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;	
	}
	
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver();
		LandingPage landingpage= new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
}
