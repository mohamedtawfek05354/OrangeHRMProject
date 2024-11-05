package WebDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class GetEdge extends GetWebDriver{
    public static WebDriver setupEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless");
            log.info("Launching Edge browser in headless mode");
        } else {
            log.info("Launching Edge browser");
        }
        WebDriver driver = new EdgeDriver(options);
        maximizeWindow(driver);
        setImplicitWait(driver);
        return driver;
    }
}
