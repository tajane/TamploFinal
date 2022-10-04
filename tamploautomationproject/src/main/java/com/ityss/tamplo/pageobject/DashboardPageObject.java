package com.ityss.tamplo.pageobject;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.ityss.tamplo.helper.javascript.JavaScriptHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;

public class DashboardPageObject 
{

	private WebDriver driver;
	private int countofcompnay;
	
	public  DashboardPageObject(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//header[@class='tamplo-mainBanner']/div/div[1]/button[1]")
	WebElement locatecompanybox;
	
	@FindBy(xpath="//header[@class='tamplo-mainBanner']/div/div[1]/button[1]/span")
	WebElement compnaydropdownicon;
	
	@FindBy(xpath="//div[@id='jq-dropdown-3']//ul[@class='jq-dropdown-menu']/li/a")
	List<WebElement> listofcompnay;

	@FindBy(xpath="//div[@class='dashboardGraph box-rsc pd1']")
	WebElement dashboardlocator;
	
	@FindBy(xpath="//button[@class='btn btnDropDown username ng-binding']")
	WebElement profileicon;
	
	@FindBy(xpath="//div[@id='jq-dropdown-2']//ul[@class='jq-dropdown-menu']")
	WebElement dropdownlists;
	
	@FindBy(xpath="//li[@class='ng-pristine ng-untouched ng-valid ng-scope ng-not-empty current']")
	WebElement  selectedlanguge;
	
	@FindBy(xpath="//li[@id='language']//a[@class='ng-binding'][contains(text(),'EN')]")
	WebElement englanguge;
	
	@FindBy(xpath="//li[@id='language']//a[@class='ng-binding'][contains(text(),'FR')]")
	WebElement frenchlanguge;
	
	@FindBy(xpath="//li[@id='language']//a[@class='ng-binding'][contains(text(),'ES')]")
	WebElement spanishlanguge;
	
	@FindBy(xpath="//button[@class='btn btnDropDown username ng-binding jq-dropdown-open']")
	WebElement dropdownopen;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/meetings/list-view\"]")
	public WebElement meetingpagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/actions/list-view\"]")
	public WebElement actionPageLink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/day_planner?filter=today\"]")
	public WebElement dayplanpagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/personalTask/list-view\"]")
	public
	WebElement personaltaskpagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/workloadGraph\"]")
	public WebElement workloadpagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/projects\"]")
	public WebElement projectpagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/process-list\"]")
	public WebElement servicepagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/departments\"]")
	public WebElement departmenntpagelink;
	
	@FindBy(xpath="//nav[@class='mainbannerNav']/ul/li/a[@href=\"../app/#!/users\"]")
	public  WebElement userspagelink;
	
	public String setCompany(String expected) 
	{
		
		String companyname = null;
		WaitHelper waitHelper =  new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, locatecompanybox);
		String getcompayname = locatecompanybox.getText();
		if (getcompayname.equalsIgnoreCase(expected)) 
		{
			System.out.println("we are on same  company");
			return companyname = getcompayname;
			
		} else 
		{
          companyname =  selectCompany(expected);
          return companyname = getcompayname;
		}
		
		
	}
	
	public String selectCompany(String expected) 
	{
		String gettext = null;
		try 
		{
			WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, compnaydropdownicon);
		compnaydropdownicon.click(); 
        countofcompnay = listofcompnay.size();
        for(WebElement allememnt : listofcompnay)
        {
        	gettext = allememnt.getText();
        	System.out.println(gettext);
        	
        	if (gettext.equalsIgnoreCase(expected)) 
        	{
			     allememnt.click();
			     break;
        		
			} 
        }
        return gettext;
		
		} catch (Exception e) 
		{
			return null;
		}
		
	}
	
	public boolean verifyDashboardPage() 
	{
		boolean isdisplay = false;
		try 
		{
			WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, dashboardlocator);
			isdisplay = dashboardlocator.isDisplayed();
			return  true;
		} catch (Exception e) 
		{
			// TODO: handle exception
			JavaScriptHelper javaScriptHelper = new JavaScriptHelper(driver);
			javaScriptHelper.generatePopup("You are not on Dashboard Page for Further Test");
			return false;
		}
		
	}

	public String setLanguage(String mylanguage) 
	{
		String selectedlang = null;
		try 
		{
			WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, profileicon);
    		JavaScriptHelper javascript =  new JavaScriptHelper(driver);
    		javascript.clickOnElement(profileicon);
    		
			//profileicon.click();
			
			String getselectedlangugae = selectedlanguge.getText();
			
			if (getselectedlangugae.equalsIgnoreCase(mylanguage)) 
			{
				String returntexte = "english language already selected ";
				System.out.println(returntexte);
				
	    		javascript.clickOnElement(dropdownopen);
				//dropdownopen.click();
				return selectedlang = returntexte;
				
			} else 
			{
				String returntext = "selected language is " + getselectedlangugae + " we change to english";
				System.out.println(returntext);
				javascript.clickOnElement(englanguge);
                //englanguge.click();
                return selectedlang = returntext;
                
			}
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}		
	}
		
	public void selectFrLanguage() 
	{
		try {
			WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, profileicon);
			profileicon.click();
			driver.findElement(By.xpath("//a[contains(text(),'FR')]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    public boolean openActionPage() 
    {
	try 
	{
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, actionPageLink);
		boolean checkelementdisplay = actionPageLink.isDisplayed();
		
		if (checkelementdisplay==true) 
		{
			actionPageLink.click();
			return  true;
			
		}
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
	return false;
    }  
    public boolean openDayPlanPage() 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, dayplanpagelink);
    		boolean checkelementdisplay = dayplanpagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper =  new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(dayplanpagelink);
    			//dayplanpagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean openPersonalTasknPage() 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, personaltaskpagelink);
    		boolean checkelementdisplay = personaltaskpagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(personaltaskpagelink);
    			//personaltaskpagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean openMeetingPage() 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitForVisibilityOfElementTimeOutOnly(20, meetingpagelink);
    		boolean checkelementdisplay = meetingpagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(meetingpagelink);
    			//meetingpagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean openProjectPage() 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, projectpagelink);
    		boolean checkelementdisplay = projectpagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(projectpagelink);
    			//projectpagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	System.out.println("project page not open");
    	return false;
    }
    
    public boolean openWorkloadPage() 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, workloadpagelink);
    		boolean checkelementdisplay = workloadpagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(workloadpagelink);
    			//workloadpagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean openDepartmentPage() 
    {
    	try 
    	{WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, departmenntpagelink);
    		boolean checkelementdisplay = departmenntpagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(departmenntpagelink);
    			//departmenntpagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean openServicePage() 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, servicepagelink);
    		boolean checkelementdisplay = servicepagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(servicepagelink);
    			//servicepagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
    public boolean openUserPage() 
    {
    	try 
    	{
    	WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.waitUntilElmentToBeClickable(20, 05, userspagelink);
    		boolean checkelementdisplay = userspagelink.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
    			scripthelper.clickOnElement(userspagelink);
    			//userspagelink.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		JavascriptExecutor javascript = (JavascriptExecutor) driver;
    		  javascript.executeScript("alert('Login user not having user page access please contact to admin');");
    		e.printStackTrace();
    		return false;
    	}
    	return false;
    }

    public boolean openPageComnMethod(WebElement choiceelement) 
    {
    	try 
    	{
    		WaitHelper waitHelper = new WaitHelper(driver);
    		waitHelper.waitUntilElmentToBeClickable(20, 05, choiceelement);
    		boolean checkelementdisplay = choiceelement.isDisplayed();
    		
    		if (checkelementdisplay==true) 
    		{
    			choiceelement.click();
    			return  true;
    		}
    	} catch (Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return false;
    }
}
