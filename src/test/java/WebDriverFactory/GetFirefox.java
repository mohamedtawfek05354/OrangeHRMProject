package WebDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetFirefox extends GetWebDriver{
    public static WebDriver setupFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("--headless");
            log.info("Launching Firefox browser in headless mode");
        } else {
            log.info("Launching Firefox browser");
        }
        WebDriver driver = new FirefoxDriver(options);
        maximizeWindow(driver);
        setImplicitWait(driver);
        return driver;
    }
}
