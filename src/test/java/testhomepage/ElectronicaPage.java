package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElectronicaPage {
    private static Logger Log = LogManager.getLogger(MarketHome.class);
    WebDriver driver;

    public ElectronicaPage(WebDriver driver) {this.driver = driver;}

    By loc_mobilephone = By.xpath("//a[@class='_2qvOOvezty _2x2zBaVN-3 _9qbcyI_fyS' and contains(text(), 'Мобильные')]");


    public MobilePhonePage clickOnMobilePhone () throws Exception
    {
        try {
            Log.info("Try to click on Mobile Phone link");
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.elementToBeClickable(loc_mobilephone)).click();
        } catch (Exception e) {
            Log.error("Error - link with Mobile Phone wasn't clickable at the page", e);
            throw e;
        }
        Log.info("Link with Mobile Phone is opened successfully");
        return new MobilePhonePage(driver);
    }


}
