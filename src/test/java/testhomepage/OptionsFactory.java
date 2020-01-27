package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsFactory {
    private static Logger Log = LogManager.getLogger(WebDriverFactory.class);

    public static MutableCapabilities createOptions(String webDriverName) throws Exception {

        WebDriverFactory.Browsers browser;

        try {
            Log.info("Try to detect browser " + webDriverName);
            browser = WebDriverFactory.Browsers.valueOf(webDriverName.toUpperCase());
            Log.info(webDriverName + " is recognized browser");
        } catch (Exception e) {
            Log.error(webDriverName + " is NOT recognized browser");
            throw e;
        }

        switch (browser) {
            case FIREFOX: {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                Log.info("Create options for "+ webDriverName + " browser");
                firefoxOptions.setHeadless(true);
                Log.info("Set up Headless options for "+ webDriverName + " browser");
                return firefoxOptions;
            }
            case CHROME: {
                ChromeOptions chromeOptions = new ChromeOptions();
                Log.info("Create options for "+ webDriverName + " browser");
                chromeOptions.setHeadless(true);
                Log.info("Set up Headless options for "+ webDriverName + " browser");
                return chromeOptions;
            }
        }
        return null;
    }

    }
