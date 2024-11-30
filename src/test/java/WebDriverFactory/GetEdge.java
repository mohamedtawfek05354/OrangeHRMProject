package WebDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class GetEdge extends GetWebDriver{
    public static WebDriver setupEdgeDriver(String mode) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        // Check if the mode contains any supported mode part and apply the corresponding Chrome option
        if (mode != null) {
            for (String supportedMode : MODES) {
                if (mode.toLowerCase().contains(supportedMode)) {
                    options.addArguments(getArgumentForMode(supportedMode));
                    log.info("Launching Edge browser with mode: " + supportedMode);
                }
            }
        }else  {
            log.info("Launching Edge browser in default mode (no special modes enabled).");
        }
        // Create and return the WebDriver
        WebDriver driver = new EdgeDriver(options);
        maximizeWindow(driver);
        setImplicitWait(driver);
        return driver;
    }

}
