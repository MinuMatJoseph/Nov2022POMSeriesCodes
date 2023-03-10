package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.exception.FrmaworkException;

import io.netty.buffer.search.AbstractMultiSearchProcessorFactory;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * this method is initializing the driver on the basis of given browser name
	 * @param browserName
	 * @return this returns the driver
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		optionsManager = new OptionsManager( prop);
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("browser name is :" + browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			
		}
		else {
			System.out.println("Please pass the right browser..."+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	/*
	 * get the local thread copy of the driver
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is reading the properties from the properties file
	 * @return 
	 */
	public Properties initProp() {
		
		//mvn clean install -Denv ="stage"
		prop = new Properties();
		FileInputStream ip=null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env:"+ envName);
		try {
		if(envName== null) {
			System.out.println("no env is passed.... Running tests on QA env...");
			 ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
		}
		else {
			switch(envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
				break;
			case "stage":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
				break;
			case "dev":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
				break;
			case "prod":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
				break;
			default:
				System.out.println("....Wrong env is passed... No need t run the test cases...");
				throw new FrmaworkException("WRONG ENV IS PASSED....");
				//break;
			}
		}
//	}
//	
//		
//		
//		
//		try {
//		 ip =new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
//		prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		} 
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile,  destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
		
	}

}
