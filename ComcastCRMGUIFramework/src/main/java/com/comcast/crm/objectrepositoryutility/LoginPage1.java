package com.comcast.crm.objectrepositoryutility;


	


	import java.time.Duration;

import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * @author Admin
 * 
 * contains Login page elements & business lib like login()
 * 
 * 
 */





public class LoginPage1 extends WebDriverUtility {
		 WebDriver driver;
		
		//Rule 1 create a separate java class DONE
		
		//Rule 2 Object identification and creation DONE
		//WebDriver driver;
		public LoginPage1(WebDriver driver) {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(name="user_name")
		private WebElement usernameEdt;
		
		@FindBy(name="user_password")
		private WebElement passwordEdt;
		
		@FindBy(id="submitButton")
		private WebElement loginBtn;
		
		//Rule 3 Object initialization DONE
		
		//Rule 4 Encapsulation DONE
		public WebElement getUsernameEdt() {
			return usernameEdt;
		}

		public WebElement getPasswordEdt() {
			return passwordEdt;
		}

		public WebElement getLoginBtn() {
			return loginBtn;
		}
		/**
		 * login to application based on username ,password arguments
		 * @param username
		 * @param password
		 */
		
		//Rule 5 Provide action DONE
		public void loginToApp( String username, String password) {
			
		
			usernameEdt.sendKeys(username);
			passwordEdt.sendKeys(password);
			loginBtn.click();
		}
	}


