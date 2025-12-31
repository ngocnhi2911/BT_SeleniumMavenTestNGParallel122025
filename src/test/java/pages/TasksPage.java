package pages;

import drivers.DriverManager;
import keywords.WebUI;
import models.TaskDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;


public class TasksPage {
    private By url = By.xpath("https://crm.anhtester.com/admin/authentication");

    //Locator Tasks Page
    private By buttonNewTask = By.xpath("//a[normalize-space()='New Task']");
    private By buttonTasksOverview = By.xpath("//a[normalize-space()='Tasks Overview']");
    private By iconFilter = By.xpath("//div[@id='vueApp']/div[@data-title='Filter by']");
    private By iconSwitchToKanban = By.xpath("//a[normalize-space()='New Task']/following-sibling::a[@data-title='Switch to Kanban']");
    private By headerTasksSummary = By.xpath("//span[normalize-space()='Tasks Summary']");

    //label status of task
    private By labelTaskTotalNotStarted = By.xpath("//span[normalize-space()='Not Started']/preceding-sibling::span");
    private By labelTaskTotalInProgress = By.xpath("//span[normalize-space()='In Progress']/preceding-sibling::span");
    private By labelTaskTotalTesting = By.xpath("//span[normalize-space()='Testing']/preceding-sibling::span");
    private By labelTaskTotalAwaitingFeedback = By.xpath("//span[normalize-space()='Awaiting Feedback']/preceding-sibling::span");
    private By labelTaskTotalComplete = By.xpath("//span[normalize-space()='Complete']/preceding-sibling::span");

    //button
    private By dropdownDatatableTasksLength = By.xpath("//div[@id='tasks_length']/descendant::select");
    private By buttonExport = By.xpath("//div[@id='tasks_length']/following-sibling::div/button[normalize-space()='Export']");
    private By buttonBulkActions = By.xpath("//div[@id='tasks_length']/following-sibling::div/button[normalize-space()='Bulk Actions']");
    private By buttonReload = By.xpath("//div[@id='tasks_length']/following-sibling::div/button[contains(@class,'btn-dt-reload')]");

    //input search
    private By inputSearchTasks = By.xpath("//div[@id='tasks_filter']/descendant::input[@type='search']");

    //table
    private By checkboxCheckAll = By.xpath("//table[@id='tasks']/thead//input[@id='mass_select_all']");
    private By headerId = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='#']");
    private By headerName = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Name']");
    private By headerStatus = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Status']");
    private By headerStartDate = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Start Date']");
    private By headerDueDate = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Due Date']");
    private By headerAssignedTo = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Assigned to']");
    private By headerTags = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Tags']");
    private By headerPriority = By.xpath("//table[@id='tasks']/thead//th[normalize-space()='Priority']");

    private By getFirstRowItemTaskName(String taskName) {
        By xpath = By.xpath("//table[@id='tasks']/descendant::a[normalize-space()='" + taskName + "']");
        return xpath;
    }

    //button
    private By buttonStartTimer(String tasksName) {
        By xpath = By.xpath("//table[@id='tasks']/descendant::a[normalize-space()='" + tasksName + "']/following-sibling::div//a[normalize-space()='Start Timer']");
        return xpath;
    }

    private By buttonEdit(String tasksName) {
        By xpath = By.xpath("//table[@id='tasks']/descendant::a[normalize-space()='" + tasksName + "']/following-sibling::div//a[normalize-space()='Edit']");
        return xpath;
    }

    private By buttonDelete(String tasksName) {
        By xpath = By.xpath("//table[@id='tasks']/descendant::a[normalize-space()='" + tasksName + "']/following-sibling::div//a[normalize-space()='Delete']");
        return xpath;
    }

    private By labelTasksInfo = By.xpath("//div[@id='tasks_info']");
    private By buttonPrevious = By.xpath("//div[@id='tasks_paginate']//li[@id='tasks_previous']");

    private By buttonNumberOfPage(String number) {
        By xpath = By.xpath("//div[@id='tasks_paginate']/descendant::a[normalize-space()='" + number + "']");
        return xpath;
    }

    private By buttonNext = By.xpath("//div[@id='tasks_paginate']//li[@id='tasks_next']");
    private By dropdownNumberOfPage = By.xpath("//div[@id='colvis']/following-sibling::div/select[@id='dt-page-jump-tasks']");

    //Locator Add New Task
    private By headerAddNewTask = By.xpath("//h4[@id='myModalLabel']");
    //checkbox
    private By checkboxPublic = By.xpath("//input[@id='task_is_public']");
    private By labelCheckboxPublic = By.xpath("//label[normalize-space()='Public']");
    private By checkboxBillable = By.xpath("//input[@id='task_is_billable']");
    private By labelCheckboxBillable = By.xpath("//label[@for='task_is_billable']");
    //attach files
    private By textlinkAttachFiles = By.xpath("//a[normalize-space()='Attach Files']");
    private By iconAddMoreFileAttachment = By.xpath("//div[@id='new-task-attachments']/descendant::button[contains(@class,'add_more_attachments')]");

    private By inputChooseFile(String number) {
        By xpath = By.xpath("//div[@id='new-task-attachments']/descendant::input[@type='file' and @name='attachments[" + number + "]']");
        return xpath;
    }

    private By iconDeleteFileAttachment(String number) {
        By xpath = By.xpath("//input[@type='file' and @name='attachments[" + number + "]']/following-sibling::span/button[contains(@class,'remove_attachment')]");
        return xpath;
    }

    //input
    private By inputSubject = By.xpath("//input[@id='name']");
    private By inputHourlyRate = By.xpath("//input[@id='hourly_rate']");
    private By inputStartDate = By.xpath("//input[@id='startdate']");
    private By inputDueDate = By.xpath("//input[@id='duedate']");
    //dropdown Priority
    private By dropdownPriority = By.xpath("//button[@data-id='priority']");

    private By getValuePriority(String priority) {
        By xpath = By.xpath("//button[@data-id='priority']/following-sibling::div/descendant::span[contains(normalize-space(),'" + priority + "')]");
        return xpath;
    }

    //dropdown Repeat every
    private By dropdownRepeatEvery = By.xpath("//button[@data-id='repeat_every']");

    private By getValueRepeatEvery(String repeatEvery) {
        By xpath = By.xpath("//button[@data-id='repeat_every']/following-sibling::div/descendant::span[contains(normalize-space(),'" + repeatEvery + "')]");
        return xpath;
    }

    private By inputRepeatEveryCustom = By.xpath("//input[@id='repeat_every_custom']");
    private By dropdownRepeatEveryCustom = By.xpath("//button[@data-id='repeat_type_custom']");

    private By getValueRepeatEveryCustom(String repeatEveryCustom) {
        By xpath = By.xpath("//button[@data-id='repeat_type_custom']/following-sibling::div/descendant::span[contains(normalize-space(),'" + repeatEveryCustom + "')]");
        return xpath;
    }

    //input Total Cycles
    private By inputTotalCycles = By.xpath("//input[@id='cycles']");
    private By checkboxInfinity = By.xpath("//input[@id='unlimited_cycles']");
   // private By checkboxInfinity = By.xpath("//input[@id='cycles']/following-sibling::div/descendant::label[@for='unlimited_cycles']");
    private By labelInfinity = By.xpath("//label[@for='unlimited_cycles']");
    //dropdown Related To

    private By labelRepeatTo = By.xpath("//label[@for='rel_type']");
    private By dropdownRepeatTo = By.xpath("//button[@data-id='rel_type']");
    private By getValueRepeatTo(String valueRepeatTo) {
        By xpathRepeatTo = By.xpath("//button[@data-id='rel_type']/following-sibling::div//span[normalize-space()='" +valueRepeatTo+ "']");
        return xpathRepeatTo;
    }


    private By labelValueForRepeatTo = By.xpath("//label[@for='rel_id']");
    private By dropdownValueForRepeatTo = By.xpath("//button[@data-id='rel_id']");
    private By inputSearchValueForRepeatTo = By.xpath("//button[@data-id='rel_id']/following-sibling::div//input[@type='search']");
    private By getValueForRepeatTo(String valueForRepeatTo) {
        By xpathValueForRepeatTo = By.xpath("//button[@data-id='rel_id']/following-sibling::div//span[normalize-space()='"+valueForRepeatTo +"']");
        return xpathValueForRepeatTo;
    }


    private By labelAssignees = By.xpath("//label[@for='assignees']");
    private By dropdownAssignees = By.xpath("//button[@data-id='assignees']");
    private By listSelectedDropdownAssignees = By.xpath("//select[@id='assignees']/following-sibling::div//ul//li[@class='selected']");
    private By inputSearchAssignees = By.xpath("//button[@data-id='assignees']/following-sibling::div//input[@type='search']");
    private By getValueAssignees(String valueAssignees) {
        By xpathAssignees = By.xpath("//button[@data-id='assignees']/following-sibling::div//span[normalize-space()='" +valueAssignees+ "']");
        return xpathAssignees;
    }


    private By labelFollowers = By.xpath("//label[@for='followers[]']");
    private By dropdownFollowers= By.xpath("//button[@data-id='followers[]']");
    private By listSelectedDropdownFollowers = By.xpath("//select[@id='followers[]']/following-sibling::div//ul//li[@class='selected']");
    private By inputSearchFollowers = By.xpath("//button[@data-id='followers[]']/following-sibling::div//input[@type='search']");
    private By getValueFollowers(String valueFollowers) {
        By xpathFollowers = By.xpath("//button[@data-id='followers[]']/following-sibling::div//span[normalize-space()='" +valueFollowers+ "']");
        return xpathFollowers;
    }


    private By labelTag = By.xpath("//label[@for='tags']");
    private By inputTag = By.xpath("//div[@id='inputTagsWrapper']//input[@placeholder='Tag']");
    private By listdropdownTag = By.xpath("//div[@id='inputTagsWrapper']//ul[@id='ui-id-2']//div");
    private By getValueTag (String valueTag ) {
        By xpathTag  = By.xpath("//input[@id='tags']/following-sibling::ul[@id='ui-id-2']//div[normalize-space()='" +valueTag+ "']");
        return xpathTag ;
    }
    private By iconCloseTag = By.xpath("//a[@class='tagit-close' and normalize-space()='×']");

    private By inputDescription = By.xpath("//textarea[@id='description']");
    private By iframeDescription = By.xpath("//iframe[@id='description_ifr']");
    private By inputDescriptionFrame = By.xpath("//body[@id='tinymce']/p");

    //button
    private By buttonClose = By.xpath("//div[contains(@id,'task_modal')]/descendant::button[normalize-space()='Close']");
    private By buttonSave = By.xpath("//div[contains(@id,'task_modal')]/descendant::button[normalize-space()='Save']");

    //icon close popup
    private By iconClosePopupTaskDetail(String headerTaskDetail) {
        By xpath = By.xpath("//h4[contains(normalize-space(),'" + headerTaskDetail + "')]/preceding-sibling::button[@aria-label='Close']");
        return xpath;
    }

    //messsage
    private String addTaskSuccessMessage = "Task added successfully.";
    private String updateTaskSuccessMessage = "Task updated successfully.";
    private String deleteTaskSuccessMessage = "Task deleted";

    private By getDeleteTaskSuccessMessage() {
        String xpathDeleteTaskMessage = "//div[@id='alert_float_1']/descendant::span[@class='alert-title' and normalize-space()='" + deleteTaskSuccessMessage + "']";
        return By.xpath(xpathDeleteTaskMessage);
    }

    private By iconCloseAddTaskSuccessMessage = By.xpath("//span[@class='alert-title' and text()='Task added successfully.']/preceding-sibling::button[@class='close']");
    private By iconCloseUpdateTaskSuccessMessage = By.xpath("//span[@class='alert-title' and text()='Task updated successfully.']/preceding-sibling::button[@class='close']");
    private By iconCloseDeleteTaskSuccessMessage = By.xpath("//span[@class='alert-title' and text()='Task deleted']/preceding-sibling::button[@class='close']");

    private By alertErrorMessageRequired = By.xpath("//p[@id='name-error' and text()='This field is required.']");

    public void clickBtnAddNewTask(){
        //click button New Lead
        // driver.findElement(By.xpath(buttonNewTasks)).click();
        WebUI.clickElement(buttonNewTask);
        Assert.assertTrue(WebUI.checkExistsElement(headerAddNewTask, 2), "Mở popup Add New Task không thành công");

    }


    public void addNewTasks (TaskDTO taskData){

        //checkbox
        if (taskData.getCheckedCheckbox() == 1) {
            WebUI.clickElement(labelCheckboxPublic);
        }
        if (taskData.getCheckedCheckbox() == 0) {
            WebUI.clickElement(labelCheckboxBillable);
        }

        //input
        WebUI.setTextElement(inputSubject, taskData.getTaskName());
        WebUI.clearTextElement(inputHourlyRate);
        WebUI.setTextElement(inputHourlyRate, taskData.getHourlyRate());
        WebUI.clearTextElement(inputStartDate);
        WebUI.setTextElement(inputStartDate, taskData.getStartDate());
        WebUI.clickElement(headerAddNewTask);
        WebUI.clearTextElement(inputDueDate);
        WebUI.setTextElement(inputDueDate, taskData.getDueDate());
        WebUI.clickElement(headerAddNewTask);

        //Priority
        WebUI.clickElement(dropdownPriority);
        WebUI.clickElement(getValuePriority(taskData.getPriority()));

        //Repeat every
        WebUI.clickElement(dropdownRepeatEvery);
        WebUI.clickElement(getValueRepeatEvery(taskData.getRepeatEvery()));
//        if (taskData.getRepeatEvery().equals("Custom")) {
//            WebUI.clearTextElement(inputRepeatEveryCustom);
//            WebUI.setTextElement(inputRepeatEveryCustom, taskData.getNumberRepeatEveryCustom());
//            WebUI.clickElement(dropdownRepeatEveryCustom);
//            WebUI.clickElement(getValueRepeatEveryCustom(taskData.getTypeRepeatEveryCustom()));
//        } else if (taskData.getRepeatEvery().equals("Week") || taskData.getRepeatEvery().equals("2 Weeks")
//                || taskData.getRepeatEvery().equals("1 Months") || taskData.getRepeatEvery().equals("2 Months")
//                || taskData.getRepeatEvery().equals("3 Months") || taskData.getRepeatEvery().equals("6 Months")
//                || taskData.getRepeatEvery().equals("1 Year")) {
//            WebUI.clickElement(checkboxInfinity);
//            WebUI.clearTextElement(inputTotalCycles);
//            WebUI.setTextElement(inputTotalCycles, taskData.getTotalCycles());
//        } else {
//            System.out.println("The Type Repeat Every is not exist.");
//        }
        boolean isSelectedInfinity = WebUI.checkSeletedElement(checkboxInfinity);
        if(isSelectedInfinity == true)
        {
            WebUI.clickElement(labelInfinity);
        }


        WebUI.clickElement(inputTotalCycles);
        WebUI.clearTextElement(inputTotalCycles);
        WebUI.setTextElement(inputTotalCycles, taskData.getTotalCycles());

        WebUI.scrollAtBottom(buttonSave);
        //Related To
//        WebUI.clickElement(dropdownRelatedTo);
//        WebUI.clickElement(getValueRelatedTo(taskData.getRelatedTo()));
//        WebUI.clickElement(dropdownTypeRelatedTo);
//        WebUI.setTextElement(inputSearchTypeRelatedTo, taskData.getTypeRelatedTo());
//        WebUI.sleep(1);
//        WebUI.actionClickBase(inputSearchTypeRelatedTo, Keys.END, " ").build().perform();
//        WebUI.clickElement(getValueTypeRelatedTo(taskData.getTypeRelateTo()));

        WebUI.clickElement(dropdownRepeatTo);
        WebUI.clickElement(getValueRepeatTo(taskData.getRelatedTo()));


        WebUI.clickElement(dropdownValueForRepeatTo);
        WebUI.setTextElement(inputSearchValueForRepeatTo, taskData.getTypeRelatedTo());
        WebUI.sleep(2);
        WebUI.setTextElement(inputSearchValueForRepeatTo, " ");
        WebUI.clickElement(getValueForRepeatTo(taskData.getTypeRelatedTo()));
        WebUI.sleep(2);

        //Assignees
        WebUI.clickElement(dropdownAssignees);
        WebUI.setTextElement(inputSearchAssignees, taskData.getAssignee());
        WebUI.clickElement(getValueAssignees(taskData.getAssignee()));

        //Followers
        WebUI.clickElement(dropdownFollowers);
        WebUI.setTextElement(inputSearchFollowers, taskData.getFollower());
        WebUI.clickElement(getValueFollowers(taskData.getFollower()));
        WebUI.clickElement(dropdownFollowers);

        //input
        WebUI.setTextAndKeyElement(inputTag, taskData.getTag(), Keys.ENTER);
        WebUI.clickElement(labelTag);
        WebUI.clickElement(labelTag);

        //iframe
        WebUI.clickElement(inputDescription);
        WebUI.switchToFrame(iframeDescription);
        WebUI.setTextElement(inputDescriptionFrame, taskData.getDescription());
        WebUI.switchToParentFrame();


        // Check sau khi save có lỗi required không
        List<WebElement> errorsRequired = WebUI.getWebElements(alertErrorMessageRequired);
        //  Nếu tìm thấy lỗi (>0) thì lấy text ra in vào log
        if (errorsRequired.size() > 0) {
            String errorMsg = "";
            // Duyệt qua tất cả các lỗi tìm thấy
            for (WebElement err : errorsRequired) {
                errorMsg = errorMsg + err.getText();
            }
            Assert.fail("FAILED: Save không thành công! Nội dung lỗi: " + errorMsg);
        }

    }



    public void clickButtonSave(){
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);
    }


    public void clickClosePopupTaskDetail(String taskName, int flagEdit) {
        WebUI.scrollAtTop(iconClosePopupTaskDetail(taskName));
        WebUI.clickElement(iconClosePopupTaskDetail(taskName));
        WebUI.sleep(2);
    }


    public void clickIconCloseAddTaskMessage() {
        WebUI.clickElement(iconCloseAddTaskSuccessMessage);
    }


    public void clickIconCloseUpdateTaskMessage() {
        WebUI.clickElement(iconCloseUpdateTaskSuccessMessage);
    }


    public void searchTasks(String taskName) {
        DriverManager.getDriver().navigate().refresh();
        WebUI.waitForPageLoaded();
        WebUI.setTextElement(inputSearchTasks, taskName);
        WebUI.sleep(1);
    }

    public void verifyTaskExists(String taskName) {
        Assert.assertTrue(WebUI.checkExistsElement(getFirstRowItemTaskName(taskName)), "Không đúng giá trị vừa thêm mới");
        WebUI.sleep(1);
    }


    public void compareFieldAttribute(String expectedValue, By by, String attributeType) {
        String actual = WebUI.getElementAttribute(by, attributeType);
        Assert.assertEquals(actual, expectedValue, "FAIL: Giá trị mong muốn là: " + expectedValue + " nhưng giá trị thực tế là: " + actual
        );
    }

    public void verifyCheckboxSelected(String checkbox) {
        boolean checked = DriverManager.getDriver().findElement(By.xpath(checkbox)).isSelected();
        Assert.assertTrue(checked, "FAILED: Checkbox [" + checked + "] chưa được chọn.");
    }

    public void clickButtonEdit(String taskName) {
        WebUI.moveToElement(getFirstRowItemTaskName(taskName));
        WebUI.sleep(0.5);
        WebUI.clickElement(buttonEdit(taskName));
        WebUI.sleep(0.5);
    }


    public void verifyEditTask(TaskDTO taskData){

        compareFieldAttribute(taskData.getTaskName(), inputSubject, "value");
       // compareFieldAttribute(taskData.getHourlyRate(), inputHourlyRate, "value");
        compareFieldAttribute(taskData.getStartDate(), inputStartDate, "value");
        compareFieldAttribute(taskData.getDueDate(), inputDueDate, "value");
        compareFieldAttribute(taskData.getPriority(), dropdownPriority, "title");
        compareFieldAttribute(taskData.getRepeatEvery(), dropdownRepeatEvery, "title");
        compareFieldAttribute(taskData.getTotalCycles(), inputTotalCycles, "value");
        compareFieldAttribute(taskData.getRelatedTo(), dropdownRepeatTo, "title");
        compareFieldAttribute(taskData.getTypeRelatedTo(), dropdownValueForRepeatTo, "title");
        //   compareFieldAttribute(tag, LocatorsTasksCRM.inputEditTag, "value");

    }



    public void editTasks (TaskDTO taskData){

        Actions actions = new Actions(DriverManager.getDriver());
      //  Robot robot = new Robot();

        boolean isSelectedPublic = WebUI.checkSeletedElement(checkboxPublic);
        if(isSelectedPublic == true)
        {
            WebUI.actionClick(checkboxPublic);
        }

        boolean isSelectedBillable = WebUI.checkSeletedElement(checkboxBillable);
        if(isSelectedBillable == false)
        {
            WebUI.actionClick(checkboxBillable);
        }

        WebUI.actionClick(inputSubject);
        WebUI.clearTextElement(inputSubject);
        WebUI.actionSendKeys(inputSubject, taskData.getTaskName());

        WebUI.actionClick(inputHourlyRate);
        WebUI.clearTextElement(inputHourlyRate);
        WebUI.actionSendKeys(inputHourlyRate, taskData.getHourlyRate());


        WebUI.actionClick(inputStartDate);
        WebUI.clearTextElement(inputStartDate);
        WebUI.actionSendKeys(inputStartDate, taskData.getStartDate());

        WebUI.actionClick(inputDueDate);
        WebUI.clearTextElement(inputDueDate);
        WebUI.actionSendKeys(inputDueDate, taskData.getDueDate());

        WebUI.actionClick(dropdownPriority);
        WebUI.actionClick(getValuePriority(taskData.getPriority()));

        WebUI.actionClick(dropdownRepeatEvery);
        WebUI.actionClick(getValueRepeatEvery(taskData.getRepeatEvery()));


        WebUI.scrollAtTop(buttonSave);

        WebUI.actionClick(dropdownRepeatTo);
        WebUI.actionClick(getValueRepeatTo(taskData.getRelatedTo()));


        WebUI.actionClick(dropdownValueForRepeatTo);
        WebUI.setTextAndKeyElement(inputSearchValueForRepeatTo, taskData.getTypeRelatedTo(), Keys.ENTER);
        WebUI.sleep(1);
        WebUI.setTextElement(inputSearchValueForRepeatTo, " ");
        WebUI.actionClick(getValueForRepeatTo(taskData.getTypeRelatedTo()));


        //tag
        WebUI.actionClick(iconCloseTag);
        WebUI.actionSendKeys(inputTag, taskData.getTag());
        WebUI.actionClick(labelTag);
        WebUI.sleep(1);
        WebUI.actionClick(labelTag);


        //iframe
        WebUI.switchToFrame(iframeDescription);
        WebUI.sleep(0.5);
        WebUI.actionSendKeys(inputDescriptionFrame, taskData.getDescription());
        WebUI.sleep(0.5);
        WebUI.switchToParentFrame();
        WebUI.sleep(0.5);

    }

}
