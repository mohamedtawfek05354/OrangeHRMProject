package org.example.AdminPages;

import io.qameta.allure.Step;
import org.example.ReusePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class AdminPage extends BasePage {

    public AdminPage(WebDriver mydriver) {
        super(mydriver);
    }
    private By AdminIcon=By.xpath("//a[contains(@href,'admin')]");
    private By AddButton=By.xpath("//button[text()=' Add ']");
    private By UserRole=By.xpath("(//div[text()='-- Select --'])[1]");
    private By Status=By.xpath("(//div[text()='-- Select --'])[2]");
    private By UserName=By.xpath("//label[text()='Username']/following::input[1]");
    private By Password=By.cssSelector("//label[text()='Password']/following::input[1]");
    private By confirmPass=By.xpath("//label[text()='Confirm Password']/following::input[1]");
    private By Save=By.xpath("//button[text()=' Save ']");
    private By cancel=By.xpath("//button[text()=' Cancel ']");
    private By SearchForEmployeeUserName=By.xpath("//label[text()='Username']/following::input[1]");
    private By SearchForEmployeeByRole=By.xpath("(//div[text()='-- Select --'])[1]");
    private By SearchForEmployeeStatus=By.xpath("(//div[text()='-- Select --'])[2]");
    private By SearchButton=By.xpath("//button[text()=' Search ']");
    private By ResetButton=By.xpath("//button[text()=' Reset ']");
    private By Message=By.xpath("//*[text()='Success']");
    private By Delete=By.xpath("//button[@type='button' and .//i[contains(@class,'trash')]][1]");
    private By ConfirmDelete=By.xpath("//button[text()=' Yes, Delete ']");
    private By edit=By.xpath("//button[@type='button' and .//i[contains(@class,'pencil-fill')]][1]");
    //--------------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------Functions-----------------------------------------------//
    @Step("Click on Admin Icon")
    public void Click_on_AdminIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(AdminIcon));
        BasePageDriver.findElement(AdminIcon).click();
        log.info("Clicked on Admin Icon");
    }

    @Step("Assert Admin Page Title")
    public void Assertion_AdminPage() {
        Assertion_TitlePage("Admin");
    }

    @Step("Click on Add Employee Button")
    public void Click_on_AddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(AddButton));
        BasePageDriver.findElement(AddButton).click();
        log.info("Clicked on Add Employee Button");
    }

    @Step("Enter User Role")
    public void Enter_UserRole(String Role) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(UserRole));
        BasePageDriver.findElement(UserRole).sendKeys(Role,Keys.DOWN);
        log.info("Entered User Role");
    }

    @Step("Enter Status")
    public void Enter_Status(String status) {
        BasePageDriver.findElement(Status).sendKeys(status, Keys.DOWN);
        log.info("Entered Status");
    }


    public void Enter_UserName(String User_Name){
        BasePageDriver.findElement(UserName).sendKeys(User_Name);
        log.info("Enter User Name: "+User_Name);
    }
    @Step("Enter Password")
    public void Enter_Password(String Pass) {
        BasePageDriver.findElement(Password).sendKeys(Pass);
        log.info("Entered Password");
    }

    @Step("Confirm Password")
    public void Confirm_Pass(String Pass) {
        BasePageDriver.findElement(confirmPass).sendKeys(Pass);
        log.info("Confirmed Password");
    }

    @Step("Click Submit Button")
    public void Click_Submit() throws InterruptedException {
        BasePageDriver.findElement(Save).click();
        log.info("Clicked Submit Button");
    }
    @Step("Search For Employee")
    public void SearchForEmployee(String EmployeeName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchForEmployeeUserName));
        BasePageDriver.findElement(SearchForEmployeeUserName).sendKeys(EmployeeName);
        BasePageDriver.findElement(SearchButton).click();
        log.info("Search For Employee");
    }
    @Step("Search Reset")
    public void SearchReset(){
        BasePageDriver.findElement(ResetButton).click();
        log.info("Search Reset");
    }
    @Step("Asserting message matches expected: {expectedMessage}")
    public void assertMessage(String expectedMessage) {
        log.info("Asserting expected message: '{}'", expectedMessage);
        String actualMessage = BasePageDriver.findElement(By.xpath("//*[text()='" + expectedMessage + "']")).getText();
        if (!actualMessage.equals(expectedMessage)) {
            log.error("Expected message: '{}' but got: '{}'", expectedMessage, actualMessage);
            throw new AssertionError("Alert message does not match. Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
        log.info("Assertion passed for message: '{}'", expectedMessage);
    }

    @Step("Asserting alert message matches expected: {expectedMessage}")
    public void assertAlertMessage(String expectedMessage) {
        log.info("Asserting expected alert message: '{}'", expectedMessage);
        String actualMessage = BasePageDriver.findElement(By.xpath("//*[text()='" + expectedMessage + "']")).getText();
        if (!actualMessage.equals(expectedMessage)) {
            log.error("Expected message: '{}' but got: '{}'", expectedMessage, actualMessage);
            throw new AssertionError("Alert message does not match. Expected: " + expectedMessage + ", but got: " + actualMessage);
        }
        log.info("Assertion passed for alert message: '{}'", expectedMessage);
    }

    @Step("Asserting user name matches expected: {UserName}")
    public void assertUserNameSearch(String UserName) {
        log.info("Asserting user name: '{}'", UserName);
        WebElement actual = BasePageDriver.findElement(By.xpath("//div[text()='" + UserName + "']"));
        wait.until(ExpectedConditions.visibilityOf(actual));
        String actualUserName = actual.getText();
        if (!actualUserName.equals(UserName)) {
            log.error("Expected user name: '{}' but got: '{}'", UserName, actualUserName);
            throw new AssertionError("The searching record does not match. Expected: " + UserName + ", but got: " + actualUserName);
        }
        log.info("Assertion passed for user name: '{}'", UserName);
    }

    @Step("Asserting user role matches expected: {UserRole}")
    public void assertUserRoleSearch(String UserRole) {
        log.info("Asserting user role: '{}'", UserRole);
        WebElement actual = BasePageDriver.findElement(By.xpath("//div[text()='" + UserRole + "']"));
        wait.until(ExpectedConditions.visibilityOf(actual));
        String actualUserRoleName = actual.getText();
        if (!actualUserRoleName.equals(UserRole)) {
            log.error("Expected user role: '{}' but got: '{}'", UserRole, actualUserRoleName);
            throw new AssertionError("The searching record does not match. Expected: " + UserRole + ", but got: " + actualUserRoleName);
        }
        log.info("Assertion passed for user role: '{}'", UserRole);
    }

    @Step("Asserting status matches expected: {Status}")
    public void assertStatusSearch(String Status) {
        log.info("Asserting status: '{}'", Status);
        WebElement actual = BasePageDriver.findElement(By.xpath("//div[text()='" + Status + "']"));
        wait.until(ExpectedConditions.visibilityOf(actual));
        String actualEmployeeStatus = actual.getText();
        if (!actualEmployeeStatus.equals(Status)) {
            log.error("Expected status: '{}' but got: '{}'", Status, actualEmployeeStatus);
            throw new AssertionError("The searching record does not match. Expected: " + Status + ", but got: " + actualEmployeeStatus);
        }
        log.info("Assertion passed for status: '{}'", Status);
    }

    @Step("Deleting the employee record")
    public void DeleteRecord() {
        log.info("Deleting the record");
        BasePageDriver.findElement(Delete).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmDelete));
        BasePageDriver.findElement(ConfirmDelete).click();
        log.info("Record deleted");
    }


}