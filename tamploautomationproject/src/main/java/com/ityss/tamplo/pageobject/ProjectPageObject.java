package com.ityss.tamplo.pageobject;

import java.util.List;

import org.apache.regexp.recompile;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class ProjectPageObject 
{
    private WebDriver driver;
	public ProjectPageObject(WebDriver driver) 
	{
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding'][contains(text(),'NEW PROJECT')]")
	WebElement addnewprojectbutton;
	
	@FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding'][contains(text(),'NEW PROJECT')]")
	List<WebElement> listofaddnewprojectbutton;
	
	@FindBy(xpath="//a[@class='dropbtn option ng-binding ng-scope'][contains(text(),'SELECT DEPARTMENT')]")
	WebElement selectdepartmentdropdownbox;
	
	@FindBy(xpath="//a[contains(text(),'DEPARTMENTS')]/ancestor::li[1]/following-sibling::li[@ng-if='loggedInData.company.status <= 20']/ul[1]/li")
	List<WebElement> howmanydepartment;
	
	@FindBy(xpath="//div[@class='projectsPageContainer zeroState']//button[@class='btn small btn-primary ng-binding']")
	WebElement addprojectzerostate;
	
	@FindBy(xpath="//div[@class='projectsPageContainer zeroState']//button[@class='btn small btn-primary ng-binding']")
	List<WebElement> listofaddprojectzerostate;
	
	// this will be use for create project if one project no need to click on drop down if multiple project then click on drop down
	
	@FindBy(xpath="//div[@class='projectInfoContentHeaderTitle']/div/input[@name='projectName']")
	WebElement projectnametextfield;
		
	@FindBy(xpath="//div[@class='zeroStateContainer']//img")
	WebElement outsideclicktosaveproject;
	
	@FindBy(xpath="//button[@class='swal-button swal-button--confirm'][contains(text(),'OK')]")
	WebElement okbutton;
	
	@FindBy(xpath="//button[@ng-click='toggleAddUserForm(projectUserListType,$index);'][contains(text(),'MEMBER')]")
	WebElement addprojectmemberbutton;
	
	@FindBy(xpath="//button[@ng-click='toggleAddUserForm(projectUserListType,$index);'][contains(text(),'MANAGER')]")
	WebElement addprojectmanagerbutton;
	
	@FindBy(xpath="//a[@class='dropbtn option ng-binding ng-scope'][contains(text(),'SELECT DEPARTMENT')]/following-sibling::div[1]/ul[1]/li")
	List<WebElement> listofdepartment;
	
	@FindBy(xpath="//li[@id='select-department-user-0']//input[@id='userQuery'][@type='text']")
	WebElement enterprojectuseremailattopside;
	
	@FindBy(xpath="//li[@id='select-department-user-1']//input[@id='userQuery'][@type='text']")
	WebElement enterprojectuseremailatbottomside;
	
	@FindBy(xpath="//li[@id='select-department-user-0']//ul[@class='employeeList']/li[1]")
	WebElement selectfirstuserfromtopside;
	
	@FindBy(xpath="//li[@id='select-department-user-1']//ul[@class='employeeList']/li[1]")
	WebElement selectfirstuserfrombottomside;
	
	@FindBy(xpath="//li[@id='select-department-user-0']/ancestor::ul[2]/ancestor::div[1]/div[1]//button[contains(text(),'Save')]")
	WebElement savebuttontopposition;
	
	@FindBy(xpath="//li[@id='select-department-user-1']/ancestor::ul[2]/ancestor::div[1]/div[1]//button[contains(text(),'Save')]")
	WebElement savebuttonbottomposition;
	
	@FindBy(xpath="//ul[@class='navbarList']//a[contains(text(),'PROJECTS')]/ancestor::li[1]/following-sibling::li[1]/ul/li/label[1]/a")
	List<WebElement> listofprojectleftpanel;
	
	@FindBy(xpath="//div[@class='projectsPageContainer']//div[@class='item createdItem tamplo-panelWrapper ng-scope']")
	List<WebElement> listofprojectincontainer;
	
	@FindBy(xpath="//button[@class='btn verysmall btn-primary ng-binding'][contains(text(),'Save')]")
	WebElement  savebuttonofuser;
	
	@FindBy(xpath="//button[@class='btn verysmall btn-link ng-binding'][contains(text(),'Cancel')]")
	WebElement  cancelbuttonofuser;
	
	@FindBy(xpath="//div[@class='swal-footer']/div/button[contains(text(),'OK')]")
	WebElement okbuttonofswal;
	
	@FindBy(xpath="//div[@class='projectInfoContentHeaderDepartment']/span/label[1][@class='navbarItemLabel ng-binding']")
	WebElement onlyonedprtpresentelement;
	
	public void clickOnAddProjectZeroState() 
	{
		JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
		scripthelper.clickOnElement(addprojectzerostate);
		
	}
	
	public void clickOnOkBtnofSwal() 
	{
		WaitHelper waitforelement = new WaitHelper(driver);
		waitforelement.waitForVisibilityOfElmentWithPollingTime(20, 05, okbuttonofswal);
		
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(okbuttonofswal);
	}
	
	public void clickOnSaveButtonOfUserForm() {
		JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
		scripthelper.clickOnElement(savebuttonofuser);
	}
	
    public void clickOnCancelButtonOfUserForm() {
    	JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
		scripthelper.clickOnElement(cancelbuttonofuser);
	}

	public boolean clickOnInputProjectInLeftSide(String inputprojectname,ExtentTest test) 
	{
		int  count=0;
		try 
		{
			
		int projectsize = listofprojectleftpanel.size();
		for (int i = 1; i <= projectsize; i++) 
		{
			WebElement getprojectname = driver.findElement(By.xpath("//ul[@class='navbarList']//a[contains(text(),'PROJECTS')]/ancestor::li[1]/following-sibling::li[1]/ul/li["+i+"]/label[1]/a"));
			String actualprojectname = getprojectname.getText();
			if (inputprojectname.equalsIgnoreCase(actualprojectname)) 
			{
				JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
				scripthelper.clickOnElement(getprojectname);
				//getprojectname.click();
				
				if (count !=0) 
				{
					break;
				}
				return true;
			}else 
			{
				continue;
			}
		}
		
		} catch (Exception e) 
		{
			e.printStackTrace();
			test.log(Status.PASS, MarkupHelper.createLabel(inputprojectname + " : this Project not present", ExtentColor.RED));
			return false;
		}
		return false;
	}
	
	private WebElement projectGridContainerElement() 
	{
		
			String projectgridcontainerpath = "//div[@class='projectsPageContainer']//div[@class='item createdItem tamplo-panelWrapper ng-scope']";	
			WebElement  projectcontainerelement =  driver.findElement(By.xpath(projectgridcontainerpath));
			return  projectcontainerelement;
		
	
	}
	
	
	
	public boolean selectProjectInGridView(String enterprojectname) 
	{
		if (listofprojectincontainer.size() > 0) 
		{
			try 
			{
				WebElement exactprojectlocation = projectGridContainerElement().findElement(By.xpath("./header/h3/a[contains(text(),'"+enterprojectname+"')]"));
				exactprojectlocation.click();
				return true;
			} catch (NoSuchElementException e) 
			{
				return false;
			}
		
		
		}else 
		{
			return false;
		}
	}
	
	public void addProjectButton() 
	{
		JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
		javaScriptHelper.clickOnElement(addnewprojectbutton);
		
	}
	
	public boolean checkAddProjectPermission() 
	{
				
		if (listofaddnewprojectbutton.size() > 0 && listofaddnewprojectbutton.size() < 2) 
		{
			addProjectButton();
			return true;
			
		} else if (listofaddprojectzerostate.size() > 0  && listofaddprojectzerostate.size() < 2) 
		{	
			clickOnAddProjectZeroState();
			return true;		
		}else 
		{
			return false;	
		} 	
	}
	
	public boolean selectDepartmentFromList(String departmentname) 
	{
		
		int dprtcount = howmanydepartment.size();
		if (dprtcount>1) 
		{
			
			selectdepartmentdropdownbox.click();
			int countofdepartment = listofdepartment.size();
			for (int i = 1; i <= countofdepartment; i++) 
			{
				
				WebElement eachdepartmentname =  driver.findElement(By.xpath("//a[@class='dropbtn option ng-binding ng-scope'][contains(text(),'SELECT DEPARTMENT')]/following-sibling::div[1]/ul[1]/li["+i+"]"));
			    String getdepartmentname =  eachdepartmentname.getText();
			    if (getdepartmentname.equalsIgnoreCase(departmentname)) 
			    {
			    	eachdepartmentname.click();
			    	return true;
			    		
				}else 
				{
					if (i>=countofdepartment) 
					{
						break;
					}else 
					{
						continue;
					}
					
				}
			   
			    
			}
			
		} else if(dprtcount==1)
		{
		
		    String getdepartmentname =  onlyonedprtpresentelement.getText();
		    if (getdepartmentname.equalsIgnoreCase(departmentname)) 
		    {
		    	return true;
		    		
			}else 
			{
				return false;
			}
			
		}else 
		{
			System.out.println("some other error ");
			return false;
		}
	return false;	
	}
	
	public void enterProjectName(String inputprojecttitle) 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, projectnametextfield);
    	projectnametextfield.sendKeys(inputprojecttitle);
	}

	 public boolean checkNewProjectOrDuplicateProject() 
	    {
	    	WebElement swaltitle = driver.findElement(By.xpath("//div[@class='swal-title']"));
	    	WaitHelper waitHelper = new WaitHelper(driver);
	    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, swaltitle);
	    	String gettextswaltitle = swaltitle.getText();
	    	
	    	if (gettextswaltitle.equalsIgnoreCase("Project created successfully!")) 
	    	{
	    		clickOnOKbtn();
				return  true;
			}else if (gettextswaltitle.equalsIgnoreCase("Duplicate project name.")) 
			{
				clickOnOKbtn();
				return false;
			}else 
			{
				clickOnOKbtn();
				return false;
			}
		}
	 
    public void clickOnOutSideToSaveProject() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
	    waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, outsideclicktosaveproject);
        outsideclicktosaveproject.click();	
    }

    public void clickOnOKbtn() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
	    waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, okbutton);
	    okbutton.click();
    }

    private String userRoleTitleElement(String rolename) 
    {
    	String inputrolename = rolename.toUpperCase();
		String elementofuserole ="//section[@class='projectInfoContentSection ng-scope']//div[@class='userSection ng-scope']//h5[@id='"+inputrolename+"']";
	    return elementofuserole;
    }
    
    public boolean checkUserRolePresentOrNot(String rolename) 
    {
	 List<WebElement> isroleprentelement = driver.findElements(By.xpath(userRoleTitleElement(rolename)));	
	  if (isroleprentelement.size() > 0 && isroleprentelement.size() < 2 ) 
	  {
		return true;
	  } else 
	  {
		return false;
	  }
	}
    
    public boolean checkAddUserPresentOrNot(String rolename) 
    {
    	WebElement usersectionelement = driver.findElement(By.xpath(userRoleTitleElement(rolename)));
  	  List<WebElement> addbtnelement = usersectionelement.findElements(By.xpath("./ancestor::div[2]//button[text()='ADD "+rolename+"']"));
  	  int sizeofelement = addbtnelement.size();
  	  if (sizeofelement > 0 && sizeofelement < 2 ) 
	  {
		return true;
	  } else 
	  {
		return false;
	  }
	}
    
    public void clickOnAddButtonOfUserRole(String rolename) 
    {
	  WebElement usersectionelement = driver.findElement(By.xpath(userRoleTitleElement(rolename)));
	  WebElement addbtnelement = usersectionelement.findElement(By.xpath("./ancestor::div[2]//button[text()='ADD "+rolename+"']"));
	  JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
	  javaScriptHelper.clickOnElement(addbtnelement);
    }
    
 public void enterUserEmailId(String rolename,String useremailid) 
 {
	 WebElement usersectionelement = driver.findElement(By.xpath(userRoleTitleElement(rolename)));
	 WebElement textfieldelement = usersectionelement.findElement(By.xpath("./ancestor::div[2]/div[1]//div[@class='projectActionsWidgetContent projectInfoContent ng-scope']//input[@type='text']"));
	 textfieldelement.sendKeys(useremailid);
 }
   
 public boolean checkThatUserPresentInListOrNot(String rolename,String useremailid) 
 {
	 WebElement usersectionelement = driver.findElement(By.xpath(userRoleTitleElement(rolename)));
	 List<WebElement> textfieldelement = usersectionelement.findElements(By.xpath("./ancestor::div[2]/div[1]//ul[@class='employeeList']/li/span[2]/span[contains(text(),'"+useremailid+"')]"));
	 if (textfieldelement.size() > 0 && textfieldelement.size() < 2) 
	   {
		return true;
		
	  } else 
	  {
		  return false;
	  }
  }
   
 public void selectChoiceUserFromList(String rolename,String useremailid) 
 {
	 WebElement usersectionelement = driver.findElement(By.xpath(userRoleTitleElement(rolename)));
	 WebElement textfieldelement = usersectionelement.findElement(By.xpath("./ancestor::div[2]/div[1]//ul[@class='employeeList']/li/span[2]/span[contains(text(),'"+useremailid+"')]"));
 
	 JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
	  javaScriptHelper.clickOnElement(textfieldelement);
 }
}
