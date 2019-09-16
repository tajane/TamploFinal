package com.tamplo.ityss.actionpagetestcases;

import java.io.IOException;

import org.apache.http.protocol.UriHttpRequestHandlerMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.helper.excel.ExcelHelper;
import com.ityss.tamplo.pageobject.ActionPageObject;
import com.ityss.tamplo.pageobject.CommonObject;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.LoginPageObject;
import com.ityss.tamplo.testbase.TestBase;
import com.ityss.tamplo.utils.SelectDate;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class addActionInListView extends TestBase
{
	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	ActionPageObject actionpageobject;
	SelectDate selectdate;
	SelectDueDateForAction choicedate;
	CommonObject filterobject ;
	
	@BeforeClass
	public void nevigateToActionPage() throws IOException
	{
		loginpageobject = new LoginPageObject(driver);
		dashboardagebject = new DashboardPageObject(driver);
		actionpageobject =  new ActionPageObject(driver);
		filterobject =  new CommonObject(driver);
		selectdate = new SelectDate(driver);
		choicedate = new SelectDueDateForAction(driver);
		
		
		/*loginpageobject.login(prob.getProperty("username"), prob.getProperty("password"));
		test.log(Status.PASS, MarkupHelper.createLabel((prob.getProperty("username")) + " : this user login successfully ", ExtentColor.BROWN));
		
		dashboardagebject.setCompany(prob.getProperty("company"));
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("company") + " : this company select successfully ", ExtentColor.BROWN));
		
		dashboardagebject.setLanguage(prob.getProperty("language"));
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("language") + " : this language selected ", ExtentColor.BROWN));*/
		
		dashboardagebject.openActionPage();
		test.log(Status.PASS, MarkupHelper.createLabel(" Action page open successfully ", ExtentColor.BROWN));
	}
	
	@Test(dataProvider="actiondata")
	public void addCriticalAction(String actionname,String year,String month, String day, String project,String assignee) throws InterruptedException
	{
		actionpageobject.OpenAddActionBtn();
		test.log(Status.PASS, MarkupHelper.createLabel(" Add Action form open successfully ", ExtentColor.ORANGE));
		
		actionpageobject.enterActionTitle(actionname);
		test.log(Status.PASS, MarkupHelper.createLabel(actionname + " : this action title  enter successfully ", ExtentColor.GREEN));
		
		actionpageobject.clickOnDueDateIcon();
		
		actionpageobject.clickOnDueDateIcon();
		
		choicedate.selectFilterDueDateByChoice(Integer.parseInt(year),month,Integer.parseInt(day));
		test.log(Status.PASS, MarkupHelper.createLabel("Due date enter successfully ", ExtentColor.GREEN));
		
		actionpageobject.selectCriticalImportance();
		test.log(Status.PASS, MarkupHelper.createLabel(" Action add into critical importance", ExtentColor.GREEN));
		
		actionpageobject.selectProject(project);
		test.log(Status.PASS, MarkupHelper.createLabel(" Project select successfully ", ExtentColor.GREEN));
		
		actionpageobject.selectActionAssginee(assignee);
		test.log(Status.PASS, MarkupHelper.createLabel("Assginee select successfully ", ExtentColor.GREEN));
		
		actionpageobject.saveActionButton();
		test.log(Status.PASS, MarkupHelper.createLabel(" Action save successfully ", ExtentColor.ORANGE));
	}
	
	@DataProvider(name="actiondata")
	public Object[][] addActionData()
	{
		Object data[][] = ExcelHelper.getExcelData("actiondata");
		return data;
	}
}
