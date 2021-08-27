import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class searchMovie {
	
	WebDriver driver;
	
	@BeforeClass
	
	public void setUp()
	{
		
		System.setProperty("webdriver.chrome.driver", "//Users//dgolla//eclipse-workspace//2021Automation//RedboxAPI//chromedriver92");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.redbox.com") ;
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
	}
	
	@Test
	
public  void search_Movie()
	
	{
	
		
		WebElement  searchfield =     driver.findElement(By.name("search_term_string"));
		
		searchfield.clear();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		searchfield.sendKeys("Wonder Woman 1984");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		String textToSelect = "Wonder Woman 1984";

	   // WebElement autoOptions= driver.findElement(By.xpath("//li[text()='Wonder Woman 1984'])"));
	    //autoOptions.sendKeys("Wonder Woman 1984");

	  //  List<WebElement> optionsToSelect = driver.findElements(By.xpath(""));
	    
	    List<WebElement> optionsToSelect = driver.findElements(By.cssSelector("[id*=\"searchinput-results-\"]"));
	    
	   int len =  optionsToSelect.size();
	   
	   for (int i=0;i<len;i++)
	   {
		   
		   if (optionsToSelect.get(i).getText().equals(textToSelect))
		   {
			   
			   optionsToSelect.get(i).click();
			 Reporter.log("Movie name is same");
			   break;
		   }
		   
	   }
	    

	    
	    }
	
	@Test(dependsOnMethods = "search_Movie")
	
	public  void explore_MovieResults()
	
	{
		
       WebElement  searchfield =     driver.findElement(By.name("search_term_string"));
		
		searchfield.clear();
		searchfield.sendKeys("Wonder Woman");
		
	
		//String textToSelect = "Wonder Woman";
	    
	    List<WebElement> optionsToSelect = driver.findElements(By.cssSelector("[id*=\"searchinput-results-\"]"));
	    
	    int len =  optionsToSelect.size();
	    
	    System.out.println(len);
	    
	    WebElement exploreButton = driver.findElement(By.cssSelector(".rb-searchbar-results button[type=\"submit\"]"));
	    
	    
		
		exploreButton.click();
		
		  List<WebElement> exploreMovieslist = driver.findElements(By.cssSelector("[class=\"title_tile-img w:100%\"]"));
		    
		 int explorelist =  exploreMovieslist.size();
		 
	Assert.assertEquals(len, explorelist);
	
	Reporter.log("Search auto complete results and Explore list have same count of movies");
		
		
		
	}

}
