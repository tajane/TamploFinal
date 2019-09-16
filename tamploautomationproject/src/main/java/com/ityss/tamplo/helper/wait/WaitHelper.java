package com.ityss.tamplo.helper.wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class WaitHelper 
{
	private WebDriver driver;
	
	public WaitHelper(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public void setImplicitWait(int time) 
	{
	     driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);    
	}
	
	public void setPageLoadTimeOut(int time) 
	{
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}
	
	public WebDriverWait getWait(int timeOutInSeconds,int pollingduration) 
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingduration, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		
		return wait;
	}
	
	public void waitForVisibilityOfElmentWithPollingTime(int  timeOutInSeconds,int pollingduration, WebElement  element) 
	{
		 WebDriverWait wait = getWait(timeOutInSeconds, pollingduration);
		 wait.until(ExpectedConditions.visibilityOf(element));	
		
		 
	}
	
	public void waitForVisibilityOfElementTimeOutOnly(int timeOutInSeconds,WebElement element) 
	{
		 WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		 wait.until(ExpectedConditions.visibilityOf(element));
		 
	}
	
	public Wait<WebDriver> getFluentWait(int timeout,int pollingtime)
	{
		 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.withTimeout(timeout, TimeUnit.SECONDS)			
		.pollingEvery(pollingtime, TimeUnit.SECONDS)
		.ignoring(ElementNotFoundException.class);
		 return wait;
	}
	
	public void checkVisiblityOfElementUsingFluent(int timeout,int pollingtime,WebElement element) 
	{
		Wait<WebDriver> wait = getFluentWait(timeout, pollingtime);
		wait.until(ExpectedConditions.visibilityOf(element));	
	}
	
	public void waitUntilElmentToBeClickable(int timeout,int pollingtime,WebElement element) 
	{
		Wait<WebDriver> wait = getFluentWait(timeout, pollingtime);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	
}
