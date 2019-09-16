package com.tamplo.ityss.meetingtestcases;

import java.io.IOException;

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
import com.ityss.tamplo.pageobject.MeetingPageObject;
import com.ityss.tamplo.testbase.TestBase;
import com.ityss.tamplo.utils.SelectDate;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class AddMeetingTest extends TestBase
{

	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	ActionPageObject actionpageobject;
	SelectDate selectdate;
	SelectDueDateForAction choicedate;
	CommonObject filterobject ;
	MeetingPageObject meetingpageobjects ;

	@BeforeClass
	public void nevigateToMeetingPage() throws IOException
	{
		loginpageobject   = new LoginPageObject(driver);
		dashboardagebject = new DashboardPageObject(driver);
		actionpageobject  = new ActionPageObject(driver);
		filterobject      = new CommonObject(driver);
		selectdate        = new SelectDate(driver);
		choicedate        = new SelectDueDateForAction(driver);
		meetingpageobjects= new MeetingPageObject(driver);
		
		dashboardagebject.openMeetingPage();	
		test.log(Status.PASS, MarkupHelper.createLabel(" meeting page open successfully ", ExtentColor.GREEN));
	}
	
	@Test(dataProvider="meetingdata")
	public  void addMeetingTest(String meetingtitle,String meetingtype,String meetingtypedata,String year,String monthfullname,String day,
			String hours,String min,String meetingoccurencetype,String selectdayofweek,String occuranceyear,String occurancemonthfullname,
			String occuranceday,
			String meetinginvitee,String meetinglocation,String meetingurl) throws InterruptedException
			
	{
		
		meetingpageobjects.MeetingAddButton();
		test.log(Status.PASS, MarkupHelper.createLabel(" add meeting popup display", ExtentColor.GREEN));
		
		boolean meetingpopupstatus = meetingpageobjects.isMeetingPopUpPresent();
		test.log(Status.PASS, MarkupHelper.createLabel(" is meeting popup display :" + meetingpopupstatus, ExtentColor.GREEN));
		
		System.out.println(meetingpopupstatus);
		
		meetingpageobjects.enterMeetingTile(meetingtitle);
		test.log(Status.PASS, MarkupHelper.createLabel(meetingtitle + " : this meeting title  enter successfully", ExtentColor.GREEN));
		
		
		
		meetingpageobjects.selectMeetingTypeAndData(meetingtype,meetingtypedata);
		test.log(Status.PASS, MarkupHelper.createLabel(meetingtype + " : this  meeting type select and meeting type data entered", ExtentColor.GREEN));
		//meetingpageobjects.selectMeetingTypeAndData("MILESTONE MEETING","meeting check event,milestone test");
		//meetingpageobjects.selectMeetingTypeAndData("STORY MEETING","workload story,fdfdfd");
		//meetingpageobjects.selectMeetingTypeAndData("FREE MEETING","");
		
		Thread.sleep(700);
		
		meetingpageobjects.ClickOnProjectIcon();
		
		meetingpageobjects.clickOnStartDayIcon();
		
		choicedate.selectMeetingStartDateByChoice(Integer.parseInt(year),monthfullname,Integer.parseInt(day));
		test.log(Status.PASS, MarkupHelper.createLabel(year+ ":" +monthfullname+ ":" +day + " : this meeting start date set ", ExtentColor.GREEN));
		
		meetingpageobjects.clickOnStartTimeIcon();
		
		meetingpageobjects.selectTime(Integer.parseInt(hours),Integer.parseInt(min));
		test.log(Status.PASS, MarkupHelper.createLabel(hours + ":" + min + " : this meeting start time set ", ExtentColor.GREEN));
		
		String meetingoccurancetype = meetingoccurencetype;
		
		meetingpageobjects.SelectMeetingOccurenceType(meetingoccurancetype);
		test.log(Status.PASS, MarkupHelper.createLabel(meetingoccurancetype + " : this meeting occurance type select", ExtentColor.GREEN));
		
		if (meetingoccurancetype.equalsIgnoreCase("daily")) 
		{
			meetingpageobjects.clickOnDayOfWeek();
			
			meetingpageobjects.selectDayOfTheWeek(selectdayofweek);
			test.log(Status.PASS, MarkupHelper.createLabel("  week of day select succussfully", ExtentColor.GREEN));
		} 
		
		
		if (meetingoccurancetype.equalsIgnoreCase("Daily") || meetingoccurancetype.equalsIgnoreCase("Monthly") || meetingoccurancetype.equalsIgnoreCase("Weekly")) 
		{
			meetingpageobjects.clickOnOccurenceEndDateField();
			
			choicedate.selectMeetingDateByChoice(Integer.parseInt(occuranceyear),occurancemonthfullname,Integer.parseInt(occuranceday));
			test.log(Status.PASS, MarkupHelper.createLabel(occuranceyear+ ":" +occurancemonthfullname+ ":" +occuranceday+ " : this meeting occurance date select ", ExtentColor.GREEN));
		}
		
		meetingpageobjects.addInviteeIntoMeeting(meetinginvitee);
		test.log(Status.PASS, MarkupHelper.createLabel("Meeting invitee added successfully", ExtentColor.GREEN));
		
		meetingpageobjects.enterMeetingLocation(meetinglocation);
		test.log(Status.PASS, MarkupHelper.createLabel("meeting location enter successfully", ExtentColor.GREEN));
		
		meetingpageobjects.enterMeetingURL(meetingurl);
		test.log(Status.PASS, MarkupHelper.createLabel("meeting url  enter successfully", ExtentColor.GREEN));
		
		meetingpageobjects.clickOnScheduleBtn();
		test.log(Status.PASS, MarkupHelper.createLabel("successfully  click on schedule button", ExtentColor.GREEN));
		
		boolean ismeetingpresent = meetingpageobjects.verifyRecordAddedOrNot(meetingtitle);
		System.out.println(meetingtitle + " is this meeting successfully added into list : " + ismeetingpresent);
		test.log(Status.PASS, MarkupHelper.createLabel(meetingtitle + " : this meeting successfully added into list ", ExtentColor.GREEN));
		
	}
	
	@DataProvider(name="meetingdata")
	public Object[][] getMeetingDataFromExcel() 
	{
		
		Object getdatta[][] = ExcelHelper.getExcelData("meetingdata");
		return getdatta;
	}
}
