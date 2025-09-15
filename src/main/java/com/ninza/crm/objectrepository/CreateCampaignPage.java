package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaignPage {

	
		WebDriver driver;
		public CreateCampaignPage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
			}
		@FindBy(name="campaignName")
		private WebElement campaignName;
		
		@FindBy(name="targetSize")
		private WebElement targetSize;
		
		@FindBy(name="expectedCloseDate")
		private WebElement expectedCloseDate;
		
		@FindBy(name="campaignStatus")
		private WebElement campaignStatus;
		
		@FindBy(xpath="//button[@type='submit']")
		private WebElement createButton;
		
		public WebElement getCampaignName() {
			return campaignName;
		}

		public WebElement getTargetSize() {
			return targetSize;
		}

		public WebElement getExpectedCloseDate() {
			return expectedCloseDate;
		}

		public WebElement getCreateButton() {
			return createButton;
		}
		public WebElement getCampaignStatus() {
			return campaignStatus;
		}
		
	}


