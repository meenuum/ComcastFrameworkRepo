package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseutility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage1;

import com.comcast.crm.objectrepositoryutility.LoginPage1;

public class BaseClass {
	
	//create object
	 public DataBaseutility dblib=new DataBaseutility();
	 public WebDriverUtility wlib = new WebDriverUtility();
	 public FileUtility flib= new FileUtility();
	 public	ExcelUtility elib=new ExcelUtility();
	 public	JavaUtility jlib=new JavaUtility();
	 public WebDriver driver = null;
	 public static WebDriver sdriver = null;
	
	 
	
	@BeforeSuite
	public void configBS()
	{
		System.out.println("====connect to db,Report config=====");
		dblib.getDbconnection1();
		
	}
	
	@Parameters("BROWSER")
	@BeforeClass
	public void configBc() throws IOException
	{
		System.out.println(" ==Launch the Browser===");
		String BROWSER= flib.getDatafromPropertiesFile("browser");
		String URL = flib.getDatafromPropertiesFile("url");
		
		  if(BROWSER.equals("chrome"))
		  {
			  driver=new ChromeDriver();
		  }
		  else if(BROWSER.equals("firefox"))
		  {
			  driver=new FirefoxDriver();
		  }
		  else
		  {
			  driver=new ChromeDriver();
		  }
		  
		  sdriver = driver;
		  UtilityClassObject.setDriver(driver);
		  
		  
		  driver.get(URL);
		  wlib.waitForPageToLoad(driver);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		  driver.manage().window().maximize();
		  
	}
	
	@BeforeMethod
	public void configBM() throws IOException
	{
		
		System.out.println("===login===");
		//String URL= flib.getDatafromPropertiesFile("url");
		String USERNAME= flib.getDatafromPropertiesFile("username");
		String PASSWORD= flib.getDatafromPropertiesFile("password");
		
	     LoginPage1 lp=new LoginPage1(driver);
	     lp.loginToApp(USERNAME, PASSWORD);
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("===logout===");
		HomePage1 hp=new HomePage1(driver);
		hp.getSignoutlink();
		
	}
	
	@AfterClass
	public void configAC()
	{
		System.out.println(" ==== close the Browser===");
		
		driver.quit();
	}
	
	@AfterSuite
	public void configAS() throws SQLException
	{
		System.out.println("===close Db , Report backup====");
		dblib.closeDbconnection();
		
	}
	

}
