package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin("minu12@gmail.com", "minu123");
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] { { "Macbook" ,"MacBook Pro",4}, 
			                    { "Macbook", "MacBook Air",4 }, 
			                    { "iMac","iMac" ,3}, 
			                    { "Apple", "Apple Cinema 30\"",6 } };

	}
	@Test(dataProvider ="getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productname, int imagesCount) {
		searchPage=accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productname);
		int actImagesCount = productInfoPage.getProductimagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	
	@Test
	public void productInfoTest() {
		searchPage=accPage.performSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String>  actProductionMap = productInfoPage.getProductInfo();
		softAssert.assertEquals(actProductionMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductionMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductionMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductionMap.get("Availability"), "In Stock");
		
		softAssert.assertAll();
	}
	
	@Test
	public void addToCartTest() {
		searchPage=accPage.performSearch("Macbook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actCartMesg = productInfoPage.addProductToCart();
		//success: You have added MacBook pro to your shopping cart!
		softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMesg.indexOf("MacBook Pro")>=0);
		
		softAssert.assertEquals(actCartMesg,"Success: You have added MacBook Pro to your shopping cart!");
		softAssert.assertAll();
		
		
	}

}
