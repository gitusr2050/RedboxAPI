
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class searchMovieTest {
	
	WebDriver driver;
	PageObjMod pom;
	
	@BeforeClass
	
	public void setUp()throws InterruptedException, IOException
	{
		
		System.setProperty("webdriver.chrome.driver", "chromedriver92");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbox.com") ;
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		pom = new PageObjMod(driver);
		
	}
	
	@Test
	
public  void TC1_search_Movie()
	
{
	
	Boolean status = pom.search_Movie();
	if (status.equals(true))
	{
		 Reporter.log("Movie name is same");
		 
	}
	else 
		 Reporter.log("Movie name is not the same"); 
	    
 }
	
	@Test(dependsOnMethods = "TC1_search_Movie")
	
	public  void TC2_explore_MovieResults()
	
	{
		
		 Boolean status = pom.explore_MovieResults();
       
		 if (status.equals(true))
			{
				 Reporter.log("Movie name is same");
				 
			}
			else 
				 Reporter.log("Movie name is not the same"); 
			    
			
		 Reporter.log("Search auto complete results and Explore list have same count of movies");
		
		
		
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void catchExceptions(ITestResult result)
	{
	    Calendar calendar = Calendar.getInstance();
	    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	    String methodName = result.getName();
	    if(!result.isSuccess())
	    {
	        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        try {
	        	
	        	String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
                
	            FileUtils.copyFile(scrFile,destFile);
	        } 
	        catch (IOException e1) 
	        {
	            e1.printStackTrace();
	        }
	        }
	    }
	
	 @AfterClass()
	 public void tearDown()
	 {
		 
		 driver.close();
		 
		 
	 }

}
