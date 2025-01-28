package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class Createorganizationwithindustries {

	public static void main(String[] args) throws IOException {
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
		
				  FileInputStream fis1=new FileInputStream("C:\\Users\\Admin\\Desktop\\testscriptdata.xlsx");
					
					Workbook wb=WorkbookFactory.create(fis1);
					Sheet sh=wb.getSheet("org");
					Row row=sh.getRow(4);
					String orgName=row.getCell(2).toString()+ randomInt;
					String industries=row.getCell(3).toString();
					System.out.println(industries);
					String type=row.getCell(4).toString();
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
					
					//step 1:login to app
					
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
					driver.get(URl);
					driver.findElement(By.name("user_name")).sendKeys("admin");
					driver.findElement(By.name("user_password")).sendKeys("admin");
					driver.findElement(By.id("submitButton")).click();
					
					//step 2: navigate to organization module
					
					driver.findElement(By.linkText("Organizations")).click();
					driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
					
					//step3: click on create organization button
					driver.findElement(By.name("accountname")).sendKeys(orgName);
					WebElement wesel1 =driver.findElement(By.name("industry"));
					Select sel=new Select(wesel1);
					
					sel.selectByValue(industries);
					
					WebElement wesel2= driver.findElement(By.name("accounttype"));
					Select sell=new Select(wesel2);
					sell.selectByValue(type);
					
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				    
				    //verify the industries and type info
				    
				    String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
				    if(actIndustries.equals(industries)) {
				    	System.out.println(industries +"information is verified==PASS");
				    }else  {
				    	System.out.println(industries +"information is not verified==FAIL");
				    }
				    
				    
				    
				    String actType=driver.findElement(By.id("mouseArea_Type")).getText();
				    if(actType.equals(type)) {
				    	System.out.println(type +"  information is verified==PASS");
				    }else  {
				    	System.out.println(type +"  information is not verified==FAIL");
				    }
				    driver.quit();

					
					
		

	}

}
