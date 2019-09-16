package com.ityss.tamplo.pageobject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.javascript.JavaScriptHelper;

public class LoginPageObject 
{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//button[@class='btn small btn-secondary ng-binding']")
	WebElement signinbuttonmac;
	
	@FindBy(xpath="//a[@class='btn btn-secondary-outline-sm mar-right-8 mar-left-8']")
	WebElement signinbutton;
	
	@FindBy(id="emailIdLoginPage")
	WebElement emailfield;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(xpath = "//div[@class='modalFooterInner']/button[1]")
	WebElement  loginbutton;
	
	
	public void login(String username,String passwrod) throws IOException
	{
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(signinbuttonmac);
		//signinbuttonmac.click();
		emailfield.sendKeys(username);
		password.sendKeys(passwrod);
		javaScriptHelper.clickOnElement(loginbutton);
		//loginbutton.click();
		
		
	}
}
