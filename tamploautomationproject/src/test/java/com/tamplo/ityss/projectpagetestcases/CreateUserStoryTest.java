package com.tamplo.ityss.projectpagetestcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.helper.excel.ExcelHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.ProjectPageObject;
import com.ityss.tamplo.pageobject.UserStoryPageObject;
import com.ityss.tamplo.testbase.TestBase;

public class CreateUserStoryTest extends TestBase
{
	DashboardPageObject dashboardagebject;
	ProjectPageObject projectpageobject;
	UserStoryPageObject  userstorypageobject;
	int count=0;
	
	@BeforeClass
	public void nevigateToProjectPage() throws IOException
	{
		dashboardagebject = new DashboardPageObject(driver);
		projectpageobject = new ProjectPageObject(driver);
		userstorypageobject = new UserStoryPageObject(driver);
		
		dashboardagebject.openProjectPage();
		test.log(Status.PASS, MarkupHelper.createLabel(" Project page open successfully ", ExtentColor.BROWN));
		
		//projectpageobject.clickOnInputProjectInLeftSide("Wrkld project telling");
		projectpageobject.clickOnInputProjectInLeftSide(prob.getProperty("projecttoadduserstories"),test);
		test.log(Status.PASS, MarkupHelper.createLabel(" Project select successfully", ExtentColor.GREEN));
		
		boolean ispresnt =userstorypageobject.checkIfUserStoryLabelPresentOrNot();
		test.log(Status.PASS, MarkupHelper.createLabel(" User story label is present : " +  ispresnt, ExtentColor.GREEN));
		
		if (ispresnt) 
		{
			userstorypageobject.clickOnUserStoryIcon();
			boolean isadduserstory = userstorypageobject.checkIfAddUserStoryButtonPresentOrNot();
			test.log(Status.PASS, MarkupHelper.createLabel("user story page open Successfully", ExtentColor.GREEN));
			
			if (isadduserstory) 
			{
				System.out.println("user story add button display");
				test.log(Status.PASS, MarkupHelper.createLabel("add user story form open successfully", ExtentColor.GREEN));
				
			}else 
			{
				System.out.println("user does not have add user story rights");
				test.log(Status.PASS, MarkupHelper.createLabel("Login user does not have right to add user story", ExtentColor.RED));
				count  = 1;
			}
		}
		else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Login user does not have right to access user story page", ExtentColor.RED));
			count  = 1;
			
		}
	}
	
	@Test(dataProvider="userstorydata")
	public  void addUserStoryTest(String storytitle,String storydescription) throws InterruptedException
	{
		if (count==0) 
		{
		int userstorycountbefore = userstorypageobject.getUserStoryCount(); 
		test.log(Status.PASS, MarkupHelper.createLabel("list of user story before adding new one count is : " + userstorycountbefore, ExtentColor.GREEN));
			
		userstorypageobject.clickOnAddUserStoryButton();
		test.log(Status.PASS, MarkupHelper.createLabel("click  on add user story button", ExtentColor.GREEN));
		
		userstorypageobject.enterAllDetailsAndSaveStory(storytitle,storydescription,test);
		
		int userstorycountafter = userstorypageobject.getUserStoryCount(); 
		test.log(Status.PASS, MarkupHelper.createLabel("list of user story after adding new one count is : " + userstorycountafter, ExtentColor.GREEN));
		
		}else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Please contact to admin to get access rights of user story", ExtentColor.BLUE));	
		}
	}
	
	
	@DataProvider(name="userstorydata")
	public Object[][] getExcelData() throws EncryptedDocumentException, Exception
	{
		Object data[][] = ExcelHelper.getExcelData("userstorydata");
		return data;
	}
}
