package LoginTest;

import ReuseTests.BaseTest;
import ReuseTests.ConfigLoader;
import ReuseTests.RetryAnalyzer;
import jdk.jfr.Description;
import org.example.ReusePages.LoginPage;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    LoginPage lg;
    @Description("Login with invalid Credentials which were taken from config.properties")
    @Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
    public void Login_with_InValid(){
        ConfigLoader configLoader = new ConfigLoader();
        String username = configLoader.getProperty("Invalidusername");
        String password=configLoader.getProperty("Invalidpassword");
        lg=new LoginPage(BaseTestDriver);
        log.debug("Open login page to enter with invalid credential");
        lg.Assertion_Login("Login");
        lg.Login(username,password);
        log.info("The login with invalid credential");
        lg.Assertion_Invalid();
    }
    @Description("Login with Valid Credentials which were taken from config.properties")
    @Test(priority = 2,retryAnalyzer = RetryAnalyzer.class)
    public void Login_with_Valid(){
        ConfigLoader configLoader = new ConfigLoader();
        String username = configLoader.getProperty("username");
        String password=configLoader.getProperty("password");
        lg=new LoginPage(BaseTestDriver);
        log.debug("Open login page");
        lg.Assertion_Login("Login");
        lg.Login(username,password);
        log.info("The login with valid credential");
        lg.Assertion_CompanyName();
        lg.Assertion_logged_in();

    }


}
