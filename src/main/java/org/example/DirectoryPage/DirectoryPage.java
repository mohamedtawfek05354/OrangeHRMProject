package org.example.DirectoryPage;

import org.example.ReusePages.BasePage;
import org.example.ReusePages.ExcelUtilis;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectoryPage extends BasePage {
    public DirectoryPage(WebDriver mydriver) {
        super(mydriver);
    }
    JavascriptExecutor js;

    ExcelUtilis Excel;
    private By DirectoryNames=By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']");
    private By DirectoryIcon=By.xpath("//span[text()='Directory']");
    private By EmployeeName=By.xpath("//input[@placeholder='Type for hints...']");
    private By Container_dir=By.xpath("//div[@class='orangehrm-container']");
    private By DirectoryContainer=By.cssSelector("div.orangehrm-container");
    //--------------------------------------------------------------------------------------------------------//
    //------------------------------------------FUN-----------------------------------------------------------//
    public void Click_DirectoryIcon(){
        BasePageDriver.findElement(DirectoryIcon).click();
    }
    private List<String> collect_Employee(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Container_dir));
        wait.until(ExpectedConditions.visibilityOfElementLocated(DirectoryNames));
        // Use JavaScript to scroll the div
        js = (JavascriptExecutor) BasePageDriver;
        // Scroll down by a specific number of pixels in the div using webElement
        js.executeScript("arguments[0].scrollTop += 300;", BasePageDriver.findElement(Container_dir));
        // Capture all suggestions and click the desired one
        List<String> EmployeeNames = new ArrayList<>();
        List<WebElement> Names = BasePageDriver.findElements(DirectoryNames);
        for (WebElement name : Names) {
            EmployeeNames.add(name.getText());
        }
        System.out.println(EmployeeNames);
        log.info("Collect Employee Names: " + EmployeeNames);
        return EmployeeNames;
    }
    public void Enter_Excel_Employees() throws IOException, InterruptedException {
        Map<String, List<String>> data = new HashMap<>();
        List<String> allEmployeeNames = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) BasePageDriver;
        // Locate the scrollable container
        WebElement scrollableContainer = BasePageDriver.findElement(DirectoryContainer);

        long lastHeight = (long) js.executeScript("return arguments[0].scrollHeight;", scrollableContainer);
        while (true) {
            // Scroll to the bottom of the container
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableContainer);

            // Wait for new data to load
            Thread.sleep(1000); // Adjust sleep time based on the data loading speed

            // Collect employee names from the visible container
            List<WebElement> employeeElements = scrollableContainer.findElements(DirectoryNames);
            for (WebElement employee : employeeElements) {
                String employeeName = employee.getText().trim();
                allEmployeeNames.add(employeeName);
                System.out.println(allEmployeeNames);

            }

            // Check if we've reached the end of the container
            long newHeight = (long) js.executeScript("return arguments[0].scrollHeight;", scrollableContainer);
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }

        // Save the collected names to an Excel file
        data.put("Employees Names", allEmployeeNames);
        Excel = new ExcelUtilis();
        Excel.ExcelData(data, "Employees Names");
        log.info("Successfully collected and saved employee names.");


    }

}
