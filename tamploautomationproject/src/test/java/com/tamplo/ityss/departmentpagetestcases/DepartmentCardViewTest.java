package com.tamplo.ityss.departmentpagetestcases;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.DepartmentPageObject;
import com.ityss.tamplo.pageobject.LoginPageObject;
import com.ityss.tamplo.testbase.TestBase;

public class DepartmentCardViewTest extends TestBase
{
	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	DepartmentPageObject departmentpageobject;
	

	@BeforeClass
	public void nevigateToDepartmentPage() throws IOException
	{
		loginpageobject   = new LoginPageObject(driver);
		dashboardagebject = new DashboardPageObject(driver);
		departmentpageobject = new DepartmentPageObject(driver); 
		
		dashboardagebject.openDepartmentPage();	
		test.log(Status.PASS, MarkupHelper.createLabel(" department page open successfully ", ExtentColor.GREEN));
	}
	
	@Test
	public  void listOfProjctAndServicesInDepartment()
	{
		String departmentname = "worload dprt";
		
		boolean isdepartmentpresent  = departmentpageobject.isDepartmentPresentInGrid(departmentname);
				
		if (isdepartmentpresent) 
		{
			int projectscount =  departmentpageobject.listOfProjectsInDepartmentGridView(departmentname);
			int servicecount = departmentpageobject.listOfServiceInDepartmentGridView(departmentname);
			test.log(Status.PASS, MarkupHelper.createLabel(" List of project present in this '" + departmentname + "' department is : " + projectscount, ExtentColor.GREEN));
			test.log(Status.PASS, MarkupHelper.createLabel(" List of service present in this '" + departmentname + "' department is : " + servicecount, ExtentColor.GREEN));
			
		} else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("' " + departmentname + " ' : this department not display in department list ", ExtentColor.RED));
		}
		
	}
	
	
}
