package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class PageBase {

    protected WebDriver driver;

    public PageBase(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void navigateToPage(WebDriver driver, String URL) {
        driver.navigate().to(URL);
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void clickOutsideThePage(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveByOffset(10, 10).click().build().perform();
    }

    public void clearElement(WebElement element) {
        String selectAll = Keys.chord(Keys.CONTROL, "a");
        element.sendKeys(selectAll);
        element.sendKeys(Keys.DELETE);
    }

    public void scrollToFindElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }

}
