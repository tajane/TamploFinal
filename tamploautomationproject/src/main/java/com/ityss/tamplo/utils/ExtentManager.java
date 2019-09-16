package com.ityss.tamplo.utils;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ityss.tamplo.helper.resources.ResourceHelper;

public class ExtentManager 
{
	
	  
	 private static ExtentReports reports;
	
	public static  ExtentReports getInstance() 
	{
	if (reports==null) 
	{
		String location = ResourceHelper.getResourcePath("src/main/resources/reports/reports.html");
	    return createInstance(location);
	}
	else
	{
		return reports;
	}
		
	}
	

	public static ExtentReports createInstance(String path) 
	{
		ExtentHtmlReporter htmlreports = new ExtentHtmlReporter(path);
		htmlreports.setAppendExisting(false);
		reports =  new ExtentReports();
		reports.attachReporter(htmlreports);
		
		htmlreports.config().setDocumentTitle(path);
		htmlreports.config().setReportName("Automation Test Script");
		htmlreports.config().setTheme(Theme.STANDARD);
		htmlreports.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		
		return reports;
	}
}
