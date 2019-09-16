package com.ityss.tamplo.utils;

import org.openqa.selenium.WebElement;

public class GetCurrentMonth
{
    public  static String Month(WebElement vairblename) throws InterruptedException
    {
        Thread.sleep(1000);
        String labelname = vairblename.getText();
        String substringdevde = labelname.substring(0,3);
        String removeingspace = substringdevde.replaceAll("-","").trim();
        return removeingspace;

    }
}
