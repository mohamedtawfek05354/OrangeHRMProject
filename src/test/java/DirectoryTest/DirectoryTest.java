package DirectoryTest;

import ReuseTests.AllureLog4jListener;
import ReuseTests.BaseTest;
import org.example.DirectoryPage.DirectoryPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(AllureLog4jListener.class)
public class DirectoryTest extends BaseTest {
    DirectoryPage DP;
    @Test
    public void Enter_all_Data_in_Excel() throws IOException, InterruptedException {
        DP=new DirectoryPage(BaseTestDriver);
        DP.Click_DirectoryIcon();
        scrollPage(0,2500);
        DP.Enter_Excel_Employees();
    }
}
