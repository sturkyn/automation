package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;


public class TrialAccountStepDefinitions {
	
	public WebDriver driver=null;  	
	
	@Given("^Open browser$")
	public void open_browser() throws Throwable {
		System.setProperty("webdriver.chrome.driver","G:\\eclipse-workspace\\CucumberAMBatch\\src\\test\\java\\drivers\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	}


	@When("^Enter the url \"([^\"]*)\"$")
	public void enter_the_url(String arg1) throws Throwable {
		driver.get("https://elopage.com/");       
	}

	@And("^Close modal and click on Start Your (\\d+) Day Free Trial$")
	public void click_on_Start_Your_Day_Free_Trial(int arg1) throws Throwable {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='ct-link-button oxy-close-modal']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='menu-item-58']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@And("^Fill registration form$")
	public void fill_registration_form() throws Throwable {
		driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Elo4");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("Test");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("elotest7@gmail.com");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='phoneNumber']")).sendKeys("+493456789");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("elotest12");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("elo2");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@And("^Click on accept checkbox$")
	public void click_on_accept_checkbox() throws Throwable {
		driver.findElement(By.xpath("//*[@class='custom-check-mark']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);  
	}

	@And("^Click on create account$")
	public void click_on_create_account() throws Throwable {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='elo-btn orange']")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='pushActionRefuse']")).click();	    
	}

	@Then("^User successfully enters elopage portal$")
	public void user_successfully_enters_elopage_portal() throws Throwable {
		
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String capText= driver.findElement(By.xpath("//*[@class='elo-modal__message-text']")).getText();
		Assert.assertEquals(true,capText.contains("Start with choosing a product type"));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
  }
