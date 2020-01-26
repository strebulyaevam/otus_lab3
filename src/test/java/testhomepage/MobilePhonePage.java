package testhomepage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobilePhonePage {
    private static Logger Log = LogManager.getLogger(MarketHome.class);
    WebDriver driver;

    public MobilePhonePage(WebDriver driver) {this.driver = driver;}

    By loc_mobilephone = By.cssSelector("a[href=\"/catalog--mobilnye-telefony/54726/list?hid=91491\"]");


}
