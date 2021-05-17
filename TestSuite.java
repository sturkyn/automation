package überall.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

/*
 * UBERALL QA and TESTING FUN
 * AUTHOR: SARGIN CAN GÜNAY
 * Please read Test Setup, Test Notes and Defect Report files that are added to the zip.
 * Hope You Enjoy Reviewing!
 */

public class TestSuite {
	
	private static String userName = "SARGIN CAN GUNAY"; //current user (also can simply change this to run a new test)
	private static String email = "qa_candidate_89153@uberall.com";
	private static String password = "uberall-qa-fun";
	private static String desiredCity = "Bielefeld";
	private static String chromeDriverPath = "..\\ÜBERALL\\src\\drivers\\chromedriver.exe"; //Driver is added to this project folder under this path
	
	private static WebDriver driver;
	
	private static int currentPage = 0; //Pre-determined as 0 for while loop to be valid for the first time
    private static int processCycle = 0; //for loop count
	
    public static void main(String[] args) throws FileNotFoundException
    {  
        
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //Webdriver is set to Chromedriver:
        driver = new ChromeDriver(); 
        
        //Test Results can be found at TestResults.txt after running:
        PrintStream out = new PrintStream(new FileOutputStream("TestResults.txt"));
        System.setOut(out);
        
        System.out.println("TEST RESULTS\n");
        
        userName = userName.replace(" ", "");
        
        login(email, password);
        
        //Filtering Active stores in Bielefeld and acquiring total count:
        int resultCount = filter();
        System.out.printf("Filtered Result Count: %d\n\n", resultCount);
        
        //Clicking first item on the filtered list:
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Filter may take some time 
        driver.findElement(By.xpath("//*[@class='location-name-wrapper']")).click();
        
        //Obtaining paginator size as integer:
        int paginatorSize = getPaginatorSize();
        
        System.out.printf("Location Identifier Results by Page\n-----------------------------------\n");
        
        while(currentPage < paginatorSize)
        {     
        	processCycle++; //Counting actual loop size to compare detailed view size with filter result size
        	
        	currentPage = getCurrentPage(paginatorSize);        	
            
            //Test Case 1: Added for a discovered bug: (pages started from 2 even though 1st item on the list was clicked)
            if (currentPage != processCycle)
            {
                System.out.printf("Test Case 1 Failed!\nDETAILED VIEW PAGE DOES NOT MATCH! Current: %d Expected: %d\n\n", currentPage,processCycle);
            }
            else
            {
            	System.out.printf("Test Case 1 Passed!\nDetailed view pages match. Current: %d Expected: %d\n\n ", currentPage,processCycle);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Displaying detailed view may take some time
            
            String cityName = getCityName();
            
            String streetNameNumber = getStreetInfo();
            
            if (cityName.equals(desiredCity)) //Filter may bring results with street name similar to city name
            {            	
            	//Building location identifier:
            	String locationIdentifier = new StringBuilder().append(cityName).append("-").append(streetNameNumber).append("-").append(userName).toString();
            	
            	//Changing and saving the location identifier:
            	manipulateLocationIdentifier(locationIdentifier);
                
                if (currentPage < paginatorSize) //To avoid searching for pagination-arrow right at last page
                {                  
                    driver.findElement(By.xpath("//*[@class='inter-location-pagination-arrow right']")).click();
                }               
            }
            else //To not change location identifier When city name is not the desired city
            {         	
                if (currentPage < paginatorSize) //To avoid searching for pagination-arrow right at last page
                {
                	System.out.printf("Location Identifier is NOT SET as city name is not as expected.\nCurrent: %s | Expected: %s\n\n", cityName,desiredCity);
                    driver.findElement(By.xpath("//*[@class='inter-location-pagination-arrow right']")).click();
                }
            }
        }
        
        System.out.printf("Total Location Detail Page Count: %d\n", paginatorSize);
        System.out.printf("Actual Location Detail Page Count: %d\n\n", processCycle);
        
        //Test Case 2: To check if the total detailed view items were equal to filtered result count
        if (processCycle != resultCount) 
        { 
            System.out.printf("Test Case 2 FAILED!!\nSome results were not present in location edit view!!! filter results were: %d but detailed view count was: %d", resultCount, processCycle);     
        }
        else
        {
        	System.out.println("Test Case 2 PASSED.\nLocations presented in location edit view are equal to filtered results count.\n");
        }
        
        driver.quit();
    }
    
    private static void login(String email, String password)
	{
		//Login:
	    driver.get("https://sandbox.uberall.com/en/app/uberall/login");
	    driver.findElement(By.xpath("//*[@id='email']")).sendKeys(email);
	    driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//*[@type='submit']")).click();
	    
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //Logging in may take some time
        System.out.println("Logged In Successfully\n");
	}
	
    private static int filter()
	{
		 //Filtering city:
        driver.findElement(By.xpath("//*[@id='locations-link']")).click();
        driver.findElement(By.xpath("//*[@class='ubui_searchBarInput___1UhYG ubui_adaptiveInput___1LNi1']")).sendKeys(desiredCity);
        driver.findElement(By.xpath("//*[@class='ubui_buttonLeftIcon___1z-mO']")).click(); //Bielefeld filter is applied
        
        //Filtering active locations:
        driver.findElement(By.xpath("//*[@class='location-list-contract-filter-wrapper']")).click();
        driver.findElement(By.xpath("//*[@class='contract-filter-status contract-filter-active']")).click();
        driver.findElement(By.xpath("//*[@class='contract-filter-apply']")).click();
        
        driver.navigate().refresh(); //Without refresh, identifier changes can not be displayed on list page
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Displaying filter may require some time
        
        System.out.println("Filters Applied Successfully\n");
        
        //Gathering filtered item count:
        return driver.findElements(By.xpath("//*[@class='location-name-wrapper']")).size();
	}
	
    private static int getPaginatorSize()
	{
		//Obtaining pagination size:
        String paginatorLast = driver.findElement(By.xpath("//*[@class='inter-location-pagination-wrapper']")).getText();
        paginatorLast = paginatorLast.split(" ")[2].trim();
        return Integer.valueOf(paginatorLast);
	}
    
    private static int getCurrentPage(int paginatorSize)
    {
    	//Obtaining current page:
        String paginatorStart = driver.findElement(By.xpath("//*[@class='inter-location-pagination-wrapper']")).getText();
        paginatorStart = paginatorStart.split(" ")[0].trim();
        currentPage = Integer.valueOf(paginatorStart);
        System.out.printf("Page: %d/%d\n", currentPage, paginatorSize);
	    return currentPage;
	}
    
    private static String getCityName()
    {
    	//Gathering city name and removing blank spaces:
        String cityName = driver.findElement(By.xpath("//*[@id='city']")).getAttribute("value");
        cityName = cityName.replaceAll(" ", "");
        return cityName;
    }
    
    private static String getStreetInfo()
    {
    	//Gathering street and street number and replacing blank spaces:
        String streetNameNumber = driver.findElement(By.xpath("//*[@id='streetAndNumber']")).getAttribute("value");
        streetNameNumber = streetNameNumber.replaceAll(" ", "");
        return streetNameNumber;
    }
    
    private static void manipulateLocationIdentifier(String identifier)
    {
    	//Setting location identifier:
        driver.findElement(By.xpath("//*[@id='identifier']")).click();
        driver.findElement(By.xpath("//*[@id='identifier']")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE)); //It is a bit dirty but clear() did not work :(
        driver.findElement(By.xpath("//*[@id='identifier']")).sendKeys(identifier);
        
        System.out.printf("Location Identifier Set To: %s\n\n", identifier);
        
        driver.findElement(By.xpath("//*[@class='spbe-submit-button-wrapper']")).click();
        
        //Save function can take time, implicit wait is not suitable:
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }
}