package InfoTest;

import ReuseTests.AllureLog4jListener;
import ReuseTests.BaseTest;
import io.qameta.allure.Description;
import org.example.InfoPage.InfoPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureLog4jListener.class)
public class InfoTest extends BaseTest {

    InfoPage ip;

    @Description("Enter Info Form")
    @Test
    public void Enter_Form() {
        log.info("Starting the test: Enter_Form");

        ip = new InfoPage(BaseTestDriver);

        log.info("Clicking on Info Icon");
        ip.Click_Info_Icon();

        log.info("Clicking on Personal Data");
        ip.Click_On_PersonalData();

        log.info("Entering form data: FirstName={}, MiddleName={}, LastName={}", fname, mname, lname);
        ip.Enter_Form(fname, mname, lname);

        log.info("Clicking Save");
        ip.Click_Save();

        log.info("Test Enter_Form completed successfully");
    }
}
