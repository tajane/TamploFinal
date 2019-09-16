package com.tamplo.ityss.departmentpagetestcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.CommonObject;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.DepartmentPageObject;
import com.ityss.tamplo.pageobject.LoginPageObject;
import com.ityss.tamplo.testbase.TestBase;
import com.ityss.tamplo.utils.SelectDate;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class AddDepartmentTest extends TestBase 
{
	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	DepartmentPageObject departmentpageobject;
	SelectDate selectdate;
	SelectDueDateForAction choicedate;
	CommonObject filterobject ;


	@BeforeClass
	public void nevigateToDepartmentPage() throws IOException
	{
		loginpageobject   = new LoginPageObject(driver);
		dashboardagebject = new DashboardPageObject(driver);
		filterobject      = new CommonObject(driver);
		selectdate        = new SelectDate(driver);
		choicedate        = new SelectDueDateForAction(driver);
		departmentpageobject = new DepartmentPageObject(driver); 
		
		
		/*loginpageobject.login(prob.getProperty("username"), prob.getProperty("password"));
		test.log(Status.PASS, MarkupHelper.createLabel((prob.getProperty("username")) + " : this user login successfully ", ExtentColor.GREEN));
		
		dashboardagebject.setCompany(prob.getProperty("company"));
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("company") + " : this company select successfully ", ExtentColor.GREEN));
		
		dashboardagebject.setLanguage(prob.getProperty("language"));
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("language") + " : this language selected ", ExtentColor.GREEN));*/
		
		dashboardagebject.openDepartmentPage();	
		test.log(Status.PASS, MarkupHelper.createLabel(" department page open successfully ", ExtentColor.GREEN));
	}
	
	@Test
	public void addDepartmentTest() throws InterruptedException
	{
		departmentpageobject.clickOnAddDepartmentbtn();
		
		departmentpageobject.enterDepartmentNametxt("new automation");
		
		departmentpageobject.clickOnOutsideToSave();
		
		boolean isnewdepartmentcreated = departmentpageobject.checkNewDeprtOrDuplicateDprt();
		
		Thread.sleep(500);
		
		if (isnewdepartmentcreated) 
		{
			departmentpageobject.clickAddButtonOfMember();
			
			departmentpageobject.enterUserAndAddUser("nitin.tajane@protonmail.com");
			
			boolean isuseradd = departmentpageobject.verifyUserAddedSuccuessfully();
			
			System.out.println("is user add : " + isuseradd);
			
			departmentpageobject.clickOnOKBtn();
			
	        departmentpageobject.clickAddButtonOfManager();
				
		    departmentpageobject.enterUserAndAddUser("nitin.tajane@tutanota.com");
				
			boolean isuseradd2 = departmentpageobject.verifyUserAddedSuccuessfully();
				
			System.out.println("is user add : " + isuseradd2);
				
			departmentpageobject.clickOnOKBtn();
		}else 
		{
			System.out.println("we found duplicate department");
		}

		
		
		
		
		
		
	}
	
}
