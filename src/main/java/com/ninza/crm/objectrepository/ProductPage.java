package com.ninza.crm.objectrepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ProductPage {
	WebDriver driver;
	public ProductPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//span[text()='Add Product']")
	private WebElement createPage;
	public WebElement getCreatePage() {
	return createPage;
	}
	public WebElement getSuccessMsg() {
	return successMsg;
	}
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement successMsg;
	}
	


