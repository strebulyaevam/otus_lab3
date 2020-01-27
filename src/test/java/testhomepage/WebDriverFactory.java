package testhomepage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private static Logger Log = LogManager.getLogger(WebDriverFactory.class);

    enum Browsers {
        CHROME,
        FIREFOX
    }

    public static WebDriver createNewDriver(String webDriverName) throws Exception{
        return createNewDriver(webDriverName, null);
    }


    public static WebDriver createNewDriver(String webDriverName, MutableCapabilities options) throws Exception {
        WebDriver driver=null;
        Browsers browser;

        try {
            Log.info("Try to set up browser " + webDriverName);
            browser = Browsers.valueOf(webDriverName.toUpperCase());
            Log.info(webDriverName + " is recognized browser");
        } catch (Exception e) {
            Log.error(webDriverName + " is NOT recognized browser");
            throw e;
        }

        switch (browser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                if (options!=null) {
                    driver = new ChromeDriver((ChromeOptions) options);
                }
                else{
                    driver = new ChromeDriver();
                }
                Log.info("Browser " + webDriverName + " set up successfully");
                break;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                if (options!=null) {
                    driver = new FirefoxDriver((FirefoxOptions) options);
                }
                else{
                    driver = new FirefoxDriver();
                }
                Log.info("Browser " + webDriverName + " set up successfully");
                break;
            }
        }
        return driver;
    }
}
