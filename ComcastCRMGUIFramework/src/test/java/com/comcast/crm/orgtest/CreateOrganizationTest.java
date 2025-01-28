package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateOrganizationTest {



	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		//create object
				FileUtility f=new FileUtility();
				ExcelUtility e=new ExcelUtility();
				JavaUtility ju=new JavaUtility();
				//read common data from json file
		
		String BROWSER=f.getDatafromPropertiesFile("browser");
		String URl=f.getDatafromPropertiesFile("url");
		String USERNAME=f.getDatafromPropertiesFile("username");
		String PASSWORD=f.getDatafromPropertiesFile("password");
		
		
		//generate the random number
		Random random=new Random();
				int randomInt=random.nextInt(1000);
		
		//read testscript data from excel file
		
				FileInputStream fis1=new FileInputStream("./testdata/testscriptdata.xlsx");					
					Workbook wb=WorkbookFactory.create(fis1);
					Sheet sh=wb.getSheet("org");
					Row row=sh.getRow(1);
					String orgName=row.getCell(2).toString()+ randomInt;
					wb.close();
					
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
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					driver.get(URl);
					driver.findElement(By.name("user_name")).sendKeys(USERNAME);
					driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
					driver.findElement(By.id("submitButton")).click();
					driver.findElement(By.linkText("Organizations")).click();
					driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
					driver.findElement(By.name("accountname")).sendKeys(orgName);
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					
					String headerInfo =	driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				
					if(headerInfo.contains(orgName)) {
						System.out.println(orgName +"is created==PASS");
						
					}else {
						System.out.println(orgName +"is created==FAIL");
					}
					
					/*
					 * String actOrgName =
					 * driver.findElement(By.id("mouseArea_Organization Name")).getText();
					 * if(headerInfo.equals(orgName)) { System.out.println(orgName
					 * +"is created==PASS");
					 * 
					 * }else { System.out.println(orgName +"is created==FAIL"); }
					 */
					
					driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
					driver.findElement(By.xpath("//a[.='Sign Out']")).click();
					
					Thread.sleep(2000);
				   driver.quit();
					

	}

}
