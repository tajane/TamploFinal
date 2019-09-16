package com.ityss.tamplo.helper.browserconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ityss.tamplo.helper.resources.ResourceHelper;

public class ChromeBrowser 
{


	public ChromeOptions getChromeOptions() 
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setJavascriptEnabled(true);
		
		options.setCapability(ChromeOptions.CAPABILITY, capabilities);
		return options;
		
	}
	
	public WebDriver getChromeBrowser(ChromeOptions opt) 
	{
		if (System.getProperty("os.name").contains("Window")) 
		{
			 System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
			  return new ChromeDriver(opt);
		}
		
	  	return null;
	}
}
