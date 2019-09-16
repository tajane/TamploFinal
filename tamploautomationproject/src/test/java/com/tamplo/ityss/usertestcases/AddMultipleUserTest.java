package com.tamplo.ityss.usertestcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.helper.excel.ExcelHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.UserPageObject;
import com.ityss.tamplo.testbase.TestBase;

public class AddMultipleUserTest extends TestBase
{
	DashboardPageObject dashboardagebject;
	UserPageObject userpageobject;
	
	@BeforeClass
	public void nevigateToActionPage() throws IOException
	{

		dashboardagebject = new DashboardPageObject(driver);
		userpageobject =  new UserPageObject(driver);
		
		boolean getpermissionstatus = dashboardagebject.openUserPage();
		if (getpermissionstatus) 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("User Page Open successfully", ExtentColor.BROWN));
		} else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Login User does not have permission for user page", ExtentColor.BROWN));
		}
		
	}

	@Test(dataProvider="adduserdata")
	public void addUserTest(String adminemailaddress,String useremailaddress) throws InterruptedException 
	{
		int getadmincountbefore = userpageobject.getAdminCount();
		
		test.log(Status.PASS, MarkupHelper.createLabel("Before adding admin email count is : " + getadmincountbefore, ExtentColor.GREEN));
		
		userpageobject.clickOnAddAdminButton();
		 test.log(Status.PASS, MarkupHelper.createLabel("successfully click  on add admin button", ExtentColor.GREEN));
		
		userpageobject.enterEmailAddressInAdminSection(adminemailaddress);
		test.log(Status.PASS, MarkupHelper.createLabel("All admin email id added successfully", ExtentColor.GREEN));
		
		int getadmincountafter = userpageobject.getAdminCount();
		
		test.log(Status.PASS, MarkupHelper.createLabel("after adding all admin email count is : " + getadmincountafter, ExtentColor.GREEN));
		
		int getusercountbefore = userpageobject.getuserCount();
		
		System.out.println("user count before " + getusercountbefore);
		test.log(Status.PASS, MarkupHelper.createLabel("before adding user email count is : " + getusercountbefore, ExtentColor.GREEN));
		
		userpageobject.clickOnAddUserButton();
		test.log(Status.PASS, MarkupHelper.createLabel("Successfully click on add user button", ExtentColor.GREEN));
		
		userpageobject.enterEmailAddressInUserSection(useremailaddress);
		test.log(Status.PASS, MarkupHelper.createLabel("all user email id added successfully", ExtentColor.GREEN));
		
		int getusercountafter = userpageobject.getuserCount();
		
		System.out.println("user count after " + getusercountafter);
		test.log(Status.PASS, MarkupHelper.createLabel("after adding all  user email count is : " + getusercountafter, ExtentColor.GREEN));
		
	}
	
	@DataProvider(name="adduserdata")
	public Object[][] getUserData()
	{
		Object data[][] = ExcelHelper.getExcelData("userdata");
		return data;
	}
}
