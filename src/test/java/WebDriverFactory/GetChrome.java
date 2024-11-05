package WebDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetChrome extends GetWebDriver {
    public static WebDriver setupChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless");
            log.info("Launching Chrome browser in headless mode");
        } else {
            log.info("Launching Chrome browser");
        }
        WebDriver driver = new ChromeDriver(options);
        maximizeWindow(driver);
        setImplicitWait(driver);
        return driver;
    }
}
