package com.ninza.crm.campaigntest;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ninza.crm.generic.WebDriverUtility.WebDriverUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.objectrepository.CampaignPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;


public class CreateCampaignWithMandatoryFieldTest {
	@Test
	public void createCampaignWithMandatoryFieldTest() throws InterruptedException, IOException {
		//FileInputStream	fs=new FileInputStream("./Resources/commonData2.properties.txt");
		
		
		
		PropertyFileUtility pf = new PropertyFileUtility();
		// get the values
		String BROWSER = 
		pf.toGetDataFromPropertiesFile("browser");
		
		String URL = pf.toGetDataFromPropertiesFile("url");
		String USERNAME = 
		pf.toGetDataFromPropertiesFile("username");
		String PASSWORD = 
		pf.toGetDataFromPropertiesFile("password");
		// excel file
		// Step 1. create the object of excel file utility
		ExcelFileUtility ex = new ExcelFileUtility();
		String CampaignName =ex.toReadTheDataFromExcel("Campaign", 1, 1);
		String targetSize =ex.toReadTheDataFromExcel("Campaign", 1, 2);
		
		
		WebDriver driver = null;
		// launch the browser
		if (BROWSER.equals("edge")) {
		driver = new EdgeDriver();
		} else if (BROWSER.equals("chrome")) {
		driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
		driver = new FirefoxDriver();
		}
		// Navigate and Login in to the application
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PASSWORD);
		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();
		// Click on create campaign
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaign().click();
		// Create campaign with Mandatory fields
		CreateCampaignPage ccp = new
		CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(CampaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);
		ccp.getCreateButton().click();
		// Verify the succesfull message
	/*	WebElement toastMsg = cp.getSuccessMsg();
		WebDriverWait wait = new WebDriverWait(driver, 
		Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
		if (msg.contains("Successfully Added")) {
		System.out.println("Campign created successfully");
		} else {
		System.out.println("Campign is not created");
		}*/

		
	   
	  // WebDriver driver = new ChromeDriver(settings);
		//Launch the browser
	  
	  // System.setProperty("webdriver.edge.driver","C:\\Users\\Lenovo\\Downloads");
	   
		
		/*//maximize the browser
		driver.manage().window().maximize();
		wlib.waitforPageToLoad(driver);
		//implicit wait
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//navigate to Ninza crm
		driver.get(URL);
		
		//Login into the Ninza crm
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(6000);*/
	   
	   
		
		
		
		
		WebDriverUtility wlib=new WebDriverUtility();
		
		//Verify the successful message
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		wlib.waitforVisibilityOfElement(driver, toastMsg);
		String msg = toastMsg.getText();
		if(msg.contains("Avengers")) {
			System.out.println("Campaign created successfully");
		} else {
			System.out.println("Campaign not created");
		}
		WebElement icon =driver.findElement(By.xpath("//div[@class='user-icon']"));
		wlib.ClickOnWebelement(driver, icon);
		driver.quit();
		

	}
	
	
}
