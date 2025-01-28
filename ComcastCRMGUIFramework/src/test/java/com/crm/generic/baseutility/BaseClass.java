package com.crm.generic.baseutility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseutility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.LoginPage1;

public class BaseClass {
	public 	DataBaseutility dBlib=new DataBaseutility();
	public 	FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public WebDriver driver=null;

	
	@BeforeSuite
	public void configBS() throws SQLException {
		System.out.println("====Connect to DB,Report Config====");
		dBlib.getDbconnection1();
	}
	
	@BeforeClass
	public void configBC() throws IOException {
		System.out.println("===Launch the Browser===");
		String BROWSER = flib.getDatafromPropertiesFile("browser");
		String URL=flib.getDatafromPropertiesFile("url");
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
		 driver.get(URL);
	}
	
	@BeforeMethod
	public void configBM() throws IOException
	{
		System.out.println("===login===");
		String URL= flib.getDatafromPropertiesFile("url");
		String USERNAME= flib.getDatafromPropertiesFile("username");
		String PASSWORD= flib.getDatafromPropertiesFile("password");
		LoginPage1 lp = new LoginPage1(driver);
		lp.loginToApp(USERNAME, PASSWORD);

	
	}
	@AfterMethod
	 public void configAM(){
	  	  System.out.println("===logout===");
	  	  HomePage1 hp=new HomePage1(driver);
	  	  hp.signOut(driver);
	}
	@AfterClass
	  public void configAC(){
		  System.out.println("==Close the Browser==");
		  driver.quit();
	  }
	
	@AfterSuite
	public void configAS() throws SQLException {
		System.out.println("===Close Db,Report backup====");
		dBlib.closeDbconnection();
	}
}
