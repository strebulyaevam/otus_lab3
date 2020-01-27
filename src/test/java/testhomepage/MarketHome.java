package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MarketHome {

    private static Logger Log = LogManager.getLogger(MarketHome.class);

    WebDriver driver;

    public MarketHome(WebDriver driver) {this.driver = driver;}

    By loc_electronica = By.cssSelector("a[href='/catalog--elektronika/54440']");


    public ElectronicaPage clickOnElectronicaMenu () throws Exception
    {
        try {
            Log.info("Try to click on menu Electronica");
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.elementToBeClickable(loc_electronica)).click();
        } catch (Exception e) {
            Log.error("Error - menu Electronica wasn't clickable at the page", e);
            throw e;
        }
        Log.info("Menu Electronica is opened successfully");
        return new ElectronicaPage(driver);
    }
}
