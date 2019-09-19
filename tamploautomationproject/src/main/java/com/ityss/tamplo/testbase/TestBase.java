package com.ityss.tamplo.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ityss.tamplo.helper.browserconfiguration.BrowserType;
import com.ityss.tamplo.helper.browserconfiguration.ChromeBrowser;
import com.ityss.tamplo.helper.browserconfiguration.FirefoxBrowser;
import com.ityss.tamplo.helper.browserconfiguration.IexploreBrowser;
import com.ityss.tamplo.helper.resources.ResourceHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;
import com.ityss.tamplo.pageobject.DashboardPageObject;
import com.ityss.tamplo.pageobject.LoginPageObject;
import com.ityss.tamplo.utils.ExtentManager;

public class TestBase 
{
	public WebDriver driver;
	public static Properties prob;
	public static ExtentTest test;
	public static ExtentReports extentReports;
	LoginPageObject loginpageobject;
	DashboardPageObject dashboardagebject;
	

 	public TestBase()  
	{
		try 
		{
		FileInputStream inputStream = new FileInputStream(ResourceHelper.getResourcePath("src/main/resources/propertiesfile/config.properties"));
		// TODO Auto-generated constructor stub
		FileInputStream inputStream2 = new FileInputStream(ResourceHelper.getResourcePath("src/main/resources/propertiesfile/departmentedit.properties"));
		prob = new Properties();
		prob.load(inputStream);
		prob.load(inputStream2);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			
		}catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@BeforeSuite
	public void beforeSuite()
	{
		extentReports = ExtentManager.getInstance();
	}
	
	@BeforeTest
	public void beforeTest() throws InstantiationException, IllegalAccessException, IOException
	{
		test = extentReports.createTest(getClass().getSimpleName());
		setUpBrowser();
		getApplicationUrl(prob.getProperty("url"));
		
		loginpageobject   = new LoginPageObject(driver);
		dashboardagebject = new DashboardPageObject(driver);
			
		loginpageobject.login(prob.getProperty("username"), prob.getProperty("password"));
		test.log(Status.PASS, MarkupHelper.createLabel((prob.getProperty("username")) + " : this user login successfully ", ExtentColor.GREEN));
		
		dashboardagebject.setCompany(prob.getProperty("company"));
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("company") + " : this company select successfully ", ExtentColor.GREEN));
		
		dashboardagebject.setLanguage(prob.getProperty("language"));
		test.log(Status.PASS, MarkupHelper.createLabel(prob.getProperty("language") + " : this language selected ", ExtentColor.GREEN));
		
	}
	
	@BeforeMethod
	public void beforemethod(Method method)
	{
		test.log(Status.INFO, method.getName()+  "Test has Started");
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if (result.getStatus()==ITestResult.FAILURE) 
		{
			test.log(Status.FAIL, result.getThrowable()+"Test fail");
			getScreenShot();
			
		} else 
			if(result.getStatus()==ITestResult.SUCCESS)
		
			{
				test.log(Status.PASS, result.getName()+"Test pass" );
				
				
			}else if (result.getStatus()==ITestResult.SKIP) 
			{
				test.log(Status.SKIP,result.getThrowable()+"Test skip");
			}
		
		extentReports.flush();
	}
	public WebDriver getBrowserObject(BrowserType type) 
	{
		try 
		{
			
		switch(type) 
		{
		
		case Chrome:
		    ChromeBrowser chromeBrowser = ChromeBrowser.class.newInstance();
		    ChromeOptions chromeoptions = chromeBrowser.getChromeOptions();
		    return chromeBrowser.getChromeBrowser(chromeoptions);
		
		case Firefox:
			FirefoxBrowser firefoxBrowser = FirefoxBrowser.class.newInstance();
			FirefoxOptions firfoxoptions  = firefoxBrowser.getFireFoxOption();
			return firefoxBrowser.getFirefoxBrowser(firfoxoptions);
			
		case Iexplore:
			IexploreBrowser iBrowser = IexploreBrowser.class.newInstance();
			InternetExplorerOptions iexploreoption = iBrowser.getIexploreOption();
			return iBrowser.getIexploreBrowser(iexploreoption);
			
			default: 
			throw new Exception("browser type not present" + type.name());	
				
			
		}
		} catch (Exception e) 
		{
			return null;
		}
		
		
	}
	
	public void setUpBrowser() throws InstantiationException, IllegalAccessException 
	{
		 driver = getBrowserObject(BrowserType.valueOf(prob.getProperty("browser")));
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.setImplicitWait(Integer.parseInt(prob.getProperty("implicitetime")));
		waitHelper.setPageLoadTimeOut(Integer.parseInt(prob.getProperty("pageloadtime")));
		driver.manage().window().maximize();
		
		
		
	}

	public void getScreenShot() throws IOException 
	{
		
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String getdate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		String filelocation = ResourceHelper.getResourcePath("src/main/resources/screenshots/");
		FileUtils.copyFile(file, new File(filelocation +getClass().getSimpleName()+"_"+getdate+".png"));
		
		
	}
	
	public void getApplicationUrl(String url)
	{
		driver.get(url);
		
	}
}
