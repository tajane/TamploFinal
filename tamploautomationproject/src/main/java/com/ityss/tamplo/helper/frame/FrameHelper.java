package com.ityss.tamplo.helper.frame;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameHelper 
{
	private WebDriver  driver;
 public FrameHelper(WebDriver driver) 
 {
	this.driver = driver;
}
 
 public void swtichToFrame(int id) 
 {
	driver.switchTo().frame(id);
 }
 
 public void switchToParentFrame() 
 {
	driver.switchTo().defaultContent();
}
 
 public void switchToFrame(String name) 
 {
	 driver.switchTo().frame(name);
}
 
 public void switchToFrame(WebElement  element) 
 {
	driver.switchTo().frame(element);
}
	
}
