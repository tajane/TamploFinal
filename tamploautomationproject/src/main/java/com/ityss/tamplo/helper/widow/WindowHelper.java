package com.ityss.tamplo.helper.widow;

import java.util.Iterator;
import java.util.Set;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class WindowHelper 
{
	private WebDriver driver;
	
	public WindowHelper(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public void SwitchToWindowDefault() 
	{
		driver.switchTo().defaultContent();
	}

	// using index base we  can move to window
	public void switchToWindow(int index) 
	{
		int i=1;
		Set<String> allwindows = driver.getWindowHandles();
		for(String window : allwindows)
		{
			if (i==index) 
			{
				driver.switchTo().window(window);
				break;
			} else 
			{
                i++;
			}
		}	
	}

	// you have all window already open and you want move to window, that window having your element 
	public void SwitchToWindow(WebElement element ) 
	{
       Set<String> allwidnows = driver.getWindowHandles();	
		int i=1;
		int j=1;
		
		for (String window : allwidnows) 
		{
			if (i==j) 
			{
				try 
				{
					boolean  elementstatus = element.isDisplayed();
									
					if (elementstatus==true) 
					{
					System.out.println("found element ");							
					break;
					}
				} catch (Exception e) 
				{
				driver.switchTo().window(window);
				System.out.println("element not found and move to next window");
				}
				i++;
				j++;
			}
			}
	}

	public void closedAllWindowExceptParent(String paret) 
	{
		Set<String> allwidnows = driver.getWindowHandles();
		//String parentwindow = driver.getWindowHandle();
		
		for (String window : allwidnows) 
		{
			if (!window.equals(paret)) 
			{
				driver.close();
			} 
		}
		
	}
	
	public void closeAllTabsAndSwitchToMainWindow() 
	{
		String parent=driver.getWindowHandle();
		 
		// This will return the number of windows opened by Webdriver and will return Set of St//rings
		Set<String>s1=driver.getWindowHandles();
		 
		// Now we will iterate using Iterator
		Iterator<String> I1= s1.iterator();
		 
		   while(I1.hasNext())
		  {
		 
		    String child_window=I1.next();
		 
		     // Here we will compare if parent window is not equal to child window then we will close
		 
		    if(!parent.equals(child_window))
		    {
		           driver.switchTo().window(child_window);
		 
		           System.out.println(driver.switchTo().window(child_window).getTitle());
		 
		           driver.close();
		     }
		 }
		// once all pop up closed now switch to parent window
		driver.switchTo().window(parent);
	}
}
