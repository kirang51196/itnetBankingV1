package ExtentReports4versionDemo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	public WebDriver driver;
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
 @BeforeTest
 public void setExtent() {
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/extentreport.html");
	 htmlReporter.config().setDocumentTitle("Automation Report");// Title  of the Report
	 htmlReporter.config().setTheme(Theme.DARK);//Name of the Theme
	 
	 extent = new ExtentReports();
	 extent.attachReporter(htmlReporter);
	 extent.setSystemInfo("Hostname","LocalHost");
	 extent.setSystemInfo("Tester Name","Kiran");
	 extent.setSystemInfo("Browser","Chrome");
	 
 }
	 @AfterTest
	public void endReporter() {
		 extent.flush();
	 }
	 
	 @BeforeMethod
	 public void setUp() {
		 
		 System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
			driver=new ChromeDriver();
			
			driver.get("https://demo.nopcommerce.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
	 
	 @Test
	 public void noCommerceTitleTesT() {
		 test = extent.createTest("noCommerceTitleTest"); //create entry
		 String title = driver.getTitle();
		 Assert.assertEquals(title, "noCommerce demo store");//intentially failed
	 }
	 
	 
	 @Test
	 public void noCommerceLogoTest() {
	   test = extent.createTest("noCommerceLogoTest"); //create entry
		boolean status = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
      Assert.assertTrue(status);
	 }
	 

	 @Test
	public void nocommerceLoginTest() {
	 test = extent.createTest("noCommerceLoginTest"); //create entry
	 
	 test.createNode("Login with vaild input");//adding subnode to main node
	 Assert.assertTrue(true);
	 
	 test.createNode("Login with invaild input");
	 Assert.assertTrue(true);
	}
	
	@AfterMethod
	 public void tearDown(ITestResult result) throws Throwable {
		 
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed" + result.getName());//to add name in extent report
			test.log(Status.FAIL, "Test Case Failed" + result.getThrowable());//to add error/exception in extent report
	   
		
			String screenshotPath = ExtentReport.getScreenShot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);//adding screenshot (always add imagepath)
		}
		
	  else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case Passed" + result.getName());
	  		
		}
		
		
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped" + result.getName());
			
		}
		driver.quit();
		
	 }
	
	public static  String getScreenShot(WebDriver driver,String screenshotName) throws Exception {
		
		
		
		String dateFormat=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destination =System.getProperty("user.dir") +  "./Screenshot/"+screenshotName + dateFormat+".png";
		
		File desFile = new File(destination);
		
		FileHandler.copy(srcFile, desFile);
		return destination;
	}
	}
	
	
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	
	
	
	


