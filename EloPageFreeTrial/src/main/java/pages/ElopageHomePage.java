package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElopageHomePage extends PageBase{

    public ElopageHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='text_block-1307-456']")
    public WebElement homePageLoginButton;


    public void clickLoginInHomePage()
    {
        clickElement(homePageLoginButton);
    }

}
