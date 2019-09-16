package com.tamplo.ityss.projectpagetestcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.MilestonePageObject;
import com.ityss.tamplo.pageobject.ProjectPageObject;
import com.ityss.tamplo.testbase.TestBase;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class CreateMilestoneTest extends TestBase
{
	DashboardPageObject dashboardagebject;
	ProjectPageObject projectpageobject;
	MilestonePageObject milestonepageobject;
	SelectDueDateForAction milestonedates;
	
	int count=0;
	
	@BeforeClass
	public void nevigateToProjectPage() throws IOException
	{
		dashboardagebject = new DashboardPageObject(driver);
		projectpageobject = new ProjectPageObject(driver);
		milestonepageobject = new MilestonePageObject(driver);
		milestonedates =new  SelectDueDateForAction(driver);
		
		
		dashboardagebject.openProjectPage();
		test.log(Status.PASS, MarkupHelper.createLabel(" Project page open successfully ", ExtentColor.BROWN));
		
		
		//boolean isprojectpresent = projectpageobject.clickOnInputProjectInLeftSide(prob.getProperty("projectforaddmilestone"),test);
		
		boolean isprojectpresent = projectpageobject.selectProjectInGridView(prob.getProperty("projectforaddmilestone"));
		
		if (!isprojectpresent) 
		{
			count =1;
		}
		
		if (isprojectpresent) 
		{
		
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("projectforaddmilestone") + " : this Project select successfully", ExtentColor.GREEN));
		
		boolean ispresnt =milestonepageobject.checkIfMileStoneLabelPresentOrNot();
		test.log(Status.PASS, MarkupHelper.createLabel( " Milestone label is present : " +  ispresnt, ExtentColor.GREEN));
		
		if (ispresnt) 
		{
			milestonepageobject.clickOnMileStoneIcon();
			boolean isaddmilestone = milestonepageobject.checkIfAddMileStoneButtonPresentOrNot();
			test.log(Status.PASS, MarkupHelper.createLabel("Milestone page open Successfully", ExtentColor.GREEN));
			
			if (isaddmilestone) 
			{
				
				test.log(Status.PASS, MarkupHelper.createLabel("milestone add button display", ExtentColor.GREEN));
				
			}else 
			{
				
				test.log(Status.PASS, MarkupHelper.createLabel("Login user does not have right to add milestone", ExtentColor.RED));
				count  = 1;
			}
		}
		else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Login user does not have right to access milestone page", ExtentColor.RED));
			count  = 1;
			
		}
		
		} else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Project not listed project list or project not added", ExtentColor.RED));
		}
	}
	
	@Test(enabled = true)
	public void AddMilestoneTest() throws InterruptedException
	{
		if (count==0) 
		{
		int milestonecountbeforeadd = milestonepageobject.getMilestoneCount();
		test.log(Status.PASS, MarkupHelper.createLabel("list of milestone before adding new one count is : " + milestonecountbeforeadd, ExtentColor.GREEN));
		
		milestonepageobject.clickOnAddMilestoneBtn();
		test.log(Status.PASS, MarkupHelper.createLabel("click  on add milestone button", ExtentColor.GREEN));
		
		boolean istitleenter = milestonepageobject.enterMilestoneTitle("automation milestone",test);
		
		if (istitleenter) 
		{
			milestonepageobject.clickOnMilestoneStartDateIcon();
			int startyear = 2019;
	        int startdate = 15;
	        String startmonthname = "october";
			milestonedates.selectFilterDueDateByChoice(startyear, startmonthname, startdate);
			test.log(Status.PASS, MarkupHelper.createLabel("Milestone start date select successfully : " + startdate + " / " + startmonthname + "/ " + startyear, ExtentColor.GREEN));
			
			milestonepageobject.clickOnMilestoneEndDateIcon();
			int endyear = 2020;
	        int enddate = 30;
	        String endmonthname = "may";
			milestonedates.selectMilestoneEndDate(endyear, endmonthname, enddate);
			test.log(Status.PASS, MarkupHelper.createLabel("Milestone end date select successfully : " + enddate + " / " + endmonthname + "/ " + endyear, ExtentColor.GREEN));
		}
		
		milestonepageobject.clickOnSaveBtnOfMilestone();
		test.log(Status.PASS, MarkupHelper.createLabel("click  on save button of milestone", ExtentColor.GREEN));
		
		milestonepageobject.VerifySuccessPopupDisplayOrNot(test);
		
		int milestonecountafteradd = milestonepageobject.getMilestoneCount();
		test.log(Status.PASS, MarkupHelper.createLabel("list of milestone after adding new one count is : " + milestonecountafteradd, ExtentColor.GREEN));
		
		
		}else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Please contact to admin to get access rights of milestone", ExtentColor.BLUE));	
		}
		
		
	}
	
}
