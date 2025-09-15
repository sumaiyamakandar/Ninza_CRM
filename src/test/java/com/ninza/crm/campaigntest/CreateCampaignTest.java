package com.ninza.crm.campaigntest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ninza.crm.generic.WebDriverUtility.JavaUtility;
import com.ninza.crm.generic.WebDriverUtility.WebDriverUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.objectrepository.CampaignPage;
import com.ninza.crm.objectrepository.CreateCampaignPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

import baseclass.baseclass;
@Listeners(com.ninza.crm.listenerutility.ListenerImplementation.class)
public class CreateCampaignTest extends baseclass{
	
		@Test(groups="Smoke")
		public void createCampaignWithMandatoryFieldTest() throws InterruptedException, IOException {
			//FileInputStream	fs=new FileInputStream("./Resources/commonData2.properties.txt");
			String CampaignName =ex.toReadTheDataFromExcel("Campaign", 1, 1);
			String targetSize =ex.toReadTheDataFromExcel("Campaign", 1, 2);
			
			// Click on campaign link in home page
			HomePage hp = new HomePage(driver);
			hp.getCampaignLink().click();
			// Click on create campaign
			CampaignPage cp = new CampaignPage(driver);
			cp.getCreateCampaign().click();
			// Create campaign with Mandatory fields
			CreateCampaignPage ccp = new CreateCampaignPage(driver);
			ccp.getCampaignName().sendKeys(CampaignName);
			ccp.getTargetSize().clear();
			ccp.getTargetSize().sendKeys(targetSize);
			ccp.getCreateButton().click();
			
			WebDriverUtility wlib=new WebDriverUtility();
			
			//Verify the successful message
			WebElement toastMsg = hp.getToastMsg();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(toastMsg));

			String msg = toastMsg.getText();
			System.out.println(msg);
			
		//	Assert.assertEquals(msg,"Campaign"+CampaignName+" Successfully Added","Both are not equal");
			Assert.assertTrue(msg.contains("Successfully Added"));
			hp.getCloseToastMsgBtn();
						
		}
		

	@Test(groups="Regression")
	public void createCampaignWithExpectedClosseDateTest() throws InterruptedException, IOException {
		
		String CampaignName =ex.toReadTheDataFromExcel("Campaign", 1, 1);
		String targetSize =ex.toReadTheDataFromExcel("Campaign", 1, 2);
		String Expectedclosedate=ex.toReadTheDataFromExcel("Campaign", 1, 3);
		//String CampaignStatus=ex.toReadTheDataFromExcel("Campaign", 1, 4);
		
		//click on campaign link
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();		
		//Click on Create Campaign button
		CampaignPage cp = new CampaignPage(driver);
		cp.getCreateCampaign().click();
		// Create campaign with Mandatory fields
		CreateCampaignPage ccp = new CreateCampaignPage(driver);
		ccp.getCampaignName().sendKeys(CampaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);
		String expectedDate=jlib.getRequireDate(30);
		ccp.getExpectedCloseDate().sendKeys(expectedDate);
		ccp.getCreateButton().click();
		
		
		//Verify the successful message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();
		Assert.assertTrue(msg.contains("Successfully Added"));
		hp.getCloseToastMsgBtn();
		}
	@Test (groups="Smoke")
	public void createCampaignWithCampaignStatusTest() throws InterruptedException, EncryptedDocumentException, IOException {

		String CampaignName =ex.toReadTheDataFromExcel("Campaign", 1, 1);
		String targetSize =ex.toReadTheDataFromExcel("Campaign", 1, 2);
		String campaignStatus=ex.toReadTheDataFromExcel("Campaign", 1, 4);
		
		//click on campaign link
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();
					
				//Click on Create Campaign button
				CampaignPage cp = new CampaignPage(driver);
				cp.getCreateCampaign().click();
				// Create campaign with Mandatory fields
				CreateCampaignPage ccp = new CreateCampaignPage(driver);
				ccp.getCampaignName().sendKeys(CampaignName);
				ccp.getTargetSize().clear();
				ccp.getTargetSize().sendKeys(targetSize);
				ccp.getCampaignStatus().sendKeys(campaignStatus);
				ccp.getCreateButton().click();
				
				
				//Verify the successful message
				WebElement toastMsg = hp.getToastMsg();

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOf(toastMsg));

				String msg = toastMsg.getText();
				Assert.assertTrue(msg.contains("Successfully Added"));
				hp.getCloseToastMsgBtn();
		
		

	}
}
