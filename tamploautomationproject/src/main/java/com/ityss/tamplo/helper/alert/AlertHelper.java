package com.ityss.tamplo.helper.alert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHelper 
{
	private WebDriver driver;

	public AlertHelper(WebDriver driver) 
	{
		this.driver  = driver;
	}
	
	public  Alert getAlert() 
	{
		 return driver.switchTo().alert();
	}
	
	
	public String getTextFromAlert() 
	{
		String gettext  = getAlert().getText();
		return gettext;
		
	}
	
	public void acceptAlert() 
	{
		getAlert().accept();
	}
	
	public void dismisedAlert() 
	{
		getAlert().dismiss();
	}
	
	public boolean isAlertPresent() 
	{
	
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			return false;
		}
	}
	
}
