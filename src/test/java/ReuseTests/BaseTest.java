package ReuseTests;

import WebDriverFactory.GetWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.example.ReusePages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger.*;
import org.openqa.selenium.WebElement;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;


public class BaseTest {
    public ScreenRecorderUtil recorderUtil;
    public static WebDriver BaseTestDriver;
    public static final Logger log = LogManager.getLogger(lookup().lookupClass());

    LoginPage lg;
    @Description("Enter OrangeHRM Demo Website with Username : 'Admin' and Password : 'admin123'")
    @BeforeSuite
    public void OpenBrowser() {
        ConfigLoader configLoader = new ConfigLoader();
        String url = configLoader.getProperty("url");
        String browser = configLoader.getProperty("browser");
        try {
            BaseTestDriver=GetWebDriver.launchBrowser(browser);
            BaseTestDriver.get(url);
        } catch (Exception e) {
            log.error("An error occurred while initializing the browser: {}", browser, e);
        }
    }


    @AfterSuite
    public void CloseBrowser(){
        GetWebDriver.quitBrowser(); // This will close the browser instance only once
    }
    @BeforeClass
    public void RecordScreen(){
        try {
            String TestClass = this.getClass().getSimpleName();
            // Create a screen recorder and start recording
            recorderUtil = new ScreenRecorderUtil(".\\ScreenRecorder\\"+TestClass+".mp4");
            // Start recording in a separate thread
            new Thread(() -> {
                try {
                    recorderUtil.startRecording();
                    log.info("Recording Starts");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod(description = "Take pic for failed testcases")
    public void failedTestCaseScreen(ITestResult TestCaseResult) throws Exception {
        if (TestCaseResult.getStatus()==ITestResult.FAILURE){
            //Take screenshot and add it to a folder and name it with the testcase name
            File SCREENSHOT =Screenshots.takeshots(BaseTestDriver,".\\screenshoots\\"+TestCaseResult.getName()+".png");
            System.out.println("ITestResult.Failure is"+ITestResult.FAILURE);
            System.out.println("TestCaseResult.getStatus()"+TestCaseResult.getStatus());
            //Add the failed test case screenshot in the testing report
            Allure.addAttachment(" Page Screenshot", FileUtils.openInputStream(SCREENSHOT));
        }

    }
    @AfterClass
    public void stoprecord() throws Exception {
        recorderUtil.stopRecording();
        System.out.println("Recording stopped.");
        log.info("Recording stopped.");
    }
    public void scrollPage(int p,int pixels){
        JavascriptExecutor js = (JavascriptExecutor) BaseTestDriver;
        js.executeScript("window.scrollBy("+p+","+pixels+")", "");
    }
    @DataProvider(name = "CSVData")
    public Object[][] CSVData() {
        String filePath = "./src/test/resources/AdminEmployeeData.csv";  // Replace with your file path
        List<String[]> data = CSVDataDriven.readCSVData(filePath);
        Object[][] testData = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            testData[i] = data.get(i);
        }
        return testData;
    }
    @DataProvider(name = "specificLineProvider")
    public Object[][] specificLineProvider() {
        String filePath = "./src/test/resources/AdminEmployeeData.csv";  // Replace with your file path
        int lineNumber = 1;  // Specify the line number you want to access (0-based index)

        // Get the specific line
        String[] line = CSVDataDriven.getSpecificLine(filePath, lineNumber);
        return new Object[][] { { line } };
    }
    public String Openai_API_Connect(String question) {
        try {
            // Get a query from OpenAI API
            return OpenaiAPI.GenerateAPIRequest(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }
}
