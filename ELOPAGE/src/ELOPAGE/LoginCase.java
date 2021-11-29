package ELOPAGE;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

public class LoginCase {

private static String userName = "SARGIN CAN GUNAY"; //current user (also can simply change this to run a new test)
private static String email = "scangunay@gmail.com";
private static String password = "Scgxnp0623!";
private static String chromeDriverPath = "G:\\eclipse-workspace\\ELOPAGE\\src\\drivers\\chromedriver.exe"; //Driver is added to this project folder under this path

private static WebDriver driver;

public static void main(String[] args) throws FileNotFoundException
{  
    
    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    //Webdriver is set to Chromedriver:
    driver = new ChromeDriver(); 
    
    	//Login:
        driver.get("https://elopage.com/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='ct-link-button oxy-close-modal']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='text_block-1307-456']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("scangunay@gmail.com");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Scgxnp0623!!");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='elo-btn orange']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='pushActionRefuse']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='fas fa-store']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='elo-btn orange']")).click();
}
}
/*private static void login(String email, String password)
{
	//Login:
    driver.get("https://elopage.com/");
    driver.findElement(By.className("ct-fancy-icon")).click();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //Logging in may take some time
    System.out.println("Logged In Successfully\n");
}

private static int filter()
{
	 //Filtering city:
    driver.findElement(By.xpath("//*[@id='locations-link']")).click();
   // driver.findElement(By.xpath("//*[@class='ubui_searchBarInput___1UhYG ubui_adaptiveInput___1LNi1']")).sendKeys(desiredCity);
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

//private static int getCurrentPage(int paginatorSize)
//{
	//Obtaining current page:
   // String paginatorStart = driver.findElement(By.xpath("//*[@class='inter-location-pagination-wrapper']")).getText();
   // paginatorStart = paginatorStart.split(" ")[0].trim();
   // currentPage = Integer.valueOf(paginatorStart);
   // System.out.printf("Page: %d/%d\n", currentPage, paginatorSize);
   // return currentPage;
//}

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
*/