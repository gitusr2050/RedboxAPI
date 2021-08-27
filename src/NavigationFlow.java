import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
//import com.google.gson. *;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class NavigationFlow {
	
	 WebDriver driver;
	 
	 @BeforeClass
	 public void setUp()
	 {
		 
		 
		 
	 }
	
	
	public static void verify_MovieTitle()
	{
		

		RestAssured.baseURI= "https://www.redbox.com";
		
	    String response = given().
	     when().
	     get("https://preprod.redbox.com/rbweb/api/product/js/__titles7").then().assertThat().statusCode(200) 
	  
	     .extract().response().asString();
	   
	  
	   JsonPath js = ReUsableMethods.rawToJson(response);
	   
	   
	  /* String movielist = js.getString("name[193]");
	   
	   System.out.println(movielist); 
	   
	   String selectedMovieUrl = js.getString("url[193]") ;

		 System.out.println(selectedMovieUrl);

		 
	String respString = given().log().all(). when().get("https://www.redbox.com/"+selectedMovieUrl).then().log().all().assertThat().statusCode(200).extract().response().asString() ;
	
	 System.out.println(respString); */
	   
	 
	String[] moviename = js.getString("name").split(",") ;
	  
	  int moviecount = moviename.length;
	    
	   
	   String[] movieurl= js.getString("url").split(",") ;
	    
	   int b = (int)(Math.random()*(moviecount-0)+0);
	   
		 System.out.println(b);


		  String selectedMovie = js.getString("name["+b+"]") ;
		  
		  
		  
		 System.out.println(selectedMovie);
		 
		  String selectedMovieUrl = js.getString("url["+b+"]") ;

			 System.out.println(selectedMovieUrl);

			 
		String respString = given().log().all(). when().get("https://www.redbox.com/"+selectedMovieUrl).then().log().all().assertThat().statusCode(200).extract().response().asString() ;
		
		 System.out.println(respString);
		 
		 
		String title = StringUtils.substringBetween(respString, "<title>","</title>");
		
		
		 System.out.println(title.substring(7).toUpperCase());
		 
		 
		 System.out.println(selectedMovie.toUpperCase()); 

       assertEquals(title.substring(7).toUpperCase().equals(selectedMovie.toUpperCase()),true);
       
       
	  System.out.println("Movie name is same"); 
		
		
	}
	
	
	
	

	public static void main(String[] args) {
    
		
		//verify_MovieTitle();
		
		NavigationFlow flow = new NavigationFlow();
		
		
     
	  

	}

	}
