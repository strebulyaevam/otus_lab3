package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobilePhonePage {
    private static Logger Log = LogManager.getLogger(MarketHome.class);
    WebDriver driver;

    public MobilePhonePage(WebDriver driver) {this.driver = driver;}

    By loc_realme = By.xpath("//input[@type='checkbox' and @name='Производитель realme']");
    By loc_xiaomi = By.xpath("//input[@type='checkbox' and @name='Производитель Xiaomi']");

    public void checkRealmeBrand() throws Exception
    {
        try {
            Log.info("Try to add filter by Realme brand");
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.visibilityOfElementLocated(loc_realme))
                    .click();
        } catch (Exception e) {
            Log.error("Error - brand Realme is absent at the page", e);
            throw e;
        }
        Log.info("Filter by Realme brand have been applied");
    }
    public void checkXiaomiBrand() throws Exception
    {
        try {
            Log.info("Try to add filter by Xiaomi brand");
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.elementToBeClickable(loc_xiaomi)).click();
        } catch (Exception e) {
            Log.error("Error - brand Xiaomi is absent at the page", e);
            throw e;
        }
        Log.info("Filter by Xiaomi brand have been applied");
    }
}
