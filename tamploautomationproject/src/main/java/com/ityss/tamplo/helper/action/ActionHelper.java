package com.ityss.tamplo.helper.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper 
{
	private WebDriver  driver;
	
	public ActionHelper(WebDriver  driver)
	{
		this.driver = driver;
	}
	
	
	private Actions initialiseActionObject() 
	{	 
		return new Actions(driver);
	}
	
	public void mouseHoverToElement(WebElement element) 
	{
		initialiseActionObject().moveToElement(element).perform();
	}

}
