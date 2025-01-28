package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
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

	
public class CreateOrgTest1 {

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
			  String orgName = eLib.getdatafromexcel("Org", 1, 2) + jLib.getrandomnumber();


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
			  
				/* If we want to call individual element
				 * LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
				 * lp.getUsernameEdt().sendKeys("admin"); lp.getPasswordEdt().sendKeys("admin");
				 * lp.getLoginBtn().click();
				 */
			  
			  //If we want to directly call the action class - helps in optimizing the code
			  
			  LoginPage1 lp=new LoginPage1(driver);
			  lp.loginToApp( PWD, UN);
			  
			  //Step 2: Navigate to the organization module
			  HomePage1 hp=new HomePage1(driver);
			  hp.getorgLink().click();
			  
			  //Step 3: Click on the Create Organization button
			  OrganizationsPage1 op=new OrganizationsPage1(driver);
			  op.getCreateOrgBtn().click();
			  
			  //Step 4: Enter all the details & create new organization
			  CreateNewOrganizationPage1 cNP=new CreateNewOrganizationPage1(driver);
			  cNP.createOrg(orgName);
			  /* We prefer using business method in the above 2 lines instead of calling the elements like below
				 * cNP.getorgNameField().sendKeys(orgName); cNP.getsaveBtn().click();
				 */
			  
			  //Verify header msg expected result
			 //String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			  OrganizationInfoPage1 oIP=new OrganizationInfoPage1(driver);
			   String headerInfo = oIP.getheaderInfo().getText();
			  if(headerInfo.contains(orgName)) {
				  System.out.println(orgName + "  is created and header verified. Test case PASS");
			  }else
			  {
				  System.out.println(orgName + " is not created. Test case FAIL");
			  }
			  
			  //Verify org name in the expected result
			   String actOrgName = oIP.getorgInfoData().getText();
			  System.out.println(actOrgName);
			  if(actOrgName.equals(orgName)) {
				  System.out.println(orgName + " org name verified. Test case PASS");
			  }else {
				  System.out.println(orgName + " name not verified. Test case FAIL");
			  }
			  
			  //Verify header info in the expected result
			  String actHeaderInfo = oIP.getheaderInfo().getText();
			  System.out.println(actHeaderInfo);
			  if(actHeaderInfo.equals(headerInfo)) {
				  System.out.println(headerInfo + " org name verified. Test case PASS");
			  }else {
				  System.out.println(headerInfo + " name not verified. Test case FAIL");
			  }
			  
			  //Step 5: Log out
			  
			  hp.signOut(driver); //Using business method/action class from the POM class-Homepage.java
			  
				/* Logging out using webdriver utility method
				 * WebElement
				 * ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"
				 * )); wLib.mouseMoveOnElement(driver, ele);
				 * 
				 * driver.findElement(By.linkText("Sign Out")).click();
				 */ //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
			
			  driver.quit();
		}
	}


