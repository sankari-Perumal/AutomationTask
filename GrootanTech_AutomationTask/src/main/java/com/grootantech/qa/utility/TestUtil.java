package com.grootantech.qa.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.grootantech.qa.base.BaseClass;

public class TestUtil extends BaseClass{
	
	public static String takeScreenShot(String fName,String folderName) throws IOException
	{
	
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
		String fileName= fName+".png";
		File dest = new File(System.getProperty("user.dir")+"\\"+folderName);
		String fullPath=dest+"\\"+fileName;
		if(!dest.exists())
		{
			dest.mkdir();
		}
		
		if(dest.exists())
		{
			File destFileName=new File(fullPath);
			FileUtils.copyFile(src,destFileName);
		}
		return fullPath;
	}
}
