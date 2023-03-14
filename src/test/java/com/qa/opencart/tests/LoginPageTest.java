package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constands.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Epic("Epic-100: design alogin for open cart app ")
//@Story("US- Login: 101: design login pagefeatures for open cart")
public class LoginPageTest extends BaseTest {
	
	//@Severity(SeverityLevel.TRIVIAL)
	//@Description("checking the title of the page... Aothor: Naveen")
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		
	}
	
	//@Severity(SeverityLevel.NORMAL)
	//@Description("... checking the url of the page.. tester...Minu")
	@Test
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
		
	}
	
	//@Severity(SeverityLevel.CRITICAL)
	//@Description("...checking forgot pwd link exist...tester: Minu")
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	//@Severity(SeverityLevel.CRITICAL)
	//@Description("....checking user is able to login t app with correct username and password...")
	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue (accPage.isLogOutLinkExist());
	}
	public void NewCustomerTest() {
		Assert.assertTrue(loginPage.getNewCustomer());
	}

}
