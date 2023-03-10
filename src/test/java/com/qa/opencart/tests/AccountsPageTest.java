package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constands.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin("minu12@gmail.com", "minu123");

	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains("route=account/account"));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}

	@Test
	public void accPageHeadersCountTest() {

		List<String> actualAccPageHeadersList = accPage.getAccountHeadersList();
		System.out.println("actualAccPageHeadersList -->" + actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}	
	
	@Test
	public void accPageHeadersValueTest() {

		List<String> actualAccPageHeadersList = accPage.getAccountHeadersList();
		System.out.println("actualAccPageHeadersList -->" + actualAccPageHeadersList);
		System.out.println("Expected Acc Page header list-->"+AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST );
		
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook" }, 
			                    { "iMac" }, 
			                    { "Apple"}, 
			                    { "Samsung" } };

	}

	@Test(dataProvider ="getProductData")
	public void searchProductCountTest(String searcgkey) {
		searchPage = accPage.performSearch(searcgkey);
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0);
	}
	
	

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "Macbook" ,"MacBook Pro"}, 
			                    { "Macbook", "MacBook Air" }, 
			                    { "iMac","iMac" },
			                   { "Apple", "Apple Cinema 30\"" }};

	}
@Test(dataProvider ="getProductTestData")
public void searchProductTest(String searcgkey, String productName) {
	searchPage=accPage.performSearch(searcgkey);
	if(searchPage.getSearchProductsCount()>0) {
		productInfoPage = searchPage.selectProduct(productName);
		String actProductHeader = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeader, productName);
	}
	
	
}
}
