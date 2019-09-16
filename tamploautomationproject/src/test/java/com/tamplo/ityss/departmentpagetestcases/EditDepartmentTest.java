package com.tamplo.ityss.departmentpagetestcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
		String departmentname = "DPT";
		
		if (deprtmenteditpageobject.isThreeDisplayOrNot(departmentname)) 
		{
			
			deprtmenteditpageobject.clickOnEditButtonOfDepartment(departmentname);
			
			int length = departmentname.length();
			
			WebElement rlrlrlr = driver.findElement(By.xpath("//textarea[@ng-model='uiData.transientDepartment.name']"));
			for (int i = 1; i <=length; i++) 
			{
				rlrlrlr.sendKeys(Keys.BACK_SPACE);
			}
			
			departmentpageobject.enterDepartmentNametxt("new update");
			
			Thread.sleep(500);
			
			driver.findElement(By.xpath("//li[@class='iconlistItem']//a[@class='iconlistItemLink']")).click();
			
			
			
		} else 
		{

		}
		
	}
}
