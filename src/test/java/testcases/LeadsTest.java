package testcases;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import models.LeadDTO;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeadsPage;
import pages.LoginPage;
import provider.DataProviderFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

@Feature("Tính năng thêm/sửa/xóa menu Lead")
@Story("Thêm/Sửa/Xóa menu Lead")
public class LeadsTest extends BaseTest {
    private LoginPage loginPage;
    private LeadsPage leadsPage;
    private DashboardPage dashboardPage;

    //@Test(priority = 1)
    @Description("Kiểm tra thêm dữ liệu màn Lead")
    @Link(name = "https://jira.anhtester.com/lead/Lead-01", url = "https://jira.anhtester.com/lead/Lead-01")
    @Test(dataProvider = "leadData", dataProviderClass = DataProviderFactory.class)
    public void testAddAndVerifyLead(LeadDTO leadData){
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

        String dateTimeAdd = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
        leadData.setLeadName(leadData.getLeadName() + dateTimeAdd);
        leadData.setEmailAddress(leadData.getEmailAddress() + dateTimeAdd + "@gmail.com");

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();
        leadsPage.fillDataLeads(leadData);
        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.verifyAddLeadSuccessMessage();
        leadsPage.clickIconClosePopupLeadDetail(leadData.getLeadName(), 0);
        leadsPage.searchLeads(leadData.getLeadName());
    }

    @Description("Kiểm tra thêm dữ liệu màn Lead và so sánh lại với màn Edit Lead")
    @Link(name = "https://jira.anhtester.com/lead/Lead-02", url = "https://jira.anhtester.com/lead/Lead-02")
    @Test(dataProvider = "leadData", dataProviderClass = DataProviderFactory.class )
    public void testVerifyEditLead(LeadDTO leadData){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

      //  String dateTimeAdd = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
        leadData.setLeadName("Yến Nhi 2");
        leadData.setEmailAddress("ngocnhi2@gmail.com");

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();
        leadsPage.fillDataLeads(leadData);
        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.verifyAddLeadSuccessMessage();
        leadsPage.clickIconClosePopupLeadDetail(leadData.getLeadName(), 0);
        leadsPage.searchLeads(leadData.getLeadName());
        leadsPage.checkLeadsExists(leadData.getLeadName());
        leadsPage.clickButtonEdit(leadData.getLeadName());
        leadsPage.verifyEditLead(leadData);

    }

    @Description("Kiểm tra sửa dữ liệu màn Lead")
    @Link(name = "https://jira.anhtester.com/lead/Lead-03", url = "https://jira.anhtester.com/lead/Lead-03")
    @Test(dataProvider = "leadData", dataProviderClass = DataProviderFactory.class )
    public void testEditLead(LeadDTO leadData){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

        //  String dateTimeAdd = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
        leadData.setLeadName("Yến Nhi 3");
        leadData.setEmailAddress("ngocnhi3@gmail.com");

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();
        leadsPage.fillDataLeads(leadData);
        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.verifyAddLeadSuccessMessage();
        leadsPage.clickIconClosePopupLeadDetail(leadData.getLeadName(), 0);
        leadsPage.searchLeads(leadData.getLeadName());
        leadsPage.checkLeadsExists(leadData.getLeadName());
        leadsPage.clickButtonEdit(leadData.getLeadName());
        leadsPage.verifyEditLead(leadData);

        LeadDTO leadDataEdit = new LeadDTO(leadData);

        leadDataEdit.setLeadName("Yến Nhi 3");
        leadDataEdit.setEmailAddress("ngocnhi3@gmail.com");
        leadDataEdit.setPhone("0965898989");
        leadDataEdit.setZipCode("98765");
        leadDataEdit.setFlag(0);
        leadDataEdit.setFlagEdit(1);

        leadsPage.fillDataLeads(leadDataEdit);
        leadsPage.clickButtonSave();
        leadsPage.verifyUpdateLeadSuccessMessage();
        leadsPage.clickIconClosePopupLeadDetail(leadDataEdit.getLeadName(), 0);
        leadsPage.searchLeads(leadDataEdit.getLeadName());
        leadsPage.checkLeadsExists(leadDataEdit.getLeadName());

    }


    @Description("Kiểm tra xóa dữ liệu màn Lead")
    @Link(name = "https://jira.anhtester.com/lead/Lead-04", url = "https://jira.anhtester.com/lead/Lead-04")
    @Test(dataProvider = "leadData", dataProviderClass = DataProviderFactory.class )
    public void testDeleteLead(LeadDTO leadData){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

        //  String dateTimeAdd = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
        leadData.setLeadName("Yến Nhi 4");
        leadData.setEmailAddress("ngocnhi4@gmail.com");

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();
        leadsPage.fillDataLeads(leadData);
        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.verifyAddLeadSuccessMessage();
        leadsPage.clickIconClosePopupLeadDetail(leadData.getLeadName(), 0);
        leadsPage.searchLeads(leadData.getLeadName());
        leadsPage.checkLeadsExists(leadData.getLeadName());

        leadsPage.clickButtonDelete(leadData.getLeadName());
        leadsPage.confirmAlertDelete();
        leadsPage.searchLeads(leadData.getLeadName());
        leadsPage.verifyAfterDeleteLead(leadData.getLeadName());

    }

    @Description("Kiểm tra số lượng Active trong Leads Summary có tăng lên sau khi thêm mới Lead với trạng thái Active")
    @Link(name = "https://jira.anhtester.com/lead/Lead-05", url = "https://jira.anhtester.com/lead/Lead-05")
    @Test(dataProvider = "leadData", dataProviderClass = DataProviderFactory.class )
    public void testAddAndVerifyActive(LeadDTO leadData){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

        //  String dateTimeAdd = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();

        int totalActiveBeforeAdd = Integer.parseInt(leadsPage.getTotalLeadTotalActive());

        leadData.setLeadName("Yến Nhi 5");
        leadData.setEmailAddress("ngocnhi5@gmail.com");

        leadsPage.verifyBtnAddNewLead();
        leadsPage.fillDataLeads(leadData);
        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.verifyAddLeadSuccessMessage();
        leadsPage.clickIconClosePopupLeadDetail(leadData.getLeadName(), 0);
        leadsPage.searchLeads(leadData.getLeadName());
        leadsPage.checkLeadsExists(leadData.getLeadName());

        leadsPage.clickIconLeadsSummary();
        int totalActiveAfterAdd = Integer.parseInt(leadsPage.getTotalLeadTotalActive());

        System.out.println("Before = " + totalActiveBeforeAdd);
        System.out.println("After  = " + totalActiveAfterAdd);

        Assert.assertEquals(totalActiveAfterAdd, totalActiveBeforeAdd + 1, "Số lượng status Active không khớp");

    }
}
