package Practicee.TestNG;
/**
 * test class for Contact module
 * 
 * @author Admin
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage1;

public class SearchContactTest extends BaseClass{
/**
 * Scenario: login()==> navigateContact==>createcontact()==verify
 
 */
	
	
	
	
	
	@Test
public void searchcontactTest()
{
	
	/*step 1: login to app*/
	 LoginPage1 lp = new LoginPage1(driver);
	 lp.loginToApp("username", "password");
}
	
	
	
	
}
