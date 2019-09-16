package com.ityss.tamplo.helper.browserconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.ityss.tamplo.helper.resources.ResourceHelper;

public class FirefoxBrowser 
{
	public FirefoxOptions getFireFoxOption() 
	{
		
		
		FirefoxProfile  profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		
		
		DesiredCapabilities capa = DesiredCapabilities.firefox();
		capa.setCapability(FirefoxDriver.PROFILE, profile);
		
		
		FirefoxOptions options =  new FirefoxOptions(capa);
		
		return options;
	}
	
	public WebDriver getFirefoxBrowser(FirefoxOptions option) 
	{
		if (System.getProperty("os.name").contains("window")) 
		{
			System.setProperty("webdriver.gecko.driver", ResourceHelper.getResourcePath("src/main/resources/drivers/geckodriver.exe"));
			return new FirefoxDriver(option);
		}
		
		return null;
		
	}

}
