package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.wait.WaitHelper;

public class DepartmentPageObject 
{
	private WebDriver  driver;
	
	public DepartmentPageObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding'][contains(text(),'NEW DEPARTMENT')]")
	WebElement adddepartmentbutton;
	
	@FindBy(xpath="//textarea[@ng-model='uiData.transientDepartment.name']")
	WebElement departmentnametextbox;
	
	@FindBy(xpath="//div[@class='tamplo-projectInfoContainer']/header/div/h4[@class='ng-binding']")
	WebElement outsidedivforsavename;
	
	@FindBy(xpath="//div[@class='swal-title'][contains(text(),'Department created successfully!')]")
	WebElement popupdepartmentaddsuccessfully;
	
	@FindBy(xpath="//button[@class='swal-button swal-button--confirm'][contains(text(),'OK')]")
	WebElement okbutton;
	
	@FindBy(xpath="//button[@ng-show='!departmentUserListType.showAddUserForm'][contains(text(),'MEMBER')]")
	WebElement adddepartmentmember;
	
	@FindBy(xpath="//button[@ng-show='!departmentUserListType.showAddUserForm'][contains(text(),'MANAGER')]")
	WebElement adddepartmentmanager;
	
	@FindBy(xpath="//li[@id='select-company-user-0']//input[@id='userQuery']")
	WebElement enterdepartmenttopposition;
	
	@FindBy(xpath="//li[@id='select-company-user-1']//input[@id='userQuery']")
	WebElement enterdepartmentbottomposition;
	
	@FindBy(xpath="//li[@id='select-company-user-1']//span[@class='employeeName ng-binding'][1]")
	WebElement selectfirstuserbottomposition;
	
	@FindBy(xpath="//li[@id='select-company-user-0']//span[@class='employeeName ng-binding'][1]")
	WebElement selectfirstusertopposition;
	
	@FindBy(xpath="//li[@id='select-company-user-0']/ancestor::ul[2]/ancestor::div[1]/div[1]//button[contains(text(),'Save')]")
	WebElement savebuttontopposition;
	
	@FindBy(xpath="//li[@id='select-company-user-1']/ancestor::ul[2]/ancestor::div[1]/div[1]//button[contains(text(),'Save')]")
	WebElement savebuttonbottomposition;
	
	@FindBy(xpath="//div[@class='swal-title'][contains(text(),'User added successfully!')]")
	WebElement popuptextuseraddedsuccfully;
	
	@FindBy(xpath="")
	WebElement departmentnamepresentornotingrid;
	
	public boolean isDepartmentPresentInGrid(String departmentname) 
	{
	List<WebElement> isdepartmentpresent = driver.findElements(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3[@class='itemTitle ng-scope']/a[contains(text(),'"+departmentname+"')]"));	
	int sizeofemement = isdepartmentpresent.size();
	  if (sizeofemement > 0) 
	   {
		return true;
	   }
	
	  return false;
	}
	
	
	public int listOfProjectsInDepartmentGridView(String departmentname) 
	{
		if (isDepartmentPresentInGrid(departmentname)) 
		{
			List<WebElement> listofprojects = driver.findElements(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/section/ul[1]/li[1]/span"));	
			 int sizeofprojects = listofprojects.size();
			 int additionalcount =0;
			 if (sizeofprojects > 2) 
			 {
				additionalcount = ifMoreProjectCountPresentOrNot(departmentname);
			
			 }
			 
			 return sizeofprojects + additionalcount;
		}
	 return 0;
	}
	
	public int ifMoreProjectCountPresentOrNot(String departmentname) 
	{
		List<WebElement> additionalprojectcountpresentornot = driver.findElements(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/section/ul[1]/li[1]/a/span"));
		int morecount =  additionalprojectcountpresentornot.size();
		if(morecount > 0) 
		{
		WebElement morecountelement = driver.findElement(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/section/ul[1]/li[1]/a/span"));	
		String gettext = morecountelement.getText();
		 String getactualprojectcount = gettext.replaceAll("[^0-9]", "").trim();
		 int projectcount = Integer.parseInt(getactualprojectcount);
		 return projectcount;
		}
	return 0;	
	}
	
	public int ifMoreServiceCountPresentOrNot(String departmentname) 
	{
		List<WebElement> additionalserviecountpresentornot = driver.findElements(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/section/ul[2]/li[1]/a/span"));
		int morecount =  additionalserviecountpresentornot.size();
		if(morecount > 0) 
		{
		WebElement morecountelement = driver.findElement(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/section/ul[2]/li[1]/a/span"));	
		String gettext = morecountelement.getText();
		 String getactualservicecount = gettext.replaceAll("[^0-9]", "").trim();
		 int servicecount = Integer.parseInt(getactualservicecount);
		 return servicecount;
		}
	return 0;	
	}
	
	public int listOfServiceInDepartmentGridView(String departmentname) 
	{
		if (isDepartmentPresentInGrid(departmentname)) 
		{
			List<WebElement> listofservice = driver.findElements(By.xpath("//section[@class='tamplo-PageContent']//div[@class='allItemWidget ng-scope']//h3/a[contains(text(),'"+departmentname+"')]/ancestor::div[@class='item createdItem tamplo-panelWrapper ng-scope']/section/ul[2]/li[1]/span"));	
			 int sizeofservice = listofservice.size();
			 int additionalcount =0;
			 if (sizeofservice > 2) 
			 {
				additionalcount = ifMoreServiceCountPresentOrNot(departmentname);
			
			 }
			 return sizeofservice + additionalcount;
		}
	 return 0;
	}
	
	public void clickOnAddDepartmentbtn() 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, adddepartmentbutton);
		adddepartmentbutton.click();
	}
	
    public void enterDepartmentNametxt(String departmerntmame) throws InterruptedException 
    {
    	
    	//departmentnametextbox.clear();
    	
		departmentnametextbox.sendKeys(departmerntmame);
	}
    
    public void clickOnOutsideToSave() 
    {
	  outsidedivforsavename.click();
    }
    
    public boolean verifyDepartmentCreate() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, popupdepartmentaddsuccessfully);
	  if (popupdepartmentaddsuccessfully.isDisplayed()) 
	  {
		 return true;
	  }else 
	  {
		return  false;
	  }
    }
    
    public boolean checkNewDeprtOrDuplicateDprt() 
    {
    	WebElement swaltitle = driver.findElement(By.xpath("//div[@class='swal-title']"));
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, swaltitle);
    	String gettextswaltitle = swaltitle.getText();
    	
    	if (gettextswaltitle.equalsIgnoreCase("Department created successfully!")) 
    	{
			clickOnOKBtn();
			return  true;
		}else if (gettextswaltitle.equalsIgnoreCase("Duplicate department name.")) 
		{
			clickOnOKBtn();
			return false;
		}else 
		{
			clickOnOKBtn();
			return false;
		}
	}
    public void clickOnOKBtn() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitUntilElmentToBeClickable(20, 05, okbutton);
	     okbutton.click();
    }
    
    public void clickAddButtonOfManager() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitUntilElmentToBeClickable(20, 05, adddepartmentmanager);
	   adddepartmentmanager.click();
    }

    public void clickAddButtonOfMember() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitUntilElmentToBeClickable(20, 05, adddepartmentmember);
	   adddepartmentmember.click();
    } 
    
    public void enterUserAndAddUser(String useremail) 
    {
    	try 
    	{
    		enterdepartmenttopposition.sendKeys(useremail);
    		selectUserAndSaveItTopPosition();
		} catch (Exception e) 
    	{
			enterdepartmentbottomposition.sendKeys(useremail);
			selectUserAndSaveItBottomPosition();
		}
	
    }
    
    public void selectUserAndSaveItTopPosition() 
    {
    	selectfirstusertopposition.click();
    	savebuttontopposition.click();
	}
    
    public void selectUserAndSaveItBottomPosition() 
    {
    	selectfirstuserbottomposition.click();
    	savebuttonbottomposition.click();
	}
    
    public boolean verifyUserAddedSuccuessfully() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, popuptextuseraddedsuccfully);
    	
    	if (popuptextuseraddedsuccfully.isDisplayed()) 
    	{
			return true;
		}  
    	
    	return false;
    	
	}
	
}
