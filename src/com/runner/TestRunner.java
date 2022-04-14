package com.runner;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestRunner {
  
	WebDriver driver = null;
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\91896\\Downloads\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://appwrk.com/");
  }

  @Test(priority=1)
  public void IfLinksAvail() {
	  List<WebElement> alllink = driver.findElements(By.tagName("a"));
	  System.out.println("No. of links on the page "+alllink.size());
	  Assert.assertTrue(alllink.size()>0);
  }
  
  @Test(priority=2)
  public void verify4o4Link() {
	  List<WebElement> alllink = driver.findElements(By.tagName("a"));
	  for(int i=0;i<alllink.size();i++)
	  {
		  String link = alllink.get(i).getAttribute("href");
		  try {
			URL url = new URL(link);
			HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
			urlconn.setConnectTimeout(5000);
			urlconn.connect();
			
			if(urlconn.getResponseCode()==404)
				System.out.println(link +" is giving 404 in response");
			else
				System.out.println(link+" OK");
				
		} catch (MalformedURLException e) {
			System.out.println("BAD URL - MalformedURLException Caught");
		} catch (IOException e) {
			System.out.println("BAD URL - IOException Caught");
		}
		  
		 
	  }
  }
  
  
  @AfterClass
  public void afterClass() {
	  driver.close();
  }

}
