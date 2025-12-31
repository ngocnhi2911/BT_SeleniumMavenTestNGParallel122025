package testcases;

import common.BaseTest;
import models.LeadDTO;
import models.TaskDTO;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LeadsPage;
import pages.LoginPage;
import pages.TasksPage;
import provider.DataProviderFactory;

public class TasksTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TasksPage tasksPage;
    private LeadsPage leadsPage;


    @Test(dataProvider = "taskData", dataProviderClass = DataProviderFactory.class )
    public void testAddAndVerifyTask(TaskDTO taskData){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        tasksPage = dashboardPage.clickMenuTask();

        taskData.setTaskName("Yến Nhi Task 1");

        tasksPage.clickBtnAddNewTask();
        tasksPage.addNewTasks(taskData);
        tasksPage.clickButtonSave();
        tasksPage.clickIconCloseAddTaskMessage();
        tasksPage.clickClosePopupTaskDetail(taskData.getTaskName(), 0);
        tasksPage.searchTasks(taskData.getTaskName());
        tasksPage.verifyTaskExists(taskData.getTaskName());
        tasksPage.clickButtonEdit(taskData.getTaskName());
        tasksPage.verifyEditTask(taskData);


    }


    @Test(dataProvider = "taskData", dataProviderClass = DataProviderFactory.class )
    public void testEditask(TaskDTO taskData){

        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        tasksPage = dashboardPage.clickMenuTask();

        taskData.setTaskName("Yến Nhi Task 2");

        tasksPage.clickBtnAddNewTask();
        tasksPage.addNewTasks(taskData);
        tasksPage.clickButtonSave();
        tasksPage.clickIconCloseAddTaskMessage();
        tasksPage.clickClosePopupTaskDetail(taskData.getTaskName(), 0);
        tasksPage.searchTasks(taskData.getTaskName());
        tasksPage.verifyTaskExists(taskData.getTaskName());
        tasksPage.clickButtonEdit(taskData.getTaskName());
        tasksPage.verifyEditTask(taskData);

        taskData.setTaskName("Yến Nhi Task 2");
        taskData.setRelatedTo("Lead");
        taskData.setTypeRelatedTo("Yến Nhi");
        taskData.setTag("HTest");

        tasksPage.editTasks(taskData);
        tasksPage.clickButtonSave();
        tasksPage.clickIconCloseUpdateTaskMessage();
        tasksPage.clickClosePopupTaskDetail(taskData.getTaskName(), 0);
        tasksPage.searchTasks(taskData.getTaskName());
        tasksPage.verifyTaskExists(taskData.getTaskName());

    }

    @Test(dataProvider = "leadTaskData", dataProviderClass = DataProviderFactory.class )
    public void testAddLeadAndTask(LeadDTO leadData, TaskDTO taskData){

        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        leadsPage = dashboardPage.clickMenuLead();

      //  LeadDTO leadData = new LeadDTO();

        //  String dateTimeAdd = new SimpleDateFormat("_ddMMyyyy_HHmmss").format(new Date());
        leadData.setLeadName("Yến Nhi 6");
        leadData.setEmailAddress("ngocnhi6@gmail.com");

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

        leadDataEdit.setLeadName("Yến Nhi 6");
        leadDataEdit.setEmailAddress("ngocnhi6@gmail.com");
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

        tasksPage = leadsPage.clickMenuTask();
     //   TaskDTO taskData = new TaskDTO();

        taskData.setTaskName("Yến Nhi Task 3");

        tasksPage.clickBtnAddNewTask();
        tasksPage.addNewTasks(taskData);
        tasksPage.clickButtonSave();
        tasksPage.clickIconCloseAddTaskMessage();
        tasksPage.clickClosePopupTaskDetail(taskData.getTaskName(), 0);
        tasksPage.searchTasks(taskData.getTaskName());
        tasksPage.verifyTaskExists(taskData.getTaskName());
        tasksPage.clickButtonEdit(taskData.getTaskName());
        tasksPage.verifyEditTask(taskData);

        taskData.setTaskName("Yến Nhi Task 3");
        taskData.setRelatedTo("Lead");
        taskData.setTypeRelatedTo("Yến Nhi");
        taskData.setTag("HTest");

        tasksPage.editTasks(taskData);
        tasksPage.clickButtonSave();
        tasksPage.clickIconCloseUpdateTaskMessage();
        tasksPage.clickClosePopupTaskDetail(taskData.getTaskName(), 0);
        tasksPage.searchTasks(taskData.getTaskName());
        tasksPage.verifyTaskExists(taskData.getTaskName());

    }

}
