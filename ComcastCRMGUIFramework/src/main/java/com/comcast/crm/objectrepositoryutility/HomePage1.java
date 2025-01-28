
package com.comcast.crm.objectrepositoryutility;


	import org.openqa.selenium.WebDriver;

	
	
	
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
	public class HomePage1 {
		
		//WebDriver driver;
		public HomePage1(WebDriver driver) {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		@FindBy(xpath="//a[.='Organizations']")
		private WebElement orgLink;
		
		public WebElement getorgLink() {
			return orgLink;
		}
		
		@FindBy(xpath=("//a[.='Contacts']"))
		private WebElement contactsLink;
		
		@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
		private WebElement adminImg;
		
		@FindBy(linkText = "Sign Out")
		private WebElement signoutlink;
		

	
		
		public WebElement getContactsLink() {
			return contactsLink;
		}
		
			@FindBy(xpath=("//img[@src='themes/softed/images/user.PNG']"))
			private WebElement imgSrc;
			
			public WebElement getImgSrc() {
				return imgSrc;
			}
				
				@FindBy(linkText=("Sign Out"))
				private WebElement signOutLink;
				
				public WebElement getSignOutLink() {
					return signOutLink;
	}
				public void signOut(WebDriver driver) {
					WebDriverUtility wLib=new WebDriverUtility();
					wLib.mouseMoveOnElement(driver, imgSrc);
					/*
					 * Actions act=new Actions(driver); act.moveToElement(imgSrc).perform();
					 */
					signOutLink.click();
				}
				
				public WebElement getSignoutlink() {
					return signoutlink;
				}
				
				public void setSignoutlink(WebElement signoutlink) {
					this.signoutlink = signoutlink;
				}
				
				public WebElement getOrgLink() {
					return orgLink;
				}
				
				
				@FindBy(linkText = "More")
			    private WebElement MoreLink;

				/**
				 * @return the adminImg
				 */
				public WebElement getAdminImg() {
					return adminImg;
				}
				/**
				 * @param adminImg the adminImg to set
				 */
				public void setAdminImg(WebElement adminImg) {
					this.adminImg = adminImg;
				}
				/**
				 * @return the moreLink
				 */
				public WebElement getMoreLink() {
					return MoreLink;
				}
				
				
				
				
				
	}


	
	/*
	 * @FindBy(linkText = "Organizations") private WebElement orgLink;
	 * 
	 * @FindBy(linkText = "Contacts") private WebElement contactLink;
	 * 
	 * 
	 * @FindBy(linkText = "Campaigns") private WebElement campaignlnk;
	 * 
	 * 
	 * @FindBy(linkText = "More") private WebElement MoreLink;
	 * 
	 * @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']") private
	 * WebElement adminImg;
	 * 
	 * @FindBy(linkText = "Sign Out") private WebElement signoutlink;
	 * 
	 * 
	 * public WebDriver getDriver() { return driver; }
	 * 
	 * public WebElement getOrgLink() { return orgLink; }
	 * 
	 * public WebElement getContactLink() { return contactLink; }
	 * 
	 * 
	 * public WebElement getMoreLink() { return MoreLink; }
	 * 
	 * 
	 * 
	 * public WebElement getCampaignlnk() {
	 * 
	 * Actions act = new Actions(driver); act.moveToElement(MoreLink).perform();
	 * return campaignlnk; }
	 * 
	 * 
	 * public void logout() { Actions act=new Actions(driver);
	 * act.moveToElement(adminImg).perform(); signoutlink.click(); }
	 * 
	 * 
	 * 
	 */