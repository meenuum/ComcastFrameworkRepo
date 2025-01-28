package Practicehometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageverificationTest {
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName() + "Test Start");
		String exptectedPage="Home page";
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://49.249.28.218:8888/");
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
	driver.findElement(By.id("submitButton")).click();
    String actTitle=	driver.findElement(By.className("hdrLink")).getText();
    //Hard Assert
   Assert.assertEquals(actTitle, exptectedPage);
    driver.close();
    System.out.println(mtd.getName() + "Test end");
	}
  
	@Test
	
	public void verifyLogohomepageTest(Method mtd)
   {
		System.out.println(mtd.getName() + "Test Start");
	
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://49.249.28.218:8888/");
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
	driver.findElement(By.id("submitButton")).click();
	
	boolean status=driver.findElement(By.xpath("//img[@title='vtiger-crm-logo.gif']")).isEnabled();
	//Hard Assert
	 Assert.assertTrue(status);
	  driver.close();
	  System.out.println(mtd.getName() + "Test end");
   }
}
