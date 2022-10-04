package com.tamplo.ityss.departmentpagetestcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.DepartmentEditPageObject;

import com.ityss.tamplo.testbase.TestBase;

public class RemoveDepartmentTest extends TestBase
{
	DashboardPageObject dashboardagebject;
	DepartmentEditPageObject deprtmenteditpageobject;
	int count =0;
	String departmentname = prob.getProperty("olddepartmentname");
	
	
	@BeforeClass
	public void nevigateToDepartmentPage() throws IOException
	{
		
		dashboardagebject = new DashboardPageObject(driver);
		deprtmenteditpageobject = new DepartmentEditPageObject(driver);
		
		dashboardagebject.openDepartmentPage();	
		test.log(Status.PASS, MarkupHelper.createLabel(" department page open successfully ", ExtentColor.GREEN));
	}
	
	@Test(priority = 0)
	public void removedDepartmentTestCase() throws InterruptedException 
	{
			
		if (deprtmenteditpageobject.isThreeDisplayDotOrNot(departmentname)) 
		{
			deprtmenteditpageobject.clickOnThreeDotOfDepartment(departmentname);
			test.log(Status.PASS, MarkupHelper.createLabel("successfully click on three dot of department : " + departmentname, ExtentColor.GREEN));
			
			deprtmenteditpageobject.clickOnRemoveButtonOfDepartment(departmentname);
			test.log(Status.PASS, MarkupHelper.createLabel("successfully click on remove button of department : " + departmentname, ExtentColor.GREEN));
			
			deprtmenteditpageobject.clickOnConfirmBtnofSwal();
			test.log(Status.PASS, MarkupHelper.createLabel(" department successfully removed  ", ExtentColor.GREEN));
			
			deprtmenteditpageobject.clickOnOkBtnofSwal();
			
			count =1;
		}else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("User not have department edit permission please contact to admin" , ExtentColor.RED));
		}
		
	}
}
