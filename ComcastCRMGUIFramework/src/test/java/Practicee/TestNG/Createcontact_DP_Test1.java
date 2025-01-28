package Practicee.TestNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Createcontact_DP_Test1 {

	
	@Test(dataProvider="getData")
	 public void createContacttest(String firstname, String lastname)
	 {
		System.out.println("firstname :"+firstname +",LastName:" +lastname);
	 }
	
	 @DataProvider
	 public Object[][] getData()
	 {
		 Object[][] objArr = new Object[3][2];
		 objArr[0][0] ="meena";
		 objArr[0][1] ="hr";
		 
		 objArr[1][0] ="smith";
		 objArr[1][1] ="rh";
		 
		 objArr[2][0] ="sam";
		 objArr[2][1] ="hra";
		 return objArr;
		 
	 }
}
