package com.comcast.crm.contacttest;

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
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.listenerutility.ListenerImpClass;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage1;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage1;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage1;
import com.comcast.crm.objectrepositoryutility.HomePage1;
import com.comcast.crm.objectrepositoryutility.LoginPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage1;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage1;
import com.crm.generic.baseutility.BaseClass;

public class Createcontacttest extends BaseClass {

	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException {

		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		/*read testscript data from excel file */

		String lastname = elib.getdatafromexcel("contact", 1, 2) + jlib.getrandomnumber();

		// navigate to contact module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Org Page");
		HomePage1 hp = new HomePage1(driver);
		hp.getContactsLink().click();

		// Step 3: Click on the Create Contacts button
		
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create Org Page");

		ContactsPage1 cp = new ContactsPage1(driver);
		cp.getCreateContactBtn().click();

		// Step 4: Enter all the details & create new contact
		
		UtilityClassObject.getTest().log(Status.INFO, "Create a new  contact");

		CreatingNewContactPage1 cn = new CreatingNewContactPage1(driver);
		cn.getLastName().sendKeys(lastname);

		cn.getSaveBtn().click();

		// Verify last name contact
		ContactInformationPage cIP = new ContactInformationPage(driver);
		String actLastName = cIP.getActLastName().getText();
		System.out.println(actLastName);

		if (actLastName.equals(lastname)) {
			System.out.println(lastname + " name is verified. Test PASS");
		} else {
			System.out.println(lastname + " name is not verified. Test FAIL");
		}

	}

	@Test
	public void createContactwithsupportDateTest() throws EncryptedDocumentException, IOException {

		// Read test script data from Excel
		String lastName = elib.getdatafromexcel("Contact", 4, 2) + jlib.getrandomnumber();
		String orgName = elib.getdatafromexcel("Contact", 7, 2) + jlib.getrandomnumber();

		// Step 2: Navigate to the Contacts module
		HomePage1 hp = new HomePage1(driver);
		hp.getContactsLink().click();
		System.out.println("hello");

		// Step 3: Click on the Create Contacts button
		ContactsPage1 cp = new ContactsPage1(driver);
		cp.getCreateContactBtn().click();
		System.out.println("hello1");

		// Step 4: Enter the start, end date, and create new contact
		String endDate = jlib.getSystemDateYYYYDDMM();
		String startDate = jlib.getrequireddataYYYYDDMM(30);

		CreatingNewContactPage1 cNCP = new CreatingNewContactPage1(driver);
		cNCP.createContactWithSupportDate(driver, orgName, 30);
		System.out.println("hello2");

		// Verify start date and end date
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

	}

	@Test
	public void createContactwithOrgTest() throws EncryptedDocumentException, IOException {
		String lastName = elib.getdatafromexcel("Contact", 4, 2) + jlib.getrandomnumber();
		String orgName = elib.getdatafromexcel("Contact", 7, 2) + jlib.getrandomnumber();
		// Step 2: Navigate to the organization module
		HomePage1 hp = new HomePage1(driver);
		hp.getorgLink().click();

		// Step 3: Click on the Create Organization button
		OrganizationsPage1 op = new OrganizationsPage1(driver);
		op.getCreateOrgBtn().click();

		// Step 4: Enter all the details & create new organization
		CreateNewOrganizationPage1 cOP = new CreateNewOrganizationPage1(driver);
		cOP.getOrgNameField().sendKeys(orgName);
		cOP.getsaveBtn().click();

		// Verify org name

		OrganizationInfoPage1 oIP = new OrganizationInfoPage1(driver);
		String actualOrgInfoData = oIP.getorgInfoData().getText();
		// String headerInfo = driver.findElement(By.id("mouseArea_Organization
		// Name")).getText();
		if (actualOrgInfoData.equals(orgName)) {
			System.out.println(orgName + "  is created and header verified. Test case PASS");
		} else {
			System.out.println(orgName + " is not created. Test case FAIL");
		}

		// navigate to contact module

		hp.getContactsLink().click();

		// click on "create contact" button

		ContactsPage1 cp = new ContactsPage1(driver);
		cp.getCreateContactBtn().click();
		// Step 7: Enter all the details & create new contact
		CreatingNewContactPage1 cNCP = new CreatingNewContactPage1(driver);
		cNCP.createContactWithOrg(driver, orgName, lastName);
		// Verify last name contact
		ContactInformationPage cIP = new ContactInformationPage(driver);
		String actLastName = cIP.getActLastName().getText();
		// String actLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last
		// Name']")).getText();
		System.out.println(actLastName);
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + " name is verified. Test PASS");
		} else {
			System.out.println(lastName + " name is not verified. Test FAIL");
		}

	}
}
