package com.NetBanking.TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.NetBanking.PageObject.LoginPage;
import com.NetBanking.TestDataBase.BaseClass;
import com.NetBanking.Utilities.XLUtils;

public class TC_Excel_Login_002  extends BaseClass{
	
	@Test(dataProvider="login")
	public void  loginDataProvider(String user,String pass) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided");
		lp.setPassword(pass);
		logger.info("password provided");

		lp.clickOnLogin();
		Thread.sleep(6000);
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");

		}
		
         else
         {
        Assert.assertTrue(true);
		logger.info("Login passed");
		Thread.sleep(3000);
        lp.clickOnLogout();
	  driver.switchTo().alert().accept();//close alert
		driver.switchTo().defaultContent();

		}
		
	}
	
	public boolean isAlertPresent() { //user defined method create to check alert is present or not
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
			
		}
	}

	
	@DataProvider(name="login")
	String[][] getData() throws IOException{
	String path = System.getProperty("user.dir")+"C:\\Users\\Kiran Gavahale\\.eclipse\\NetBankingProject\\src\\test\\java\\com\\NetBanking\\TestDataBase\\login.xlsx";
	
	int rownum = XLUtils.getRowCount(path,"Sheet1");
	int colcount= XLUtils.getCellCount(path,"Sheet1",1);
	
	String logindata[][] = new String [rownum][colcount];
	
	for(int i=0; i<=rownum; i++) {
		for(int j=0; j<colcount; j++) {
		
			logindata[i][j] = XLUtils.getCellData(path, "Sheet1", i, j);//1 0
		}
	}
	  
	return logindata;
	
	}
	
	
	
	
	
	
	
	
	
	
	

}
