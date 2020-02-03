package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;


public class Lab3Steps {

    private static Logger Log = LogManager.getLogger(Lab3Steps.class);

    WebDriver driver;


    @BeforeClass
    public void init(){

        String browser = System.getProperty("browser"); // Get parameter from maven
        boolean useOptions = Boolean.valueOf(System.getProperty("useOptions"));

        try{
            MutableCapabilities mutableCapabilities = null;
            if (useOptions) {
                mutableCapabilities = OptionsFactory.createOptions(browser);
                driver = WebDriverFactory.createNewDriver(browser, mutableCapabilities);
            } else {
                driver = WebDriverFactory.createNewDriver(browser);
            }
            if (driver==null) {
                Log.fatal("New driver for " + browser +" browser isn't created - browser not supported");
                Assert.fail();
            }
        } catch (Exception e){
            Log.fatal("New driver for " + browser +" browser isn't created");
            Assert.fail();
        }
    }


//    @Parameters({"hostname"})
    @Test
    public void whenComparePopUpIsShown() throws Exception {
        String hostname = "https://market.yandex.ru/";

        Log.info("Try to get " + hostname);
        driver.manage().window().maximize();

        try {
//            driver.get(hostname);
            driver.get("https://ya.ru/");
            Cookie cookie1 = driver.manage().getCookieNamed("yandex_gid");
            Cookie cookie2 = driver.manage().getCookieNamed("yandexuid");
            driver.get(hostname);
            (new WebDriverWait(driver, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@id, '27903768-tab')]")));
            driver.manage().addCookie(new Cookie("yandex_gid", cookie1.getValue()));
            driver.manage().addCookie(new Cookie("yandexuid", cookie2.getValue()));
            driver.get(hostname);

            Log.info(hostname + " was got successfully");
        } catch (Exception e) {
            Log.fatal("Host - " + hostname +" isn't available");
            Assert.fail();
        }

        MarketHome homePage = new MarketHome(driver);
        ElectronicaPage electronicaPage= homePage.clickOnElectronicaMenu();
        MobilePhonePage mobilePhonePage=electronicaPage.clickOnMobilePhone();
        mobilePhonePage.checkRealmeBrand();
        mobilePhonePage.checkXiaomiBrand();
        mobilePhonePage.sortByPrice();
        Assert.assertTrue(mobilePhonePage.addFirstXiaomiToCompare(), "Add Xiaomi To Compare is failed");
        Assert.assertTrue(mobilePhonePage.addFirstRealmeToCompare(), "Add Realme To Compare is failed");
    }

    @Test
    public void checkCountOfItemsIs2() throws Exception {
        String hostname = "https://market.yandex.ru/";

        Log.info("Try to get " + hostname);
        driver.manage().window().maximize();

        try {
//            driver.get(hostname);
            driver.get("https://ya.ru/");
            Cookie cookie1 = driver.manage().getCookieNamed("yandex_gid");
            Cookie cookie2 = driver.manage().getCookieNamed("yandexuid");
            driver.get(hostname);
            (new WebDriverWait(driver, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@id, '27903768-tab')]")));
            driver.manage().addCookie(new Cookie("yandex_gid", cookie1.getValue()));
            driver.manage().addCookie(new Cookie("yandexuid", cookie2.getValue()));
            driver.get(hostname);

            Log.info(hostname + " was got successfully");
        } catch (Exception e) {
            Log.fatal("Host - " + hostname +" isn't available");
            Assert.fail();
        }

        MarketHome homePage = new MarketHome(driver);
        ElectronicaPage electronicaPage= homePage.clickOnElectronicaMenu();
        MobilePhonePage mobilePhonePage=electronicaPage.clickOnMobilePhone();
        mobilePhonePage.checkRealmeBrand();
        mobilePhonePage.checkXiaomiBrand();
        mobilePhonePage.sortByPrice();
        Assert.assertTrue(mobilePhonePage.addFirstXiaomiToCompare(), "Add Xiaomi To Compare is failed");
        Assert.assertTrue(mobilePhonePage.addFirstRealmeToCompare(), "Add Realme To Compare is failed");
        ComparePage comparePage = mobilePhonePage.clickOnCompareBtn();
        Assert.assertEquals(comparePage.getAmountOfItems(), 2, "Amount of items to compare isn't correct");
    }

    @Test
    public void checkOS() throws Exception {
        String hostname = "https://market.yandex.ru/";

        Log.info("Try to get " + hostname);
        driver.manage().window().maximize();

        try {
//            driver.get(hostname);
            driver.get("https://ya.ru/");
            Cookie cookie1 = driver.manage().getCookieNamed("yandex_gid");
            Cookie cookie2 = driver.manage().getCookieNamed("yandexuid");
            driver.get(hostname);
            (new WebDriverWait(driver, 4)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@id, '27903768-tab')]")));
            driver.manage().addCookie(new Cookie("yandex_gid", cookie1.getValue()));
            driver.manage().addCookie(new Cookie("yandexuid", cookie2.getValue()));
            driver.get(hostname);

            Log.info(hostname + " was got successfully");
        } catch (Exception e) {
            Log.fatal("Host - " + hostname +" isn't available");
            Assert.fail();
        }

        MarketHome homePage = new MarketHome(driver);
        ElectronicaPage electronicaPage= homePage.clickOnElectronicaMenu();
        MobilePhonePage mobilePhonePage=electronicaPage.clickOnMobilePhone();
        mobilePhonePage.checkRealmeBrand();
        mobilePhonePage.checkXiaomiBrand();
        mobilePhonePage.sortByPrice();
        Assert.assertTrue(mobilePhonePage.addFirstXiaomiToCompare(), "Add Xiaomi To Compare is failed");
        Assert.assertTrue(mobilePhonePage.addFirstRealmeToCompare(), "Add Realme To Compare is failed");
        ComparePage comparePage = mobilePhonePage.clickOnCompareBtn();
        comparePage.clickOnAllCharacteristic();
        Assert.assertEquals(comparePage.isOSShown(), true, "Error - OS block is not shown for case with All Characteristic, ER - OS block is shown ");
        comparePage.clickOnDiffCharacteristic();
        Assert.assertEquals(comparePage.isOSShown(), false, "Error - OS block is shown for case with Different Characteristic, ER - OS block is NOT shown");
    }


    @Parameters({"browser"})
    @AfterClass
    public void quitBrowser (String browser) {
        if(driver!=null){
            Log.info("Quit from " + browser);
            driver.quit();
        }
    }
}
