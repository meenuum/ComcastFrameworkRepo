package Practicee.TestNG;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SampleTestForscreenShot {
@Test
public void amazonTest() throws IOException
{
	WebDriver driver = new FirefoxDriver();
	driver.get("http://amazon.com");
	
	//step:1: create an object to Takescreenshot
	
	TakesScreenshot ts=(TakesScreenshot)driver;
	
	//step2: use getscreenshotAs method to get file type of screenshot
	File src=ts.getScreenshotAs(OutputType.FILE);
	
	//step3: store screen on local driver
	FileUtils.copyFile(src, new File("./screenshot/test.png"));
}
}