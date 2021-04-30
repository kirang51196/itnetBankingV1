package com.NetBanking.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.NetBanking.PageObject.AddCustomerPage;
import com.NetBanking.PageObject.LoginPage;
import com.NetBanking.TestDataBase.BaseClass;

public class Tc_AddCustomer_003 extends BaseClass {
	@Test
	public void addNewCustomer() throws Exception {
	LoginPage lp = new LoginPage(driver);
	lp.setUserName(username);
	logger.info("User name is provided");

	lp.setPassword(password);
	logger.info("password is provided");

	lp.clickOnLogin();
	
	AddCustomerPage addcust = new AddCustomerPage(driver);
	addcust.clickAddNewCustomer();
  logger.info("providing customer details....");

	addcust.custName("Kiran");
	addcust.custgender("Female");
	addcust.custdob("12","09","1996");
	Thread.sleep(3000);

	addcust.custaddress("INDIA");
	addcust.custcity("HYD");
	addcust.custstate("AP");
	addcust.custpinno("500074");
	addcust.custtelephoneno("8999459461");
	
	String email = randomString()+"@gmail.com";
	addcust.custemailid(email);
	addcust.custpassword("Kiran@123");
	addcust.custsubmit();
	Thread.sleep(3000);

	logger.info("validation started....");
	boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
	
	if(res==true)
	{
		Assert.assertTrue(true);
		logger.info("test case passed....");
		
	}
	else
	{
		logger.info("test case failed....");
		CaptureScreenShot(driver,"addNewCustomer");
		Assert.assertTrue(false);
	}
	}
   
	

}

