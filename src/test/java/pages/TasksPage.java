package pages;

import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;


public class TasksPage {
    private By url = By.xpath("https://crm.anhtester.com/admin/authentication");

    //Locators for Login Page
    private By headerLoginPage = By.xpath("//h1[normalize-space() = 'Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By checkboxRememberMe = By.xpath("//input[@id='remember']");
    private By labelRememberMe = By.xpath("//label[@for='remember']");
    private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot Password?']");
    private By errorMessageInvalidEmailOrPassword = By.xpath("//div[@id='alerts']/div");
    private By alertErrorMessageEmailRequired = By.xpath("//div[text()='The Email Address field is required.']");
    private By alertErrorMessagePasswordRequired = By.xpath("//div[text()='The Password field is required.']");

    //Locators for menu Tasks
    private By menuTasks = By.xpath("//ul[@id='side-menu']//span[@class='menu-text' and normalize-space()='Tasks']");

    //Locators for Task Page
    private By urlTasks = By.xpath("https://crm.anhtester.com/admin/tasks");
    private By headerTasksPage = By.xpath("//span[normalize-space()='Tasks Summary']");
    private By buttonNewTasks = By.xpath("//a[normalize-space()='New Task']");


    private By inputSearch = By.xpath("//div[@id='tasks_filter']//input[@type='search']");
    // Tìm kiếm phần tử đầu tiên của bảng
    private By firstRowItemTasks = By.xpath("//table[@id='tasks']//tbody/tr[1]/td[3]/a");
    private By iconCloseProfile = By.xpath("//div[@id='task-modal']//button[@class='close']");


    private By firstRow = By.xpath("//table[@id='tasks']//tbody/tr[1]/td[3]");

    private By linkEdit = By.xpath("//table[@id='tasks']/tbody/tr[1]//a[normalize-space()='Edit']");
    private By inputEditTag = By.xpath("//div[@id='inputTagsWrapper']/input[@id='tags']");

    // Locators for Add New Leads Page
    private By headerAddNewTask = By.xpath("//div[@id='_task_modal']//h4[normalize-space()='Add new task']");

    private By checkboxPublic = By.xpath("//input[@id='task_is_public']");
    private By labelPublic = By.xpath("//input[@id='task_is_public']/following-sibling::label");

    private By checkboxBillable= By.xpath("//input[@id='task_is_billable']");
    private By labelBillable = By.xpath("//input[@id='task_is_billable']/following-sibling::label");

    private By linkAttachFiles = By.xpath("//div[@id='new-task-attachments']/preceding-sibling::a");

    private By labelAttachment = By.xpath("//div[@id='new-task-attachments']/descendant::label");
    private By inputAttachment = By.xpath("//div[@id='new-task-attachments']/descendant::input[@name='attachments[0]']");
    private By buttonAddAttachment = By.xpath("//input[@name='attachments[0]']/following::button[contains(@class,'add_more_attachments')]");

    // dùng hàm linh động
    private By buttonRemoteAttachment = By.xpath("(//div[@class='attachment']//button[contains(@class,'remove_attachment')])[1]");

    private By labelSubject = By.xpath("//div[@app-field-wrapper='name']/label");
    private By inputSubject= By.xpath("//input[@id='name']");

    private By labelHourlyRate = By.xpath("//div[@app-field-wrapper='hourly_rate']/label[@for='hourly_rate']");
    private By inputHourlyRate = By.xpath("//input[@id='hourly_rate']");

    private By labelStartDate = By.xpath("//div[@app-field-wrapper='startdate']/label[@for='startdate']");
    private By inputStartDate = By.xpath("//div[@app-field-wrapper='startdate']//input[@id='startdate']");
    private By iconStartDate = By.xpath("//input[@id='startdate']/following-sibling::div");


    private By labelDueDate = By.xpath("//label[@for='duedate']");
    private By inputDueDate = By.xpath("//input[@id='duedate']");
    private By iconDueDate= By.xpath("//input[@id='duedate']/following-sibling::div");


    private By labelPrioryty = By.xpath("//label[@for='priority']");
    private By dropdownPrioryty = By.xpath("//button[@data-id='priority']");
    // dùng hàm, vì giá trị có thể thay đổi, dùng hàm để truyền giá trị vào
    private By getValuePrioryty(String valuePrioryty) {
        By xpathPrioryty = By.xpath("//button[@data-id='priority']/following-sibling::div//span[normalize-space()='" +valuePrioryty+ "']");
        return xpathPrioryty;
    }


    private By labelRepeatEvery = By.xpath("//label[@for='repeat_every']");
    private By dropdownRepeatEvery = By.xpath("//button[@data-id='repeat_every']");
    private By getValueRepeatEvery(String valueRepeatEvery) {
        By xpathRepeatEvery = By.xpath("//button[@data-id='repeat_every']/following-sibling::div//span[normalize-space()='" +valueRepeatEvery+ "']");
        return xpathRepeatEvery;
    }


    private By labelTotalCycles = By.xpath("//label[@for='cycles']");
    private By inputTotalCycles = By.xpath("//input[@id='cycles']");
    private By checkboxInfinity = By.xpath("//input[@id='unlimited_cycles']");
    private By labelInfinity = By.xpath("//label[@for='unlimited_cycles']");


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

    private By labelTaskDescription = By.xpath("//div[@id='inputTagsWrapper']/following::p[text()='Task Description']");
    private By inputDescription = By.xpath("//textarea[@id='description']");
    //private By inputTaskDescription = By.xpath("//iframe[@id='description_ifr']");
    private By iframeDescription = By.xpath("//body[@id='tinymce']");

    private By buttonClose= By.xpath("//button[@type='submit' and text()='Save']/preceding-sibling::button");

    private By buttonSave = By.xpath("//button[@type='submit' and text()='Save']");

    private By alertErrorMessageRequired = By.xpath("//p[@id='name-error' and text()='This field is required.']");

    //icon close popup
    private By iconClosePopupTaskDetail(String headerTaskDetail) {
        By xpath = By.xpath("//h4[contains(normalize-space(),'" + headerTaskDetail + "')]/preceding-sibling::button[@aria-label='Close']");
        return xpath;
    }
    //messsage
    private By addTaskSuccessMessage = By.xpath("//span[@class='alert-title' and text()='Task added successfully.']");
    private By updateTaskSuccessMessage = By.xpath("//span[@class='alert-title' and text()='Task updated successfully.']");

    private By getFirstRowItemTaskName(String taskName) {
        By xpath = By.xpath("//table[@id='tasks']/descendant::a[normalize-space()='" + taskName + "']");
        return xpath;
    }


//    public void verifyMenuTask() throws InterruptedException {
//        //click menu Lead
//        WebUI.clickElement(menuTasks);
//        Thread.sleep(1000);
//
//        Assert.assertTrue(Action_OLD.checkExistsElement(driver, headerTasksPage), "Không truy cập được vào trang Tasks!");
//    }


    public void clickBtnAddNewTask(){
        //click button New Lead
        // driver.findElement(By.xpath(buttonNewTasks)).click();
        WebUI.clickElement(buttonNewTasks);
        Assert.assertTrue(WebUI.checkExistsElement(headerAddNewTask, 2), "Mở popup Add New Task không thành công");

    }

    public  void addNewTasks (String subject, String hourlyRate, String startDate, String dueDate, String priority, String repeatEvery,
                                    String totalCycles, String relatedTo,
                                    String typeRelatedTo, String assignee, String follower, String tag){

        Actions action = new Actions(DriverManager.getDriver());

        boolean isSelectedPublic = WebUI.checkSeletedElement(checkboxPublic);
        if(isSelectedPublic == false)
        {
            WebUI.clickElement(labelPublic);
        }



        boolean isSelectedBillable = WebUI.checkSeletedElement(checkboxBillable);
        if(isSelectedBillable == true)
        {
            WebUI.clickElement(labelBillable);
        }


        WebUI.clickElement(inputSubject);
        WebUI.setTextElement(inputSubject, subject);

        WebUI.clickElement(inputHourlyRate);
        WebUI.clearTextElement(inputHourlyRate);
        WebUI.setTextElement(inputHourlyRate, hourlyRate);

        WebUI.clickElement(inputStartDate);
        WebUI.clearTextElement(inputStartDate);
        WebUI.actionSendKeys(inputStartDate, startDate);


        WebUI.clickElement(inputDueDate);
        WebUI.clearTextElement(inputDueDate);
        WebUI.actionSendKeys(inputDueDate, dueDate);

        WebUI.actionClick(dropdownPrioryty);
        WebUI.actionClick(getValuePrioryty(priority));

        WebUI.clickElement(dropdownRepeatEvery);
        WebUI.clickElement(getValueRepeatEvery(repeatEvery));


        boolean isSelectedInfinity = WebUI.checkSeletedElement(checkboxInfinity);
        if(isSelectedInfinity == true)
        {
            WebUI.clickElement(labelInfinity);
        }


        WebUI.clickElement(inputTotalCycles);
        WebUI.clearTextElement(inputTotalCycles);
        WebUI.setTextElement(inputTotalCycles, totalCycles);

        WebUI.scrollAtBottom(buttonSave);


        WebUI.clickElement(dropdownRepeatTo);
        WebUI.clickElement(getValueRepeatTo(relatedTo));


        WebUI.clickElement(dropdownValueForRepeatTo);
        WebUI.setTextElement(inputSearchValueForRepeatTo, typeRelatedTo);
        WebUI.sleep(2);
        WebUI.setTextElement(inputSearchValueForRepeatTo, " ");
        WebUI.clickElement(getValueForRepeatTo(typeRelatedTo));
        WebUI.sleep(2);

//        WebUI.clickElement(dropdownValueForRepeatTo);
//        WebUI.setTextElement(inputSearchValueForRepeatTo, typeRelatedTo);
//        WebUI.sleep(1);
//        Actions actions = new Actions(driver);
//        actions.click(WebUI.getWebElement(inputSearchValueForRepeatTo)).sendKeys(" ").build().perform();
//        WebUI.clickElement(getValueForRepeatTo(typeRelatedTo));
//        WebUI.sleep(2);


        //Assignees
        WebUI.clickElement(dropdownAssignees);
        List<WebElement> selectedAssignees = WebUI.getWebElements(listSelectedDropdownAssignees);
        for (WebElement cb : selectedAssignees) {
            cb.click();
        }
        WebUI.clickElement(labelAssignees);


        WebUI.clickElement(dropdownAssignees);
        WebUI.setTextElement(inputSearchAssignees, assignee);
        WebUI.clickElement(getValueAssignees(assignee));

        //Followers
        WebUI.clickElement(dropdownFollowers);
        List<WebElement> selectedFollowers = WebUI.getWebElements(listSelectedDropdownFollowers);
        for (WebElement cb : selectedFollowers) {
            cb.click();
        }
        WebUI.clickElement(labelFollowers);


        WebUI.clickElement(dropdownFollowers);
        WebUI.setTextElement(inputSearchFollowers, follower);
        WebUI.clickElement(getValueFollowers(follower));


        // Tag
        WebUI.clickElement(inputTag);
        List<WebElement> allOptions = WebUI.getWebElements(listdropdownTag);
        for (WebElement option : allOptions) {
            if (option.getText().equals(tag)) {
                option.click();   // click vào WebElement tìm được
                break;            // thoát vòng lặp sau khi click
            }
        }


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
    }



    public void searchTasks(String tasksName){
        DriverManager.getDriver().navigate().refresh();
        WebUI.sleep(1);
        WebUI.setTextElement(inputSearch, tasksName);


        Assert.assertTrue(WebUI.checkExistsElement(getFirstRowItemTaskName(tasksName), 2),
                "Không đúng giá trị vừa thêm mới");
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


    public void verifyEditTask(String subject, String hourlyRate, String startDate, String dueDate, String priority, String repeatEvery,
                                      String totalCycles, String relatedTo,
                                      String typeRelatedTo, String assignee, String follower, String tag){

        WebElement firstRows = WebUI.getWebElement(firstRow);
        // B2: Hover chuột vào dòng đầu tiên
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(firstRows).perform();
        WebUI.sleep(3);


        WebUI.clickElement(linkEdit);

        //    verifyCheckboxSelected(LocatorsTasksCRM.checkboxPublic);
        //   verifyCheckboxSelected(checkboxBillable);
//        compareFieldAttribute(subject, inputSubject, "value");
//        compareFieldAttribute(hourlyRate, inputHourlyRate, "value");
//        compareFieldAttribute(startDate, inputStartDate, "value");
//        compareFieldAttribute(dueDate, inputDueDate, "value");
//        compareFieldAttribute(priority, dropdownPrioryty, "title");
//        compareFieldAttribute(repeatEvery, dropdownRepeatEvery, "title");
//    //    verifyCheckboxSelected(checkboxInfinity);
//        compareFieldAttribute(totalCycles, inputTotalCycles, "value");
//        compareFieldAttribute(relatedTo, dropdownRepeatTo, "title");
//        compareFieldAttribute(typeRelatedTo, dropdownValueForRepeatTo, "title");
//        compareFieldAttribute(tag, LocatorsTasksCRM.inputEditTag, "value");

        compareFieldAttribute(subject, inputSubject, "value");
        compareFieldAttribute(hourlyRate, inputHourlyRate, "value");
        compareFieldAttribute(startDate, inputStartDate, "value");
        compareFieldAttribute(dueDate, inputDueDate, "value");
        compareFieldAttribute(priority, dropdownPrioryty, "title");
        compareFieldAttribute(repeatEvery, dropdownRepeatEvery, "title");
        compareFieldAttribute(totalCycles, inputTotalCycles, "value");
        compareFieldAttribute(relatedTo, dropdownRepeatTo, "title");
        compareFieldAttribute(typeRelatedTo, dropdownValueForRepeatTo, "title");
        //   compareFieldAttribute(tag, LocatorsTasksCRM.inputEditTag, "value");

    }


    public void editTasks (String subject, String hourlyRate, String startDate, String dueDate, String priority, String repeatEvery,
                                  String relatedTo, String typeRelatedTo, String tag, String description){

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
        WebUI.actionSendKeys(inputSubject, subject);

        WebUI.actionClick(inputHourlyRate);
        WebUI.clearTextElement(inputHourlyRate);
        WebUI.actionSendKeys(inputHourlyRate, hourlyRate);
      //  Thread.sleep(1000);

        WebUI.actionClick(inputStartDate);
        WebUI.clearTextElement(inputStartDate);
        WebUI.actionSendKeys(inputStartDate, startDate);
      //  Thread.sleep(1000);

        WebUI.actionClick(inputDueDate);
        WebUI.clearTextElement(inputDueDate);
        WebUI.actionSendKeys(inputDueDate, dueDate);
      //  Thread.sleep(1000);

        WebUI.actionClick(dropdownPrioryty);
        WebUI.actionClick(getValuePrioryty(priority));
      //  Thread.sleep(1000);


        WebUI.actionClick(dropdownRepeatEvery);
        WebUI.actionClick(getValueRepeatEvery(repeatEvery));
      //  Thread.sleep(1000);


        WebUI.scrollAtTop(buttonSave);
     //   Thread.sleep(1000);


        WebUI.actionClick(dropdownRepeatTo);
        WebUI.actionClick(getValueRepeatTo(relatedTo));


        WebUI.actionClick(dropdownValueForRepeatTo);
        WebUI.setTextAndKeyElement(inputSearchValueForRepeatTo, typeRelatedTo, Keys.ENTER);
        WebUI.sleep(1);
        WebUI.setTextElement(inputSearchValueForRepeatTo, " ");
        WebUI.actionClick(getValueForRepeatTo(typeRelatedTo));


        //tag
        WebUI.actionClick(iconCloseTag);
        WebUI.actionSendKeys(inputTag, tag);
        WebUI.actionClick(labelTag);
        WebUI.sleep(1);
        WebUI.actionClick(labelTag);


        //iframe
        WebUI.clickElement(inputDescription);
        WebUI.sleep(1);
        DriverManager.getDriver().switchTo().frame("description_ifr");

        WebElement iframeDescriptions = WebUI.getWebElement(iframeDescription);
        iframeDescriptions.sendKeys(description);
        WebUI.sleep(2);
        DriverManager.getDriver().switchTo().parentFrame();
        WebUI.sleep(2);

    }


}
