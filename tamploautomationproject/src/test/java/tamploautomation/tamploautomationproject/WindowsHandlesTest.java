package tamploautomation.tamploautomationproject;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ityss.tamplo.pageobject.LoginPageObject;

public class WindowsHandlesTest
{
	
	 WebDriver driver;
	 String Path = System.getProperty("user.dir");

	@Test
	public void windowTestOne() throws IOException, InterruptedException, InstantiationException, IllegalAccessException
	{
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\eclipse-workspace\\tamploNewPageObjectModel\\tamploautomationproject\\src\\main\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://122.179.158.177:8888/tamplo.5.0.0/landing/#!/home");
		LoginPageObject loginobject = new LoginPageObject(driver);
		
		loginobject.login("nitin.tajane@yahoo.com", "1234");
		driver.findElement(By.xpath("html/body/div[1]/div[1]/header/div/div[3]/nav/ul/li[3]/a")).click();
		
		Thread.sleep(1000);
		
		Actions action=new Actions(driver);
		action.keyDown(Keys.CONTROL).perform();
		driver.findElement(By.xpath("//span[contains(text(),'test 3')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'test 2')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'test 1')]")).click();
		action.keyUp(Keys.CONTROL).build().perform();
		
				 
		}
		
		
	

}
