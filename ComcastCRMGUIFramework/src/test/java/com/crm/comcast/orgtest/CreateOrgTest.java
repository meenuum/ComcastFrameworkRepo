package com.crm.comcast.orgtest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

public class CreateOrgTest extends BaseClass {
	
	

	@Test
	public void createorgTest()
	{
		System.out.println("execute createorgTest & verify");
	}
	@Test
	public void createorgwithindustry()
	{
		System.out.println("execute createorgwithindustry & verify");
	}
	
	
}
