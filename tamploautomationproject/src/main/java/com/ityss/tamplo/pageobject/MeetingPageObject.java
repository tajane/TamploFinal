package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.Yaml;

import com.ityss.tamplo.helper.javascript.JavaScriptHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;

public class MeetingPageObject 
{

	private WebDriver driver;
	
	public MeetingPageObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding'][@ng-click='createNewMeetingData()'][contains(text(),'NEW MEETING')]")
	WebElement btnaddmeeting;
	
	@FindBy(xpath="//div[@class='tamplo-modalNew ng-scope'][@ng-if='showCreateNewMeetingModal']")
	WebElement meetingpopup;
	
	@FindBy(xpath="//input[@ng-model='newMeeting.meetingSequence.title']")
	WebElement meetingtitlefield;
	
	@FindBy(xpath="//tamplo-select[@ng-model='newMeeting.meetingSequence.meetingType']/div/ul/li/a")
	WebElement meetingtype;
	
	@FindBy(xpath="//tamplo-input-select[@select-options='meetingTypeData as meetingTypeData.dataName for meetingTypeData in meetingData.meetingTypeDataList']/div/ul/li/input")
	WebElement meetingtypedatainput;
	
	@FindBy(xpath="//tamplo-input-select[@select-options='meetingTypeData as meetingTypeData.dataName for meetingTypeData in meetingData.meetingTypeDataList']/div/ul/li[1][@ng-class='{ \"showhint\": expanded }']/ul[1][@class='employeeList ng-scope']/li[1]/span[2]")
    WebElement firstmeetingdatatype;
	
	@FindBy(xpath="//li[@class='iconlistItem block2 ng-scope']//span[@class='icon icon-ic-project']")
	WebElement projecticon;
	
	@FindBy(xpath="//section[@class='tamplo-modalNewContentSection']/div[1]/ul[1]/li[2]/span[1]/a")
	WebElement meetingstarttime;
	
	@FindBy(xpath="//section[@class='tamplo-modalNewContentSection']/div[1]/ul[1]/li[1]/span[2]/a")
	WebElement meetingstartdate;
	
	@FindBy(xpath="//tamplo-select[@ng-model='newMeeting.meetingSequence.occuranceType']/div[1]/ul[1]/li[1]/a")
    WebElement  meetingoccurancetypeicon;
	
	@FindBy(xpath="//header[@class='tamplo-dialogHeader brbt']/ul[3]/li[1]/ul[1]/li")
	List<WebElement> listofselectedprojects;
	
	@FindBy(xpath="//li[@class='iconlistItem ng-scope']/span[contains(text(),'END ON')]/following-sibling::span[@class='moment-picker left']/a[1]/span[1]")
	WebElement occuranceenddateicon;
	
	@FindBy(xpath="//tamplo-select[@select-options='day as day.name  for day in meetingData.dayList']/div[1]/ul[1]/li[1]/a")
	WebElement dayoftheweekicon;
	
	@FindBy(xpath="//tamplo-select[@select-options='day as day.name  for day in meetingData.dayList']/div[1]/ul[1]/li[1]/div[1]/ul[1]/li/a")
	List<WebElement>  listofdayofweek;
	
	@FindBy(xpath="//span[contains(text(),'END ON')]")
	WebElement endonicon;
	
	@FindBy(xpath="//input[@placeholder='ADD PEOPLE']")
	WebElement addinviteeicon;
	
	/*@FindBy(xpath="//li[@class='iconlistItem withInputField addPeople ng-scope']/span[@class='textSmall ng-binding']")
	WebElement meetinginviteecount;*/
	
	@FindBy(xpath="//span[@class='textSmall ng-binding']")
	WebElement meetinginviteecount;
	
	@FindBy(xpath="//input[@ng-model='newMeeting.meetingSequence.location']")
	WebElement locationinputfield;
	
	@FindBy(xpath="//input[@ng-model='newMeeting.meetingSequence.meetingLink']")
	WebElement urlfield;
	
	@FindBy(xpath="//button[@ng-click='scheduleMeeting(newMeeting)']")
	WebElement schedulebutton;
	
	public void clickOnScheduleBtn() 
	{
		schedulebutton.click();
	}
	
	public void enterMeetingLocation(String locationtext) 
	{
		locationinputfield.sendKeys(locationtext);
	}
	
	public void enterMeetingURL(String urltext) 
	{
		urlfield.sendKeys(urltext);
	}
	
	public int getmeetingInviteeCount() 
	{
		int defaultcount = 0;
		try 
		{
			
		
		String countinvitee = meetinginviteecount.getText();
		String countaftertrim =  countinvitee.trim();
		
		if (countaftertrim.isEmpty()) 
		{
			return defaultcount;
			
		}else 
		{
			int actualcount = Integer.parseInt(countaftertrim); 
			return actualcount;
		}
		
		} catch (Exception e) 
		{
			return defaultcount;
		}
		
	}
	
	public void addInviteeIntoMeeting(String alluserfromexcel) 
	{
		String allinviteelist = alluserfromexcel;
		String[] allinviteeaftersorting= allinviteelist.split(",");
		for (String singleinvitee : allinviteeaftersorting) 
		{
			int beforeaddingmeetinginviteecount = getmeetingInviteeCount();
			
			
			addinviteeicon.sendKeys(singleinvitee);
			addinviteeicon.sendKeys(Keys.ENTER);
			
			int afteraddingmeetinginviteecount = getmeetingInviteeCount();
			
			
			if (afteraddingmeetinginviteecount > beforeaddingmeetinginviteecount) 
			{
				String testss = driver.findElement(By.xpath("//span[@class='textSmall error ng-binding']")).getText();
				
				System.out.println(singleinvitee + " : user added successfully into list " + "after adding count is : " + afteraddingmeetinginviteecount);
			}else if (afteraddingmeetinginviteecount == beforeaddingmeetinginviteecount) 
			{
				String testss = driver.findElement(By.xpath("//span[@class='textSmall error ng-binding']")).getText();
				if (testss.isEmpty()) 
				{
					System.out.println(singleinvitee + " : user already present into list " + "count is : " + afteraddingmeetinginviteecount);
				}
				
				if (testss.equalsIgnoreCase("Invalid email address")) 
				{
					System.out.println("you have enter the wrong email address : " + singleinvitee + "count is : " + afteraddingmeetinginviteecount);
					addinviteeicon.clear();
				}
				
				
			}
			else 
			{
				System.out.println(singleinvitee + " : user not added into list " );
			}
			
			
		}
	}
	
	public void clickOnDayOfWeek() 
	{
		dayoftheweekicon.click();
	}
	
	public boolean verifyRecordAddedOrNot(String meetingtitlenname) 
	{
    try 
	{	
		WebElement searchmeetingtitle = driver.findElement(By.xpath("//section[@class='tamplo-PageContent']/div[1]//a[contains(text(),'"+meetingtitlenname+"')]"));
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, searchmeetingtitle);
	     if (searchmeetingtitle.isDisplayed()) 
	     { 
	       return true;
	     } else 
	       {
             return false;
           }
		
	} catch (Exception e) 
	       {
			System.out.println("Meeting not added into list");
			return false;
			}
	}
	
	public void selectDayOfTheWeek(String weekdays) 
	{
		String[] weeknamesList = weekdays.split(",");	
		int i=1;
		for(String weekname : weeknamesList) 
		{
			while (i<=listofdayofweek.size()) 
			{
				WebElement dayofweekonebyonelocate = driver.findElement(By.xpath("//tamplo-select[@select-options='day as day.name  for day in meetingData.dayList']/div[1]/ul[1]/li[1]/div[1]/ul[1]/li["+i+"]"));
			    String gettextofweekoftheday =  dayofweekonebyonelocate.getText();
			    if (gettextofweekoftheday.equalsIgnoreCase(weekname)) 
			    {
			    	
			    	String getelementclassname = dayofweekonebyonelocate.getAttribute("class");
				   if (getelementclassname.equals("ng-scope")) 
				   {
					   dayofweekonebyonelocate.click();
					   dayoftheweekicon.click();
					  
					   break;
				   }else if (getelementclassname.equals("ng-scope selected")) 
				   {
					  
					  break;
				    }	
				}
			    i++;
			}
			i++;
			
		}
		endonicon.click();
		
	}
	
	public void SelectMeetingOccurenceType(String inputoccurancetype) 
	{
		
		try 
		{
			
		if (inputoccurancetype.equalsIgnoreCase("Once")) 
		{
			System.out.println("chiose meeting occurence type  already selected");
			//selectMeetingDataType(inputoccurancetype);
			
		}else 
		{
			WebElement occurenicon = driver.findElement(By.xpath("//tamplo-select[@ng-model='newMeeting.meetingSequence.occuranceType']/div[1]/ul[1]/li[1]/a"));
			WaitHelper waitHelper = new WaitHelper(driver);
			waitHelper.waitForVisibilityOfElmentWithPollingTime(20,05,occurenicon);
			occurenicon.click();
			int count =0;
			for (int i = 1; i <= 4; i++) 
			{
				WebElement occurencetype = driver.findElement(By.xpath("//tamplo-select[@ng-model='newMeeting.meetingSequence.occuranceType']/div[1]/ul[1]/li[1]/div/ul[1]/li["+i+"]/a"));
			    String getoccurencetypetext  = occurencetype.getText();
				
			    if (inputoccurancetype.equalsIgnoreCase(getoccurencetypetext)) 
				{
					
			    	occurencetype.click();
			    	
					if (getoccurencetypetext.equalsIgnoreCase("Once")) 
					{
						
						System.out.println("chiose meeting occurence type  already selected");
						count++;
						break;
					}else 
					{
						
						//selectMeetingDataType(inputoccurancetype); 
						count++;
						break;
						
					}
	
				}
			    
			    if (count!=0) 
			    {
					break;
				}
			    
			   
			}
		}
	
		} catch (Exception e) 
		{
			System.out.println("Meeting occurence type  not found or not able to select");
		}
	}
	
	public void ClickOnProjectIcon() 
	{
		
		Actions ob = new Actions(driver);
		ob.click(meetingtitlefield);
		Action action  = ob.build();
		action.perform();
		
		
	}
	
	public void enterMeetingTile(String title) 
	{
		meetingtitlefield.clear();
		meetingtitlefield.sendKeys(title);
	}
	
	public void clickOnStartDayIcon() 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, meetingstartdate);
		meetingstartdate.click();
	}
	
	public void clickOnOccurenceEndDateField() 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, occuranceenddateicon);
		occuranceenddateicon.click();
	}
	
	public void clickOnStartTimeIcon() 
	{
		WebElement meetingstartitme = driver.findElement(By.xpath("//section[@class='tamplo-modalNewContentSection']/div[1]/ul[1]/li[2]/span[1]/a"));
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, meetingstartitme);
		JavaScriptHelper javascripthelper = new JavaScriptHelper(driver);
		javascripthelper.clickOnElement(meetingstartitme);
		//meetingstartitme.click();
	}
	
	public void selectTime(int hours,int minuts) 
	{
		int  count =0;
		for (int i = 1; i <= 6; i++) 
		{
			for (int j = 1; j <= 4; j++) 
			{
				WebElement fulltime = driver.findElement(By.xpath("//div[@class='moment-picker-container day-view open']//tbody/tr["+i+"]/td["+j+"]"));
			    String getfulltimetext = fulltime.getText();
			    String getfirsttwonumber = getfulltimetext.substring(0, 2);
			    //System.out.println(getfirsttwonumber);
			    if (Integer.parseInt(getfirsttwonumber)==hours) 
			    {
			    	String getclassname = fulltime.getAttribute("class");
			    	if (getclassname.equals("ng-binding ng-scope")) 
			    	{
			    		
			    		try 
			        	{
			    			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
						     driver.findElement(By.xpath("//div[@class='moment-picker-container day-view open']//tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
						     selectMinutes(minuts);
						     count++;
						    break;
						    
			        	} catch (ElementNotVisibleException e) 
			        	{
			        		e.printStackTrace();
			        	}
			        }else 
			        if (getclassname.equals("ng-binding ng-scope disabled")) 
			        {
			            System.out.println("hours is over and it will select as current time");
			            meetingtitlefield.click();
			            count++;
			            break;
			        
					}else if (getclassname.equals("ng-binding ng-scope selected")) 
					{
						driver.findElement(By.xpath("//div[@class='moment-picker-container day-view open']//tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
						  selectMinutes(minuts);
						count++;
						break;
					}
			    	else 
			    	{
					     System.out.println("hours is over");	
					     break;
					}
				}
			}
			if (count!=0) {
				break;
			}
			
		}
	}
	
	private void selectMinutes(int minuts) 
	{
		int count1 =0;
		for (int i = 1; i <= 3; i++) 
		{
			for (int j = 1; j <= 4; j++) 
			{
				WebElement fullhours = driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'hour'\"]//tbody/tr["+i+"]/td["+j+"]"));
			    String getfullhourstext = fullhours.getText();
			    String getlasttwonumber = getfullhourstext.substring(getfullhourstext.length()-2);
			    //System.out.println(getlasttwonumber);
			    if (Integer.parseInt(getlasttwonumber)==minuts) 
			    {
			    	
					String getclassname = fullhours.getAttribute("class");
			    	if (getclassname.equals("ng-binding ng-scope")) 
			    	{
			    		
			    		try 
			        	{
			    			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
						     driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'hour'\"]//tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
						    count1++;
						    break;
						    
			        	} catch (ElementNotVisibleException e) 
			        	{
			        		e.printStackTrace();
			        	}
			        }else 
			        if (getclassname.equals("ng-binding ng-scope disabled")) 
			        {
			            System.out.println("time is disable and it will select as current time");
			            meetingtitlefield.click();
			            count1++;
			            break;
			        
					}else if (getclassname.equals("ng-binding ng-scope selected")) 
					{
						driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'hour'\"]//tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
						count1++;
						break;
					}
			    	else 
			    	{
					     //System.out.println("time not found");	
					     break;
					}
					
				}
			}
			if (count1!=0) 
			{
				break;
			}
		}
	}
	
	public void selectMeetingTypeAndData(String inputmeetingtype,String inputmeetingtypedata) throws InterruptedException 
	{
		try 
		{
			
	
		if (inputmeetingtype.equalsIgnoreCase("PROJECT MEETING")) 
		{
			System.out.println("chiose meeting type  already selected");
			selectMeetingDataType(inputmeetingtypedata);
			
		}else 
		{
			meetingtype.click();
			int count =0;
			for (int i = 1; i <= 4; i++) 
			{
				WebElement meetingtypelemnt = driver.findElement(By.xpath("//tamplo-select[@ng-model='newMeeting.meetingSequence.meetingType']/div/ul/li/div[1]/ul[1]/li["+i+"]/a"));
			    String getmeetingtypetext  = meetingtypelemnt.getText();
				
			    if (inputmeetingtype.equalsIgnoreCase(getmeetingtypetext)) 
				{
					
			    	meetingtypelemnt.click();
					if (getmeetingtypetext.equalsIgnoreCase("FREE MEETING")) 
					{
						
						//System.out.println("free meeting type does not have meeting type data");
						count++;
						break;
					}else 
					{
						Thread.sleep(500);
						selectMeetingDataType(inputmeetingtypedata); 
						count++;
						break;
						
					}
	
				}
			    
			    if (count!=0) 
			    {
					break;
				}
			    
			   
			}
		}
	
		} catch (Exception e) 
		{
			//System.out.println("Meeting type and Meeting type data not found or not able to select");
		}
		
	}
	
	private void selectMeetingDataType(String datatypeevent) throws InterruptedException 
	{
		String names = datatypeevent;
		String[] namesList = names.split(",");	
		for(String name : namesList)
		{
		  meetingtypedatainput.sendKeys(name);
		  Thread.sleep(700);
		  try 
		  {
			  firstmeetingdatatype.click();
		
		  } catch (Exception e) 
		  {
			checkProjectSelectedOrNot(name);
		}
		 
		}
	}
	
	public void checkProjectSelectedOrNot(String projectnamefromarray) 
	{
		if (listofselectedprojects.size() > 0) 
		{
			int sizeofallproject = listofselectedprojects.size();
			for (int i = 1; i <= sizeofallproject; i++) 
			{
				WebElement gettextfromproject = driver.findElement(By.xpath("//header[@class='tamplo-dialogHeader brbt']/ul[3]/li[1]/ul[1]/li["+i+"]"));
			    String projectname = gettextfromproject.getText();
			    if (projectname.equalsIgnoreCase(projectnamefromarray)) 
			    {
					System.out.println(projectnamefromarray + " : project all ready selected");
					meetingtitlefield.click();
					break;
				}else 
				{
					System.out.println(projectnamefromarray + " : this project not selected or project not display in list");
					
				}
			}
		}
	} 
	
	public void MeetingAddButton() 
	{
		try 
		{
			WebElement addmeetingbutton = driver.findElement(By.xpath("//button[@class='btn verysmall btn-primary ng-binding'][@ng-click='createNewMeetingData()'][contains(text(),'NEW MEETING')]"));
			WaitHelper waitHelper = new WaitHelper(driver);
			waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, addmeetingbutton);
			addmeetingbutton.click();
		} catch (Exception e) 
		{
			System.out.println("Add Meeting button not found");
		}
			
	}
	
	public boolean isMeetingPopUpPresent() 
	{
		try 
		{
			if (meetingpopup.isDisplayed()) 
			{
				return true;
			}	
		} catch (Exception e) 
		{
			return false;
			// TODO: handle exception
		}
		return false;
	}
}
