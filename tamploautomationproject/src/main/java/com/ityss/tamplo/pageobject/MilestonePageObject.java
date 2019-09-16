package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.helper.javascript.JavaScriptHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;
import com.ityss.tamplo.utils.SelectDueDateForAction;

public class MilestonePageObject 
{

	private WebDriver driver;

	public MilestonePageObject(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//header[@class='projectInfoContentHeader ng-scope']//span[contains(text(),'MILESTONES')]")
	List<WebElement> milestonelabelinheader;
	
	@FindBy(xpath="//div[@class='swal-modal']")
	List<WebElement> milestonesuccesspopup;
	
	@FindBy(xpath="//div[@class='projectActionsWidgetHeader']/h2[contains(text(),'ALL MILESTONE')]")
	List<WebElement> milestoneheaderforcount;
	
	@FindBy(xpath="//div[@class='projectActionsWidgetHeader']/h2[contains(text(),'ALL MILESTONE')]")
	WebElement milestonecounttitle;
	
	@FindBy(xpath="//div[@class='swal-title']")
	WebElement milestonesuccesspopuptitle;
	
	@FindBy(xpath="//button[@class='swal-button swal-button--confirm'][contains(text(),'OK')]")
	WebElement okbutton;
	
	@FindBy(xpath="//header[@class='projectInfoContentHeader ng-scope']//span[contains(text(),'MILESTONES')]")
	WebElement milestonelabelinheaderforclick;
	
	@FindBy(xpath="//div[@class='tamplo-footerbar']//div[@class='footerActionBarItemsRight']//button[contains(text(),'ADD MILESTONE')]")
	List<WebElement> addnewmilestonelabel;
	
	@FindBy(xpath="//div[@class='tamplo-footerbar']//div[@class='footerActionBarItemsRight']//button[contains(text(),'ADD MILESTONE')]")
	WebElement addmilestonebutton;
	
	@FindBy(xpath="//div[@class='tamplo-dialogLayout dialogAddMemberFollower ng-scope']")
	List<WebElement> addmilestonepopup;
	
	@FindBy(xpath="//button[@class='icon icon-ic-remove'][@ng-click='closeMileStoneForm()']")
	WebElement closedmilestonepopupbutton;
	
	@FindBy(xpath="//div[@class='userSection']//h5[@class='titleIcon userListTitle projectInfoContentHeaderTitle']/input[@type='text']")
	WebElement milestonetitlefield;
	
	@FindBy(xpath="//span[@class='moment-picker right']//span[contains(text(),'Add start date')]")
	WebElement startdateicon;
	
	@FindBy(xpath="//a[@min-date='minEndDate']//span[contains(text(),'Add end date')]")
	WebElement enddateicon;
	
	@FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding ng-scope'][contains(text(),'SAVE')]")
	WebElement savebutton;
	
	
	public void enterMileStoneTitle(String inputmilestonetitle) 
	{
		milestonetitlefield.sendKeys(inputmilestonetitle);
	}
	public boolean checkIfMileStoneLabelPresentOrNot() 
	{
		 if (milestonelabelinheader.size() > 0) 
		  {
			return true;	
		  } 

		  return false;
	}

	public boolean VerifySuccessPopupDisplayOrNot(ExtentTest test) 
	{
		if (milestonesuccesspopup.size() > 0) 
		{
			String getpopuptitle = milestonesuccesspopuptitle.getText();
			if (getpopuptitle.contains("Milestone created successfully!")) 
			{
				JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
				scriptHelper.clickOnElement(okbutton);	
				test.log(Status.PASS, MarkupHelper.createLabel("Milestone save successfully", ExtentColor.GREEN));
				   return  true;
			} else if (getpopuptitle.contains("This milestone name is already used")) 
			{
				JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
				scriptHelper.clickOnElement(okbutton);
				scriptHelper.clickOnElement(closedmilestonepopupbutton);
				test.log(Status.PASS, MarkupHelper.createLabel("This milestone name is already used.", ExtentColor.RED));
				return false;
			} else 
			{
				test.log(Status.PASS, MarkupHelper.createLabel("Milestone not save", ExtentColor.RED));
				driver.navigate().refresh();
	            return false;
			}
			
		} else 
		{
			test.log(Status.PASS, MarkupHelper.createLabel("Milestone popup not display", ExtentColor.RED));
			driver.navigate().refresh();
            return false;
		}
		
	}
	
	
	public void clickOnClosedButtonAddMilestoneform() 
	{
		JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
		   scriptHelper.clickOnElement(closedmilestonepopupbutton);	
	}
	public void clickOnMileStoneIcon() {
		 JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
		   scriptHelper.clickOnElement(milestonelabelinheaderforclick);
		
	}

	public boolean checkIfAddMileStoneButtonPresentOrNot() 
	{
		if (addnewmilestonelabel.size() > 0) 
		  {
			return true;	
		  } 

		  return false;
	}
	
	public void clickOnAddMilestoneBtn() 
	{
		JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
		   scriptHelper.clickOnElement(addmilestonebutton);
	}
	
	public boolean isAddMilestonePopUpDisplay() 
	{
		 if (addmilestonepopup.size() > 0) 
		   {
		         return true;
		   }
		   return  false;
	}
	
	public boolean enterMilestoneTitle(String inputmilestonetitle,ExtentTest test)  
	{
		
		if (isAddMilestonePopUpDisplay()) 
		{
			if (inputmilestonetitle.isEmpty()) 
			   {
				   test.log(Status.PASS, MarkupHelper.createLabel("milestone Title empty", ExtentColor.RED));
				   clickOnClosedButtonAddMilestoneform();
				   return false;
				   
			   }else 
			   {
				   enterMileStoneTitle(inputmilestonetitle);
				   test.log(Status.PASS, MarkupHelper.createLabel("milestone Title enter successfully", ExtentColor.GREEN));
				   return true;
			   }
			
		} else 
		{
			 test.log(Status.PASS, MarkupHelper.createLabel("milestone popup not open", ExtentColor.RED));
			 return false;
		}
	}

	public void clickOnMilestoneStartDateIcon() 
	{
		JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
		scriptHelper.clickOnElement(startdateicon);
		
	}
	
	public void clickOnMilestoneEndDateIcon() 
	{
		JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
		   scriptHelper.clickOnElement(enddateicon);
		   
	}
	
	public void clickOnSaveBtnOfMilestone() 
	{
		JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
		   scriptHelper.clickOnElement(savebutton);
	}
	
	public int getMilestoneCount() 
	   {
		   if (milestoneheaderforcount.size() > 0) 
		   {
			   WaitHelper waitHelper = new WaitHelper(driver);
		   	   waitHelper.waitUntilElmentToBeClickable(20, 05, milestonecounttitle);
				String getuserstorytext =  milestonecounttitle.getText();
				String getlastthreecharacter = getuserstorytext.substring(getuserstorytext.length()-3);
				String afterreplaceallcharacter = getlastthreecharacter.replaceAll("[^\\d.]", "");
				return Integer.parseInt(afterreplaceallcharacter.trim());
		  } else 
		  {
			return 0;
		  }
	   	   
		}
}
