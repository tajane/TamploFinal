package com.ityss.tamplo.helper.select;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectHelper 
{
	private WebDriver driver;
	public SelectHelper(WebDriver driver) 
	{
	    this.driver = driver;
	}
	
	public void selectByIndex(WebElement element,  int index) 
	{
	 Select select = new Select(element);
	 select.selectByIndex(index);
	}
	
	public void selectByValue(WebElement element, String value) 
	{
		Select select = new Select(element);
		select.selectByValue(value);
	}
	
	public void selectByVisibleText(WebElement element,String text) 
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

}
