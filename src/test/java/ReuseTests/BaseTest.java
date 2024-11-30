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
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;


public class BaseTest extends GetWebDriver {
    public ScreenRecorderUtil recorderUtil;
    public static WebDriver BaseTestDriver;
    public ConfigLoader configLoader;
    public static String usernameInvalid;
    public static String passwordInvalid;
    public static String username ;
    public static String password;
    public static String fname;
    public static String mname ;
    public static String lname;
    protected String url;
    protected String browser;
    protected String mode;
    public String filepath;
    LoginPage lg;
    @Description("Enter OrangeHRM Demo Website with Username : 'Admin' and Password : 'admin123'")
    @BeforeSuite(dependsOnMethods = "Setup_Configurations")
    public void OpenBrowser() {
         url = configLoader.getProperty("url");
         browser = configLoader.getProperty("browser");
         mode=configLoader.getProperty("mode");
        try {
            BaseTestDriver=GetWebDriver.launchBrowser(browser,mode);
            BaseTestDriver.get(url);
        } catch (Exception e) {
            log.error("An error occurred while initializing the browser: {} ", browser, e);
        }
    }
    @BeforeSuite
    public void Setup_Configurations(){
        configLoader = new ConfigLoader();
        usernameInvalid = configLoader.getProperty("Invalidusername");
        passwordInvalid= configLoader.getProperty("Invalidpassword");
        username = configLoader.getProperty("username");
        password=configLoader.getProperty("password");
        fname= configLoader.getProperty("fname");
        mname = configLoader.getProperty("mname");
        lname=configLoader.getProperty("lname");
    }
    @AfterSuite
    public void CloseBrowser(){
        BaseTestDriver.quit(); // This will close the browser instance only once
        log.info("Browser instance closed");
    }
    @BeforeClass
    public void RecordScreen(){
        try {
            String TestClass = this.getClass().getSimpleName();
            filepath=".\\ScreenRecorder\\"+TestClass+".mp4";
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
    @AfterClass
    public void stoprecord() throws Exception {
        recorderUtil.stopRecording();
        System.out.println("Recording stopped.");
        log.info("Recording stopped.");

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

}
