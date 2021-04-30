package com.NetBanking.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReportNetBanking extends TestListenerAdapter   {

		
		public WebDriver driver;
		
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest test;
		public ExtentTest logger;
		
	public void onStartContext(ITestContext testContext) {  //time stamp
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String repName = "Reports"+timestamp+".html";
		
     htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/"+repName);
     htmlReporter.loadXMLConfig(System.getenv("user.dir")+"/extentreport.xml");
		
     htmlReporter.config().setDocumentTitle("Automation Report");// Title  of the Report
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
  htmlReporter.config().setTheme(Theme.DARK);//Name of the Theme
		 
		 extent = new ExtentReports();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Hostname","LocalHost");
		 extent.setSystemInfo("Tester Name","Kiran");
		 extent.setSystemInfo("Browser","Chrome");
		 
		 htmlReporter.config().setDocumentTitle("InterNetBanking Test Reporter ");// Title  of the Report
		 htmlReporter.config().setReportName("Functional Test Automation Report");//Name of the  report
		 htmlReporter.config().setTheme(Theme.DARK);//Name of the Theme
		 
		 
		 }
	
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());//create new entry in report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the
		//passed information to the report with GREEN color highlighted
	
	}
	
	
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());//create new entry in report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed informatiom

		String screenshotPath=System.getProperty("user.dir")+"\\Screenshot\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath); 
		if(f.exists())
		{
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
