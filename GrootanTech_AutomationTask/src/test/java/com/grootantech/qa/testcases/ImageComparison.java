package com.grootantech.qa.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.grootantech.qa.base.BaseClass;
import com.grootantech.qa.pages.HomePage;
import com.grootantech.qa.utility.ExcelUtils;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ImageComparison extends BaseClass {

	int colNo, rowNo;
	String path = System.getProperty("user.dir") + "\\TestResult\\TestCases_Result.xlsx";
	String sheetName = "TSR";
	SoftAssert softassert;

	@BeforeTest
	public void setup() throws FileNotFoundException {
		initialization();
		setExtent();
	}

	@Test(priority = 1)
	public void compare() throws Exception {
		colNo = 2;
		rowNo = 2;
		test = extent.createTest("Image Comparison For Menu Screenshots");
		File screenShotFolder1 = new File(System.getProperty("user.dir") + "\\ScreenshotFolder1\\");
		File screenShotFolder2 = new File(System.getProperty("user.dir") + "\\ScreenshotFolder2\\");
		softassert = new SoftAssert();
		// List of all screenshots in the folder
		String imageList1[] = screenShotFolder1.list();
		String imageList2[] = screenShotFolder2.list();
		for (int i = 0; i < imageList1.length; i++) {
			if (imageList1[i].contentEquals(imageList2[i])) {
				BufferedImage expImg = ImageIO
						.read(new File(System.getProperty("user.dir") + "\\ScreenshotFolder1\\" + imageList1[i]));
				BufferedImage actualImg = ImageIO
						.read(new File(System.getProperty("user.dir") + "\\ScreenshotFolder2\\" + imageList2[i]));

				ImageDiffer imagediff = new ImageDiffer();
				ImageDiff diff = imagediff.makeDiff(expImg, actualImg);
				System.out.println(diff.hasDiff());
				softassert.assertTrue(diff.hasDiff() == false);
			}

		}

	}

	@Test(priority = 2)
	public void teamsImageComparison() throws Exception {
		colNo = 2;
		rowNo = 3;
		test = extent.createTest("Image Comparison For Teams Screenshots");
		Thread.sleep(5000);
		HomePage.cookies().click();
		Thread.sleep(1000);
		HomePage.teamMenu().click();
		Thread.sleep(10000);
		Screenshot logoCTO = new AShot().takeScreenshot(driver, HomePage.teamCTOCOFounder());
		Screenshot logoHR = new AShot().takeScreenshot(driver, HomePage.teamHRManager());

		BufferedImage ctoImg = logoCTO.getImage();
		BufferedImage hrImg = logoHR.getImage();
		ImageDiffer imagediff = new ImageDiffer();
		ImageDiff diff = imagediff.makeDiff(ctoImg, hrImg);
		softassert = new SoftAssert();
		softassert.assertTrue(diff.hasDiff() == true);

	}

	@Test(priority = 3)
	public void junioreng() throws Exception {
		colNo = 2;
		rowNo = 4;
		test = extent.createTest("Entering Junior Engineers in Excel Sheet");
		List<WebElement> je = driver.findElements(By.xpath("//h5[text()='Junior Engineer']//preceding-sibling::h3"));
		ExcelUtils.setExcelFile(System.getProperty("user.dir") + "\\TestResult\\TestCases_Result.xlsx",
				"JuniorEngineers");

		for (int i = 0; i < je.size(); i++) {
			ExcelUtils.setCellData((je.get(i).getText()), i, 0);

		}
		softassert = new SoftAssert();
		softassert.assertTrue(true);

	}

	@AfterMethod
	@Parameters("folderName3")
	public void reportStaus(ITestResult result, String folderName) throws Exception {
		ExcelUtils.setExcelFile(path, sheetName);
		getResult(result, colNo, rowNo, folderName);

	}

	@AfterTest
	public void endReport() throws IOException {
		extent.flush();
	}
}
