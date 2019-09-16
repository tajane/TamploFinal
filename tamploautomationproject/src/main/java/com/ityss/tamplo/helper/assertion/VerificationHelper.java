package com.ityss.tamplo.helper.assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VerificationHelper 
{
private WebDriver driver;

	public VerificationHelper(WebDriver driver) 
	{
		this.driver =  driver;
	}
	
	public boolean isElementDisplayed(WebElement element) 
	{
		try {
			element.isDisplayed();
			return true;
			
		} catch (Exception e) 
		{
			// TODO: handle exception
			return false;
		}	
	}
	
	public String readvalueFromElement(WebElement element) 
	{
		if (element == null) 
		{
			return null;
		}
		
		boolean status = element.isDisplayed();
		if (status) 
		{
			return element.getText();
		}
		else {
			return null;
		}
		
	}
	
	public boolean isElementSelected(WebElement element) 
	{
	 try 
	 {
		element.isSelected();
		return true;
	} catch (Exception e) 
	 {
		return false;
	}
		
	}
	
	public boolean isElementEnabled(WebElement element) 
	{
		try {
			element.isEnabled();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
