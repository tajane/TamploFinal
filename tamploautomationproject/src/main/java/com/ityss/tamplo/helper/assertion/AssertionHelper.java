package com.ityss.tamplo.helper.assertion;

import org.testng.Assert;

public class AssertionHelper 
{

	public void verifyText(String actual,String expected) 
	{
		Assert.assertEquals(actual, expected);
	}
	
	public void verifyTrue(boolean status) 
	{
	  Assert.assertTrue(status);	
	}
	
	public void verifyFalse(boolean status) 
	{
		Assert.assertFalse(status);
	}
	
}
