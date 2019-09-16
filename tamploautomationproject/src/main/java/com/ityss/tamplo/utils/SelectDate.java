package com.ityss.tamplo.utils;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SelectDate 
{
	private WebDriver driver;
	private  int today;
    private  int year;
    public  int yearnumber;
    private  String month;
     String headermonth ;
     int lengthheader;
     String gettextmonth =null;

public SelectDate(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

    public void selectChoiceDate(int choiceyear,String enterfirstthreecharacterofmonth, int choicedate) throws InterruptedException
    {

        //do not run for current today date it will  continueously execution mode
        driver.findElement(By.xpath("//div[@class='ng-scope']/ul/li[1]/div[2]/span/button[@title='DUE DATE']")).click();
       List<WebElement> findnorows = driver.findElements(By.xpath("//div[@class='duedate']/span/div/div/table/tbody/tr"));
        today = getCurrentDay();
        month = getCurrentMonth();
        year = getCurrentYear();
        int getrows = findnorows.size();
        int differenceyear = choiceyear-year ;


        for(int g= 0; g<=differenceyear; g++)
        {
            WebElement gettxtyear = driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[2]"));
            yearnumber  = GetYear.Year(gettxtyear);

            if (yearnumber == choiceyear)
            {
                moveMonth(enterfirstthreecharacterofmonth);

                for (int i = 1; i <= getrows; i++)
                {
                    for (int j = 1; j <= 7; j++)
                    {
                        Thread.sleep(300);
                        try {
                            // below element find only active dates from respective month
                            WebElement actualnuumber = driver.findElement(By.xpath("//div[@class='duedate']/span/div/div/table/tbody/tr["+i+"]/td["+j+"]"));
                            String actualdate = actualnuumber.getText(); // use this above path becauae previous found many date so its fail

                            if (Integer.valueOf(actualdate).equals(choicedate))
                            {
                                // if searching value from  calendar and sum of date ()like 28 is same then click
                                actualnuumber.click();
                                break;
                                //          System.out.println("click on actual date");
                            }
                        } catch (Exception output)
                        {
                        }
                    }

                }

            } moveYear();

        }

    }

    public  void moveYear()
    {
        try {
            if (year == yearnumber)
            {
                driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[2]")).click();
                driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[3]")).click();
            }
            else
            {
                driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[3]")).click();
            }
        }catch (Exception output) {}
    }

    public void moveMonth(String choicemonth) throws InterruptedException
    {
        WebElement gettxtyear2 = driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[2]"));
        headermonth = gettxtyear2.getText();
        lengthheader = headermonth.length();
        if(lengthheader>=6)
        {
            WebElement gettxtyear3 = driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[2]"));
            gettextmonth = GetCurrentMonth.Month(gettxtyear3);
            if (gettextmonth.equals(choicemonth))
            {

            }else {
                for (int a = 1;a<=12;a++)
                {
                    driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[3]")).click();
                    String getnexttextmonth = GetCurrentMonth.Month(gettxtyear3);
                    if(getnexttextmonth.equals(choicemonth))
                    {
                        gettextmonth = GetCurrentMonth.Month(gettxtyear3);
                        break;
                    }

                }
            }
        }else
        {
            for (int p = 1; p <= 3; p++)
            {
                for (int q = 1; q <= 4; q++)
                {
                    WebElement monthttext = driver.findElement(By.xpath("//div[@class='duedate']/span/div/div/table/tbody/tr[" + p + "]/td[" + q + "]"));
                    gettextmonth = monthttext.getText();

                    if (gettextmonth.equals(choicemonth))
                    {

                        driver.findElement(By.xpath("//div[@class='duedate']/span/div/div/table/tbody/tr[" + p + "]/td[" + q + "]")).click();
                        break;

                    }
                }
            }
        }

    }

    public int getCurrentYear() throws InterruptedException
    {

        driver.findElement(By.xpath("//div[@class='ng-scope']/ul/li[1]/div[2]/span/button[@title='DUE DATE']")).click();

        WebElement getyear = driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[2]"));
        int yearnumber = GetYear.Year(getyear);
        System.out.println("current year is = " + yearnumber);
        return yearnumber;
    }

    public String getCurrentMonth() throws InterruptedException
    {
        driver.findElement(By.xpath("//div[@class='ng-scope']/ul/li[1]/div[2]/span/button[@title='DUE DATE']")).click();
        WebElement getmonth = driver.findElement(By.xpath("//div[@class='duedate']/span/div/table/thead/tr/th[2]"));
        String monthnumber = GetCurrentMonth.Month(getmonth);
        System.out.println("current month is = " + monthnumber);
        return monthnumber;
    }

    private int getCurrentDay()
    {
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("Today Int: " + todayInt + "\n");

        //Integer to String Conversion
        int todayStr = todayInt;
        System.out.println("Today Str: " + todayStr + "\n");

        return todayStr;
    }
}
