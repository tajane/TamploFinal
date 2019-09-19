package com.tamplo.ityss.departmentpagetestcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.DepartmentEditPageObject;
import com.ityss.tamplo.pageobject.DepartmentPageObject;
import com.ityss.tamplo.pageobject.LoginPageObject;
import com.ityss.tamplo.testbase.TestBase;

public class RemoveUserFromDepartmentTest extends TestBase
{

	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	DepartmentPageObject departmentpageobject;
	DepartmentEditPageObject deprtmenteditpageobject;
	int count =0;
	String departmentname = prob.getProperty("olddepartmentname");
	String username = prob.getProperty("usertoremove");

	@BeforeClass
	public void nevigateToDepartmentPage() throws IOException
	{
		loginpageobject   = new LoginPageObject(driver);
		dashboardagebject = new DashboardPageObject(driver);
		departmentpageobject = new DepartmentPageObject(driver); 
		deprtmenteditpageobject = new DepartmentEditPageObject(driver);
		
		dashboardagebject.openDepartmentPage();	
		test.log(Status.PASS, MarkupHelper.createLabel(" department page open successfully ", ExtentColor.GREEN));
	}
	
	@Test(priority = 0)
	public void editDepartmentTestCase() throws InterruptedException 
	{
			
		if (deprtmenteditpageobject.isThreeDisplayDotOrNot(departmentname)) 
		{
			deprtmenteditpageobject.clickOnEditButtonOfDepartment(departmentname);
			test.log(Status.PASS, MarkupHelper.createLabel("successfully click on edit button of department : " + departmentname, ExtentColor.GREEN));
			count =1;
		}else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("User not have department edit permission please contact to admin" , ExtentColor.RED));
		}
		
	}
	
	@Test(priority = 1)
	public void removeUserFromDprtTest() 
	{
	if (count==1) 
	  {
		if (username.equals(null) || username.isEmpty()) 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("not specify which to be remove " , ExtentColor.GREEN));
			System.exit(0);
			
		}
		String alluserlist = username;
		String[] alluseraftersorting= alluserlist.split(",");
		for (String singleuser : alluseraftersorting) 
		{
		
		  boolean userstatus = deprtmenteditpageobject.checkUserPresentOrNot(singleuser);
		  test.log(Status.PASS, MarkupHelper.createLabel("we want to remove " + singleuser + "  user from list" , ExtentColor.BLUE));
		  
		  if (userstatus) 
		  {
			 test.log(Status.PASS, MarkupHelper.createLabel(" is this user present in department or not : " + userstatus , ExtentColor.GREEN));	

			boolean editbuttondisplayornot = deprtmenteditpageobject.checkUserEditButtonPresentOrNot(singleuser);
			if (editbuttondisplayornot) 
			{
				deprtmenteditpageobject.clickOnEditButtonOfUser(singleuser);
				test.log(Status.PASS, MarkupHelper.createLabel("click on edit button " , ExtentColor.GREEN));
				
				deprtmenteditpageobject.clickOnThreeDotOfuser(singleuser);
				
				deprtmenteditpageobject.clickOnRemoveBtnOfUser(singleuser);
				test.log(Status.PASS, MarkupHelper.createLabel("click on remove button" , ExtentColor.GREEN));
				
				deprtmenteditpageobject.clickOnConfirmBtnofSwal();
				test.log(Status.PASS, MarkupHelper.createLabel(singleuser + " : this user successfully remove from user list" , ExtentColor.PURPLE));
				
				deprtmenteditpageobject.clickOnOkBtnofSwal();
					
			} else 
			{
				test.log(Status.PASS, MarkupHelper.createLabel("login user not having edit permission of user " , ExtentColor.RED));
				
			}
			
		    } else 
		    {
			test.log(Status.PASS, MarkupHelper.createLabel("user not present in selected department" , ExtentColor.RED));
		    }
	     } 
	
	  }else 
	     {
		   test.log(Status.PASS, MarkupHelper.createLabel("login user having only department read permission so he can't remove user" , ExtentColor.GREY));
	      }	
		
	}
}
