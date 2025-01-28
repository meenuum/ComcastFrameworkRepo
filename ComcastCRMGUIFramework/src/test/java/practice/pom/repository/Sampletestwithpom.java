package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Sampletestwithpom {
	
	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;

	@FindBy(id="submitButton")
	WebElement ele3;

	
	@Test
	public void sampletest()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		
		Sampletestwithpom s =   PageFactory.initElements(driver, Sampletestwithpom.class);
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		
		
		driver.navigate().refresh();
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		
		
		s.ele3.click();

	}
	

}
