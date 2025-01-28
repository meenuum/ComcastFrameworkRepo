package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatecontactwithorgTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileUtility f=new FileUtility();
		ExcelUtility e=new ExcelUtility();
		JavaUtility ju=new JavaUtility();
		WebDriverUtility w=new WebDriverUtility();
		
		String BROWSER=f.getDatafromPropertiesFile("browser");
		String URl=f.getDatafromPropertiesFile("url");
		String USERNAME=f.getDatafromPropertiesFile("username");
		String PASSWORD=f.getDatafromPropertiesFile("password");
		
		
		//read testscript data from excel file
		
				FileInputStream fis1=new FileInputStream("./testdata/testscriptdata.xlsx");
					
					Workbook wb=WorkbookFactory.create(fis1);
					Sheet sh=wb.getSheet("contact");
					Row row=sh.getRow(4);
					String contactLastName=row.getCell(2).toString()+ ju.getrandomnumber();
					wb.close();
					
					WebDriver driver=null;
					
					if(BROWSER.equals("chrome")) {
						driver=new ChromeDriver();
					}
					else if(BROWSER.equals("firefox")){
						driver=new FirefoxDriver();
					}
					else if(BROWSER.equals("edge")) {
						driver=new EdgeDriver();
					}
					/*
					 * else { driver=new ChromeDriver(); }
					 */
					
					
					
					//login to application
					w.waitForPageToLoad(driver);
					driver.get(URl);
					driver.findElement(By.name("user_name")).sendKeys(USERNAME);
					driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
					driver.findElement(By.id("submitButton")).click();
					
					//navigate to organization module
					driver.findElement(By.linkText("Organizations")).click();
					
					//click on create organization Button
					
					driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
					
					//enter all the details & create new organization
					
				    driver.findElement(By.name("accountname")).sendKeys(contactLastName);
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					
					
					//verify Header phone number info Expected Result
					
					String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
					if(headerInfo.contains(contactLastName)) {
						System.out.println(contactLastName + "heade verified==PASS");
					}else {
							System.out.println(contactLastName + "header is verified==FAIL");
						}
					
					
					
					
					//navigate to organization module
					driver.findElement(By.linkText("Contacts")).click();
					
					
					
					//click on "create organization" button
					
					driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
					
					//enter all the details &create new organization
					driver.findElement(By.name("lastname")).sendKeys(contactLastName);
					driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
					
					//switch to child window
					
					w.switchToTabOnURL(driver, "module=Accounts");
				
					
					driver.findElement(By.name("search_text")).sendKeys(contactLastName);
					driver.findElement(By.name("search")).click();
					driver.findElement(By.xpath("//a[.='"+contactLastName+"']")).click();
					
					
					
					//switch to parent window
					
					w.switchToTabOnURL(driver, "Contacts&action");
				
				    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				    
				    driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
					driver.findElement(By.xpath("//a[.='Sign Out']")).click();

							
							//verify header phone number info expectedResult
							
							headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
							if(headerInfo.contains(contactLastName)) {
								System.out.println(contactLastName +"header verified==PASS");
							}else {
								System.out.println(contactLastName +"header is verified==FAIL");
							}
							
							
							//verify header orgName info Expected Result
							String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
							if(actOrgName.trim().equals(actOrgName)){
								System.out.println(contactLastName + "information is created==PASS");
							}else {
								System.out.println(actOrgName);
								
							}
							
	}
								
							}
							


