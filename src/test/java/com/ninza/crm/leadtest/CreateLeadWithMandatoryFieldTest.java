package com.ninza.crm.leadtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ninza.crm.generic.WebDriverUtility.JavaUtility;
import com.ninza.crm.generic.WebDriverUtility.WebDriverUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;


public class CreateLeadWithMandatoryFieldTest {
	public static void main(String[] args) throws InterruptedException, IOException {
		PropertyFileUtility pf =new PropertyFileUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		JavaUtility jlib=new JavaUtility();
		//ExcelFileUtility ex=new ExcelFileUtility();
		
		
		String BROWSER=pf.toGetDataFromPropertiesFile("browser");
		String URL=pf.toGetDataFromPropertiesFile("url");
		String USERNAME=pf.toGetDataFromPropertiesFile("username");
		String PASSWORD=pf.toGetDataFromPropertiesFile("password");
		
		
		ExcelFileUtility ex =new ExcelFileUtility();
		String LeadName =ex.ReadTheDataFromExcelLead("Sheet1", 1, 1);
		String Company=ex.ReadTheDataFromExcelLead("Sheet1", 1, 2);
		String LeadSource =ex.ReadTheDataFromExcelLead("Sheet1", 1, 3);
		String Industry =ex.ReadTheDataFromExcelLead("Sheet1", 1, 4);
		String Phone =ex.ReadTheDataFromExcelLead("Sheet1", 1, 5);
		String LeadStatus =ex.ReadTheDataFromExcelLead("Sheet1", 1, 6);
		String  Campaign  =ex.ReadTheDataFromExcelLead("Sheet1", 1, 7);
		
		
	
		ChromeOptions settings = new ChromeOptions();
		
		Map<String, Object> prefs = new HashMap<>();
	   prefs.put("profile.password_manager_leak_detection", false);
	   settings.setExperimentalOption("prefs", prefs);
		
		 WebDriver driver =null;
		   if(BROWSER.equals("edge")) {
			    driver = new EdgeDriver();
		   }else if(BROWSER.equals("chrome")) {
			    driver = new ChromeDriver(settings);
		   }else if(BROWSER.equals("firefox")) {
			    driver = new FirefoxDriver();
		   }
		   
			//maximize the browser
			driver.manage().window().maximize();
			wlib.waitforPageToLoad(driver);

			//implicit wait
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			
			//navigate to Ninza crm
			driver.get(URL);
			
			//Login into the Ninza crm
			driver.findElement(By.id("username")).sendKeys(USERNAME);
			driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
			
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(6000);
			driver.findElement(By.xpath("//a[text()='Leads']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[text()='Create Lead']")).click();
			
			driver.findElement(By.name("name")).sendKeys(LeadName+jlib.getRandomNumber());
			Thread.sleep(2000);
			driver.findElement(By.name("company")).sendKeys(Company);
			Thread.sleep(2000);
			driver.findElement(By.name("leadSource")).sendKeys(LeadSource);
			Thread.sleep(2000);
			driver.findElement(By.name("industry")).sendKeys(Industry);
			Thread.sleep(2000);
			driver.findElement(By.name("phone")).sendKeys(Phone);
			Thread.sleep(2000);
			driver.findElement(By.name("leadStatus")).sendKeys(LeadStatus);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Campaign']/..//button[@type='button']")).click();
			
			String parentid=driver.getWindowHandle();
			Set<String> allids=driver.getWindowHandles();
			allids.remove(parentid);
			for(String string :allids) {
				driver.switchTo().window(string);
			}
			driver.findElement(By.xpath("//td[text()='Campaigntest']/..//button[@class='select-btn']")).click();
			driver.switchTo().window(parentid);
			
			driver.findElement(By.xpath("//button[text()='Create Lead']")).click();
			Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
			
			driver.findElement(By.xpath("//td[text()='"+LeadName+"']/..//i[@title='Delete']"));
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("//input[@value='Delete'")).click();
			driver.close();
	}

}
