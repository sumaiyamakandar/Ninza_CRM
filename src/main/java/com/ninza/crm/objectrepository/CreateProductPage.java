package com.ninza.crm.objectrepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class CreateProductPage {
	WebDriver driver;
	public CreateProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	@FindBy(name="Products")
	private WebElement productsLink;
	
	@FindBy(name="productName")
	private WebElement productsName;
	
	@FindBy(name="quantity")
	private WebElement quantity;
	
	@FindBy(name="price")
	private WebElement price;
	
	@FindBy(name="productCategory")
	private WebElement productCategory;
	
	@FindBy(name="vendorId")
	private WebElement vendorId;
	
	@FindBy(name="AddProductBtn")
	private WebElement AddProductBtn;

	public WebElement getProductsName() {
		return productsName;
	}


	public WebElement getQuantity() {
		return quantity;
	}


	public WebElement getPrice() {
		return price;
	}


	public WebElement getProductCategory() {
		return productCategory;
	}


	public WebElement getVendorId() {
		return vendorId;
	}
	

	
	public WebElement getProductsLink() {
		return productsLink;
	}
	public WebElement AddProductBtn() {
		return AddProductBtn;
	}
}
