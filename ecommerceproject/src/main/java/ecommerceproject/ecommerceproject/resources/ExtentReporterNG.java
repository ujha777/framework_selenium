package ecommerceproject.ecommerceproject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReporterObject() {
	 	String path=System.getProperty("user.dir")+"//reports/index.html";
	 	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	 	reporter.config().setReportName("Web Automation Result");
	 	reporter.config().setDocumentTitle("Test Result");
	 	ExtentReports extent=new ExtentReports();
	 	extent.attachReporter(reporter);
	 	extent.setSystemInfo("TESTER", "Gangesh Gunjan Jha");
	 	return extent;
	}
}
