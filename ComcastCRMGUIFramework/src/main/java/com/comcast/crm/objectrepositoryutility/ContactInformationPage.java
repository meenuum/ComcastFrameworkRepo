package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class ContactInformationPage {
	//WebDriver driver;
	public ContactInformationPage (WebDriver driver) {
		//this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=("//span[@id='dtlview_Last Name']"))
	private WebElement actLastName;
	
	public WebElement getActLastName() {
		return actLastName;
	}
	
	public void verifyStartEndDates() {
		JavaUtility jLib=new JavaUtility();
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYMMDD(30);
	
	}	
}