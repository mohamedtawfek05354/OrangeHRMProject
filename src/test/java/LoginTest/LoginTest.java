package LoginTest;

import ReuseTests.AllureLog4jListener;
import ReuseTests.BaseTest;
import ReuseTests.ConfigLoader;
import ReuseTests.RetryAnalyzer;
import jdk.jfr.Description;
import org.example.ReusePages.LoginPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureLog4jListener.class)
public class LoginTest extends BaseTest {

    LoginPage lg;
    @Description("Login with invalid Credentials which were taken from config.properties")
    @Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
    public void Login_with_InValid(){
        lg=new LoginPage(BaseTestDriver);
        log.debug("Open login page to enter with invalid credential");
        lg.Assertion_Login("Login");
        lg.Login(usernameInvalid,passwordInvalid);
        log.info("The login with invalid credential");
        lg.Assertion_Invalid();
    }
    @Description("Login with Valid Credentials which were taken from config.properties")
    @Test(priority = 2,retryAnalyzer = RetryAnalyzer.class)
    public void Login_with_Valid(){
        lg=new LoginPage(BaseTestDriver);
        log.debug("Open login page");
        lg.Assertion_Login("Login");
        lg.Login(username,password);
        log.info("The login with valid credential");
        lg.Assertion_CompanyName();
        lg.Assertion_logged_in();

    }


}
