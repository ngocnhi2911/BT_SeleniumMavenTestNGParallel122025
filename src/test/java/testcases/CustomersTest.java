package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.DashboardPage;
import pages.LoginPage;

public class CustomersTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private CustomersPage customersPage;
    String customerName;
    @Test
    public void testDeleteCustomers() throws InterruptedException{
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customersPage = dashboardPage.clickMenuCustomers();

        customerName = "a";
      //  customersPage.searchTotalW();
        for (int i = 0; i<5; i++){
            customersPage.searchTotal(customerName);
            customersPage.hoverTotal(customerName);
            customersPage.clickLinkDelete(customerName);
            customersPage.clickAcceptAlert(customerName);
        }
    }
}
