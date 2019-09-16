package com.ityss.tamplo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.wait.WaitHelper;

public class ActionPageObject 
{
	private WebDriver driver;
	
	public ActionPageObject(WebDriver driver) 
	{
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='projectActionsWidgetContainer']/div[1]/div[1]/div[3]/div[2]/button[1]")
	WebElement buttonaddopen;
	
	@FindBy(xpath="//div[@class='projectActionsWidgetContainer']/div[1]/div[2]/div[3]/div[2]/button[1]")
	WebElement buttonaddinprogress;
	
	@FindBy(xpath="//div[@class='projectActionsWidgetContainer']/div[1]/div[3]/div[3]/div[2]/button[1]")
	WebElement buttonaddvalidationpending;
	
	@FindBy(xpath="//input[@name='actionName']")
	WebElement textactiontitle;
	
	@FindBy(xpath="//button[@min-date='actionDueMinDate']")
	WebElement duedateicon;
	
	@FindBy(xpath="//tamplo-icon-select[@ng-model='actionListType.transientAction.priority']")
	WebElement importanceicon;
	
	@FindBy(xpath="//td[@class='ng-binding ng-scope selected']")
	WebElement selecttodaydate;
	
	@FindBy(xpath="//tamplo-icon-select[@ng-model='actionListType.transientAction.priority']/span[1]/div/ul[1]/li[1]/a")
	WebElement critical;
	
	@FindBy(xpath="//tamplo-icon-select[@ng-model='actionListType.transientAction.priority']/span[1]/div/ul[1]/li[2]/a")
	WebElement importance;
	
	@FindBy(xpath="//tamplo-icon-select[@ng-model='actionListType.transientAction.priority']/span[1]/div/ul[1]/li[3]/a")
	WebElement lessimportance;
	
	@FindBy(xpath="//tamplo-icon-select[@ng-model='actionListType.transientAction.assignee']/span[1]")
	WebElement assigneeicon;
	
	@FindBy(xpath="//tamplo-icon-select[@ng-model='actionListType.transientAction.project']/span[1]")
	WebElement projecticon;
	
	@FindBy(xpath="//div/button[1][@ng-if='actionListType.showAddForm']")
	WebElement savebutton;
	
	@FindBy(xpath="//div/button[2][@ng-if='actionListType.showAddForm']")
	WebElement  cancelbutton;
	
	public void selectActionAssginee(String assigneename) throws InterruptedException 
	{
		Thread.sleep(500);
		WebElement selectassignee =  driver.findElement(By.xpath("//tamplo-icon-select[@ng-model='actionListType.transientAction.assignee']/span[1]/div[1]/ul/li/a[@title='"+assigneename+"']"));
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(30, 05, selectassignee);
		
		selectassignee.click();
    	
	
	}
	
	public void selectProject(String projectname) throws InterruptedException 
	{
		Thread.sleep(500);
		 WebElement selectproject = driver.findElement(By.xpath("//tamplo-icon-select[@ng-model='actionListType.transientAction.project']/span[1]/div[1]/ul/li/a[@title='"+projectname+"']"));
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(30, 05, selectproject);
		selectproject.click();
    	
	
	}
	
	public void OpenAddActionBtn() throws InterruptedException 
	{
		Thread.sleep(500);
		WebElement addbutton = driver.findElement(By.xpath("//div[@class='projectActionsWidgetContainer']/div[1]/div[1]/div[3]/div[2]/button[1]"));
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 10, addbutton);
		addbutton.click();
	}
	
	public void inProgressAddActionBtn() 
	{
		buttonaddinprogress.click();
	}
	
	public void validationPendingAddActionBtn() 
	{
		buttonaddvalidationpending.click();
	}
	
	public void enterActionTitle(String title) 
	{
		textactiontitle.sendKeys(title);
	}
	
	public void clickOnDueDateIcon() 
	{
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, duedateicon);
	  duedateicon.click();	
	  	
	}
	
	public void ClickOnImportanceIcon() 
	{
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, importanceicon);
		importanceicon.click(); 	
	}
	
	public void selectCriticalImportance() 
	{
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, critical);
		critical.click();
	}
	
	public void selectImportant() 
	{
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, importance);
		importance.click();
	}
	
	public void selectLessImportant() 
	{
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, lessimportance);
		lessimportance.click();
	}
	
	public void saveActionButton() 
	{
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 10, savebutton);
	    savebutton.click();	
	}
	
}
