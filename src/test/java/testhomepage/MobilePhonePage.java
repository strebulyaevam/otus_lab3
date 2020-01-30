package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

public class MobilePhonePage {
    private static Logger Log = LogManager.getLogger(MarketHome.class);
    WebDriver driver;

    public MobilePhonePage(WebDriver driver) {this.driver = driver;}

    By loc_realme = By.xpath("//span[@class='NVoaOvqe58' and contains(text(), 'realme')]");
    By loc_xiaomi = By.xpath("//span[@class='NVoaOvqe58' and contains(text(), 'Xiaomi')]");
    By loc_sortPrice = By.xpath("//a[@class = 'link link_theme_major n-filter-sorter__link i-bem link_js_inited' and contains(text(), 'по цене')]");
    By loc_imgXiaomi = By.cssSelector("a[title*='Смартфон Xiaomi']");
    By loc_compareXiaomi = By.cssSelector("div[data-bem*='Смартфон Xiaomi']");

    public void unHideElem (WebDriver driver, WebElement element){
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script, element);
    }


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

    public void sortByPrice() throws Exception
    {
        try {
            Log.info("Try to sort result by price");
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.elementToBeClickable(loc_sortPrice)).click();
        } catch (Exception e) {
            Log.error("Error with Price filter", e);
            throw e;
        }
        Log.info("Filter by Price have been applied");
    }

    public void addFirstXiaomiToCompare() throws Exception
    {
        Actions actions = new Actions (driver);

        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        try {
            Log.info("Try to add first Xiaomi To Compare");
            WebElement img = driver.findElement(loc_imgXiaomi);
            WebElement divComp = driver.findElement(loc_compareXiaomi);

            Log.info("Try to add first Xiaomi To Compare");

//            actions.moveToElement(divComp).clickAndHold(divComp).build().perform();
            actions.moveToElement(divComp).perform();
            Log.info("Is elem is displayed? - " + divComp.isDisplayed());
            divComp.click();

/*
            (new WebDriverWait(driver, 4))
                    .until(ExpectedConditions.elementToBeClickable(loc_compareXiaomi)).click();
*/


        } catch (Exception e) {
            Log.error("Error when add first Xiaomi To Compare", e);
            throw e;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
        Log.info("First Xiaomi added to compare successfully");
    }
}
