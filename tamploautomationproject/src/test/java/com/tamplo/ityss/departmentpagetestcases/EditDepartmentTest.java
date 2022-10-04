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

public class EditDepartmentTest extends TestBase
{

	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	DepartmentPageObject departmentpageobject;
	DepartmentEditPageObject deprtmenteditpageobject;
	

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
	
	@Test
	public void editDepartmentTestCase() throws InterruptedException 
	{
		String departmentname = "automation testing";
		String newdepartmentname = "automation testing department 1";
		
		if (deprtmenteditpageobject.isThreeDisplayDotOrNot(departmentname)) 
		{
			
			deprtmenteditpageobject.clickOnEditButtonOfDepartment(departmentname);
			test.log(Status.PASS, MarkupHelper.createLabel("successfully click on edit button of department : " + departmentname, ExtentColor.GREEN));
			
			deprtmenteditpageobject.clearDprtName(departmentname);
			test.log(Status.PASS, MarkupHelper.createLabel("clear previous department name", ExtentColor.GREEN));
			
			departmentpageobject.enterDepartmentNametxt(newdepartmentname);
			test.log(Status.PASS, MarkupHelper.createLabel("department name update successfully as : " + newdepartmentname , ExtentColor.GREEN));
	
			deprtmenteditpageobject.clickOutsideToSaveDprt();
			test.log(Status.PASS, MarkupHelper.createLabel("successfully save new department name " , ExtentColor.GREEN));
		
		} else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("User not have department edit permission please contact to admin" , ExtentColor.RED));
		}
		
	}
}
