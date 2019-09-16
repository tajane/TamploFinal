package com.tamplo.ityss.projectpagetestcases;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.ActionPageObject;
import com.ityss.tamplo.pageobject.CommonObject;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.MeetingPageObject;
import com.ityss.tamplo.pageobject.ProjectPageObject;
import com.ityss.tamplo.testbase.TestBase;
import com.ityss.tamplo.utils.SelectDate;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class AddProjectTest  extends TestBase
{

	
	DashboardPageObject dashboardagebject;
	ActionPageObject actionpageobject;
	SelectDate selectdate;
	SelectDueDateForAction choicedate;
	CommonObject filterobject ;
	MeetingPageObject meetingpageobjects ;
	ProjectPageObject  projectpageobject ;

	@BeforeClass
	public void nevigateToProjectPage() throws IOException
	{
		
		dashboardagebject = new DashboardPageObject(driver);
		actionpageobject  = new ActionPageObject(driver);
		filterobject      = new CommonObject(driver);
		selectdate        = new SelectDate(driver);
		choicedate        = new SelectDueDateForAction(driver);
		meetingpageobjects= new MeetingPageObject(driver);
		projectpageobject =  new ProjectPageObject(driver);
		
		
		dashboardagebject.openProjectPage();	
		test.log(Status.PASS, MarkupHelper.createLabel(" project page open successfully ", ExtentColor.GREEN));
	}
	
	@Test
	public void createProjectTest() throws InterruptedException
	{
		
		projectpageobject.addProjectButton();
		
		projectpageobject.enterProjectName("worload dprt", "automation teesting");
		
		projectpageobject.clickOnOutSideToSaveProject();
		
		boolean isnewprojectcreated = projectpageobject.checkNewProjectOrDuplicateProject();
		
		if (isnewprojectcreated) 
		{
			Thread.sleep(500);
			
			projectpageobject.addProjectMemberBtn();
			
			projectpageobject.enterUserAndAddUser("nitin.tajane@tutanota.com");
			
			projectpageobject.clickOnOKbtn();
			
			projectpageobject.addProjectManagerBtn();
			
			projectpageobject.enterUserAndAddUser("nitin.tajane@protonmail.com");
			
			projectpageobject.clickOnOKbtn();
			
		}else 
		{
			System.out.println("we found duplicate project");
		}
		
	}
	
}
