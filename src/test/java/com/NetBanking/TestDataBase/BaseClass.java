package com.NetBanking.TestDataBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.NetBanking.Utilities.ReadConfig;


public class BaseClass {
	ReadConfig readconfig = new ReadConfig();
	
	public 	String baseURL =readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static  Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		
	logger = Logger.getLogger("ebanking");
	PropertyConfigurator.configure("log4j.properties");
		
	if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		driver=new ChromeDriver();
	}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}
	
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver","./Drivers/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(baseURL);
	
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
public static  String CaptureScreenShot(WebDriver driver,String screenshotName) throws Exception {
		
		
		
		String dateFormat=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destination =System.getProperty("user.dir") +  "./Screenshot/"+screenshotName + dateFormat+".png";
		
		File desFile = new File(destination);
		
		FileHandler.copy(srcFile, desFile);
		return destination;
	}
	

public String randomString () {
	String generateString = RandomStringUtils.randomAlphabetic(8);
	return (generateString);
}

public static String randomNum () {
	String generateString2 = RandomStringUtils.randomNumeric(4);
	return (generateString2);
}
	
	
	
	
	
	
	
	
	
	
	
	
}
