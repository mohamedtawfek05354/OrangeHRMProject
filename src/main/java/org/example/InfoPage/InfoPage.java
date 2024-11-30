package org.example.InfoPage;

import org.example.ReusePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class InfoPage extends BasePage {
    public InfoPage(WebDriver mydriver) {
        super(mydriver);
    }
    private final By InfoIcon=By.xpath("//a[contains(@href,'viewMyDetails')]");
    private final By PersonalData=By.xpath("//a[text()='Personal Details']");
    private final By ContactDetails=By.xpath("//a[text()='Contact Details']");
    private final By EmergencyContacts=By.xpath("//a[text()='Emergency Contacts']");
    private final By Dependents=By.xpath("//a[text()='Dependents']");
    private final By Immigration=By.xpath("//a[text()='Immigration']");
    private final By Job=By.xpath("//a[text()='Job']");
    private final By Salary=By.xpath("//a[text()='Salary']");
    private final By Report_to=By.xpath("//a[text()='Report-to']");
    private final By Qualifications=By.xpath("//a[text()='Qualifications']");
    private final By Memberships=By.xpath("//a[text()='Memberships']");
    //-------------------------PersonalData Form-----------------------------------------------//
    private final By firstname=By.xpath("//input[@name='firstName']");
    private final By Middle_name=By.xpath("//input[@name='middleName']");
    private final By lastname=By.xpath("//input[@name='lastName']");
    private final By ExpireDate=By.xpath("//label[text()='License Expiry Date']/following::input[1]");
    private final By Nationality=By.xpath("//div[text()='-- Select --']");
    private final By Male=By.xpath("//label[text()='Male']/span");
    private final By Save=By.xpath("//p[text()=' * Required']/following-sibling::button[text()=' Save ']");


    public void Click_Info_Icon(){
        BasePageDriver.findElement(InfoIcon).click();
    }
    public void Click_On_PersonalData(){
        BasePageDriver.findElement(PersonalData).click();
    }
    public void Enter_Form(String fname,String mname,String lname){
        BasePageDriver.findElement(firstname).sendKeys(fname);
        BasePageDriver.findElement(Middle_name).sendKeys(mname);
        BasePageDriver.findElement(lastname).sendKeys(lname);
        BasePageDriver.findElement(Nationality).sendKeys(Keys.DOWN);
        BasePageDriver.findElement(Male).click();

    }
    public void Click_Save(){
        BasePageDriver.findElement(Save).click();
    }



}
