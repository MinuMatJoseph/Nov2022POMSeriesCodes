package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constands.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1.private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By newCustomer = By.xpath("//div[@class='well']/a[text()='Continue']");
	private By registerLink =  By.linkText("Register");
	
	
	//2.page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	@Step("...getting the login page title....")
	//3.page actions/methods
	public String getLoginPageTitle() {
		String title =eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Loginpage title :"+ title);
		return title;
	}
	@Step("...getting the login page url....")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url: "+ url);
		return url;
	}
	@Step("...getting the forgot the password link....")
	public boolean isForgotPwdLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();
	}

	@Step("login with username :{0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("App Credential are: "+un + ": "+  pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.getElement(password).sendKeys(pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}
	public boolean getNewCustomer() {
		return eleUtil.waitForElementVisible(newCustomer, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		//return driver.findElement(newCustomer).isDisplayed();
		
	}
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
		
		
	}
}
