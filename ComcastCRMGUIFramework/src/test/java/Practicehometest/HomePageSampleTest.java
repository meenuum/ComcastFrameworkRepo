package Practicehometest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageSampleTest {
	@Test
	public void homePageTest(Method mtd)
	{
		Reporter.log(mtd.getName() + "Test Start");
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		Reporter.log("step-3",true);
		Reporter.log("step-4",true);
		Reporter.log(mtd.getName() + "Test end");
	}

	@Test
	
	public void verifyLogohomepageTest(Method mtd)
   {
		Reporter.log(mtd.getName() + "Test Start");
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		Reporter.log("step-3",true);
		Reporter.log("step-4",true);
		Reporter.log(mtd.getName() + "Test end");
	}

}
