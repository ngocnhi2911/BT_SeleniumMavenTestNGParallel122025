package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeadsPage;
import pages.LoginPage;
import pages.TasksPage;

public class TasksTest extends BaseTest {
    // Task
    String taskName = "Yến Nhi Task 1";
    String hourlyRate = "10";
    String startDate = "14-12-2025";
    String dueDate = "18-12-2025";
    String priority = "High";
    String repeatEvery = "1 Month";
    String totalCycles = "56622";
    String relatedTo = "Customer";
    String typeRelatedTo = "Anh Tester 2811A2";
    String assignee = "Admin Anh Tester";
    String follower = "Admin Example";
    String tag = "JSC_NEW";
    String description;

    //Lead
    String status = "Active";
    String source = "Google";
    String assigned = "Admin Anh Tester";
    String tag_lead = "JSC_NEW";
    String leadName = "Yến Nhi 6";
    String address = "Đại Linh";
    String position = "Tester";
    String city = "Việt Nam";
    String emailAddress = "ngocnhi6@gmail.com";
    String state = "Hà Nội";
    String website = "htester.com.vn";
    String country = "Vietnam";
    String phone = "0965898989";
    String zipCode = "0001";
    String leadValue = "12345";
    String language = "Vietnamese";
    String company = "NODO JSC";
    String description_lead = "htest add new lead";
    String dateContacted = "10-11-2025 00:00:00";
    int flag = 1;
    int flagEdit = 0;

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TasksPage tasksPage;
    private LeadsPage leadsPage;


    @Test(priority = 1)
    public void testAddAndVerifyTask(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        tasksPage = dashboardPage.clickMenuTask();

        TasksTest btTasks = new TasksTest();

        btTasks.taskName = "Yến Nhi Task 1";

        tasksPage.clickBtnAddNewTask();
        tasksPage.addNewTasks(btTasks.taskName, hourlyRate, startDate, dueDate, priority, repeatEvery, totalCycles, relatedTo, typeRelatedTo, assignee, follower, tag);
        tasksPage.clickButtonSave();
        tasksPage.clickClosePopupTaskDetail(btTasks.taskName, 0);
        tasksPage.searchTasks(btTasks.taskName);

        tasksPage.verifyEditTask(btTasks.taskName, hourlyRate + ".00", startDate, dueDate, priority, repeatEvery, totalCycles, relatedTo, typeRelatedTo, assignee, follower, tag);

    }

    @Test(priority = 2)
    public void testEditask() throws InterruptedException {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        tasksPage = dashboardPage.clickMenuTask();

        TasksTest btTasks = new TasksTest();

        btTasks.taskName = "Yến Nhi Task 2";

        tasksPage.clickBtnAddNewTask();
        tasksPage.addNewTasks(btTasks.taskName, hourlyRate, startDate, dueDate, priority, repeatEvery, totalCycles, relatedTo, typeRelatedTo, assignee, follower, tag);
        tasksPage.clickButtonSave();
        tasksPage.clickClosePopupTaskDetail(btTasks.taskName, 0);
        tasksPage.searchTasks(btTasks.taskName);

        tasksPage.verifyEditTask(btTasks.taskName, hourlyRate + ".00", startDate, dueDate, priority, repeatEvery, totalCycles, relatedTo, typeRelatedTo, assignee, follower, tag);

        btTasks.taskName = "Yến Nhi Task 2";
        btTasks.hourlyRate  = "20";
        btTasks.startDate  = "20-12-2025";
        btTasks.dueDate  = "25-12-2025";
        btTasks.priority  = "High";
        btTasks.repeatEvery  = "2 Months";
        btTasks.relatedTo  = "Lead";
        btTasks.typeRelatedTo  = "Yến Nhi";
        btTasks.tag = "HTest";
        btTasks.description = "description iframe";

        tasksPage.editTasks(btTasks.taskName, btTasks.hourlyRate, btTasks.startDate, btTasks.dueDate ,  btTasks.priority , btTasks.repeatEvery , btTasks.relatedTo , btTasks.typeRelatedTo, btTasks.tag,  btTasks.description);
        tasksPage.clickButtonSave();
        tasksPage.clickClosePopupTaskDetail(btTasks.taskName, 1);
        tasksPage.searchTasks(btTasks.taskName);

    }

    @Test(priority = 3)
    public void testAddLeadAndTask() throws InterruptedException{
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

        LeadsTest leadsTest = new LeadsTest();

        leadsTest.leadName = "Yến Nhi 1";
        leadsTest.emailAddress = "ngocnhi1@gmail.com";

        leadsPage.clickIconLeadsSummary();
        leadsPage.verifyLeadSummaryDisplay();
        leadsPage.verifyBtnAddNewLead();

        leadsPage.fillDataLeads(status, source, assigned, tag_lead, leadsTest.leadName, address, position, city,
                leadsTest.emailAddress, state, website, country, phone, zipCode, leadValue, language,
                company, description_lead, dateContacted,flag, flagEdit);

        leadsPage.verifyEmailResult(true, "");
        leadsPage.clickButtonSave();
        leadsPage.clickIconClosePopupLeadDetail(leadsTest.leadName, 0);
        leadsPage.searchLeads(leadsTest.leadName);


        tasksPage = dashboardPage.clickMenuTask();

        TasksTest btTasks = new TasksTest();

        btTasks.taskName = leadsTest.leadName + " - " + leadsTest.emailAddress;
        btTasks.relatedTo = "Lead";
       // btTasks.typeRelatedTo = "Yến Nhi";
        btTasks.typeRelatedTo = leadsTest.leadName + " - " + leadsTest.emailAddress;

        tasksPage.clickBtnAddNewTask();
        tasksPage.addNewTasks(btTasks.taskName, hourlyRate, startDate, dueDate, priority, repeatEvery, totalCycles, btTasks.relatedTo, btTasks.typeRelatedTo, assignee, follower, tag);
        tasksPage.clickButtonSave();
        tasksPage.clickClosePopupTaskDetail(btTasks.taskName, 0);
        tasksPage.searchTasks(btTasks.taskName);
    }


}
