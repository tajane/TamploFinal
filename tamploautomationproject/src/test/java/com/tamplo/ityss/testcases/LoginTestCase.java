package com.tamplo.ityss.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.LoginPageObject;
import com.ityss.tamplo.testbase.TestBase;

public class LoginTestCase extends TestBase
{
	/*private WebDriver driver;
	public LoginTestCase(WebDriver  driver)
	{
		this.driver = driver;
	}*/
	// to  run login test case you need to remove constructor
	
	@Test
	public void loginCase1() throws IOException 
	{
		LoginPageObject loginpageobject = new LoginPageObject(driver);
		DashboardPageObject dashboardagebject = new DashboardPageObject(driver);
		
		
		
		
		String selectedcompnay = dashboardagebject.setCompany(prob.getProperty("company"));
		test.log(Status.PASS, MarkupHelper.createLabel(selectedcompnay + " : this company set successfully", ExtentColor.GREEN));
		
		boolean  check= dashboardagebject.verifyDashboardPage();
		test.log(Status.PASS, MarkupHelper.createLabel( "are we on dashboard page : " + check, ExtentColor.GREEN));
		
	}
}
