package testhomepage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MobilePhonePage {
    private static Logger Log = LogManager.getLogger(MarketHome.class);

    WebDriver driver;
    WebDriverWait waiter;

    public MobilePhonePage(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(driver, 4);
    }

    By loc_realme = By.xpath("//span[@class='NVoaOvqe58' and contains(text(), 'realme')]");
    By loc_xiaomi = By.xpath("//span[@class='NVoaOvqe58' and contains(text(), 'Xiaomi')]");
    By loc_sortPrice = By.xpath("//a[@class = 'link link_theme_major n-filter-sorter__link i-bem link_js_inited' and contains(text(), 'по цене')]");
    By loc_preloader = By.cssSelector("div.spin2.spin2_size_m.i-bem.spin2_js_inited.spin2_progress_yes");

    By loc_compareXiaomi = By.cssSelector("div[data-bem*='Смартфон Xiaomi']");
    By loc_popupXiaomi = By.xpath("//div[@class='popup-informer__pane popup-informer__pane_type_notify']//div[contains(text(), 'Смартфон Xiaomi') and contains(text(),'добавлен к сравнению')]");

    By loc_comparerealme = By.cssSelector("div[data-bem*='Смартфон realme']");
    By loc_popuprealme = By.xpath("//div[@class='popup-informer__pane popup-informer__pane_type_notify']//div[contains(text(), 'Смартфон realme') and contains(text(),'добавлен к сравнению')]");

    By loc_compareBtn = By.cssSelector("a.button.button_size_m.button_theme_action.i-bem.button_js_inited");


    public void checkRealmeBrand() throws Exception
    {
        try {
            Log.info("Try to add filter by Realme brand");
            waiter
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
            waiter.until(ExpectedConditions.elementToBeClickable(loc_xiaomi)).click();
        } catch (Exception e) {
            Log.error("Error - brand Xiaomi is absent at the page", e);
            throw e;
        }
        Log.info("Filter by Xiaomi brand have been applied");
    }

    public void sortByPrice() throws Exception
    {
        try {
            Log.info("Try to sort result by price");
            waiter.until(ExpectedConditions.elementToBeClickable(loc_sortPrice)).click();
        } catch (Exception e) {
            Log.error("Error with Price filter", e);
            throw e;
        }
        Log.info("Filter by Price have been applied");
    }

    public boolean addFirstXiaomiToCompare() throws Exception
    {
        try {
            Log.info("Try to add first Xiaomi To Compare");
            waiter.until(ExpectedConditions.invisibilityOfElementLocated(loc_preloader));

            List<WebElement> compare_btns = driver.findElements(loc_compareXiaomi);
            compare_btns.get(0).click();

            Log.info("Check pop-up informer");
            waiter.until(ExpectedConditions.visibilityOfElementLocated(loc_popupXiaomi));
            Log.info("Pop-up informer about compare is displayed");
            Log.info("First Xiaomi added to compare successfully");
            return true;

        } catch (Exception e) {
            Log.error("Error when add first Xiaomi To Compare", e);
            return false;
//            throw e;
        }
    }

    public boolean addFirstRealmeToCompare() throws Exception
    {
        try {
            Log.info("Try to add first Realme To Compare");
            waiter.until(ExpectedConditions.invisibilityOfElementLocated(loc_preloader));

            List<WebElement> compare_btns = driver.findElements(loc_comparerealme);
            compare_btns.get(0).click();

            Log.info("Check pop-up informer");
            waiter.until(ExpectedConditions.visibilityOfElementLocated(loc_popuprealme));
            Log.info("Pop-up informer about compare is displayed");
            Log.info("First Realme added to compare successfully");
            return true;

        } catch (Exception e) {
            Log.error("Error when add first Realme To Compare", e);
            return false;
//            throw e;
        }
    }


    public ComparePage clickOnCompareBtn () throws Exception
    {
        try {
            Log.info("Try to click on Compare button");
            waiter
                  .until(ExpectedConditions.elementToBeClickable(loc_compareBtn)).click();
        } catch (Exception e) {
            Log.error("Error - Compare button isn't clickable at the page", e);
            throw e;
        }
        Log.info("Page Compare is opened successfully");
        return new ComparePage(driver);
    }

}
