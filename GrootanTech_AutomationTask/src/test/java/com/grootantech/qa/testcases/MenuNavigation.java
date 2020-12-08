package com.grootantech.qa.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.grootantech.qa.base.BaseClass;
import com.grootantech.qa.pages.HomePage;
import com.grootantech.qa.utility.ExcelUtils;
import com.grootantech.qa.utility.TestUtil;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class MenuNavigation extends BaseClass {

	String fileName;
	String headerName;
	String folder;
	String path="C:\\Users\\New\\eclipse-workspace\\Grootan\\GrootanTech_Automation\\TestResult\\TestCases.xlsx";
	String sheetName="TSR";
	int colNo;
	int rowNo;
	public MenuNavigation() {
		super();
	}

	@BeforeTest
	public void setup() throws FileNotFoundException {
		initialization();
		setExtent();
	}
	
	@Test
	@Parameters("folderName1")
	public void menuNNavigation(String scrnFolderName) throws Exception {
		colNo=2;
		rowNo=1;
		String folderName=scrnFolderName;
		test = extent.createTest("Menu Navigation for Grootan Technologies");	
		Thread.sleep(15000);
		// To Allow Cookies
		HomePage.cookies().click();

		// To Close the Chat Box
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='drift-frame-controller']")));
		HomePage.closeChat().click();

		driver.switchTo().parentFrame();

		// Home Menu
		headerName = HomePage.homeMenu().getText();
		HomePage.homeMenu().click();		
		//new Actions(driver).sendKeys(org.openqa.selenium.Keys.DOWN).perform();
		driver.findElement(By.xpath("//div[@class='swiper-pagination swiper-pagination-clickable swiper-pagination-bullets']/span[3]")).click();
		Thread.sleep(2000);
		screenshots(folderName);
		//test.createNode("Screenshots taken for Home Menu");


		// To Navigate to Services Menu and Take Screen Shot
		headerName = HomePage.servicesMenu().getText();
		HomePage.servicesMenu().click();
		Thread.sleep(3500);
		screenshots(folderName);
		//test.createNode("Screenshots taken for Services Menu");

		// To Navigate to OpenSources Menu and Take Screen Shot
		headerName = HomePage.openSourceMenu().getText();
		HomePage.openSourceMenu().click();
		Thread.sleep(3000);
		screenshots(folderName);
		//test.createNode("Screenshots taken for openSources Menu");

		// To Navigate to Team Menu and Take Screen Shot
		headerName = HomePage.teamMenu().getText();
		HomePage.teamMenu().click();
		Thread.sleep(15000);
		screenshots(folderName);
		//test.createNode("Screenshots taken for Team Menu");
		

		// To Navigate to Careers Menu and Take Screen Shot
		headerName = HomePage.careersMenu().getText();
		HomePage.careersMenu().click();
		Thread.sleep(3000);
		screenshots(folderName);
		//test.createNode("Screenshots taken for Careers Menu");

		// To Navigate to Careers Menu and Take Screen Shot
		headerName = HomePage.contactusMenu().getText();
		HomePage.contactusMenu().click();
		Thread.sleep(3000);
		screenshots(folderName);
//		Thread.sleep(1000);
		//test.createNode("Screenshots taken for contactus Menu");

		// To Navigate to Blog Menu and Take Screen Shot
		headerName = HomePage.blogMenu().getText();
		HomePage.blogMenu().click();
		Thread.sleep(7000);
		screenshots(folderName);
		//test.createNode("Screenshots taken for Blog Menu");
		
		Assert.assertTrue(true);

	}

	public void screenshots(String folderName) throws IOException, InterruptedException {
		
//		Thread.sleep(4000);
		String fileName = headerName;
		String folder=folderName;
		TestUtil.takeScreenShot(fileName,folder);
	}


	@AfterMethod
	@Parameters("folderName3")
	public void reportStaus(ITestResult result,String folderName) throws Exception
	{
		
		ExcelUtils.setExcelFile(path,sheetName);
		getResult(result,colNo,rowNo,folderName);
		
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
}
