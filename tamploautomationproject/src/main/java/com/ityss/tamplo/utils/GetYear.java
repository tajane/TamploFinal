package com.ityss.tamplo.utils;

import org.openqa.selenium.WebElement;

import static java.lang.Integer.parseInt;

public class GetYear {
    public  static int Year(WebElement vairblename) throws InterruptedException 
    {
        Thread.sleep(1000);
        String labelname = vairblename.getText();
        String substringdevde = labelname.substring(labelname.length()-4);
        String removeingspace = substringdevde.replaceAll("-","").trim();
        int gettcount = parseInt(String.valueOf(removeingspace));
        return gettcount;

    }
}
