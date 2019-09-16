package com.tamplo.ityss.dayplantestcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.DayPlanObject;
import com.ityss.tamplo.testbase.TestBase;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class RemoveActionFromDayPlanTest extends TestBase
{

	DashboardPageObject dashboardagebject;
	DayPlanObject dayplanobject;
	SelectDueDateForAction dayplandate;
	
	@BeforeClass
	public void nevigateToDayPlan() 
	{
		dashboardagebject = new DashboardPageObject(driver);
		dayplanobject = new DayPlanObject(driver);
		dayplandate =new  SelectDueDateForAction(driver);
		
		dashboardagebject.openDayPlanPage();
		test.log(Status.PASS, MarkupHelper.createLabel(" DayPlan page open successfully ", ExtentColor.BROWN));
	}
	
	@Test
	public void removeActionFromDayPlanByChoiceSDate() throws InterruptedException
	{
		dayplanobject.clickOnDayPlanDate();
        int year = 2019;
        int day = 30;
        String monthname = "august";
		dayplandate.selectFilterDueDateByChoice(year, monthname, day);
		test.log(Status.PASS, MarkupHelper.createLabel("Day plan date select as : " + day +" / " + monthname + " / "+year , ExtentColor.GREEN));
	
		if (dayplanobject.isMyActionFilterEnableOrNot()) 
		{
			dayplanobject.clickOnMyActionButton();
			test.log(Status.PASS, MarkupHelper.createLabel(" Disable my action  filter", ExtentColor.GREEN));
			
		} else
		{
             System.out.println("button not enable");
		}
		
        int openactioncountbeforemoving = dayplanobject.getOpenActionCountFromDueFilter();
		
		test.log(Status.PASS, MarkupHelper.createLabel("open action count before moving from due action filter is : " + openactioncountbeforemoving, ExtentColor.GREEN));
		
		int dayplanopenactioncountbeforemoving = dayplanobject.getOpenActionCountFromDayPlan();
		
	    test.log(Status.PASS, MarkupHelper.createLabel("day plan open action count before moving into day plan is : " + dayplanopenactioncountbeforemoving, ExtentColor.GREEN));
		
	    if (dayplanopenactioncountbeforemoving!=0) 
	    {
	    	dayplanobject.MouseHoverToFirstActionTitle();
	    	
	    	dayplanobject.clickOnRemoveButton();
	    	
	    	test.log(Status.PASS, MarkupHelper.createLabel("Successfully click  on remove button of first Action", ExtentColor.GREEN));
	    	
	    	int openactioncountaftermoving = dayplanobject.getOpenActionCountFromDueFilter();
			
			test.log(Status.PASS, MarkupHelper.createLabel("open action count after moving into due action filter : " + openactioncountaftermoving, ExtentColor.GREEN));
			
			int dayplanopenactioncountaftermoving = dayplanobject.getOpenActionCountFromDayPlan();
			
			test.log(Status.PASS, MarkupHelper.createLabel("day plan open action count after removing from day plan  is : " + dayplanopenactioncountaftermoving, ExtentColor.GREEN));
		
	    	
		}else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Action not present in selected date day plan", ExtentColor.GREEN));
		}
	}
}
