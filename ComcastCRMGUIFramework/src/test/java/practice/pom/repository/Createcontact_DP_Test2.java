package practice.pom.repository;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Createcontact_DP_Test2 {
	
	
	@Test(dataProvider="getData")
	 public void createContacttest(String firstname, String lastname, long phoneNumber)
	 {
		System.out.println("Firstname :"+firstname +",LastName:" +lastname +",phoneNumber :"+phoneNumber);
	 }
	
	 @DataProvider
	 public Object[][] getData()
	 {
		 Object[][] objArr = new Object[3][3];
		 objArr[0][0] ="meena";
		 objArr[0][1] ="hr";
		 objArr[0][2] =7896541233l;
		 
		 objArr[1][0] ="smith";
		 objArr[1][1] ="rh";
		 objArr[1][2] =8523697412l;
		 
		 objArr[2][0] ="sam";
		 objArr[2][1] ="hra";
		 objArr[2][2] =9874563212l;
		 return objArr;
		 
	 }


}
