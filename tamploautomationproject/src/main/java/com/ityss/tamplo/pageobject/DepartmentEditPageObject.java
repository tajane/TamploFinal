package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.javascript.JavaScriptHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;

public class DepartmentEditPageObject 
{

	private WebDriver driver;

	@FindBy(xpath=".//div[1]/ul[1]/li/a[contains(text(),'EDIT')]")
	By editbuttonofdprt;
	
	@FindBy(xpath="//h5[@class='dialogHeadDesc ng-binding']")
	WebElement outsideclickforeditdepartmentname;
	
	@FindBy(xpath="//li[@class='iconlistItem']//a[@class='iconlistItemLink']")
	WebElement outsideelementofdprttitle ;
	
	@FindBy(xpath="//div[@class='swal-footer']/div/button[contains(text(),'CANCEL')]")
	WebElement cancelbuttonofswal;
	
	@FindBy(xpath="//div[@class='swal-footer']/div/button[contains(text(),'CONFIRM')]")
	WebElement confirmbuttonofswal;
	
	@FindBy(xpath="//div[@class='swal-footer']/div/button[contains(text(),'OK')]")
	WebElement okbuttonofswal;
	
	private String editbuttonofdprtlink =".//div[1]/ul[1]/li/a[contains(text(),'EDIT')]";
	private String deletebuttonofdprtlink =".//div[1]/ul[1]/li/a[contains(text(),'REMOVE')]";
	private String deactivatebuttonofdprtlink =".//div[1]/ul[1]/li/a[contains(text(),'DEACTIVATE')]";
	
	
	public DepartmentEditPageObject(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void outSideClickAfterEnterDptName() 
	{
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(outsideclickforeditdepartmentname);
		
	}
	private String threeDotObject(String departmentname) 
	{
		String threedotattribute = "//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/header/div[1]/span[1]";
	    return threedotattribute;
	}
	
	public void clickOnCancelBtnofSwal() 
	{
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(cancelbuttonofswal);
	}
	
	public void clickOnConfirmBtnofSwal() 
	{
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(confirmbuttonofswal);
	}
	
	public void clickOnOkBtnofSwal() 
	{
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(okbuttonofswal);
	}
	
	public boolean isThreeDisplayDotOrNot(String departmentname) 
	{
		List<WebElement> threedotlocation = driver.findElements(By.xpath(threeDotObject(departmentname)));
		int countofthree = threedotlocation.size();
		if (countofthree > 0 && countofthree < 2) 
		{
			return true;
		} else 
		{
			return false;
		}
		
	}
	
	public void clickOnEditButtonOfDepartment(String departmentname) 
	{
		WebElement locatethreebutton = driver.findElement(By.xpath(threeDotObject(departmentname)));
		WebElement clckon= locatethreebutton.findElement(By.xpath(editbuttonofdprtlink));   // this called as element chaining 
		System.out.println(clckon);
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(clckon);
		
	}
	
	public void clickOnRemoveButtonOfDepartment(String departmentname) 
	{
		WebElement locatethreebutton = driver.findElement(By.xpath(threeDotObject(departmentname)));
		WebElement clckon= locatethreebutton.findElement(By.xpath(deletebuttonofdprtlink));   // this called as element chaining 
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(clckon);
	}
	
	
	public void clickOnDeactivateButtonOfDepartment(String departmentname) 
	{
		WebElement locatethreebutton = driver.findElement(By.xpath(threeDotObject(departmentname)));
		WebElement clckon= locatethreebutton.findElement(By.xpath(deactivatebuttonofdprtlink));   // this called as element chaining 
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(clckon);
	}
	
   public void clickOnThreeDotOfDepartment(String departmentname) 
   {
	WebElement threedotlocation = driver.findElement(By.xpath(threeDotObject(departmentname)));
	WaitHelper waitforelement = new WaitHelper(driver);
	waitforelement.waitUntilElmentToBeClickable(20, 05, threedotlocation);
	threedotlocation.click();
   }
	
   public void clearDprtName(String departmentname) 
   {
	   int length = departmentname.length();
		
		WebElement textareaelement = driver.findElement(By.xpath("//textarea[@ng-model='uiData.transientDepartment.name']"));
		
		for (int i = 1; i <=length; i++) 
		{
			textareaelement.sendKeys(Keys.BACK_SPACE);
		}
   }
   
   public void clickOutsideToSaveDprt() 
   {
	   outsideelementofdprttitle.click();
    }
   
   private String departmentUserSectionElement(String usernametoremove) 
   {
	 String locateusersection = "//section[@class='projectInfoContentSection']//div[@class='userGroupBlock userAdmin deptUserBlock ng-scope']//ul/li/div[@class='titleblock']/span[contains(text(),'"+usernametoremove+"')]";
	 return locateusersection;
   }
   public boolean checkUserPresentOrNot(String usernametoremove) 
   {
	 List<WebElement> userlocate = driver.findElements(By.xpath(departmentUserSectionElement(usernametoremove)));
	   int sizeofusers = userlocate.size();
       if (sizeofusers > 0 && sizeofusers < 2) 
       {
		  return true;
	   } else 
	   {
          return false;
	   }
   }
   
   private WebElement UserSectionDiv(String usernametoremove) 
   {
	
	
	   WebElement userfulldiv = driver.findElement(By.xpath(departmentUserSectionElement(usernametoremove)));
	   return userfulldiv;
   }
   
   public boolean checkUserEditButtonPresentOrNot(String usernametoremove) 
   {
	   List<WebElement> usereditbutton = UserSectionDiv(usernametoremove).findElements(By.xpath("./ancestor::li//div/button[@title='EDIT']"));
	   int sizeofeditbutton = usereditbutton.size();
	   
	   if (sizeofeditbutton > 0 && sizeofeditbutton < 2) 
       {
		  return true;
	   } else 
	   {
          return false;
	   }
   }
   
   public boolean clickOnEditButtonOfUser(String usernametoremove) 
   {
	   if (checkUserEditButtonPresentOrNot(usernametoremove)) 
	   {
		   WebElement usereditbutton = UserSectionDiv(usernametoremove).findElement(By.xpath("./ancestor::li//div/button[@title='EDIT']"));
		   JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		   javaScriptHelper.clickOnElement(usereditbutton);
		   return true;
	    } else 
	    {
             System.out.println("edit button not present");
             return false;
	    }
	  
   }
   
   public void clickOnThreeDotOfuser(String usernametoremove) 
   {
	   WebElement threedotelement = UserSectionDiv(usernametoremove).findElement(By.xpath("./ancestor::li//div[@class='actiontool ng-scope']/span[@ng-if='departmentUserListType.writePermission']"));
	   JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
	   javaScriptHelper.clickOnElement(threedotelement);

   }
   public void clickOnRemoveBtnOfUser(String usernametoremove) 
   {
	   WebElement userremovebtn = UserSectionDiv(usernametoremove).findElement(By.xpath("./ancestor::li//div[@class='actiontool ng-scope']/span[@ng-if='departmentUserListType.writePermission']/div/ul/li/a[contains(text(),'REMOVE')]"));
	   JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
	   javaScriptHelper.clickOnElement(userremovebtn);

   }
   
}
