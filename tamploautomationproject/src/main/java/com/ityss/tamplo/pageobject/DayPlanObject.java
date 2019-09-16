package com.ityss.tamplo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.action.ActionHelper;
import com.ityss.tamplo.helper.javascript.JavaScriptHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;

public class DayPlanObject 
{
	private WebDriver driver;
	
	public DayPlanObject(WebDriver  driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='tabFooter']/div[@class='filterActions']/ul/li[1]")
	WebElement onlymyactionicon;
	
	@FindBy(xpath="//div[@id='tab-dueActions']//h2[@class='headingprojectPriority ng-binding'][contains(text(),'OPEN')]")
	WebElement openactionlabel;
	
	@FindBy(xpath="//div[@class='ng-scope']//div[1]//div[2]//ul[1]//li[1]//div[2]/button")
	WebElement  firstactionaddbuttonduefilter;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']//h2[@class='headingprojectPriority ng-binding'][contains(text(),'OPEN')]")
    WebElement dayplanopenactioncount;
	
	@FindBy(xpath="//span[@class='text ng-binding']")
	WebElement dayplandateicon;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']//div[@class='projectActionsWidgetContainer']//div[@class='projectActionsWidget ng-scope'][1]//ul[1]/li[1]//div[@class='titleblock']/div[1]/a")
	WebElement  mousehoverfirstactiontitle;
	
	@FindBy(xpath="//button[@class='btnIcon icon icon-ic-remove']")
	WebElement removebutton;
	
	public void clickOnRemoveButton() 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, removebutton);
		removebutton.click();
	}
	
	public boolean isMyActionFilterEnableOrNot() throws InterruptedException 
	{
		Thread.sleep(700);
		String elementclassname = onlymyactionicon.getAttribute("class");
		if (elementclassname.equalsIgnoreCase("iconlistItem active")) 
		{
			return true;
			
		} else 
		{
			return false;
		}
		
	}

	public void clickOnMyActionButton() 
	{
		onlymyactionicon.click();
	}

	public int getOpenActionCountFromDueFilter() throws InterruptedException 
	{
	 Thread.sleep(500);
	 String getopenlabel = openactionlabel.getText();
	 int Length= getopenlabel.length();
	 	
	 String getcount = getopenlabel.substring(4, getopenlabel.length());
	 String afterreplace = getcount.replaceAll("[^0-9]+", "");
	 int actualcount = Integer.parseInt(afterreplace.trim());
	 return actualcount; 
	}

	public void clickOnAddButtonOfFirstActionFromDue() 
	{
		firstactionaddbuttonduefilter.click();
	}
	
	public int getOpenActionCountFromDayPlan() throws InterruptedException 
	{
	 Thread.sleep(500);
	 String getopenlabel = dayplanopenactioncount.getText();
	 int Length= getopenlabel.length();
	 	
	 String getcount = getopenlabel.substring(4, getopenlabel.length());
	 String afterreplace = getcount.replaceAll("[^0-9]+", "");
	 int actualcount = Integer.parseInt(afterreplace.trim());
	 return actualcount; 
	}
	
	public void clickOnDayPlanDate() 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, dayplandateicon);
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(dayplandateicon);
		
	}
	
	public void MouseHoverToFirstActionTitle() 
	{
		ActionHelper  actionhelper = new ActionHelper(driver);
		actionhelper.mouseHoverToElement(mousehoverfirstactiontitle);
		
	}

}
