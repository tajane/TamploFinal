package com.ityss.tamplo.helper.javascript;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class JavaScriptHelper 
{
	private WebDriver driver;
	
	public JavaScriptHelper(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public Object scriptExecutor(String script) 
	{
		JavascriptExecutor execute = (JavascriptExecutor)driver;
		return  execute.executeScript(script);
	}
	

	public Object scriptExecutor(String script,Object...args) 
	{
		JavascriptExecutor execute = (JavascriptExecutor)driver;
		return  execute.executeScript(script,args);
	}
	
	public void scrollToElment(WebElement element)
	{
		scriptExecutor("arguments[0].scrollIntoView()", element);
		
	}
	
	public void clickOnElement(WebElement element) 
	{
		scriptExecutor("arguments[0].click();", element);
		
	}
	
	public void generatePopup(String popupcontains) 
	{
		scriptExecutor("alert('"+popupcontains+"..');");
	}
	
	
}
