package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constands.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchProductReslts = By.cssSelector("div#content div.product-layout");
	

	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	public int getSearchProductsCount() {
		int productCount = eleUtil.waitForElementsVisible(searchProductReslts, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Count:::"+productCount);
		return productCount;
	}
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
		
		
	}

}
