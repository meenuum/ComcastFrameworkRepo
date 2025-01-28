package practice.pom.repository;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductinfoTes {

	@Test(dataProvider = "getData")
	
	public void getProductInfoTest(String brandName , String  productName)
	{
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://amazon.com");
		
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		
		//capture the product info
		
		String x="//span[.='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.quit();
		
	}
	 @DataProvider
	 public Object[][] getData() throws EncryptedDocumentException, IOException
	 {
		 
		 ExcelUtility elib=new ExcelUtility();
		 int rowCount = elib.getrowcount("product");
		 
		 
		 Object[][] objArr = new Object[rowCount][2];
		 
		 
		 for(int i=0; i<rowCount; i++)
		 {
		 objArr[i][0] =elib.getdatafromexcel("product", i+1, 0);
		 objArr[i][1] =elib.getdatafromexcel("product", i+1, 1);
		
		 
		
		 }
		 return objArr;
		 
	 }

	
	
	
	
	
}
