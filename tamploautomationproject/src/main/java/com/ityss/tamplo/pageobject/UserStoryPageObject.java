package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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

public class UserStoryPageObject 
{

private WebDriver driver;

public UserStoryPageObject(WebDriver driver) 
  {
	this.driver = driver;
	PageFactory.initElements(driver, this);
  }

   @FindBy(xpath="//header[@class='projectInfoContentHeader ng-scope']//span[contains(text(),'USER STORIES')]")
   List<WebElement> userstorylabelinheader;
   
   @FindBy(xpath="//header[@class='projectInfoContentHeader ng-scope']//span[contains(text(),'USER STORIES')]")
   WebElement userstorylabelinheaderforclick;

   @FindBy(xpath="//div[@class='tamplo-footerbar ng-scope']//button[contains(text(),'ADD NEW STORY')]")
   List<WebElement> addnewuserstorylabel;
   
   @FindBy(xpath="//div[@class='tamplo-footerbar ng-scope']//button[contains(text(),'ADD NEW STORY')]")
   WebElement  adduserstorybutton;
   
   @FindBy(xpath="//section[@class='tamplo-dialogContentSection']")
   List<WebElement> adduserstorypopup;
   
   @FindBy(xpath="//header[@class='tamplo-dialogHeader']//button[@class='icon icon-ic-remove']")
   WebElement  closeduserstorybutton;
   
   @FindBy(xpath="//section[@class='tamplo-dialogContentSection']//input[@name='title'][@ng-model='story.name']")
    WebElement storytitletextfield;

   @FindBy(xpath="//section[@class='tamplo-dialogContentSection']/div[2]//div[@class='ta-scroll-window ng-scope ta-text ta-editor form-control']//div[@contenteditable='true']")
   WebElement userstorydescriptionfield;
   
   @FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding'][contains(text(),'SAVE')]")
   WebElement savebutton;
   
   @FindBy(xpath="//div[@ng-repeat='storyListType in uiData.storyListTypeList']//h2")
   List<WebElement> userstorycountlabel;
   
   @FindBy(xpath="//div[@ng-repeat='storyListType in uiData.storyListTypeList']//h2")
   WebElement getuserstorycount;
   
   @FindBy(xpath="//button[@class='swal-button swal-button--confirm'][contains(text(),'OK')]")
   WebElement okbutton;
   
   public void clickOnOkButton() 
   {
	   JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
	   scriptHelper.clickOnElement(okbutton);
	  
   }
   
   public void clickOnClosedOfUserStroryPopupButton() 
   {
	   JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
	   scriptHelper.clickOnElement(closeduserstorybutton);
	  
   }
   
   public void clickOnSaveButton() 
   {
	   JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
	   scriptHelper.clickOnElement(savebutton);
	   //savebutton.click();

   }
   
   public void enterAllDetailsAndSaveStory(String inputstorytitle,String inputdescription,ExtentTest test) throws InterruptedException 
   {
	   if (isAddUserStoryPopUpDisplay()) 
	   {
		   if (inputstorytitle.isEmpty()) 
		   {
			   test.log(Status.PASS, MarkupHelper.createLabel("story Title empty", ExtentColor.RED));
			   clickOnClosedOfUserStroryPopupButton();
		   }else 
		   {
		   enterStoryTitle(inputstorytitle);
		   test.log(Status.PASS, MarkupHelper.createLabel("story Title enter successfully", ExtentColor.GREEN));
		   
		  
		   if (inputdescription != null && !inputdescription.isEmpty()) 
		   {
			   enterStoryDescription(inputdescription);
			   test.log(Status.PASS, MarkupHelper.createLabel("story description enter successfully", ExtentColor.GREEN));
		   }
		   
		   clickOnSaveButton();
		    Thread.sleep(300);
		   WebElement elementofswaltitle = driver.findElement(By.xpath("//div[@class='swal-title']"));
		   String getswaltitle = elementofswaltitle.getText();
		  
		   if (getswaltitle.contains("User story create successfully!")) 
		   {
			   test.log(Status.PASS, MarkupHelper.createLabel("User story create successfully", ExtentColor.GREEN));
			   clickOnOkButton();
		   } else if(getswaltitle.contains("This story title is already used.")) 
		   {
			   test.log(Status.PASS, MarkupHelper.createLabel("This story title is already used", ExtentColor.RED));
			   clickOnOkButton();
			   clickOnClosedOfUserStroryPopupButton();
			   test.log(Status.PASS, MarkupHelper.createLabel("user story popup closed successfully", ExtentColor.GREEN));
			   
		   } else 
		   {try 
		   {
			   clickOnOkButton();
			   clickOnClosedOfUserStroryPopupButton();
			   test.log(Status.PASS, MarkupHelper.createLabel("user story popup closed successfully", ExtentColor.GREEN));
		       
		   } catch (Exception e) 
		   {
			e.printStackTrace();
		
		   }
			   
		   } 
		  
	   
		   }
	   
	   }
   }
   public static boolean isNullOrEmpty(String str) 
   {
       if(str != null && !str.isEmpty())
           return false;
       return true;
   }
   public void enterStoryDescription(String inputdescription) 
   {
	   userstorydescriptionfield.sendKeys(inputdescription);
   }
   public void enterStoryTitle(String inputstorytitle) 
   {
	   storytitletextfield.clear();
	   storytitletextfield.sendKeys(inputstorytitle);
   }
   
   
   public boolean isAddUserStoryPopUpDisplay() 
   {
	   if (adduserstorypopup.size() > 0) 
	   {
	         return true;
	   }
	   return  false;
   }
   public boolean checkIfUserStoryLabelPresentOrNot() 
   {
	  if (userstorylabelinheader.size() > 0) 
	  {
		return true;	
	  } 

	  return false;
	  	 
   }

   public void clickOnUserStoryIcon() 
   {
	   JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
	   scriptHelper.clickOnElement(userstorylabelinheaderforclick);
	   //userstorylabelinheaderforclick.click();

   }

   public boolean checkIfAddUserStoryButtonPresentOrNot() 
   {
	  if (addnewuserstorylabel.size() > 0) 
	  {
		return true;	
	  } 

	  return false;
	  	 
   }
   
   public void clickOnAddUserStoryButton() 
   {
	   JavaScriptHelper scriptHelper =  new JavaScriptHelper(driver);
	   scriptHelper.clickOnElement(adduserstorybutton);
	   //adduserstorybutton.click();

   }
   
   public int getUserStoryCount() 
   {
	   if (userstorycountlabel.size() > 0) 
	   {
		   WaitHelper waitHelper = new WaitHelper(driver);
	   	   waitHelper.waitUntilElmentToBeClickable(20, 05, getuserstorycount);
			String getuserstorytext =  getuserstorycount.getText();
			String getlastthreecharacter = getuserstorytext.substring(getuserstorytext.length()-3);
			String afterreplaceallcharacter = getlastthreecharacter.replaceAll("[^\\d.]", "");
			return Integer.parseInt(afterreplaceallcharacter.trim());
	  } else 
	  {
		return 0;
	  }
   	   
	}
}
