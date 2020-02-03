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

    public ComparePage(WebDriver driver) {this.driver = driver;}

    By loc_allcharacteristic = By.xpath("//span[@class='link n-compare-show-controls__all']/span[contains(text(),'все характеристики')]");
    By loc_itemsToCompare = By.cssSelector("div.n-compare-content__line.i-bem.n-compare-content__line_js_inited div.n-compare-cell");


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
        return compare_items.size();
    }

}
