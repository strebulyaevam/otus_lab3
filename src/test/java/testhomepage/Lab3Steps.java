package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

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
            driver.get(hostname);
            Log.info(hostname + " was got successfully");
        } catch (Exception e) {
            Log.fatal("Host - " + hostname +" isn't available");
            Assert.fail();
        }

        MarketHome homePage = new MarketHome(driver);
        ElectronicaPage electronicaPage= homePage.clickOnElectronicaMenu();
        MobilePhonePage mobilePhonePage=electronicaPage.clickOnMobilePhone();

/*
        try {
            Log.info("Check "+ hostname + " home page");

            Assert.assertTrue(homePage.findMainCourses(), "get Otus home page is failed");
        } catch (Exception e) {
            Log.error("Error - " + hostname + " home page didn't get", e);
            throw e;
        }
*/
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
