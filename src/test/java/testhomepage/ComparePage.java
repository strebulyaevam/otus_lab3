package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ComparePage {
    private static Logger Log = LogManager.getLogger(MarketHome.class);
    WebDriver driver;
    WebDriverWait waiter;

    public ComparePage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
    }

    By loc_allcharacteristic = By.xpath("//span[@class='link n-compare-show-controls__all']/span[contains(text(),'все характеристики')]");
    By loc_diffcharacteristic = By.xpath("//span[@class='link n-compare-show-controls__diff']/span[contains(text(),'различающиеся характеристики')]");

    By loc_itemsToCompare = By.cssSelector("div.n-compare-content__line.i-bem.n-compare-content__line_js_inited div.n-compare-cell");
    By loc_OSblock = By.xpath("//div[@class='n-compare-row-name i-bem' and contains(text(), 'Операционная система')]");
    By loc_preloader = By.cssSelector("div.spin2.spin2_size_m.i-bem.spin2_js_inited.spin2_progress_yes");
    By loc_delelems = By.cssSelector("div.n-compare-toolbar__action span.link__inner");


    public int getAmountOfItems () throws Exception
    {
        try {
            Log.info("Try to count the amount of items to compare");
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.elementToBeClickable(loc_allcharacteristic));
        } catch (Exception e) {
            Log.error("Error - Page Compare isn't loaded correctly", e);
            throw e;
        }
        Log.info("Link with Mobile Phone is opened successfully");

        List<WebElement> compare_items = driver.findElements(loc_itemsToCompare);
        Log.info("Amount of items to compare is - "+compare_items.size());
        return compare_items.size();
    }

    public void clickOnAllCharacteristic () throws Exception
    {
        try {
            Log.info("Try to click on all characteristic link");
            waiter
                  .until(ExpectedConditions.elementToBeClickable(loc_allcharacteristic)).click();
        } catch (Exception e) {
            Log.error("Error - all characteristic link is not clickable", e);
            throw e;
        }
        Log.info("All characteristic is set successfully");
    }

    public void clickOnDiffCharacteristic () throws Exception
    {
        try {
            Log.info("Try to click on different characteristic link");
            waiter
                    .until(ExpectedConditions.elementToBeClickable(loc_diffcharacteristic)).click();
        } catch (Exception e) {
            Log.error("Error - different characteristic link is not clickable", e);
            throw e;
        }
        Log.info("Different characteristic is set successfully");
    }

    public void clickOnDeleteList () throws Exception
    {
        try {
            Log.info("Try to click on Delete List button");
            waiter
                    .until(ExpectedConditions.elementToBeClickable(loc_delelems)).click();
        } catch (Exception e) {
            Log.error("Error - Delete List button is not clickable", e);
            throw e;
        }
        Log.info("Items are deleted from compare page successfully");
    }

    public boolean isOSShown()
    {
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(loc_preloader));
        try {
            Log.info("Try to find OS block at Compare page");
            waiter.until(ExpectedConditions.visibilityOfElementLocated(loc_OSblock));

            Log.info("OS block is present at Compare page");
            return true;

        } catch (Exception e) {
            Log.info("OS block isn't present at Compare page", e);
            return false;
        }
    }


}
