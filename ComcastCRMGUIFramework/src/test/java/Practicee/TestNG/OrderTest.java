
package Practicee.TestNG;

import org.testng.annotations.Test;

public class OrderTest {
	@Test(invocationCount = 10)
	public void createOrderTest()
	{
		System.out.println("Execute createOrderTest==>123");
		String str=null;
		System.out.println(str.equals("123"));
	}
	@Test(enabled=false)
	public void billingOrderTest()
	{
		System.out.println("Execute billingOrderTest==>123");
	}
	
	
}
