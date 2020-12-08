package com.grootantech.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.grootantech.qa.base.BaseClass;

public class HomePage extends BaseClass {

	private static WebElement element = null;

	public static WebElement homeMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[(@href='/') and (text()='Home')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement servicesMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//a[(@href='/#built-tech') and (text()='Services')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement openSourceMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[(@href='/opensource') and (text()='Open Source')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement blogMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[(@href='https://blog.grootan.com') and (text()='Blog')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement teamMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[(@href='/team') and (text()='Team')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement careersMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[(@href='/careers') and (text()='Careers')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement contactusMenu() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[(@href='/contactus') and (text()='Contact Us')]"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement teamCTOCOFounder() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@src='img/testimonials/lokesh.jpg']"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement teamHRManager() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@src='img/testimonials/sasi.jpg']"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement closeChat() throws Exception {
		try {
			element = driver.findElement(By.xpath("/html/body/div/main/button/div"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

	public static WebElement cookies() throws Exception {
		try {
			element = driver.findElement(By.xpath("//button[text()='Allow Cookies']"));
		} catch (Exception e) {
			throw (e);
		}
		return element;
	}

}
