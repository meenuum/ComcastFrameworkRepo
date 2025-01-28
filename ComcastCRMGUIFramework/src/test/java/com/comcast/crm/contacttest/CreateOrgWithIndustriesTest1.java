package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CreateOrgWithIndustriesTest1 {
			
			public static void main(String[] args) throws Exception {
				
				//Create object
						FileUtility fLib=new FileUtility();
						ExcelUtility eLib=new ExcelUtility();
						JavaUtility jLib=new JavaUtility();
						WebDriverUtility wLib=new WebDriverUtility();
						  
					WebDriver driver = null;

					//Read system config data from properties file
					String URL = fLib.getDatafromPropertiesFile("url");
					  String BROWSER = fLib.getDatafromPropertiesFile("browser");
					  String UN = fLib.getDatafromPropertiesFile("username");
					  String PWD = fLib.getDatafromPropertiesFile("password");
					  
					  //Read test script data from Excel
					  String orgName = eLib.getdatafromexcel("Org", 1, 2) + jLib.getrandomnumber();
					  String industry=eLib.getdatafromexcel("Org", 4, 3).toString();
					  System.out.println(industry);
					  String type = eLib.getdatafromexcel("Org", 4, 4).toString();
					  System.out.println(type);
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
					  lp.loginToApp(PWD, UN);
					  
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
					 //cOP.getorgNameField().sendKeys(orgName);
					  //driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
					  
					  //Selecting industry option
					  cOP.createOrg(orgName);
					  //cOP.createOrgWithType(ele2, type);
					  cOP.getsaveBtn();
					  //OrganizationInfoPage1 op1=new OrganizationInfoPage1(driver);
					  //op1.getSavebtn();
					  		  
					  
						/*
						 * WebElement dropDown1 =
						 * driver.findElement(By.xpath("//select[@name='industry']"));
						 * wLib.selectVisibleText(dropDown1,industry);
						 * 
						 * //Selecting the type option WebElement dropDown2 =
						 * driver.findElement(By.xpath("//select[@name='accounttype']"));
						 * wLib.selectVisibleText(dropDown2,type);
						 * 
						 * driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						 */
					  
					  //Verify industries and type
					  String actIndustry = cOP.getIndustryData().getText();
					  System.out.println(actIndustry);
					  //String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
					  //System.out.println(actIndustry);
					  if(actIndustry.equals(industry)) {
						  System.out.println(industry + " name is verified. Test PASS");
					  }else
					  {
						  System.out.println(industry + " name is not verified. Test FAIL");
					  }
					  
					  String actType = cOP.getTypeData().getText();
					  System.out.println(actType);
					  //String actType = driver.findElement(By.id("dtlview_Type")).getText();
					 // System.out.println(actType);
					  if(actType.equals(type)) {
						  System.out.println(type + " is verified. Test PASS");
					  }else
					  {
						  System.out.println(type + " is not verified. Test FAIL");
					  }
					  
					  //Step 5: Log out
					  hp.signOut(driver);
					  
					  /*
						 * WebElement ele=(driver.findElement(By.xpath(
						 * "//img[@src='themes/softed/images/user.PNG']")));
						 * wLib.mouseMoveOnElement(driver, ele);
						 * driver.findElement(By.linkText("Sign Out")).click();
						 */
					  //driver.findElement(By.xpath("//a[.='Sign Out']")).click();
					
					  driver.quit();
			}
		}


