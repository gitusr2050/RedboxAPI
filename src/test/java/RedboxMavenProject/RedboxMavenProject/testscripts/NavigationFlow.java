package RedboxMavenProject.RedboxMavenProject.testscripts;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import files.ReUsableMethods;


public class NavigationFlow  {

	static WebDriver driver;

	@BeforeClass
	public void setUp()throws Exception

	{

		System.setProperty("webdriver.chrome.driver", "chromedriver92");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver();
		driver.manage().window().maximize();


	}


	@Test

	public static void verifyMovieTitle()
	{

		RestAssured.baseURI= "https://www.redbox.com";
	

		String response = given().when().get("https://preprod.redbox.com/rbweb/api/product/js/__titles7").then().assertThat().statusCode(200) 

				.extract().response().asString();

		JsonPath js = ReUsableMethods.rawToJson(response);


		String[] moviename = js.getString("name").split(",") ;


		int moviecount = moviename.length;


		String[] movieurl= js.getString("url").split(",") ;

		int b = (int)(Math.random()*(moviecount-0)+0);

		System.out.println(b);

		String selectedMovie = js.getString("name["+b+"]") ;

		System.out.println(selectedMovie);

		String selectedMovieUrl = js.getString("url["+b+"]") ;

		System.out.println(selectedMovieUrl);

		//String respString = given().log().all(). when().get("https://www.redbox.com/"+selectedMovieUrl).then().log().all().assertThat().statusCode(200).extract().response().asString() ;

		//System.out.println(respString);

		driver.get("https://www.redbox.com/"+selectedMovieUrl) ;

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String title = driver.findElement(By.cssSelector("[data-test-id='title_detail-name']")).getText();


		System.out.println(title.toUpperCase());


		System.out.println(selectedMovie.toUpperCase()); 

		assertEquals(title.toUpperCase().equals(selectedMovie.toUpperCase()),true);


		System.out.println("Movie name is same"); 
		
	

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

	@AfterClass
	public void tearDown()

	{

		driver.close();
	}

}
