

import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PageObjMod {
	
	WebDriver driver;
	
	
	Boolean movieStatus;
	Boolean exploreliststatus;
	WebElement  searchBox;
	
	String textToSelect;

		
		public By searchfield = By.name("search_term_string");
		public By searchinput = By.cssSelector("[id*=\"searchinput-results-\"]");
		
		public By explorelink = By.cssSelector(".rb-searchbar-results button[type=\"submit\"]");
		
		public By exploreMoviescount = By.cssSelector("[class=\"title_tile-img w:100%\"]");

		
		public PageObjMod(WebDriver driver) throws IOException
		{
			
			this.driver=driver;

			driver.get("https://www.redbox.com") ;
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
		}
		
		public  boolean search_Movie()
		
		{
		
			searchBox =     driver.findElement(searchfield);
			
			searchBox.clear();
			try {
				
				Thread.sleep(5000);
			} catch (InterruptedException e1) 
			{
				
				e1.printStackTrace();
			}

			
			searchBox.sendKeys("Wonder Woman 1984");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		
			String textToSelect = "Wonder Woman 1984";

		    List<WebElement> optionsToSelect = driver.findElements(searchinput);
		    
		   int len =  optionsToSelect.size();
		   
		   for (int i=0;i<len;i++)
		   {
			   
			   if (optionsToSelect.get(i).getText().equals(textToSelect))
			   {
				   
				   movieStatus = true;
				 optionsToSelect.get(i).click();
				
				 
				   break;
			   }
			   
			  
		   }
		    
          return movieStatus;
		    
		    }
		
		
		public  Boolean explore_MovieResults()
		
		{
			
	       WebElement  searchBox =     driver.findElement(searchfield);
			
	       searchBox.clear();
	       searchBox.sendKeys("Wonder Woman");
			
		
		   textToSelect = "Wonder Woman";
		    
		    List<WebElement> optionsToSelect = driver.findElements(searchinput);
		    
		    int len =  optionsToSelect.size();
		    
		    System.out.println(len);
		    
		    WebElement exploreButton = driver.findElement(explorelink);
		    
			exploreButton.click();
			
			  List<WebElement> exploreMovieslist = driver.findElements(exploreMoviescount);
			    
			 int explorelist =  exploreMovieslist.size();
			
			 if (len==explorelist)
			 {
				 exploreliststatus=true;
				 
			 }
			 
			 return exploreliststatus;
		
			
			
		}
}


