package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constands.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	@BeforeClass
	public void regPageSetup() {
		registerPage =loginPage.navigateToRegisterPage();
	}
	public String getRandomEmail() {
		Random random = new Random();
//		String email="autoamtion"+random.nextInt(1000)+"@gmail.com";
		String email ="automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
		
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
		
	}
@Test(dataProvider="getRegTestData")
public void userRegTest(String firstName, String lastName, 
			 String telephone, String password, String subscribe) {
	Assert.assertTrue(registerPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	
}
}
