package baseclass;

import org.testng.annotations.Test;

import com.ninza.crm.generic.WebDriverUtility.JavaUtility;
import com.ninza.crm.generic.WebDriverUtility.WebDriverUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class baseclass {
	public PropertyFileUtility pf =new PropertyFileUtility();
	public ExcelFileUtility ex=new ExcelFileUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public  WebDriver driver =null;
	public JavaUtility jlib=new JavaUtility();
	public HomePage hp = new HomePage(driver);
	public static WebDriver sdriver =null;
 
	@BeforeMethod(groups= {"Smoke","Regression"})
	  public void beforeMethod() throws IOException {
		String BROWSER=pf.toGetDataFromPropertiesFile("browser");
		//String BROWSER=System.getProperty("Browser");
	String URL=pf.toGetDataFromPropertiesFile("url");
	String USERNAME=pf.toGetDataFromPropertiesFile("username");
	String PASSWORD=pf.toGetDataFromPropertiesFile("password");
	//	String URL=System.getProperty("URL");
	//	String USERNAME=System.getProperty("username");
	//	String PASSWORD=System.getProperty("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PASSWORD);
		  System.out.println("Login to the application");
	  }

	  @AfterMethod(groups= {"Smoke","Regression"})
	  public void afterMethod() {
		  System.out.println("Logout");
		  HomePage hp=new HomePage(driver);
		
		 
	  }
  //   @Parameters("Browser")
	  @BeforeClass(groups= {"Smoke","Regression"})
	  public void beforeClass() throws IOException, InterruptedException {
		String BROWSER=pf.toGetDataFromPropertiesFile("browser");
		  
		  ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
		   prefs.put("profile.password_manager_leak_detection", false);
		   settings.setExperimentalOption("prefs", prefs);
		   
		 //  WebDriverManager.chromedriver().setup();
		  //WebDriverManager.edgedriver().setup();
		  if(BROWSER.equalsIgnoreCase("edge")) {
			    driver = new EdgeDriver();
		   }else if(BROWSER.equalsIgnoreCase("chrome")) {
			    driver = new ChromeDriver(settings);
		   }else if(BROWSER.equalsIgnoreCase("firefox")) {
			    driver = new FirefoxDriver();
		   }
		  Thread.sleep(2000);
		  System.out.println("launch the browser");
		  sdriver=driver;
	  }

	  @AfterClass(groups= {"Smoke","Regression"})
	  public void afterClass() {
		  System.out.println("close the browser");
		  driver.quit();
	  }

	  @BeforeTest(groups= {"Smoke","Regression"})
	  public void beforeTest() {
		  System.out.println("pre-conditions for parallel executions");
	  }

	  @AfterTest(groups= {"Smoke","Regression"})
	  public void afterTest() {
		  System.out.println("post-conditions for parallel executions");
	  }

	  @BeforeSuite(groups= {"Smoke","Regression"})
	  public void beforeSuite() {
		  System.out.println("connect to database");
	  }

	  @AfterSuite(groups= {"Smoke","Regression"})
	  public void afterSuite() {
		  System.out.println("disconnect to database");
	  }

}
