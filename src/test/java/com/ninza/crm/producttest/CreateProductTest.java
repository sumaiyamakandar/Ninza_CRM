package com.ninza.crm.producttest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ninza.crm.generic.WebDriverUtility.JavaUtility;
import com.ninza.crm.generic.WebDriverUtility.WebDriverUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.ProductPage;

import baseclass.baseclass;


public class CreateProductTest extends baseclass {
	@Test (groups= {"Smoke","Regression"})
	public void CreateProductWithMandatoryFieldTest() throws InterruptedException, EncryptedDocumentException, IOException {

		
		int ranNum=jlib.getRandomNumber();
		
		String ProductName =ex.ReadTheDataFromExcelproduct("Sheet1", 1, 1);
		String SelectCategory =ex.ReadTheDataFromExcelproduct("Sheet1", 1, 2);
		String Quantity =ex.ReadTheDataFromExcelproduct("Sheet1", 1, 3);
		String PricePerUnit =ex.ReadTheDataFromExcelproduct("Sheet1", 1, 4);
		String SelectVendor =ex.ReadTheDataFromExcelproduct("Sheet1", 1, 5);
		
		
		
		   HomePage hp=new HomePage(driver);
		   hp.getProductsLink().click();
		  
			
			driver.findElement(By.xpath("//a[@href='/products']")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@name='productName']")).sendKeys(ProductName+jlib.getRandomNumber());
			
			WebElement ProductCategory = driver.findElement(By.xpath("//select[@name='productCategory']"));
			Select Products=new Select(ProductCategory);
			Products.selectByVisibleText(SelectCategory);
			
			driver.findElement(By.xpath("//input[@name='quantity']")).clear();
			driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys(Quantity);
			Thread.sleep(4000);
			
			driver.findElement(By.xpath("//input[@name='price']")).clear();
			driver.findElement(By.xpath("//input[@name='price']")).sendKeys(PricePerUnit);
			
			WebElement VendorId = driver.findElement(By.xpath("//select[@name='vendorId']"));
			VendorId.click();
			Select Vendors=new Select(VendorId);
			 Vendors.selectByIndex(2);
			 
			 driver.findElement(By.xpath("//button[@type='submit']")).click();
			 Thread.sleep(1000);
			 
			 WebElement toastMsg = hp.getToastMsg();

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOf(toastMsg));

				String msg = toastMsg.getText();

				Assert.assertTrue(msg.contains("Successfully Added"));
				hp.getCloseToastMsgBtn();
				driver.quit();
			
			//write data back to excel
			 
		/* Cell c = wb.getSheet("Sheet1").getRow(1).createCell(6);
			c.setCellType(CellType.STRING);
			c.setCellValue(msg);
			
			FileOutputStream fs2=new FileOutputStream("./Resources/Book2.xlsx");	
			wb.write(fs2);
			wb.close();*/
			// WebElement icon =driver.findElement(By.xpath("//div[@class='user-icon']"));
				//wlib.ClickOnWebelement(driver, icon);
				
}

}
