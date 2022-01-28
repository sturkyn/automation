package StepDefinitions;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeOptions;


public class TrialAccountStepDefinitions {
	
	public WebDriver driver=null;  	
	
	@Given("^Open browser$")
	public void open_browser() throws Throwable {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en-GB");
		System.setProperty("webdriver.chrome.driver","..\\chromedriver.exe");
	    driver = new ChromeDriver(options);
	    driver.manage().window().maximize();
	}


	@When("^Enter the url \"([^\"]*)\"$")
	public void enter_the_url(String arg1) throws Throwable {
		driver.get("https://staging.elopage.com/");
	}

	@And("^Close modal and click on Start Your (\\d+) Day Free Trial$")
	public void click_on_Start_Your_Day_Free_Trial(int arg1) throws Throwable {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='menu-item-58']")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@And("^Fill registration form$")
	public void fill_registration_form() throws Throwable {

		Faker faker = new Faker();

		String name = faker.name().firstName();
		String ulastname= faker.name().lastName();

		FakeValuesService fakeValuesService = new FakeValuesService(
				new Locale("DE"), new RandomService());

		String uemail = fakeValuesService.bothify("???????######@gmail.com");
		String pnumber = fakeValuesService.bothify("+############");
		String pword = fakeValuesService.bothify("+#??###???##???##");

		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(name);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys(ulastname);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='email']")).sendKeys(uemail);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='phoneNumber']")).sendKeys(pnumber);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys(pword);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='username']")).sendKeys("TESTAUT");
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
