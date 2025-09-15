package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	

	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	@FindBy(linkText="Contacts")
	private WebElement ContactsLink;
	@FindBy(linkText="Leads")
	private WebElement LeadsLink;
	@FindBy(linkText="opportunities")
	private WebElement opportunitiesLink;
	@FindBy(linkText="Products")
	private WebElement productsLink;
	@FindBy(linkText="Quotes")
	private WebElement QuotesLink;
	@FindBy(linkText="Purchase Order")
	private WebElement purchaseOrderLink;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement toastMsg;
	@FindBy(xpath="//button[@aria-label='close']")
	private WebElement closeToastMsgBtn;
	
	@FindBy(className ="user-icon")
	private WebElement userIcon;
		
	@FindBy(xpath="//div[text()='Logout ']")
	private WebElement logoutBtn;
	
	
	public WebElement getToastMsg() {
		return toastMsg;
	}
	public WebElement getCloseToastMsgBtn() {
		return closeToastMsgBtn;
	}
	public WebElement getUserIcon() {
		return userIcon;
	}
	
	public WebElement getCampaignLink() {
		return campaignLink;
	}
	public WebElement getContactsLink() {
		return ContactsLink;
	}
	public WebElement getLeadsLink() {
		return LeadsLink;
	}
	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}
	public WebElement getProductsLink() {
		return productsLink;
	}
	public WebElement getQuotesLink() {
		return QuotesLink;
	}
	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}
	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
	
//public void logout() {
//	Actions action=new Actions(driver);
//	action.moveToElement(userIcon).perform();
//	action.moveToElement(logoutBtn).click().perform();
//	}
}
