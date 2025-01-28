package com.comcast.crm.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage1;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage1;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.LoginPage1;

public class CreateContactsTest1 {

		public static void main(String[] args) throws Exception {
			  
			//Create object
			FileUtility fLib=new FileUtility();
			ExcelUtility eLib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wLib=new WebDriverUtility();
			
			  //Read data from properties file using file utility classes
			  String URL = fLib.getDatafromPropertiesFile("url");
			  String BROWSER = fLib.getDatafromPropertiesFile("browser");
			  String UN = fLib.getDatafromPropertiesFile("username");
			  String PWD = fLib.getDatafromPropertiesFile("password");
						  
						  //Read test script data from Excel
						  String lastName = eLib.getdatafromexcel("Contact", 1, 2) + jLib.getrandomnumber();
						  
						  WebDriver driver = null;
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
						  wLib.waitForPageToLoad(driver);
						  driver.get(URL);
						  wLib.maximizeWindow(driver);
						  LoginPage1 lp=new LoginPage1(driver);
						  lp.getUsernameEdt().sendKeys(UN);
						  lp.getPasswordEdt().sendKeys(PWD);
						  lp.getLoginBtn().click();
						  
						  //Step 2: Navigate to the Contacts module
						  HomePage1 hp=new HomePage1(driver);
						  hp.getContactsLink().click();
						  
						  //Step 3: Click on the Create Contacts button
						  ContactsPage1 cp=new ContactsPage1(driver);
						  cp.getCreateContactBtn().click();
						  
						  //Step 4: Enter all the details & create new contact
						  CreatingNewContactPage1 cn=new CreatingNewContactPage1(driver);
						  cn.getLastName().sendKeys(lastName);
						  
						  cn.getSaveBtn().click();
						  
						  //Verify last name contact
						  ContactInformationPage cIP=new ContactInformationPage(driver);
						  String actLastName = cIP.getActLastName().getText();
						  System.out.println(actLastName);
						
						  if(actLastName.equals(lastName)) {
							  System.out.println(lastName + " name is verified. Test PASS");
						  }else
						  {
							  System.out.println(lastName + " name is not verified. Test FAIL");
						  }
						  
						  //Step 5: Log out
						  
						  hp.signOut(driver);
						  /*
							 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
							 * wLib.mouseMoveOnElement(driver,
							 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
							 * driver.findElement(By.linkText("Sign Out")).click();
							 */
						  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
						
						  driver.quit();
		}
}

