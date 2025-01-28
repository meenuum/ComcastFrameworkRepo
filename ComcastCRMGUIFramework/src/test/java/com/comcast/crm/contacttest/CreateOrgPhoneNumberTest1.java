package com.comcast.crm.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage1;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.LoginPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage1;

public class CreateOrgPhoneNumberTest1 {

		public static void main(String[] args) throws Exception {
			//Create object
					FileUtility fLib=new FileUtility();
					ExcelUtility eLib=new ExcelUtility();
					JavaUtility jLib=new JavaUtility();
					WebDriverUtility wLib=new WebDriverUtility();
					
			WebDriver driver = null;
			  //Read data from properties file using file utility classes
			  String URL = fLib.getDatafromPropertiesFile("url");
			  String BROWSER = fLib.getDatafromPropertiesFile("browser");
			  String UN = fLib.getDatafromPropertiesFile("username");
			  String PWD = fLib.getDatafromPropertiesFile("password");
			  
			  //Read test script data from Excel
			  String orgName = eLib.getdatafromexcel("Org", 7, 2) + jLib.getrandomnumber();
			  String phoneNumber = eLib.getdatafromexcel("Org", 7, 3).toString();
			  
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
			  //Step 1: Login to the app
			  driver.get(URL);
			  
			  wLib.maximizeWindow(driver);
			  wLib.waitForPageToLoad(driver);
			  LoginPage1 lp=new LoginPage1(driver);
			  lp.loginToApp( PWD, URL);
			  
			  //Step 2: Navigate to the Org module
			  HomePage1 hp=new HomePage1(driver);
			  hp.getorgLink().click();
			  //driver.findElement(By.xpath("//a[.='Organizations']")).click();
			  
			  //Step 3: Click on the Create Org button
			  OrganizationsPage1 op=new OrganizationsPage1(driver);
			  op.getCreateOrgBtn().click();
			  //driver.findElement(By.xpath("//img[@title='Create Organization...\']")).click();
			  
			  //Step 4: Enter the org name and the phone number, and create new Org
			  CreateNewOrganizationPage1 cOP=new CreateNewOrganizationPage1(driver);
			  cOP.getOrgNameField().sendKeys(orgName);
			  //driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
			  cOP.getOrgPhoneNoField().sendKeys(phoneNumber);
			  //driver.findElement(By.id("phone")).sendKeys(phoneNumber);
			  
			  cOP.getsaveBtn().click();
			  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			  
			  //Verify the phone number
			  OrganizationInfoPage1 oIP=new OrganizationInfoPage1(driver);
			  String actPhoneNumber = oIP.getPhoneInfo().getText();
			  //String actPhoneNumber = driver.findElement(By.xpath("//span[@id='dtlview_Phone\']")).getText();
			  System.out.println(actPhoneNumber);
			  if(actPhoneNumber.equals(phoneNumber)) {
				  System.out.println(phoneNumber + " is verified. Test PASS");
			  }else
			  {
				  System.out.println(phoneNumber + " is not verified. Test FAIL");
			  }
			  
			  //Step 5: Log out
			  hp.signOut(driver);
			  
			  /*
				 * WebElement
				 * ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"
				 * )); wLib.mouseMoveOnElement(driver, ele);
				 * driver.findElement(By.linkText("Sign Out")).click();
				 */
			  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
			
			  driver.quit();
		}
	}


