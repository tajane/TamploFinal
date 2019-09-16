package com.ityss.tamplo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonObject 
{
	private WebDriver driver;
	public CommonObject(WebDriver driver)
	{
		this.driver =  driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath="//div[@class='filterActions']/ul/li[1]/a/span[2]")
	WebElement iconfilterandsort;
	
	@FindBy(xpath="//div[@class='labelWithDropDown']/ul/li[6]/tamplo-select/div[1]/ul[1]/li[1]/a")
	WebElement iconcreationAndDuedate;
	
	@FindBy(xpath="//div[@class='labelWithDropDown']/ul/li[6]/tamplo-select/div[1]/ul[1]/li[1]/div[1]/ul[@class='dropList ng-scope']/li[1]/a[1]")
	WebElement iconDueDatetitle;
	
	@FindBy(xpath="//div[@class='labelWithDropDown']/ul/li[8]/span[1]/a[@start-view='month']")
	WebElement iconDate;
	
	public void clickOnFilterAndSortIcon() 
	{
		iconfilterandsort.click();
	}
	
	public void clickOnDueDateIcon() 
	{
		iconcreationAndDuedate.click();
		iconDueDatetitle.click();		
	}
	
	public void clickOnDateIcon() 
	{
		iconDate.click();
	}
	

}
