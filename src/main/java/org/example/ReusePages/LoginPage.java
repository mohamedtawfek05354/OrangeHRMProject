package org.example.ReusePages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class LoginPage extends BasePage{
    public LoginPage(WebDriver mydriver) {
        super(mydriver);
    }
    SoftAssert softAssert;
    //-------------------------------------------LOG IN--------------------------------------------------------------------//
    private By Company_title=By.xpath("//a[@href='https://www.orangehrm.com/']");
    private By Login_Title=By.xpath("//h5[text()='Login']");
    private By UserName=By.xpath("//input[@name='username']");
    private By Password=By.xpath("//input[@name='password']");
    private By Submit=By.xpath("//button[text()=' Login ']");
    private By Dashboard_title=By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6");
    private By Invalidalert=By.xpath("//*[text()='Invalid credentials']");
    //---------------------------------------------------------------------------------------------------------------------//
    //------------------------------------------------------- FUNCTIONS----------------------------------------------------//
    @Step("Verifying the login page title is '{LoginTitle}'")
    public void Assertion_Login(String LoginTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Login_Title));
        String Login = BasePageDriver.findElement(Login_Title).getText();
        SoftAssert so = new SoftAssert();
        so.assertEquals(Login, LoginTitle);
        log.info("You are in the login page. Expected title: '{}', Actual title: '{}'", LoginTitle, Login);
        so.assertAll();
    }

    @Step("Logging in with username '{userName}' and password")
    public void Login(String userName, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(UserName));
        BasePageDriver.findElement(UserName).clear();
        BasePageDriver.findElement(UserName).sendKeys(userName);
        BasePageDriver.findElement(Password).clear();
        BasePageDriver.findElement(Password).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Submit));
        BasePageDriver.findElement(Submit).click();
        log.info("You have entered the username and password");
    }

    @Step("Verifying invalid credentials alert")
    public void Assertion_Invalid() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Invalidalert));
        String title = BasePageDriver.findElement(Invalidalert).getText();
        Assert.assertEquals(title, "Invalid credentials");
        log.warn("Invalid login attempt: {}", title);
    }

    @Step("Verifying successful login by checking dashboard title")
    public void Assertion_logged_in() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Dashboard_title));
        String title = BasePageDriver.findElement(Dashboard_title).getText();
        Assert.assertEquals(title, "Dashboard");
        log.info("Successfully logged in, dashboard title: '{}'", title);
    }
    @Step("Verifying the company URL contains 'orangehrm'")
    public void Assertion_CompanyName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Company_title));
        String title = BasePageDriver.findElement(Company_title).getAttribute("href");
        Assert.assertTrue(title.contains("orangehrm"));
    }
}
