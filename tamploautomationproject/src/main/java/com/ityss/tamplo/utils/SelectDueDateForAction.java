package com.ityss.tamplo.utils;

import java.time.Month;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ityss.tamplo.helper.javascript.JavaScriptHelper;

public class SelectDueDateForAction 
{
	private WebDriver driver;
	public SelectDueDateForAction(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//table[@class='header-view']/thead/tr[1]/th[2]")
	 WebElement getcalendarheader;
	
	@FindBy(xpath="//table[@class='header-view']/thead/tr[1]/th[3]")
	 WebElement nextbuttoncalendarheader;
	
	@FindBy(xpath="//span[contains(text(),'END DATE :')]//following-sibling::span//table[@class='header-view']/thead/tr[1]/th[3]")
	 WebElement milestoneendatenextbuttoncalendarheader;
	
	@FindBy(xpath="//span[contains(text(),'END DATE :')]//following-sibling::span//table[@class='header-view']/thead/tr[1]/th[1]")
	 WebElement milestoneendatePreviousbuttoncalendarheader;
	
	@FindBy(xpath="//div[@class='moment-picker-container month-view open']/table[@class='header-view']/thead/tr[1]/th[3]")
	WebElement meetingstarttimenextbutton;
	
	@FindBy(xpath="//div[@class='moment-picker-container month-view open']/table[@class='header-view']/thead/tr[1]/th[1]")
	WebElement meetingstarttimepreviousbutton;
	
	@FindBy(xpath="//table[@class='header-view']/thead/tr[1]/th[1]")
	 WebElement previousbuttoncalendarheader;
	
	@FindBy(xpath="//div[@class='moment-picker-container month-view open']/table[@class='header-view']/thead/tr[1]/th[2]")
	WebElement getmeetingcalendarheader;
	
	@FindBy(xpath="//div[@class='moment-picker-container open year-view']/table/thead/tr[1]/th[2]")
	WebElement meetingmonthheader;
	
	@FindBy(xpath="//span[contains(text(),'END DATE :')]//following-sibling::span//table[@class='header-view']/thead/tr[1]/th[2]")
	WebElement milestoneenddatehedaer;
	
	private void moveBackCalendar() 
	{
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(previousbuttoncalendarheader);
		//previousbuttoncalendarheader.click();
	}
	
	private void milestoneenddatemoveBackCalendar() 
	{
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(milestoneendatePreviousbuttoncalendarheader);
		
	}
	
	private void milestoneenddatemoveForwardCalendar() 
	{
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(milestoneendatenextbuttoncalendarheader);
		
	}
	
	private void MoveFarwardCalendar() 
	{
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(nextbuttoncalendarheader);
		//nextbuttoncalendarheader.click();
	}

	public void selectActionDueDateByChoice(int enteryears,String fullmonthname,int date) throws InterruptedException 
	{
		String getstring = getcalendarheader.getText();
		//System.out.println(getstring);
		String getmonthfromheader = getFullMonthNameFromHeader(getstring);
		int getyearfromheader = getYearFromHeader(getcalendarheader.getText());
		int count = 0;
		for (int i = 1; i < 15; i++) 
		{
			
			if (getyearfromheader==enteryears && getmonthfromheader.equalsIgnoreCase(fullmonthname)) 
			{	
				selectDueDate(date);
				break;
			}else 
			if (getyearfromheader!=enteryears || !getmonthfromheader.equalsIgnoreCase(fullmonthname)) 
			{	
				MoveFarwardCalendar();
			     Thread.sleep(500);
			     String getmonthfromheaderafter = getFullMonthNameFromHeader(getcalendarheader.getText());
					int getyearfromheaderafter = getYearFromHeader(getcalendarheader.getText());
				if (getyearfromheaderafter==enteryears && getmonthfromheaderafter.equalsIgnoreCase(fullmonthname)) 
				{
					selectDueDate(date);
					count++;
					break;
				} 
				
				continue;
			}
			
			if (count != 0) 
			{
				break;
			}
		}
		
	}
	
	public void selectMeetingStartDateByChoice(int enteryears,String fullmonthname,int date) throws InterruptedException 
	{
		String getstring = getmeetingcalendarheader.getText();
		
		String getmonthfromheader = getFullMonthNameFromHeader(getstring);
		int getyearfromheader = getYearFromHeader(getmeetingcalendarheader.getText());
		int count = 0;
		for (int i = 1; i < 15; i++) 
		{
			
			if (getyearfromheader==enteryears && getmonthfromheader.equalsIgnoreCase(fullmonthname)) 
			{	
				selectMeetingDate(date);
				break;
			}else 
			if (getyearfromheader!=enteryears || !getmonthfromheader.equalsIgnoreCase(fullmonthname)) 
			{	
                 meetingstarttimenextbutton.click();
			     Thread.sleep(500);
			     String getmonthfromheaderafter = getFullMonthNameFromHeader(getmeetingcalendarheader.getText());
					int getyearfromheaderafter = getYearFromHeader(getmeetingcalendarheader.getText());
				if (getyearfromheaderafter==enteryears && getmonthfromheaderafter.equalsIgnoreCase(fullmonthname)) 
				{
					selectMeetingDate(date);
					count++;
					break;
				} 
				
				continue;
			}
			
			if (count != 0) 
			{
				break;
			}
		}
		
	}
	
	public void selectFilterDueDateByChoice(int enteryears,String monthname,int date) throws InterruptedException 
	{
		String getstring = getcalendarheader.getText();
		//System.out.println(getstring);
		String getfullmonthfromheader = getFullMonthNameFromHeader(getstring);
		
		int getyearfromheader = getYearFromHeader(getcalendarheader.getText());
		
		int count =  0;
		for (int i = 1; i <= 15; i++) 
		{
        
		    if (getyearfromheader==enteryears && getfullmonthfromheader.equalsIgnoreCase(monthname)) 
		    {			
		    	selectDueDate(date);
		    	count++;
			
		    }else    
		    {
		    	selectInputMonthName(monthname,enteryears);
		    	selectDueDate(date);
		    	count++;
		    	
		    }
		    
			if (count!=0) 
			{
				break;
			}	
		} 
		
	}
	
	public void selectMilestoneEndDate(int enteryears,String monthname,int date) throws InterruptedException 
	{
		
		String getstring = milestoneenddatehedaer.getText();
		//System.out.println(getstring);
		String getfullmonthfromheader = getFullMonthNameFromHeader(getstring);
		
		int getyearfromheader = getYearFromHeader(milestoneenddatehedaer.getText());
		
		int count =  0;
		for (int i = 1; i <= 15; i++) 
		{
        
		    if (getyearfromheader==enteryears && getfullmonthfromheader.equalsIgnoreCase(monthname)) 
		    {			
		    	selectMilestoneEndDate(date);
		    	count++;
			
		    }else    
		    {
		    	selectMilestoneEndDateInputMonthName(monthname,enteryears);
		    	selectMilestoneEndDate(date);
		    	count++;
		    	
		    }
		    
			if (count!=0) 
			{
				break;
			}	
		} 
		
	}
	
    private void selectDueDate(int dateinpt) 
	{
		
			List<WebElement> findnorows = driver.findElements(By.xpath("//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr"));
		    int count =0;
		    int totalrows = findnorows.size();
			for (int i = 1; i <= totalrows; i++)
            {
                for (int j = 1; j <= 7; j++)
                {
                                               
                        WebElement actualnuumber = driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr["+i+"]/td["+j+"]"));
                        String actualdate = actualnuumber.getText(); // use this above path becauae previous found many date so its fail

                        if (actualdate.isEmpty()) 
                        {
							continue;
						}
                        int converttoint = Integer.parseInt(actualdate);
                        if (converttoint==dateinpt)
                        {
                        	String getclassname = actualnuumber.getAttribute("class");
                        	if (getclassname.equals("ng-binding ng-scope")) 
                        	{
                        		
                        		try 
                            	{
                        			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
    							     driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
    							    count++;
    							    break;
    							    
                            	} catch (ElementNotVisibleException e) 
                            	{
                            		e.printStackTrace();
                            	}
                            }else 
                            if (getclassname.equals("ng-binding ng-scope disabled")) 
                            {
                                System.out.println("day is disable");
                                continue;
                            
							}else if (getclassname.equals("ng-binding ng-scope selected")) 
							{
								driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
								count++;
								break;
							}
                        	else 
                        	{
							    // System.out.println("element not found");	
							}
						 
                        }else 
                          {
							 //System.out.println("request date  not found row : " + i + "  column : " + j);
						   }
                        
                       
	
                }
                if (count!=0) {
					break;
				}
                        
            }
	}
    
    private void selectMilestoneEndDate(int dateinpt) 
   	{
   		
   			List<WebElement> findnorows = driver.findElements(By.xpath("//span[contains(text(),'END DATE :')]//following-sibling::span//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr"));
   		    int count =0;
   		    int totalrows = findnorows.size();
   			for (int i = 1; i <= totalrows; i++)
               {
                   for (int j = 1; j <= 7; j++)
                   {
                                                  
                           WebElement actualnuumber = driver.findElement(By.xpath("//span[contains(text(),'END DATE :')]//following-sibling::span//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr["+i+"]/td["+j+"]"));
                           String actualdate = actualnuumber.getText(); // use this above path becauae previous found many date so its fail

                           if (actualdate.isEmpty()) 
                           {
   							continue;
   						}
                           int converttoint = Integer.parseInt(actualdate);
                           if (converttoint==dateinpt)
                           {
                           	String getclassname = actualnuumber.getAttribute("class");
                           	if (getclassname.equals("ng-binding ng-scope")) 
                           	{
                           		
                           		try 
                               	{
                           			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
       							     driver.findElement(By.xpath("//span[contains(text(),'END DATE :')]//following-sibling::span//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
       							    count++;
       							    break;
       							    
                               	} catch (ElementNotVisibleException e) 
                               	{
                               		e.printStackTrace();
                               	}
                               }else 
                               if (getclassname.equals("ng-binding ng-scope disabled")) 
                               {
                                   System.out.println("day is disable");
                                   continue;
                               
   							}else if (getclassname.equals("ng-binding ng-scope selected")) 
   							{
   								driver.findElement(By.xpath("//span[contains(text(),'END DATE :')]//following-sibling::span//table[@ng-if=\"view.selected == 'month'\"]/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
   								count++;
   								break;
   							}
                           	else 
                           	{
   							    // System.out.println("element not found");	
   							}
   						 
                           }else 
                             {
   							 //System.out.println("request date  not found row : " + i + "  column : " + j);
   						   }
                           
                          
   	
                   }
                   if (count!=0) {
   					break;
   				}
                           
               }
   	}
       
    
    private void selectMeetingDate(int dateinpt) 
   	{
   		
   			List<WebElement> findnorows = driver.findElements(By.xpath("//div[@class='moment-picker-container month-view open']//table[@class='ng-scope']/tbody/tr"));
   		    int count =0;
   		    int totalrows = findnorows.size();
   			for (int i = 1; i <= totalrows; i++)
               {
                   for (int j = 1; j <= 7; j++)
                   {
                                                  
                           WebElement actualnuumber = driver.findElement(By.xpath("//div[@class='moment-picker-container month-view open']//table[@class='ng-scope']/tbody/tr["+i+"]/td["+j+"]"));
                           String actualdate = actualnuumber.getText(); // use this above path becauae previous found many date so its fail

                           if (actualdate.isEmpty()) 
                           {
   							continue;
   						}
                           int converttoint = Integer.parseInt(actualdate);
                           if (converttoint==dateinpt)
                           {
                           	String getclassname = actualnuumber.getAttribute("class");
                           	if (getclassname.equals("ng-binding ng-scope")) 
                           	{
                           		
                           		try 
                               	{
                           			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
       							     driver.findElement(By.xpath("//div[@class='moment-picker-container month-view open']//table[@class='ng-scope']/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
       							    count++;
       							    break;
       							    
                               	} catch (ElementNotVisibleException e) 
                               	{
                               		e.printStackTrace();
                               	}
                               }else 
                               if (getclassname.equals("ng-binding ng-scope disabled")) 
                               {
                                   System.out.println("day is disable");
                                   continue;
                               
   							}else if (getclassname.equals("ng-binding ng-scope selected")) 
   							{
   								driver.findElement(By.xpath("//div[@class='moment-picker-container month-view open']//table[@class='ng-scope']/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
   								count++;
   								break;
   							}
                           	else 
                           	{
   							     //System.out.println("element not found");	
   							}
   						 
                           }else 
                             {
   							 // day not found
   						   }
                           
                          
   	
                   }
                   if (count!=0) {
   					break;
   				}
                           
               }
   	}

    private void selectOccuranceDueDate(int dateinpt) 
   	{
   		
   			List<WebElement> findnorows = driver.findElements(By.xpath("//span[contains(text(),'END ON')]/following-sibling::span//table/tbody/tr"));
   		    int count =0;
   		    int totalrows = findnorows.size();
   			for (int i = 1; i <= totalrows; i++)
               {
                   for (int j = 1; j <= 7; j++)
                   {
                                                  
                           WebElement actualnuumber = driver.findElement(By.xpath("//span[contains(text(),'END ON')]/following-sibling::span//table/tbody/tr["+i+"]/td["+j+"]"));
                           String actualdate = actualnuumber.getText(); // use this above path becauae previous found many date so its fail

                           if (actualdate.isEmpty()) 
                           {
   							continue;
   						}
                           int converttoint = Integer.parseInt(actualdate);
                           if (converttoint==dateinpt)
                           {
                           	String getclassname = actualnuumber.getAttribute("class");
                           	if (getclassname.equals("ng-binding ng-scope")) 
                           	{
                           		
                           		try 
                               	{
                           			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
       							     driver.findElement(By.xpath("//span[contains(text(),'END ON')]/following-sibling::span//table/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
       							    count++;
       							    break;
       							    
                               	} catch (ElementNotVisibleException e) 
                               	{
                               		e.printStackTrace();
                               	}
                               }else 
                               if (getclassname.equals("ng-binding ng-scope disabled")) 
                               {
                                   System.out.println("day is disable");
                                   continue;
                               
   							}else if (getclassname.equals("ng-binding ng-scope selected")) 
   							{
   								driver.findElement(By.xpath("//span[contains(text(),'END ON')]/following-sibling::span//table/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
   								count++;
   								break;
   							}
                           	else 
                           	{
   							     //System.out.println("element not found");	
   							}
   						 
                           }else 
                             {
   							 // day not found
   						   }
                           
                          
   	
                   }
                   if (count!=0) {
   					break;
   				}
                           
               }
   	}

    private void selectMilestoneEndDateInputMonthName(String inputmonth,int yearsinput) 
	{
		
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(milestoneenddatehedaer);	
		//getcalendarheader.click();
			
			String getcalendrheader = milestoneenddatehedaer.getText();
			int convertyearintointerger = Integer.parseInt(getcalendrheader);
			
			
			if (convertyearintointerger==yearsinput) 
			{
				clickOnInputMonthName(inputmonth);			
			} else if (yearsinput > convertyearintointerger) 
			{
				for (int i = 0; i < 4; i++) 
				{
					milestoneenddatemoveForwardCalendar();
					String getcalendrheaderaftermove = milestoneenddatehedaer.getText();
					int convertyearintointergeraftermove = Integer.parseInt(getcalendrheaderaftermove);
					if (convertyearintointergeraftermove==yearsinput) 
					{
						clickOnInputMonthName(inputmonth);	
						break;
					}
				}
			}else if (yearsinput < convertyearintointerger) 
			{
				for (int i = 0; i < 4; i++) 
				{
					milestoneenddatemoveBackCalendar();
					String getcalendrheaderaftermove = milestoneenddatehedaer.getText();
					int convertyearintointergeraftermove = Integer.parseInt(getcalendrheaderaftermove);
					if (convertyearintointergeraftermove==yearsinput) 
					{
						clickOnInputMonthName(inputmonth);	
						break;
					}
				}
			}
	                	
	}
	
    
	private void selectInputMonthName(String inputmonth,int yearsinput) 
	{
		
		JavaScriptHelper scriptexecute = new JavaScriptHelper(driver);
		scriptexecute.clickOnElement(getcalendarheader);	
		//getcalendarheader.click();
			
			String getcalendrheader = getcalendarheader.getText();
			int convertyearintointerger = Integer.parseInt(getcalendrheader);
			
			
			if (convertyearintointerger==yearsinput) 
			{
				clickOnInputMonthName(inputmonth);			
			} else if (yearsinput > convertyearintointerger) 
			{
				for (int i = 0; i < 4; i++) 
				{
					MoveFarwardCalendar();
					String getcalendrheaderaftermove = getcalendarheader.getText();
					int convertyearintointergeraftermove = Integer.parseInt(getcalendrheaderaftermove);
					if (convertyearintointergeraftermove==yearsinput) 
					{
						clickOnInputMonthName(inputmonth);	
						break;
					}
				}
			}else if (yearsinput < convertyearintointerger) 
			{
				for (int i = 0; i < 4; i++) 
				{
					moveBackCalendar();
					String getcalendrheaderaftermove = getcalendarheader.getText();
					int convertyearintointergeraftermove = Integer.parseInt(getcalendrheaderaftermove);
					if (convertyearintointergeraftermove==yearsinput) 
					{
						clickOnInputMonthName(inputmonth);	
						break;
					}
				}
			}
	                	
	}
	
	public void selectMeetingDateByChoice(int enteryears,String fullmonthname,int date) throws InterruptedException 
	{
		String getstring = getmeetingcalendarheader.getText();
		
		//System.out.println(getstring);
		String getfullmonthfromheader = getFullMonthNameFromHeader(getstring);
		
		int getyearfromheader = getYearFromHeader(getmeetingcalendarheader.getText());
		
		int count =  0;
		for (int i = 1; i < 15; i++) 
		{
			
			 if (getyearfromheader==enteryears && getfullmonthfromheader.equalsIgnoreCase(fullmonthname)) 
		    {			
				 selectOccuranceDueDate(date);
		    	count++;
			
		    }else    
		    {
		    	selectMeetingInputMonthName(fullmonthname,enteryears);
		    	selectOccuranceDueDate(date);
		    	count++;
		    	
		    }
		    
			if (count!=0) 
			{
				break;
			}	
		}
		
	}
	
	
	public void selectMeetingInputMonthName(String inputmonth,int yearsinput) 
	{
		
		
		    getmeetingcalendarheader.click();
			
			String getcalendrheader = meetingmonthheader.getText();
			int convertyearintointerger = Integer.parseInt(getcalendrheader);
			
			
			if (convertyearintointerger==yearsinput) 
			{
				clickOnInputMonthName(inputmonth);			
			} else if (yearsinput > convertyearintointerger) 
			{
				for (int i = 0; i < 4; i++) 
				{
					driver.findElement(By.xpath("//div[@class='moment-picker-container open year-view']/table[@class='header-view']/thead/tr[1]/th[3]")).click();
					String getcalendrheaderaftermove = meetingmonthheader.getText();
					int convertyearintointergeraftermove = Integer.parseInt(getcalendrheaderaftermove);
					if (convertyearintointergeraftermove==yearsinput) 
					{
						clickOnInputMonthName(inputmonth);	
						break;
					}
				}
			}else if (yearsinput < convertyearintointerger) 
			{
				for (int i = 0; i < 4; i++) 
				{
					driver.findElement(By.xpath("//div[@class='moment-picker-container open year-view']/table[@class='header-view']/thead/tr[1]/th[1]")).click();
					String getcalendrheaderaftermove = meetingmonthheader.getText();
					int convertyearintointergeraftermove = Integer.parseInt(getcalendrheaderaftermove);
					if (convertyearintointergeraftermove==yearsinput) 
					{
						clickOnInputMonthName(inputmonth);	
						break;
					}
				}
			}
	                	
	}
	
	private void clickOnInputMonthName(String inputmonth) 
	{
		int count =0;
		int breaks =0;
		for (int i = 1; i <= 3; i++)
        {
            for (int j = 1; j <= 4; j++)
            {
                                           
                    WebElement actualnuumber = driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'year'\"]/tbody/tr["+i+"]/td["+j+"]"));
                    String getmonthname = actualnuumber.getText(); // use this above path becauae previous found many date so its fail
                    String getfirstthreecharacter = getmonthname.substring(0, 3);
                    String inputmonththreecharacter = inputmonth.substring(0, 3);
                    if (getfirstthreecharacter.equalsIgnoreCase(inputmonththreecharacter))
                    {
                    	String getclassname = actualnuumber.getAttribute("class");
                    	if (getclassname.equals("ng-binding ng-scope")) 
                    	{
                    		
                    		try 
                        	{
                    			//Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
							     driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'year'\"]/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope']")).click();
							    count++;
							    break;
							    
                        	} catch (ElementNotVisibleException e) 
                        	{
                        		e.printStackTrace();
                        	}
                        }else 
                        if (getclassname.equals("ng-binding ng-scope disabled")) 
                        {
                            System.out.println("month is disable");
                            breaks++;    // if month disable we break the two for loop
                            break;
                            
                          
                        
						}else if (getclassname.equals("ng-binding ng-scope selected")) 
						{
							driver.findElement(By.xpath("//table[@ng-if=\"view.selected == 'year'\"]/tbody/tr["+i+"]/td["+j+"][@class='ng-binding ng-scope selected']")).click();
							count++;
							break;
						}
                    	else 
                    	{
						    //month not found
						}
                    }else 
                    {
						//System.out.println("request month  not found");
						if (breaks!=0) {
							break;
						}
					}

            }
            if (count!=0) {
				break;
			}
	}

	}
	
	
	
	private int getYearFromHeader(String labelname) 
	{
		//String substringgetfirstthree = labelname.substring(labelname.length()-4);
		String substringgetfirstthree = labelname;
        String getactualmonth = substringgetfirstthree.replaceAll("[^0-9]", "").trim();
        String getnum = getactualmonth;
        return Integer.parseInt(getactualmonth);
		
	}
	
	private String getFullMonthNameFromHeader(String labelname) 
	{
				
		//String substringgetfirstthree = labelname.substring(0, labelname.length()-4);
		String substringgetfirstthree = labelname;
        String getactualmonth = substringgetfirstthree.replaceAll("[^a-zA-Z]", "").trim();
        
        return getactualmonth;	
	
	}
	
	
}
