package com.ninza.crm.campaigntest;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
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

import com.ninza.crm.generic.WebDriverUtility.JavaUtility;
import com.ninza.crm.generic.WebDriverUtility.WebDriverUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;



public class CreateCampaignWithExpectedcloseddateTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		PropertyFileUtility pf =new PropertyFileUtility();
		String BROWSER=pf.toGetDataFromPropertiesFile("browser");
		String URL=pf.toGetDataFromPropertiesFile("url");
		String USERNAME=pf.toGetDataFromPropertiesFile("username");
		String PASSWORD=pf.toGetDataFromPropertiesFile("password");
		
		
		ExcelFileUtility ex=new ExcelFileUtility();
		String CampaignName =ex.toReadTheDataFromExcel("Campaign", 1, 1);
		String targetSize =ex.toReadTheDataFromExcel("Campaign", 1, 2);
		String Expectedclosedate=ex.toReadTheDataFromExcel("Campaign", 1, 3);
		String CampaignStatus=ex.toReadTheDataFromExcel("Campaign", 1, 4);
		
		WebDriverUtility wlib=new WebDriverUtility();
		JavaUtility jlib=new JavaUtility();
		
		ChromeOptions settings = new ChromeOptions();
		
		Map<String, Object> prefs = new HashMap<>();
	   prefs.put("profile.password_manager_leak_detection", false);
	   settings.setExperimentalOption("prefs", prefs);
	   
	  // WebDriver driver = new ChromeDriver(settings);
		//Launch the browser
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//navigate to Ninza crm
		driver.get(URL);
		
		//Login into the Ninza crm
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(6000);

		//click on campaign link
		driver.findElement(By.linkText("Campaigns")).click();
		
		//Click on Create Campaign button
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("campaignName")).sendKeys(CampaignName);
		driver.findElement(By.name("campaignStatus")).sendKeys(CampaignStatus);
		Thread.sleep(2000);
		WebElement targSize = driver.findElement(By.name("targetSize"));
		targSize.clear();
		targSize.sendKeys(targetSize);
		Thread.sleep(2000);
		
		driver.findElement(By.name("expectedCloseDate")).sendKeys(jlib.getRequireDate(30));
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		
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
