package com.grootantech.qa.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.testng.Assert;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.grootantech.qa.base.BaseClass;
import com.grootantech.qa.pages.HomePage;
import com.grootantech.qa.utility.ExcelUtils;
import com.grootantech.qa.utility.TestUtil;

public class MenuNavigation extends BaseClass {

	String fileName;
	String headerName;
	String folder;
	String path = System.getProperty("user.dir") + "\\TestResult\\TestCases.xlsx";
	String sheetName = "TSR";
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
		colNo = 2;
		rowNo = 1;
		String folderName = scrnFolderName;
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
		driver.findElement(By.xpath(
				"//div[@class='swiper-pagination swiper-pagination-clickable swiper-pagination-bullets']/span[3]"))
				.click();
		Thread.sleep(1000);

		// To Navigate to Services Menu and Take Screen Shot
		headerName = HomePage.servicesMenu().getText();
		HomePage.servicesMenu().click();
		Thread.sleep(3500);
		screenshots(folderName);

		// To Navigate to OpenSources Menu and Take Screen Shot
		headerName = HomePage.openSourceMenu().getText();
		HomePage.openSourceMenu().click();
		Thread.sleep(3000);
		screenshots(folderName);

		// To Navigate to Team Menu and Take Screen Shot
		headerName = HomePage.teamMenu().getText();
		HomePage.teamMenu().click();
		Thread.sleep(15000);
		screenshots(folderName);

		// To Navigate to Careers Menu and Take Screen Shot
		headerName = HomePage.careersMenu().getText();
		HomePage.careersMenu().click();
		Thread.sleep(3000);
		screenshots(folderName);

		// To Navigate to Careers Menu and Take Screen Shot
		headerName = HomePage.contactusMenu().getText();
		HomePage.contactusMenu().click();
		Thread.sleep(3000);
		screenshots(folderName);

		// To Navigate to Blog Menu and Take Screen Shot
		headerName = HomePage.blogMenu().getText();
		HomePage.blogMenu().click();
		Thread.sleep(10000);
		screenshots(folderName);

		Assert.assertTrue(true);

	}

	public void screenshots(String folderName) throws IOException, InterruptedException {

		String fileName = headerName;
		String folder = folderName;
		TestUtil.takeScreenShot(fileName, folder);
	}

	@AfterMethod
	@Parameters("folderName3")
	public void reportStaus(ITestResult result, String folderName) throws Exception {

		ExcelUtils.setExcelFile(path, sheetName);
		// To generate Excel and Create HTML report
		getResult(result, colNo, rowNo, folderName);

	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}
}
