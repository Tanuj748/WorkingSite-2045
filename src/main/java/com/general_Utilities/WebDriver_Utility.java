package com.general_Utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class WebDriver_Utility 
{

	public static void takesscreenshotOfWebPage(WebDriver driver , String screenshotName) throws IOException
	{
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/" + "screenshotName" + " .png");
		
		try {
			FileHandler.copy(src, dest);
		    }
		
	       catch(IOException e)
	       {
		      e.printStackTrace();
	       }
     }
	
	
	
	public static String getBase64Screenshot(WebDriver driver)
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
		return base64Screenshot ;
	}


}








