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
		String rolenname = prob.getProperty("newuserroletoadduser");
		String departmentname = prob.getProperty("departmenttoaddproject");
		String newprojectname = prob.getProperty("newprojectname");
		String useremailid = prob.getProperty("newprojectusers");
		
		boolean  isprojectaddpermission = projectpageobject.checkAddProjectPermission();
		test.log(Status.PASS, MarkupHelper.createLabel("does user having add project permission : " + isprojectaddpermission, ExtentColor.GREEN));
		
		if (isprojectaddpermission) 
		{
			
			boolean isdprtpresent = projectpageobject.selectDepartmentFromList(departmentname);
			
			if (isdprtpresent) 
			{
			
			projectpageobject.enterProjectName(newprojectname);	
			
			test.log(Status.PASS, MarkupHelper.createLabel(" project name enter successfully ", ExtentColor.GREEN));
			
			projectpageobject.clickOnOutSideToSaveProject();
			
			boolean isnewprojectcreated = projectpageobject.checkNewProjectOrDuplicateProject();
			
			
			if (isnewprojectcreated) 
			{
				test.log(Status.PASS, MarkupHelper.createLabel(" project create successfully ", ExtentColor.GREEN));
				Thread.sleep(500);
				
				
				
				boolean  userrolepresentornot = projectpageobject.checkUserRolePresentOrNot(rolenname);
				
				if (userrolepresentornot) 
				{
					test.log(Status.PASS, MarkupHelper.createLabel(rolenname + " : this user role  present in user section ", ExtentColor.GREEN));
					
					boolean isaddpermissionpresentornot = projectpageobject.checkAddUserPresentOrNot(rolenname);
					if (isaddpermissionpresentornot) 
					{
						
						String[] alluseraftersorting= useremailid.split(",");
						for (String singleuser : alluseraftersorting) 
						{
						  projectpageobject.clickOnAddButtonOfUserRole(rolenname);
						  test.log(Status.PASS, MarkupHelper.createLabel(" click on add button of user ", ExtentColor.BROWN));
						
						  projectpageobject.enterUserEmailId(rolenname,singleuser);
						  test.log(Status.PASS, MarkupHelper.createLabel(" Email id enter successfully ", ExtentColor.GREEN));
						
						  boolean isuserpresent = projectpageobject.checkThatUserPresentInListOrNot(rolenname,singleuser);
						
						  
						  if (isuserpresent) 
						   { 
						     	projectpageobject.selectChoiceUserFromList(rolenname,singleuser);
						     	test.log(Status.PASS, MarkupHelper.createLabel(singleuser +" : this user select successfully ", ExtentColor.GREEN));
						   
						     	Thread.sleep(500);
						     	projectpageobject.clickOnSaveButtonOfUserForm();
						     	test.log(Status.PASS, MarkupHelper.createLabel("click  on save button", ExtentColor.GREEN));
						   
						     	projectpageobject.clickOnOkBtnofSwal();
						     	test.log(Status.PASS, MarkupHelper.createLabel("user added successfully", ExtentColor.BLUE));
						   } else 
						   {
							   Thread.sleep(500);
							   projectpageobject.clickOnCancelButtonOfUserForm();
							   test.log(Status.PASS, MarkupHelper.createLabel(singleuser +" : this user not present in user list  ", ExtentColor.RED));
						   }
						}
						
						
					} else 
					{
						test.log(Status.PASS, MarkupHelper.createLabel(" Login user not having add user permission ", ExtentColor.RED));
					}
					
				} else 
				{
					test.log(Status.PASS, MarkupHelper.createLabel(rolenname + " : this user role  not present in user section ", ExtentColor.RED));
				}
				
				
			}else 
			{
				test.log(Status.PASS, MarkupHelper.createLabel(newprojectname + " : This project already created ", ExtentColor.RED));
			}	
			
			}else 
			{
				test.log(Status.PASS, MarkupHelper.createLabel(departmentname + " : this department not exist ", ExtentColor.RED));	
			}
		
	    } else 
	    {
	      test.log(Status.PASS, MarkupHelper.createLabel(" login user not having add project permission", ExtentColor.RED));
	    }
	}
		
}
