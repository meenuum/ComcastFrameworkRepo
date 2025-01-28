package com.comcast.crm.objectrepositoryutility;


	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class OrganizationsPage1{
		
		//WebDriver driver;
		public OrganizationsPage1(WebDriver driver) {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		@FindBy(xpath="//img[@title='Create Organization...']")
		private WebElement createOrgBtn;
		
		@FindBy(name="search_text")
		private WebElement searchEdt;
		
		@FindBy(name="search_field")
		private WebElement searchDD;
		
		@FindBy(xpath="//input[@name='submit']")
		private WebElement searchBtn;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement savebtn;
		
		public WebElement getSearchBtn() {
			return searchBtn;
		}
		public WebElement getCreateOrgBtn() {
			return createOrgBtn;
		}
		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getSearchDD() {
			return searchDD;
		}
	
		public WebElement getSavebtn() {
			return savebtn;
		}
		 @FindBy(xpath = "//img[@alt='Create Organization...']")
	     private WebElement createNeworgbtn;
		 public WebElement getCreateNeworgbtn() {
		 		return createNeworgbtn;
		 	}
	
	
	
	}
	
	


