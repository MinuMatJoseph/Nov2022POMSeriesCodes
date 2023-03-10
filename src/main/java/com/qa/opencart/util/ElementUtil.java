package com.qa.opencart.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//ALIGNMENT ==>cntrl+ shft + F
//syso = cntrl +enter for ystem.out.println("")
public class ElementUtil {
	private WebDriver driver;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	

	public WebElement getElement(By locator) {
		return driver.findElement(locator);

	}
	public WebElement getElement(By locator, int timeOut) {
		return waitForElementVisible(locator, timeOut);

	}

	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		getElement(locator).sendKeys(value);
	}
	
	public  void doClick(By locator) {
		getElement(locator).click();
		
	}
	
	public void doActionsCick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}
	
	
	public  boolean doElementIsDisplay(By locator) {
		return getElement(locator).isDisplayed();
		
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public  void getElementAttributes(By locator, String attrName) {
		List<WebElement> attributeList = getElements(locator);
		System.out.println("Total sizes" + attributeList.size());
		for(WebElement e: attributeList) {
			String srcVal = e.getAttribute(attrName);
			System.out.println(srcVal);
		}


}
	
	public int getTotalElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("Total elements for : "+locator +"--->"+ eleCount);
		return eleCount;
	}
	
	
	
	
	
	
	
	
//***************************Select based dropdown util*****************************//
	
	
	
	
	
	
	//*********************Wait Utils *********************//
	
	
	
	/**presenceOfElementLocated(By locator)==>An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * @return 
	*/
		public  WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			WebElement email_ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			//email_ele.sendKeys("test@gamil.com");
			return email_ele;
			
			
		}
		/*An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed 
		 * but also has a height and width that is greater than 0.
		 * */
		
		
		public  WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 WebElement email_ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			 return email_ele;
			//email_ele.sendKeys("test@gamil.com");
			
			
		}
		
		/**
		 * An expectation for checking that there is at least one element present on a web page
		 * @param locator
		 * @param timeOut
		 * return wait
		 **/
		
		public  List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));					
		}
		
		
		/*
		 * An expectation for checking that all elements present on the web page that match the locator are visible. 
		 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
		 */
		public  List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));		
		}
		/**
		 * Wait for time
		 */
		public void waitForFrameAndSwitchToItByOrAname(int timeOut, String idOrName){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));   //now driver is available on the frame
			
		}
		public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			
		}
		//we do not prefer with WebElement
		
		public void waitForFrameAndSwitchToItByFrameElement(int timeOut,  WebElement frameElement){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
			
		}
		public void waitForFrameAndSwitchToItByLocator(int timeOut,  By locator){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			
		}
				
		/**
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param timeOut
		 * @param locator
		 */
		
		//click for button, link
		
		public void clickWhenReady(int timeOut, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click(); 
		}
		public WebElement waitForElementToBeClickable(int timeOut, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		public void doClickWithActionsAndWait(int timeOut, By locator) {
			WebElement ele = waitForElementToBeClickable(timeOut, locator);
			Actions act = new Actions(driver);
			act.click(ele).build().perform();
			
		}
		
		
		
		/*******************Alert Wait********/
		/**
		 * 
		 * @param timeOut
		 * @return
		 */
		public Alert waitForAlertPresence(int timeOut){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
			
		}
		public String getAlertText(int timeOut) {
			return waitForAlertPresence(timeOut).getText();
			
		}
		public void acceptAlert(int timeOut) {
			waitForAlertPresence(timeOut).accept();
		}
		public void dismissAlert(int timeOut) {
			waitForAlertPresence(timeOut).dismiss();
		}
		public void alertSendKeys(int timeOut, String value) {
			waitForAlertPresence(timeOut).sendKeys(value);
		}
		
		
		/****************wait getTitle**********************/
		public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleContains(titleFractionValue));
			return driver.getTitle();
		}
		public String waitForTitleIsAndFetch(int timeOut, String titleValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.titleIs(titleValue));
			return driver.getTitle();
		}
		
		/*******************wait getURL*******/
	
		 public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(urlFractionValue));
		return driver.getCurrentUrl();
		
	}
	
	public String waitForURLIsAndFetch(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlToBe(urlValue));
		return driver.getCurrentUrl();
	}
	public Boolean waitForURLContains(int timeOut, String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFractionValue));
		
	}
	
		
		
	
}