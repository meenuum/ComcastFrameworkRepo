package com.comcast.crm.generic.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpClass implements ITestListener ,ISuiteListener {
	 public ExtentSparkReporter spark;
	 public static ExtentReports report;
	 public static  ExtentTest test;
	 
	@Override
	public void onStart(ISuite suite) {
		System.out.println("report configuration");
		String time=new Date().toString().replace(" ","_").replace(":", "_");
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		// add environment & create test
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", "windows-10");
		report.setSystemInfo("Browser", "CHROME-100");
		
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		report.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("====="+result.getMethod().getMethodName()+"=====START====");
		 test= report.createTest("result.getMethod().getMethodName");
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName() +"==> STARTED <======");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("====="+result.getMethod().getMethodName()+"===END======");
		test.log(Status.PASS, result.getMethod().getMethodName() +"==> COMPLETED <======");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
	    TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String filePath =ts.getScreenshotAs(OutputType.BASE64);
		String time=new Date().toString().replace(" ","_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath,testName+""+time);
		test.log(Status.FAIL, result.getMethod().getMethodName() +"==> FAILED <======");
		
	}
		
	

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
