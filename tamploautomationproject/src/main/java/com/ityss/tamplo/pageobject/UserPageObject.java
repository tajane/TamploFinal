package com.ityss.tamplo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.javascript.JavaScriptHelper;
import com.ityss.tamplo.helper.wait.WaitHelper;

public class UserPageObject 
{
	private WebDriver driver;

	public UserPageObject(WebDriver  driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[1]//button[contains(text(),'ADD ADMIN')]")
    WebElement adminaddbutton;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[2]//button[contains(text(),'ADD USER')]")
    WebElement useraddbutton;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[1]//input[@type='text']")
	WebElement admintextfieldemailaddress;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[2]//input[@type='text']")
	WebElement usertextfieldemailaddress;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[2]//button[contains(text(),'Save')]")
	WebElement usersavebutton;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[1]//button[contains(text(),'Save')]")
	WebElement adminsavebutton;
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[1]//h2[contains(text(),'ADMIN')]")
	WebElement adminlabelforcount;
	
	
	@FindBy(xpath="//section[@class='projectInfoContentSection']/div/div/div[2]//h2[contains(text(),'USER')]")
	WebElement userlabelforcount;
	
	public void clickOnAddAdminButton() 
	{
		JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
		scripthelper.clickOnElement(adminaddbutton);
		//adminaddbutton.click();
	}
	
	public void clickOnAddUserButton() 
	{
		JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
		scripthelper.clickOnElement(useraddbutton);
		
	}
	
	
	public void enterEmailAddressInAdminSection(String inputemailaddress) throws InterruptedException 
	{
		String[] data = inputemailaddress.split(",");
		int check = data.length;
		int count = 1;
		for (String sperated : data) 
		{
			
			int admincountbefore = getAdminCount();
			admintextfieldemailaddress.sendKeys(sperated);
			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
			scripthelper.clickOnElement(adminsavebutton);
			//adminsavebutton.click();
			Thread.sleep(1000);
			int admincountafter = getAdminCount();
			
			if (admincountafter > admincountbefore) 
			{
				if (count < check) 
				{
					clickOnAddAdminButton();
					count++;
					continue;
				}else 
				{
					break;
				}
				
				
			}else 
			{
			   break;	
			}
			
		}
		
		
	}
	
    public void testenterEmailAddressInUserSection(String inputemailaddress) 
    {
    	usertextfieldemailaddress.sendKeys("user email   address");
	}
    
    public void enterEmailAddressInUserSection(String inputemailaddress) throws InterruptedException 
	{
		String[] data = inputemailaddress.split(",");
		int check = data.length;
		int count = 1;
		for (String sperated : data) 
		{
			
			int usercountbefore = getuserCount();
			usertextfieldemailaddress.sendKeys(sperated);
			JavaScriptHelper scripthelper = new JavaScriptHelper(driver);
			scripthelper.clickOnElement(usersavebutton);
			
			Thread.sleep(1000);
			
			int usercountafter = getuserCount();
			
			if (usercountafter > usercountbefore) 
			{
				if (count < check) 
				{
					clickOnAddUserButton();
					count++;
					continue;
				}else 
				{
					break;
				}
				
				
			}else 
			{
			   break;	
			}
			
		}
		
		
	}
    
    public int getAdminCount() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitUntilElmentToBeClickable(20, 05, adminlabelforcount);
		String getadmintext =  adminlabelforcount.getText();
		String getlastthreecharacter = getadmintext.substring(getadmintext.length()-3);
		String afterreplaceallcharacter = getlastthreecharacter.replaceAll("[^\\d.]", "");
		return Integer.parseInt(afterreplaceallcharacter.trim());
	}
    
    public int getuserCount() 
    {
    	WaitHelper waitHelper = new WaitHelper(driver);
    	waitHelper.waitUntilElmentToBeClickable(20, 05, userlabelforcount);
		String getadmintext =  userlabelforcount.getText();
		String getlastthreecharacter = getadmintext.substring(getadmintext.length()-3);
		String afterreplaceallcharacter = getlastthreecharacter.replaceAll("[^\\d.]", "");
		return Integer.parseInt(afterreplaceallcharacter.trim());
	}
}
