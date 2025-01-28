package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage1 {

		//WebDriver driver;
		public CreateNewOrganizationPage1(WebDriver driver) {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		@FindBy(xpath="//input[@name='accountname']")
		private WebElement orgNameField;
		
		@FindBy(id="phone")
		private WebElement orgPhoneNoField;
		
		public WebElement getOrgPhoneNoField() {
			return orgPhoneNoField;
		}
			@FindBy(xpath="//input[@title='Save [Alt+S]']")
			private WebElement saveBtn;
			
			public WebElement getsaveBtn() {
				return saveBtn;
	}
			@FindBy(name="industry")
			private WebElement industryDB;
			
			@FindBy(xpath=("//select[@name='accounttype']"))
			private WebElement typeInfo;
			
			public WebElement getOrgNameField() {
				return orgNameField;
			}


			public WebElement getIndustryDB() {
				return industryDB;
			}

			public WebElement getTypeInfo() {
				return typeInfo;
			}

			public void createOrg(String orgString) {
				orgNameField.sendKeys(orgString);
				saveBtn.click();
			}
			
			
			
			@FindBy(id="dtlview_Industry")
			private WebElement industryData;
			
			@FindBy(id="dtlview_Type")
			private WebElement typeData;


	public WebElement getIndustryData() {
				return industryData;
			}

			public WebElement getTypeData() {
				return typeData;
			}
			
			
			
			
			  public void createOrgWithIndustry(String orgString, WebElement ele1, String industry, WebElement ele2, String type) {
					WebDriverUtility wLib=new WebDriverUtility();
				  orgNameField.sendKeys(orgString); 
			  Select sel=new Select(industryDB);
			  sel.selectByVisibleText(industry); 
				wLib.selectValue(ele2, type);
				saveBtn.click();
			  }
			 
			
			public void createOrgWithType(WebElement ele2, String type) {
				WebDriverUtility wLib=new WebDriverUtility();
				wLib.selectValue(ele2, type);
				/*
				 * Select sel2=new Select(typeInfo); sel2.selectByVisibleText(type);
				 */
				saveBtn.click();

			
			
			
			}		
	
			@FindBy(name="accountname")
			private WebElement orgNameEdt;
			
			@FindBy(xpath = "//input[@title='Save [Alt+S]']")
					private WebElement savebtn;

			@FindBy(name="industry")
			private WebElement industryDD;
			
			
			public WebElement getOrgNameEdt() {
				return orgNameEdt;
			}

			
			public WebElement getSavebtn() {
				return savebtn;
			}
			
			public void createOrg1(String orgName) {
				orgNameEdt.sendKeys(orgName);
				savebtn.click();
			}
			
			public void createOrg(String orgName, String industry ) {
				orgNameEdt.sendKeys(orgName);
				Select sel=new Select(industryDD);
				sel.selectByVisibleText(industry);
				
				savebtn.click();
			}

			




}






