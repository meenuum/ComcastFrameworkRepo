package com.comcast.crm.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage1;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage1;
@Listeners(com.comcast.crm.generic.listenerutility.ListenerImpClass.class)
public class CreateOrgtest extends BaseClass {
	@Test
	public void Createorgtest() throws EncryptedDocumentException, IOException
	{
		
	  UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
	  String orgName = elib.getdatafromexcel("Org", 7, 2) + jlib.getrandomnumber();
	  String phoneNumber = elib.getdatafromexcel("Org", 7, 3).toString();
	  
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
	}
	 @Test
	 
	 public void createphonenumber() throws EncryptedDocumentException, IOException
	 {
		  String orgName = elib.getdatafromexcel("Org", 1, 2) + jlib.getrandomnumber();
		  
		  //Step 2: Navigate to the organization module
		  HomePage1 hp=new HomePage1(driver);
		  hp.getorgLink().click();
		  
		  //Step 3: Click on the Create Organization button
		  OrganizationsPage1 op=new OrganizationsPage1(driver);
		  op.getCreateOrgBtn().click();
		  
		  //Step 4: Enter all the details & create new organization
		  CreateNewOrganizationPage1 cNP=new CreateNewOrganizationPage1(driver);
		  cNP.createOrg(orgName);
		  
		  
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
	 }

	 @Test
	 
	 public void orgwithindustries() throws EncryptedDocumentException, IOException
	 {
		  String orgName = elib.getdatafromexcel("Org", 1, 2) + jlib.getrandomnumber();
		  String industry=elib.getdatafromexcel("Org", 4, 3).toString();
		  
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
		  
		  
		  
		  
		 
		  cOP.getsaveBtn();
		  //Verify industries and type
		  String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
		    if(actIndustries.equals(industry)) {
		    	System.out.println(industry +"information is verified==PASS");
		    }else  {
		    	System.out.println(industry +"information is not verified==FAIL");
		    }
		    
		  
		  
		  
		  
		  
		  
			/*
			 * String actIndustry = cOP.getIndustryData().getText();
			 * System.out.println(actIndustry); //String actIndustry =
			 * driver.findElement(By.id("dtlview_Industry")).getText();
			 * //System.out.println(actIndustry); if(actIndustry.equals(industry)) {
			 * System.out.println(industry + " name is verified. Test PASS"); }else {
			 * System.out.println(industry + " name is not verified. Test FAIL"); }
			 */
		 
		  
		  
		  
		  
	 }
	 
	 
	 
	 
	 }
	  
	  

	
