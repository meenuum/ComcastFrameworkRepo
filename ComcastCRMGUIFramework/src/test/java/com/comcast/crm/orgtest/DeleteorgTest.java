package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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

public class DeleteorgTest {
public static void main(String[] args) throws IOException, InterruptedException {
	//create object
			FileUtility f=  new FileUtility();
			ExcelUtility e= new ExcelUtility();
			JavaUtility ju= new JavaUtility();
			WebDriverUtility w= new WebDriverUtility();
			
			
			//read common data from json file
			
			String BROWSER=f.getDatafromPropertiesFile("browser");
			String URl=f.getDatafromPropertiesFile("url");
			String USERNAME=f.getDatafromPropertiesFile("username");;
			String PASSWORD=f.getDatafromPropertiesFile("password");
			
			
			//read testscript data from excel file
			
			  String orgName = e.getdatafromexcel("org", 1, 2) + ju.getrandomnumber();
			
            	WebDriver driver=null;
				
				if(BROWSER.equals("chrome")) {
					driver=new ChromeDriver();
				}else if(BROWSER.equals("firefox")){
					driver=new FirefoxDriver();
				}
				else if(BROWSER.equals("edge")) {
					driver=new EdgeDriver();
				}else {
					driver=new ChromeDriver();
				}
				
				
				//Step1: login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.get(URl);
				
				LoginPage1 lp=new LoginPage1(driver);
				
				lp.loginToApp("admin","admin");
				
				
				
				
				//step2:navigate to organization module
			    HomePage1 hp=new HomePage1(driver);
				hp.getOrgLink().click();
				
				
				//step3:click on create organization button
			   OrganizationsPage1 cnp=new OrganizationsPage1(driver);
			   cnp.getCreateNeworgbtn().click();
			   
				
				//step4:enter all the details & create new organization
			   CreateNewOrganizationPage1 cnop=new CreateNewOrganizationPage1(driver);
			   cnop.createOrg(orgName);
				
				
				//verify Header msg Expected Result
				OrganizationInfoPage1 oip=new OrganizationInfoPage1(driver);
				String actorgName=oip.getheaderInfo().getText();
				if(actorgName.contains(orgName)) {
					System.out.println(orgName  + " name is verified==PASS");
				} else {
					System.out.println(orgName  + " name is not  verified==PASS");
				}
			     //go back organization page
					
			
				hp.getOrgLink().click();
						
				//search for organization
				cnp.getSearchEdt().sendKeys(orgName);
				 w.selectVisibleText(cnp.getSearchDD(), "Organization Name");
				cnp.getSearchBtn().click();
				
				
				
			//in dynamic webtable select & delete org
				driver.findElement(By.xpath("//a[.='"+orgName+"']/../../td[8]/a[text()='del']")).click();
				Thread.sleep(2000);
				w.switchToAlertAndDismiss(driver);
				
				
				//logout
				hp.getSignoutlink();
				driver.quit();
				}		
}


