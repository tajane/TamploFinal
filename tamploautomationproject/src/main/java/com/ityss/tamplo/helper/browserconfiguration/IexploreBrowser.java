package com.ityss.tamplo.helper.browserconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ityss.tamplo.helper.resources.ResourceHelper;

public class IexploreBrowser 
{

	public InternetExplorerOptions getIexploreOption() 
	{
		
		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
	    capabilities.setJavascriptEnabled(true);
	    
	    InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
	   
	    return  options;
	}
	
	public WebDriver getIexploreBrowser(InternetExplorerOptions option) 
	{
		if (System.getProperty("os.namee").contains("window")) 
		{
			System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath("src/main/resources/drivers/IEDriverServer.exe"));
			return new InternetExplorerDriver(option);
		}
		return  null;
	}
}
