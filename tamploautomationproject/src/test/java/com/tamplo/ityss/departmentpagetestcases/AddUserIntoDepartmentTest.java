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
import com.ityss.tamplo.testbase.TestBase;

public class AddUserIntoDepartmentTest extends TestBase
{
	
	DashboardPageObject dashboardagebject;
	DepartmentPageObject departmentpageobject;
	DepartmentEditPageObject deprtmenteditpageobject;
	int count =0;
	String departmentname = prob.getProperty("olddepartmentname");
	String username = prob.getProperty("adduserintodpt");
	String choiceuserroletoadduser = prob.getProperty("userroletoadduser");
	
	@BeforeClass
	public void nevigateToDepartmentPage() throws IOException
	{
		
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
	
	@Test(priority = 1,enabled=true)
	public void addUserIntoDprtTest() throws InterruptedException 
	{
		if (count==1) 
		  {
			if (username.equals(null) || username.isEmpty()) 
			{
				test.log(Status.PASS, MarkupHelper.createLabel("not specify which to be add " , ExtentColor.GREEN));
				System.exit(0);
				
			}
			boolean userrolepresentornot = deprtmenteditpageobject.userRoleTitlePresentOrNot(test,choiceuserroletoadduser);
			
			
			if (userrolepresentornot) 
			{
				test.log(Status.PASS, MarkupHelper.createLabel("we want to add user into " + choiceuserroletoadduser + " user role" , ExtentColor.GREEN));
				
				boolean addbuttondisplayornot = deprtmenteditpageobject.checkAddUserButtonPresentOrNot(test,choiceuserroletoadduser);
				
				if (addbuttondisplayornot) 
				{
					String alluserlist = username;
					String[] alluseraftersorting= alluserlist.split(",");
					for (String singleuser : alluseraftersorting) 
					{
						deprtmenteditpageobject.clickOnAddUserRoleButton(choiceuserroletoadduser);
						test.log(Status.PASS, MarkupHelper.createLabel("add user form open successfully" , ExtentColor.GREEN));
						
						deprtmenteditpageobject.enterUserEmailIdIntoText(choiceuserroletoadduser, singleuser);
						test.log(Status.PASS, MarkupHelper.createLabel(singleuser + " :  this user want to add into department" , ExtentColor.BLUE));
						
						boolean verifychoiceUserListedOrNot =deprtmenteditpageobject.checkChoiceUserListedOrNot(choiceuserroletoadduser, singleuser);
								
						if (verifychoiceUserListedOrNot) 
						{
							deprtmenteditpageobject.selectChoiceUserFromList(choiceuserroletoadduser, singleuser);
							test.log(Status.PASS, MarkupHelper.createLabel("User present in list that we have entered email address" , ExtentColor.GREEN));
						
							deprtmenteditpageobject.clickOnSaveButtonOfUser();
							test.log(Status.PASS, MarkupHelper.createLabel("click on save button" , ExtentColor.GREEN));
							
							Thread.sleep(500);
							deprtmenteditpageobject.clickOnOkBtnofSwal();
							test.log(Status.PASS, MarkupHelper.createLabel("user successfully added into list" , ExtentColor.GREEN));
							
							
						} else 
						{
							test.log(Status.PASS, MarkupHelper.createLabel("User email  id not listed that we have entered" , ExtentColor.RED));
						
							Thread.sleep(500);
							deprtmenteditpageobject.clickOnCancelButtonOfUser();
							test.log(Status.PASS, MarkupHelper.createLabel("click on cancel button" , ExtentColor.GREY));
						}
								
						
					}
				} else 
				{
					test.log(Status.PASS, MarkupHelper.createLabel("login user not having add user permission" , ExtentColor.RED));
				}
			}else 
			{
				
				test.log(Status.PASS, MarkupHelper.createLabel("user role title not present" , ExtentColor.RED));
				System.out.println("user role title not present");
				System.exit(0);
			}
			
			
			
		  }
	}
}
