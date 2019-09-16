package com.ityss.tamplo.pageobject;

import java.util.List;

import org.openqa.selenium.By;
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
	
	public boolean isThreeDisplayOrNot(String departmentname) 
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
		//clckon.click();
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
	
}
