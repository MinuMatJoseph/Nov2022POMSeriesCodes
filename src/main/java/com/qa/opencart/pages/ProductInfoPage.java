package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constands.AppConstants;
import com.qa.opencart.util.ElementUtil;


public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessmessag = By.cssSelector("div.alert.alert-success"); 
	
	private Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public String getProductHeaderValue() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("Product Header: "+ productHeaderVal);
		return productHeaderVal;
	}
	public int getProductimagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product images count");
		return imagesCount;
		
	}
	
	public void enterQuantity(int qty) {
		System.out.println("Product Quanity: "+ qty);
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMessg = eleUtil.waitForElementVisible(cartSuccessmessag, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
//		successMessg = successMessg.substring(0, successMessg.length()-1).replace("\n", "");
		
		//testing
		//StringBuilder
		StringBuilder sb = new StringBuilder(successMessg);
		String mesg =sb.substring(0, successMessg.length()-1).replace("\n", "").toString();
		
		System.out.println("Cart Success Mesg:"+ mesg );
		return mesg;
	}
	
//	Brand: Apple
//	Product Code: Product 16
//	Reward Points: 600
//	Availability: In Stock
	
	public Map<String, String> getProductInfo() {
		
//		productInfoMap = new HashMap<String, String>();
		productInfoMap = new LinkedHashMap<String, String>();
		
		
		//header
		productInfoMap.put("productname", getProductHeaderValue());
			
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productInfoMap);
				
		return productInfoMap;
			
		}
		

		
	
	
	private void getProductMetaData() {
		//meta data
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);

	}
		}
	
	//fetching price data
	private void getProductPriceData() {
//			$602.00
//			Ex Tax: $500.00
			List<WebElement> priceList = eleUtil.getElements(productPriceData);
			String price = priceList.get(0).getText();
			String exTax = priceList.get(1).getText();
			String extaxVal = exTax.split(":")[1].trim();
			
			productInfoMap.put("productprice",price);
			productInfoMap.put("productprice",extaxVal);	
		
	}
}
