package com.ityss.tamplo.helper.resources;

public class ResourceHelper 
{
	public static String getResourcePath(String path) 
	{
		String actualpath = System.getProperty("user.dir");
		 
		return actualpath +"/"+ path;
	}

}
