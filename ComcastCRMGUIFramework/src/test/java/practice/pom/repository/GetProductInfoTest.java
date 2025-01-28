package practice.pom.repository;


	import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

	public class GetProductInfoTest {
		
		@Test (dataProvider = "getData")
		public void getProductInfoTest(String brandName, String productName) {
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.get("https:www.amazon.in");
			
			//search product
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
			
			//capture product info
			//String x="//span[.='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[2]/div[1]/a/span/span/span[2]";
			
			String x="//span[.='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div/a/span/span[2]/span[2]";
			String price = driver.findElement(By.xpath(x)).getText();
			System.out.println(price);
		}
		
		@DataProvider
		public Object[][] getData() throws Exception {
			
			//Using data from excel sheet
			
			ExcelUtility eLib=new ExcelUtility();
			int rowCount=eLib.getrowcount("product2");
			
			Object[][] objArr = new Object[rowCount][2];
			
			for(int i=0; i<rowCount; i++)
			{
				objArr[i][0] = eLib.getdatafromexcel("product2", i+1, 0);
				objArr[i][1] = eLib.getdatafromexcel("product2", i+1, 1);
			}
			/* Hard coded data
			 * objArr[0][0] = "iphone"; objArr[0][1] = "Apple iPhone 15 (128 GB) - Pink";
			 * 
			 * objArr[1][0] = "iphone"; objArr[1][1] = "Apple iPhone 15 (128 GB) - Black";
			 * 
			 * objArr[2][0] = "iphone"; objArr[2][1] = "Apple iPhone 15 (128 GB) - Yellow";
			 */
			
			return objArr;
		}
	}

