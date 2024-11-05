package WebDriverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;


import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;

public class GetWebDriver {
    public static int timeout = 10; //to set ImplicitWait for the driver
    private static WebDriver webDriver;
    public static final Logger log = LogManager.getLogger(lookup().lookupClass());
    public static WebDriver launchBrowser(String browser) throws Exception {
        initialize_Logging();
        switch (browser.toLowerCase()) {
            case "chrome":
                webDriver=GetChrome.setupChromeDriver(false);
                break;
            case "chrome--headless":
                webDriver=GetChrome.setupChromeDriver(true);
                break;
            case "firefox":
                webDriver=GetFirefox.setupFirefoxDriver(false);
                break;
            case "firefox--headless":
                webDriver=GetFirefox.setupFirefoxDriver(true);
                break;
            case "edge":
                webDriver=GetEdge.setupEdgeDriver(false);
                break;
            case "edge--headless":
                webDriver=GetEdge.setupEdgeDriver(true);
                break;
            default:
                log.warn("Unknown browser specified: '{}'", browser);
                throw new Exception("Unknown browser specified.");
        }
        log.info("Website loaded successfully in {} mode for {}", browser.contains("headless") ? "headless" : "normal", browser);
        return webDriver;
    }
    public static void maximizeWindow(WebDriver Driver) {
        Driver.manage().window().maximize();
        log.debug("Browser window maximized");
    }
    public static void quitBrowser() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
            log.info("Browser instance closed");
        }
    }
    public static void setImplicitWait(WebDriver Driver) {
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        log.debug("Implicit wait of {} seconds set for all elements", timeout);
    }
    private static void initialize_Logging() {
        String logFilePath = "./logs/application.log";
        try (FileWriter writer = new FileWriter(logFilePath, false)) {
            writer.write("");
            log.info("Log file '{}' cleaned successfully.", logFilePath);
        } catch (IOException e) {
            log.error("Failed to clean the log file '{}'. Error: {}", logFilePath, e.getMessage(), e);
        }
    }

}
