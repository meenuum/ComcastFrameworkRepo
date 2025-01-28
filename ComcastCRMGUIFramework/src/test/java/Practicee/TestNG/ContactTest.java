package Practicee.TestNG;

import org.testng.annotations.Test;

public class ContactTest {

	@Test
	public void createcontactTest() {
		System.out.println("execute createcontact with -->HDFC");
	}

	@Test(dependsOnMethods ="createcontactTest" )
	public void modifycontactTest() {
		
		System.out.println("execute modifycontactTest-->HDFC=>ICICI");
	}

	@Test(dependsOnMethods = "modifycontactTest")
	public void DeletecontactTest() {
	
		System.out.println("execute deletecontactTest--> ICICI");
	}

}

/*
 * @Test public void createContactTest() { System.out.println("execute login");
 * System.out.println("execute navigate to contact");
 * System.out.println("execute create Contact");
 * System.out.println("execute verify contact");
 * System.out.println("execute logout"); }
 * 
 * @Test public void createcontactwithmobilenumberTest() {
 * System.out.println("execute createcontactwithmobilenumberTest"); }
 * 
 * }
 * 
 * 
 */