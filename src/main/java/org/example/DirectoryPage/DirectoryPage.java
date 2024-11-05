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
    public void Enter_Excel_Employees() throws IOException {
        Map<String,List<String>> Data=new HashMap<>();
        Data.put("Employees Names",collect_Employee());
        BasePageDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        Excel=new ExcelUtilis();
        Excel.ExcelData(Data,"Employees Names");
    }

}
