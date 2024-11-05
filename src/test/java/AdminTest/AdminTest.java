package AdminTest;

import ReuseTests.BaseTest;
import ReuseTests.RetryAnalyzer;
import io.qameta.allure.Description;
import org.example.AdminPages.AdminPage;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdminTest extends BaseTest {
    AdminPage Admin;
    @Test(priority = 1 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("This test case verifies that an admin user can be added successfully with valid user role, status, employee name, username, and password.")
    public void Enter_Admin_Valid(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_Valid test with UserRole: {}, Status: {}, EmployeeName: {}, UserName: {}", userRole, status, employeeName, userName);
        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password);
        Admin.Confirm_Pass(confirmPassword);
        Admin.Click_Submit();
        log.info("Completed Enter_Admin_Valid test.");
    }
    @Test(priority = 2 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("This test case checks the behavior when the password and confirm password do not match.")
    public void Enter_Admin_inValid_Mismatch_Pass_ConfirmPass(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_inValid_Mismatch_Pass_ConfirmPass test with UserRole: {}, Status: {}, EmployeeName: {}, UserName: {}", userRole, status, employeeName, userName);
        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password);
        Admin.Confirm_Pass(confirmPassword);
        Admin.Click_Submit();

        // Assertion for mismatched passwords
        Admin.assertAlertMessage("password doesn't not match.");
        log.info("Completed Enter_Admin_inValid_Mismatch_Pass_ConfirmPass test.");
    }

    @Test(priority = 3 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("This test case verifies the behavior when the password field is left empty.")
    public void Enter_Admin_inValid_empty_Pass(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_inValid_empty_Pass test with UserRole: {}, Status: {}, EmployeeName: {}, UserName: {}", userRole, status, employeeName, userName);
        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password);
        Admin.Confirm_Pass(confirmPassword);
        Admin.Click_Submit();

        // Assertion for required password alert
        Admin.assertAlertMessage("Required");
        log.info("Completed Enter_Admin_inValid_empty_Pass test.");
    }

    @Test(priority = 4 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("This test case verifies the behavior when the employee name field is left empty.")
    public void Enter_Admin_inValid_Empty_Employee(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_inValid_Empty_Employee test with UserRole: {}, Status: {}, UserName: {}", userRole, status, userName);
        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password);
        Admin.Confirm_Pass(confirmPassword);
        Admin.Click_Submit();

        // Assertion for required employee name alert
        Admin.assertAlertMessage("Required");
        log.info("Completed Enter_Admin_inValid_Empty_Employee test.");
    }

    @Test(priority = 5 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("This test case verifies the behavior when the employee username is different from the expected format or value.")
    public void Enter_Admin_inValid_MismatchEmployee(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_inValid_MismatchEmployee test with UserRole: {}, Status: {}, EmployeeName: {}, UserName: {}", userRole, status, employeeName, userName);
        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password);
        Admin.Confirm_Pass(confirmPassword);
        Admin.Click_Submit();

        // Assertion for existing username alert
        Admin.assertAlertMessage("Already exists");
        log.info("Completed Enter_Admin_inValid_MismatchEmployee test.");
    }
    @Test(priority = 6 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("This test case verifies the behavior when the username is left empty, with valid passwords provided.")
    public void Enter_Admin_inValid_Empty_UserName(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_inValid_Empty_UserName test with UserRole: {}, Status: {}, EmployeeName: {}, Password: {}, ConfirmPassword: {}", userRole, status, employeeName, password, confirmPassword);

        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName); // Username is empty
        Admin.Enter_Password(password);
        Admin.Confirm_Pass(confirmPassword);
        Admin.Click_Submit();

        // Assertion for required username alert
        Admin.assertAlertMessage("Required"); // Update the expected alert message as per your application's behavior
        log.info("Completed Enter_Admin_inValid_Empty_UserName test.");
    }
    @Test(priority = 7 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("Verify successful submission with valid data for employee 'Tefa'.")
    public void Enter_Admin_Valid_Tefa(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_Valid_Tefa test with UserRole: {}, Status: {}, EmployeeName: {}, UserName: {}", userRole, status, employeeName, userName);

        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password); // Password: 2200?dd
        Admin.Confirm_Pass(confirmPassword); // Confirm Password: 2200?dd
        Admin.Click_Submit();
        log.info("Completed Enter_Admin_Valid for user Tefa test.");
    }
    @Test(priority = 8 , dataProvider = "CSVData",retryAnalyzer = RetryAnalyzer.class)
    @Description("Verify system behavior on attempting to add a duplicate employee entry for 'Mohamed'.")
    public void Enter_Admin_Duplicate_Entry(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) throws InterruptedException, IOException {
        log.info("Starting Enter_Admin_Duplicate_Entry test with UserRole: {}, Status: {}, EmployeeName: {}, UserName: {}", userRole, status, employeeName, userName);

        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();
        Admin.Click_on_AddEmployee();
        Admin.Enter_UserRole(userRole);
        List<String> Employee=Admin.EmployeesNames("./src/test/resources/Employees Names.xlsx");
        int random= ThreadLocalRandom.current().nextInt(1,14);
        Admin.Enter_EmployeeName(Employee.get(random));
        Admin.Enter_Status(status);
        Admin.Enter_UserName(userName);
        Admin.Enter_Password(password); // Password: 123
        Admin.Confirm_Pass(confirmPassword); // Confirm Password: 123
        Admin.Click_Submit();
        Admin.assertAlertMessage("Already exists");
        log.info("Completed Enter_Admin_Duplicate_Entry test.");
    }
    @Test(priority = 9 , dataProvider = "specificLineProvider")
    @Description("This test searches for an employee using the provided parameters, verifies the employee details, deletes the record, and asserts the success message.")
    public void SearchForEmployee(String userRole, String status, String employeeName, String userName, String password, String confirmPassword) {
        log.info("Starting the test: SearchForEmployee");

        Admin = new AdminPage(BaseTestDriver);
        Admin.Click_on_AdminIcon();
        Admin.Assertion_AdminPage();

        log.info("Searching for employee with name: {}", employeeName);
        Admin.SearchForEmployee(employeeName);

        log.info("Performing assertions for username, user role, and status.");
        Admin.assertUserNameSearch(userName);
        Admin.assertUserRoleSearch(userRole);
        Admin.assertStatusSearch(status);

        log.info("Deleting the record for employee: {}", employeeName);
        Admin.DeleteRecord();
        Admin.assertMessage("Success");

        Admin.SearchReset();
        log.info("Test completed: SearchForEmployee");
    }
}
