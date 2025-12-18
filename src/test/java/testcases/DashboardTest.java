package testcases;

import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeadsPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LeadsPage leadsPage;

    @Test
    public void testLabelProjectInProgress() throws InterruptedException {
        loginPage = new LoginPage();
//      loginPage.loginCRM("admin@example.com", "123456");
//      loginPage.verifyLoginSuccess();
//
//      dashboardPage = new DashboardPage(driver);

        dashboardPage = loginPage.loginCRM();

        dashboardPage.verifyDashboardPageDisplayed();

        String totalConvertedLeadsOnDashboard = dashboardPage.getTotalConvertedLeads();

        leadsPage = dashboardPage.clickMenuLead(); //Chuyển sang trang Projects
        leadsPage.clickIconLeadsSummary();

        Assert.assertEquals(totalConvertedLeadsOnDashboard, leadsPage.getTotalLeadTotalCustomer() + " / " + leadsPage.getTotalLeads(),
                "Total Leads on Dashboard does not match total Leads on Leads Page.");

    }

}
