package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;

import com.comcast.crm.objectrepositoryutility.ContactsPage1;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage1;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage1;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.LoginPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage1;

public class CreateContactWithOrgTest1 {

		public static void main(String[] args) throws Exception {
			
			//Create object
			FileUtility fLib=new FileUtility();
			ExcelUtility eLib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wLib=new WebDriverUtility();
			
			
			WebDriver driver = null;
			
			 String URL = fLib.getDatafromPropertiesFile("url");
			  String BROWSER = fLib.getDatafromPropertiesFile("browser");
			String un = fLib.getDatafromPropertiesFile("username");
			String pwd = fLib.getDatafromPropertiesFile("password");
			  
			  //Read test script data from Excel
			String lastName = eLib.getdatafromexcel("Contact", 4, 2) + jLib.getrandomnumber();
			  String orgName = eLib.getdatafromexcel("Contact", 7, 2) + jLib.getrandomnumber();
				/*
				 * String orgName = eLib.getdatafromexcel("contact", 8, 1) +
				 * jLib.getrandomnumber(); String lastName = eLib.getdatafromexcel("contact", 7,
				 * 3);
				 */
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
			  wLib.maximizeWindow(driver);
			  wLib.waitForPageToLoad(driver);
			  driver.get(URL);
			  LoginPage1 lp=new LoginPage1(driver);
			  lp.loginToApp( un,pwd);
				/*
				 * driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
				 * driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pwd);
				 * driver.findElement(By.id("submitButton")).click();
				 */
			  
			  //Step 2: Navigate to the organization module
			  HomePage1 hp=new HomePage1(driver);
			  hp.getorgLink().click();
			  //driver.findElement(By.xpath("//a[.='Organizations']")).click();
			  
			  //Step 3: Click on the Create Organization button
			  OrganizationsPage1 op=new OrganizationsPage1(driver);
			  op.getCreateOrgBtn().click();
			  //driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			  
			  //Step 4: Enter all the details & create new organization
			  CreateNewOrganizationPage1 cOP=new CreateNewOrganizationPage1(driver);
			  cOP.getOrgNameField().sendKeys(orgName);
			  cOP.getsaveBtn().click();
				/*
				 * driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName
				 * ); driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				 */
			  
			  //Step 5: Navigate to Contacts module
				  for (int i = 0; i < 3; i++) { // Retry mechanism
				  try { 
					  WebElement contactsLink = driver.findElement(By.xpath("//a[.='Contacts']"));
					  contactsLink.click();
						break; // Exit loop if successful 
						} 
				  catch (StaleElementReferenceException e)
						  { 
							  System.out.println("Element went stale. Retrying..."); 
							  } 
				  }
						 
			  //Step 6: Click on the Create Contacts button
		ContactsPage1 cP=new ContactsPage1(driver);
				  cP.getCreateContactBtn().click();
				  //driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();
			  
			  //Step 7: Enter all the details & create new contact
				  CreatingNewContactPage1 cNCP=new CreatingNewContactPage1(driver);
				  cNCP.createContactWithOrg(driver, orgName, lastName);
				/*
				 * cNCP.getLastName().sendKeys(lastName); cNCP.getSaveBtn().click();
				 */
					/*
					 * driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
					 * driver.findElement(By.xpath(
					 * "//input[@name='account_name']/following-sibling::img")).click();
					 */
			  
			  //Switching control to window popup (child) on adding org name
				/*
				 * wLib.switchToTabOnURL(driver, "module=Accounts");
				 * 
				 * driver.findElement(By.id("search_txt")).sendKeys(orgName);
				 * driver.findElement(By.name("search")).click();
				 * 
				 * WebElement ele = driver.findElement(By.xpath("//a[.='" + orgName + "']"));
				 * 
				 * wLib.waitForElementPresent(driver, ele, 10); ele.click();
				 * 
				 * //Switching the control back to the parent window
				 * wLib.switchToTabOnURL(driver, "Contacts&action");
				 * 
				 * cOP.getsaveBtn().click();
				 */
			  //driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			  
			  //Verify last name contact
			  ContactInformationPage cIP=new ContactInformationPage(driver);
			  String actLastName = cIP.getActLastName().getText();
			  //String actLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
			  System.out.println(actLastName);
			  if(actLastName.equals(lastName)) {
				  System.out.println(lastName + " name is verified. Test PASS");
			  }else
			  {
				  System.out.println(lastName + " name is not verified. Test FAIL");
			  }
			  
			  //Verify org name
			  
			  OrganizationInfoPage1 oIP=new OrganizationInfoPage1(driver);
			  String actualOrgInfoData=oIP.getorgInfoData().getText();
			  //String headerInfo = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			  if(actualOrgInfoData.equals(orgName)) {
				  System.out.println(orgName + "  is created and header verified. Test case PASS");
			  }else
			  {
				  System.out.println(orgName + " is not created. Test case FAIL");
			  }
			  
			  //Step 8: Log out
			  hp.signOut(driver);
			  /*
				 * WebElement ele1 =
				 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				 * wLib.mouseMoveOnElement(driver, ele1);
				 * driver.findElement(By.linkText("Sign Out")).click();
				 */
			  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
			
			  driver.quit();
		}
}
