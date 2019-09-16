package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//a[@class='dropbtn option ng-binding ng-scope'][contains(text(),'SELECT DEPARTMENT')]")
	WebElement selectdepartmentdropdownbox;
	
	@FindBy(xpath="//a[contains(text(),'DEPARTMENTS')]/ancestor::li[1]/following-sibling::li[@ng-if='loggedInData.company.status <= 20']/ul[1]/li")
	List<WebElement> howmanydepartment;
	
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
		WebElement exactprojectlocation = projectGridContainerElement().findElement(By.xpath("./header/h3/a[contains(text(),'"+enterprojectname+"')]"));
		exactprojectlocation.click();
		return true;
		
		}else 
		{
			return false;
		}
	}
	
	public void addProjectButton() 
	{
		addnewprojectbutton.click();
	}
	
	public void enterProjectName(String departmentname, String inputprojecttitle) 
	{
		if (howmanydepartment.size()>1) 
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
			    	
			    	WaitHelper waitHelper = new WaitHelper(driver);
			    	waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, projectnametextfield);
			    	projectnametextfield.sendKeys(inputprojecttitle);
			    	break;
				}
			}
		}else 
		{
			projectnametextfield.sendKeys(inputprojecttitle);
		}
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

    public void addProjectMemberBtn() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
	    waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, addprojectmemberbutton);
    	addprojectmemberbutton.click();
    }
    
    public void addProjectManagerBtn() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
	    waitHelper.waitForVisibilityOfElmentWithPollingTime(20, 05, addprojectmanagerbutton);
   	   addprojectmanagerbutton.click();
    }
    
    public void enterUserAndAddUser(String useremail) throws InterruptedException 
    {
    	try 
    	{
    		
    		enterprojectuseremailattopside.sendKeys(useremail);
    		selectUserAndSaveItTopPosition();
		} catch (Exception e) 
    	{
			
			enterprojectuseremailatbottomside.sendKeys(useremail);
			selectUserAndSaveItBottomPosition();
		}
	
    }
    
    public void selectUserAndSaveItTopPosition() 
    {
    	try 
    	{
    	selectfirstuserfromtopside.click();
    	savebuttontopposition.click();
    	} catch (Exception e) 
    	{
    		savebuttontopposition.click();
    		WebElement swaltitleusercheck = driver.findElement(By.xpath("//div[@class='swal-title']"));
			String getswaltitle = swaltitleusercheck.getText();
			if (getswaltitle.contentEquals("Already added to this project.")) 
			{
				System.out.println("user already added");
			    clickOnOKbtn();	 
			}
    	}
	}
    
    public void selectUserAndSaveItBottomPosition() throws InterruptedException 
    {	
    	try 
    	{
    		selectfirstuserfrombottomside.click();
        	savebuttonbottomposition.click();
      	
		} catch (Exception e) 
    	{
			savebuttonbottomposition.click();
			WebElement swaltitleusercheck = driver.findElement(By.xpath("//div[@class='swal-title']"));
			String getswaltitle = swaltitleusercheck.getText();
			if (getswaltitle.contentEquals("Already added to this project.")) 
			{
				System.out.println("user already added");
			    clickOnOKbtn();	 
			}
		}
    	
	}
}
