package com.grootantech.qa.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.grootantech.qa.utility.ExcelUtils;
import com.grootantech.qa.utility.TestUtil;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static  ExtentTest test;
	public static WebDriverWait wait; 

	public BaseClass() {
		String filePath= "\\src\\main\\java\\com\\grootantech\\qa\\config\\configurations.properties";
		readProperty(filePath);
	}

	public static void initialization() throws FileNotFoundException {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}

	public static void setExtent() {
		reporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/GrootanTech_Report.html");
		reporter.setAppendExisting(true);
		reporter.config().setDocumentTitle("AutomationReports");
		reporter.config().setReportName("Functional Report");
		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);

		extent.setSystemInfo("Hostname", "local");
		extent.setSystemInfo("Tester", "Sankari");
	}
	
	public void readProperty(String path)
	{
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ path);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void getResult(ITestResult result,int colNum, int rowNum,String folderName) throws Exception{
		String tempPath=TestUtil.takeScreenShot(result.getMethod().getMethodName(),folderName);
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, "Test Case Failed is "+result.getMethod().getMethodName());
			ExcelUtils.setCellData("Failed",rowNum, colNum );
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath(tempPath).build());
		}else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, "Test Case Skipped is "+result.getMethod().getMethodName());
			ExcelUtils.setCellData("Skipped",rowNum, colNum);
			test.skip("Skipped",MediaEntityBuilder.createScreenCaptureFromPath(tempPath).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, "Test Case SUCCESS is "+result.getMethod().getMethodName());
			ExcelUtils.setCellData("Passed", rowNum, colNum);
			test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath(tempPath).build());
		}

	}

}
