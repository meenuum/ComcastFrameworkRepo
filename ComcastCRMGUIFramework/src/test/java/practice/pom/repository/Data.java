package practice.pom.repository;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Data {
	@DataProvider(name="Data")
	public Object[][] getData()
	{
	Object[][]data= {{"meena@gmail.com","123"},{"srilakshmi.mg01@gmail.com","456"},{"lakshmi@gmail.com","789"}};
	return data;
	}
	
	@Test(dataProvider="login",dataProviderClass=Data.class)
	public void loginTest(String un, String pwd)
	{
		System.out.println(un + ""+ pwd);
	}

}
