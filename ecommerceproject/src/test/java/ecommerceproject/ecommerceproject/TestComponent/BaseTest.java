package ecommerceproject.ecommerceproject.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerceproject.ecommerceproject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//ecommerceproject//ecommerceproject//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");		
		if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*"); 
		if (browserName.contains("headless")) {
			options.addArguments("headless");
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		
		driver = new ChromeDriver(options);
	
		
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//System.out.println("Lanch firefox");
			System.getProperty("webdriver.gecko.driver","F:/geckodriver-v0.30.0-win64/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			System.out.println("Edge");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;	
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//Read Json to String
		String JsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	//Read string to hashMap by Jackson Databinding
			ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
		
		
	});
	
	return data;
	
			
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		    TakesScreenshot	ts=(TakesScreenshot)driver;
		    File source = ts.getScreenshotAs(OutputType.FILE);
		    File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		    FileUtils.copyFile(source, file);
		    return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		    
		    }
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver();
		landingpage= new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
