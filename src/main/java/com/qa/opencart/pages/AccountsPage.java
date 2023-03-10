package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constands.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logOutLink= By.linkText("Logout");
	private By accHeaders= By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon =By.cssSelector("#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccPageTitle() {
		String title= eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Acc page title : "+ title);
		return title;
	}
	
	public String getAccPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Acc Page URL:"+ url);
		return url;
	}
	public boolean isLogOutLinkExist() {
		return eleUtil.waitForElementVisible(logOutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public List<String> getAccountHeadersList() {
		List<WebElement> accHeadersList = eleUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accHeadersVaList = new ArrayList<String>();
		
		for(WebElement e : accHeadersList) {
			String text = e.getText();
			accHeadersVaList.add(text);
		}
		return accHeadersVaList;
	}
	public SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("Search field is not present on the page....");
			return null;
		}
		
		
	}

}
