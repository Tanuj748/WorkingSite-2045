  package com.general_Utilities;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base_Test
{

public WebDriver driver = null;
  
public ExtentReports report ;
public ExtentTest test;


  @BeforeSuite
  public void configureReport()
  {
	  ExtentSparkReporter spark = new ExtentSparkReporter("./src/main/resorces/reports/DWS_Report_");
	  report = new ExtentReports();
	  report.attachReporter(spark);
  }

  @Parameters("browser")
  @BeforeClass()
  public void launchBrowser(@Optional("chrome") String Browser)
      {
	   
	  if(Browser.equals("chrome"))
	  {
		  driver = new ChromeDriver();
	  }
	  
	  else if(Browser.equals("edge"))
	  {
		  driver = new EdgeDriver();
	  }
	  
	  else if(Browser.equals("firefox"))
	  {
		  driver = new FirefoxDriver();
	  }
	  
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	   driver.get(File_Utility.getDataFromPropertiesFile("url"));
	   
      }
  
  @BeforeMethod(alwaysRun = true)
  public void loginToDWS(Method method) throws Exception
  {
	  
	  String methodName = method.getName();
	  test = report.createTest(methodName);
	  driver.findElement(By.linkText("Log in")).click();
	  driver.findElement(By.id("Email")).sendKeys("tanishbarwal2024@gmail.com");
	  driver.findElement(By.id("Password")).sendKeys("123321123");
	  driver.findElement(By.xpath("(//input[@type='submit'])[2]")).click();
	  test.log(Status.INFO, "Successfully Logged in to DWS.......");
  }
  
  @AfterMethod
  public void logoutFromDWS()
  {
	  driver.findElement(By.xpath("//a[text()='Log out']")).click();
	  test.log(Status.INFO, "Logged out from DWS......");
  }

  @AfterClass
  public void closeBrowser()
  {
	  driver.quit();
  }




}
