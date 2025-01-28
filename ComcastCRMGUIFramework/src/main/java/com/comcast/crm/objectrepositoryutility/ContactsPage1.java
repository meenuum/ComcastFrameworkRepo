package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


	
	public class ContactsPage1 {

		//WebDriver driver;

		public ContactsPage1 (WebDriver driver) {

			//this.driver=driver;

			PageFactory.initElements(driver, this);

		}

		

		@FindBy(xpath=("//img[@title='Create Contact...']"))

		private WebElement createContactBtn;

		

		public WebElement getCreateContactBtn() {

			return createContactBtn;

		}

		@FindBy(linkText = "Contacts")
		public WebElement contlink;
		
		
		@FindBy(xpath = "//a[@title='Create Contact...']")
	    public WebElement createcontact;
		
		@FindBy(name="lastname")
		public WebElement lastname;
		
		
		@FindBy(id="mobile")
		public WebElement mob;


		
		public WebElement getContlink() {
			return contlink;
		}


		
		public WebElement getCreatecontact() {
			return createcontact;
		}



		public WebElement getLastname() {
			return lastname;
		}

		public WebElement getMob() {
			return mob;
		}
		
		
		
		
		
		
	}


