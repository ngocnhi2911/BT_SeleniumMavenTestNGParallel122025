package testcases;

import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeadsPage;
import pages.LoginPage;

public class LeadsTest extends BaseTest {
    String status = "Active";
    String source = "Google";
    String assigned = "Admin Anh Tester";
    String tag = "JSC_NEW";
    String leadName = "Yến Nhi 1";
    String address = "Đại Linh";
    String position = "Tester";
    String city = "Việt Nam";
    String emailAddress = "ngocnhi1@gmail.com";
    String state = "Hà Nội";
    String website = "htester.com.vn";
    String country = "Vietnam";
    String phone = "0965898989";
    String zipCode = "0001";
    String leadValue = "12345";
    String language = "Vietnamese";
    String company = "NODO JSC";
    String description = "htest add new lead";
    String dateContacted = "10-11-2025 00:00:00";
    int flag = 1;
    int flagEdit = 0;

    private LoginPage loginPage;
    private LeadsPage leadsPage;
    private DashboardPage dashboardPage;


    @Test(priority = 1)
    public void testAddAndVerifyLead(){
        loginPage = new LoginPage();

        // loginPage.loginCRM();
        //dashboardPage = new DashboardPage();
       // dashboardPage.(....)
        dashboardPage = loginPage.loginCRM();
        // khi gọi loginPage.loginCRM(): nghĩa là hàm loginCRM() sẽ đc chạy và khởi tạo trang DashboardPage (do có câu lệnh return new DashboardPage())
        // dashboardPage = ... --> lấy đối tượng dashboardPage hứng nhận sự khởi tạo đó từ login
        // --> Nghĩa là : Khi login xong sẽ trả về trang dashboard, rồi từ gàm dashborard gọi hàm thao tác tiếp tục trong trang Dashboard mà k cần khởi tạo độc lập
        // Khi login thành công, dashboard mới mang giá trị để đi tiếp. Nếu login k thành công, dashboard sẽ k có giá trị để khởi tạo


        leadsPage = dashboardPage.clickMenuLead();
        LeadsTest leadsTest = new LeadsTest();

        leadsTest.leadName = "Yến Nhi 1";
        leadsTest.emailAddress = "ngocnhi1@gmail.com";

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();

        leadsPage.fillDataLeads(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted,flag, flagEdit);

        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadsTest.leadName, 0);
        leadsPage.searchLeads(leadsTest.leadName);
    }


    @Test(priority = 2)
    public void testVerifyEditLead(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();
        LeadsTest leadsTest = new LeadsTest();

        leadsTest.leadName = "Yến Nhi 2";
        leadsTest.emailAddress = "ngocnhi2@gmail.com";

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();

        leadsPage.fillDataLeads(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted,flag, flagEdit);

        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadsTest.leadName, 0);
        leadsPage.searchLeads(leadsTest.leadName);
        leadsPage.clickButtonEdit(leadsTest.leadName);

        leadsPage.verifyEditLead(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted);


    }


    @Test(priority = 3)
    public void testEditLead(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();
        LeadsTest leadsTest = new LeadsTest();

        leadsTest.leadName = "Yến Nhi 3";
        leadsTest.emailAddress = "ngocnhi3@gmail.com";

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();

        leadsPage.fillDataLeads(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted,flag, flagEdit);

        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadsTest.leadName,0);
        leadsPage.searchLeads(leadsTest.leadName);
        leadsPage.clickButtonEdit(leadsTest.leadName);

        leadsPage.verifyEditLead(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted);

        leadsTest.status = "Active";
        leadsTest.source = "Facebook";
        leadsTest.assigned = "Admin Anh Tester";
        leadsTest.tag = "JSC_NEW";
        leadsTest.leadName = "Yến Nhi 3";
        leadsTest.address = "Thanh Oai";
        leadsTest.position = "Tester";
        leadsTest.city = "Việt Nam";
        leadsTest.emailAddress = "ngocnhi3@gmail.com";
        leadsTest.state = "Hà Nội";
        leadsTest.website = "htester.com.vn";
        leadsTest.country = "Vietnam";
        leadsTest.phone = "0965898989";
        leadsTest.zipCode = "777";
        leadsTest.leadValue = "8888";
        leadsTest.language = "Vietnamese";
        leadsTest.company = "NODO JSC";
        leadsTest.description = "htest add new lead";
        leadsTest.dateContacted = "10-11-2025 00:00:00";
        leadsTest.flag = 0;
        leadsTest.flagEdit = 1;

        leadsPage.fillDataLeads(leadsTest.status, leadsTest.source, leadsTest.assigned, leadsTest.tag, leadsTest.leadName, leadsTest.address, leadsTest.position, leadsTest.city,
                leadsTest.emailAddress, leadsTest.state, leadsTest.website, leadsTest.country, leadsTest.phone, leadsTest.zipCode, leadsTest.leadValue, leadsTest.language,
                leadsTest.company, leadsTest.description, leadsTest.dateContacted,leadsTest.flag, leadsTest.flagEdit);

        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadsTest.leadName,0);
        leadsPage.searchLeads(leadsTest.leadName);

    }


    @Test(priority = 4)
    public void testDeleteLead(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();
        LeadsTest leadsTest = new LeadsTest();

        leadsTest.leadName = "Yến Nhi 4";
        leadsTest.emailAddress = "ngocnhi4@gmail.com";

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();

        leadsPage.fillDataLeads(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted,flag, flagEdit);

        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadsTest.leadName, 0);
        leadsPage.searchLeads(leadsTest.leadName);
        leadsPage.clickButtonDelete(leadsTest.leadName);
        leadsPage.confirmAlertDelete();

    }


    @Test(priority = 5)
    public void testAddAndVerifyActive(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();

        int totalActiveBeforeAdd = Integer.parseInt(leadsPage.getTotalLeadTotalActive());

        LeadsTest leadsTest = new LeadsTest();

        leadsTest.leadName = "Yến Nhi 5";
        leadsTest.emailAddress = "ngocnhi5@gmail.com";
//
//        leadsPage.clickIconLeadsSummary();
//       leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();

        leadsPage.fillDataLeads(status, source, assigned, tag, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description, dateContacted,flag, flagEdit);

        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadName, 0);
        leadsPage.searchLeads(leadName);
        leadsPage.clickIconLeadsSummary();

        int totalActiveAfterAdd = Integer.parseInt(leadsPage.getTotalLeadTotalActive());

        System.out.println("Before = " + totalActiveBeforeAdd);
        System.out.println("After  = " + totalActiveAfterAdd);

        Assert.assertEquals(totalActiveAfterAdd, totalActiveBeforeAdd + 1, "Số lượng status Active không khớp");

    }

}
