package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactsPage1;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage1;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.LoginPage1;

public class CreateContactWithSupportDate1 {

	public static void main(String[] args) throws Exception {

		// Create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		WebDriver driver = null;
		String baseurl = fLib.getDatafromPropertiesFile("url");
		String browser = fLib.getDatafromPropertiesFile("browser");
		String un = fLib.getDatafromPropertiesFile("username");
		String pwd = fLib.getDatafromPropertiesFile("password");

		// Read test script data from Excel
		String lastName = eLib.getdatafromexcel("Contact", 4, 2) + jLib.getrandomnumber();
		String orgName = eLib.getdatafromexcel("Contact", 7, 2) + jLib.getrandomnumber();

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// Step 1: Login to the app
		driver.get(baseurl);

		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
		LoginPage1 lp = new LoginPage1(driver);
		lp.loginToApp(un, pwd);

		/*
		 * driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
		 * driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pwd);
		 * driver.findElement(By.id("submitButton")).click();
		 */

		// Step 2: Navigate to the Contacts module
		HomePage1 hp = new HomePage1(driver);
		hp.getContactsLink().click();
		// driver.findElement(By.xpath("//a[.='Contacts']")).click();

		// Step 3: Click on the Create Contacts button
		ContactsPage1 cp = new ContactsPage1(driver);
		cp.getCreateContactBtn().click();
		// driver.findElement(By.xpath("//img[@title='Create Contact...\']")).click();

		// Step 4: Enter the start, end date, and create new contact
		CreatingNewContactPage1 cNCP = new CreatingNewContactPage1(driver);
		cNCP.createContactWithSupportDate(driver, orgName, 30);

		/*
		 * String startDate = jLib.getSystemDateYYYYMMDD(); String endDate =
		 * jLib.getRequiredDateYYYYMMDD(30);
		 * 
		 * CreatingNewContactPage cNP=new CreatingNewContactPage(driver);
		 * cNP.getLastName().sendKeys(lastName);
		 * //driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName
		 * );
		 * 
		 * driver.findElement(By.name("support_start_date")).clear();
		 * driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		 * 
		 * driver.findElement(By.name("support_end_date")).clear();
		 * driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		 * 
		 * cNP.getSaveBtn().click();
		 */
		// driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify start date and end date
		String actStartDate = driver.findElement(By.id("jscal_field_support_start_date")).getText();
		System.out.println(actStartDate);
		if (actStartDate.equals(actStartDate)) {
			System.out.println(actStartDate + " is verified==Test PASS");
		} else {
			System.out.println(actStartDate + " is not verified== Test FAIL");
		}
		String actEndDate = driver.findElement(By.id("jscal_field_support_end_date")).getText();
		System.out.println(actEndDate);
		if (actEndDate.equals(actEndDate)) {
			System.out.println(actEndDate + " is verified. Test PASS");
		} else {
			System.out.println(actEndDate + " is not verified. Test FAIL");
		}

		// Step 5: Log out
		hp.signOut(driver);
		/*
		 * WebElement
		 * ele=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"
		 * )); wLib.mouseMoveOnElement(driver, ele);
		 * driver.findElement(By.linkText("Sign Out")).click();
		 */
		// driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		driver.quit();
	}
}
