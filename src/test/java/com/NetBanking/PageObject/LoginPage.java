package com.NetBanking.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	

	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnlogin;
	
	@FindBy(xpath="/html/body/div[4]/div/ul/li[15]/a")
	@CacheLookup
	WebElement logoutbtn;
	
	
	 public LoginPage(WebDriver driver) {
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }

   
   public void setUserName(String uname) {
	   txtUserName.sendKeys(uname);
   }
   
   public void setPassword(String upass) {
	  txtPassword.sendKeys(upass); 
   }
   
   public void  clickOnLogin() {
	   btnlogin.click();
   }
   
   public void  clickOnLogout() {
	  logoutbtn.click();
   }
   
   
}
