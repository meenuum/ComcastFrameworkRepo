package com.comcast.crm.objectrepositoryutility;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class OrganizationInfoPage1 {
		//WebDriver driver;
		public OrganizationInfoPage1(WebDriver driver) {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement headerInfoData;
		
		public WebElement getheaderInfo() {
			return headerInfoData;
		}
			 
		@FindBy(id="dtlview_Organization Name")
		private WebElement orgInfoData;
		
		public WebElement getorgInfoData() {
			return orgInfoData;
		}
		
		@FindBy(xpath="//span[@id='dtlview_Phone']")
		private WebElement phoneInfo;
		
		public WebElement getPhoneInfo() {
			return phoneInfo;
		}

		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement savebtn;
		
		public WebElement getSavebtn() {
			return savebtn;
		}
	}

