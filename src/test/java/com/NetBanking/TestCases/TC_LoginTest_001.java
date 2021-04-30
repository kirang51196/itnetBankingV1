package com.NetBanking.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NetBanking.PageObject.LoginPage;
import com.NetBanking.TestDataBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass {
	
	
	@Test
	public void loginTest() throws Exception {
		
	logger.info("URL is opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickOnLogin();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		
		else {
			 CaptureScreenShot( driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
			
		}
	}

}
