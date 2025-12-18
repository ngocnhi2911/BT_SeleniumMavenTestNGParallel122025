
package pages;


import common.BasePage;
import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;



public class LeadsPage extends BasePage{

    //Locators for menu Leads
    private By menuLeads = By.xpath("//span[@class='menu-text' and normalize-space()='Leads']");
    private By menuDashboard = By.xpath("//span[@class='menu-text' and normalize-space()='Dashboard']");

    //Locators for Leads Page

    private By labelLeadTotalActive = By.xpath("//span[normalize-space()='Active']/preceding-sibling::span");
    private By labelLeadTotalCustomer = By.xpath("//span[normalize-space()='Customer']/preceding-sibling::span");


    private By urlLeads = By.xpath("https://crm.anhtester.com/admin/leads");
    private By buttonNewLead = By.xpath("//a[normalize-space()='New Lead']");
    private By iconLeadsSummary = By.xpath("//a[@data-title='Leads Summary']");
    private By iconSwitchToKanban = By.xpath("//a[@data-title='Switch to Kanban']");
    private By iconFilterBy = By.xpath("//div[@id='vueApp']//button[@type='button']");
    private By headerLeadPage = By.xpath("//h4[normalize-space()='Leads Summary']");

    private By dropdownLength = By.xpath("//select[@name='leads_length']");
    private By buttonExport = By.xpath("//button[normalize-space()='Export']");
    private By buttonBulkActions = By.xpath("//button[normalize-space()='Bulk Actions']");
    private By iconReload = By.xpath("//button[@title='Reload']");

    private By inputSearch = By.xpath("//div[@id='leads_filter']//input[@type='search']");
    // Tìm kiếm phần tử đầu tiên của bảng
    private By firstRowItemLeads = By.xpath("//table[@id='leads']//tbody/tr[1]/td[3]/a");
    private By iconCloseProfile = By.xpath("//div[@id='lead-modal']//button[@class='close']");

    private By firstRow = By.xpath("//table[@id='leads']//tbody/tr[1]/td[3]");

    //----------Locators for Leads button

    // dùng hàm, vì giá trị có thể thay đổi, dùng hàm để truyền giá trị vào
    private By linkView = By.xpath("//tr[@id='lead_248']//a[normalize-space()='View']");
    //  private By linkEdit = By.xpath("//table[@id='leads']//tbody/tr[1]/td[3]/a/following-sibling::div/a[normalize-space()='Edit']");
    private By linkEdit = By.xpath("//table[@id='leads']/tbody/tr[1]//a[normalize-space()='Edit']");
    private By linkDelete = By.xpath("//table[@id='leads']/tbody/tr[1]//a[normalize-space()='Delete']");

    private By buttonPrevious = By.xpath("//li[@id='leads_previous']/a[text()='Previous']");
    private By buttonNumber = By.xpath("//div[@id='leads_paginate']//li[@class='paginate_button active']/a");
    private By buttonNext = By.xpath("//li[@id='leads_next']/a[text()='Next']");

    private By buttonEdit(String leadName) {
        By xpath = By.xpath("//table[@id='leads']//a[normalize-space()='" + leadName + "']/following-sibling::div/a[normalize-space()='Edit']");
        return xpath;
    }

    //----------Locators for Leads table

    private By inputCheck = By.xpath("//input[@id='mass_select_all']");
    private By headerSTT = By.xpath("//th[@id='th-number']");
    private By headerName = By.xpath("//th[@id='th-name']");
    private By headerCompany = By.xpath("//th[@id='th-company']");
    private By headerEmail = By.xpath("//th[@id='th-email']");
    private By headerPhone = By.xpath("//th[@id='th-phone']");
    private By headerValue= By.xpath("//th[@id='th-lead-value']");
    private By headerTags = By.xpath("//th[@id='th-tags']");
    private By headerAssigned= By.xpath("//th[@id='th-assigned']");
    private By headerStatus = By.xpath("//th[@id='th-status']");
    private By headerSource = By.xpath("//th[@id='th-source']");
    private By headerLastContact= By.xpath("//th[@id='th-last-contact']");
    private By headerCreated = By.xpath("//th[@id='th-date-created']");


    // Locators for Add New Leads Page

    private By headerAddNewLead = By.xpath("//h4[normalize-space()='Add new lead']");

    private By getFirstRowItemLeadName(String leadName) {
        By xpath = By.xpath("//table[@id='leads']//a[normalize-space()='" + leadName + "']");
        return xpath;
    }
//    private By iconClosePopupLeadDetail(String headerLeadDetail) {
//        By xpath = By.xpath("//h4[contains(normalize-space(),'" + headerLeadDetail + "')]/preceding-sibling::button[@aria-label='Close']");
//        return xpath;
//    }


    //bỏ
    private By labelStatus = By.xpath("//label[@for='status']");
    private By dropdownStatus = By.xpath("//button[@data-id='status']");
    private By inputSearchStatus = By.xpath("//button[@data-id='status']/following-sibling::div//input[@type='search']");
    //  private By dropdownListStatus = By.xpath("//div[@app-field-wrapper='status']//span[normalize-space()='Active']");
    private By iconAddNewStatus = By.xpath("//div[@app-field-wrapper='status']//div[@class='input-group-btn']");
    private By getValueStatus(String valueStatus) {
        By xpathStatus = By.xpath("//div[@app-field-wrapper='status']//span[normalize-space()='" + valueStatus +"']");
        return xpathStatus;
    }


    private By labelSource = By.xpath("//label[text()='Source']");
    private By dropdownSource = By.xpath("//button[@data-id='source']");
    private By inputSearchSource = By.xpath("//button[@data-id='source']/following-sibling::div//input[@type='search']");

    // dùng hàm, vì giá trị có thể thay đổi, dùng hàm để truyền giá trị vào
    // private By dropdownListSource = By.xpath("//div[@app-field-wrapper='source']//span[text()='Google']");
    private By iconAddNewSource = By.xpath("//div[@app-field-wrapper='source']//div[@class='input-group-btn']");
    private By getValueSource(String valueSource) {
        By xpathSource = By.xpath("//div[@app-field-wrapper='source']//span[text()='" + valueSource + "']");
        return xpathSource;
    }


    private By labelAssigned = By.xpath("//label[@for='assigned']");
    private By dropdownAssigned = By.xpath("//button[@data-id='assigned']");
    private By inputSearchAssigned = By.xpath("//button[@data-id='assigned']/following-sibling::div//input[@type='search']");
    // private By dropdownListAssigned = By.xpath("//div[@app-field-wrapper='assigned']//span[normalize-space()='Admin Anh Tester']");
    private By getValueAssigned(String valueAssigned) {
        By xpathAssigned= By.xpath("//div[@app-field-wrapper='assigned']//span[normalize-space()='"+valueAssigned+"']");
        return xpathAssigned;
    }

    private By iconTag = By.xpath("//div[@id='inputTagsWrapper']//i[@class='fa fa-tag']");
    private By labelTag = By.xpath("//label[@for='tags']");
    //private By inputTag = By.xpath("//div[@id='inputTagsWrapper']//input[@placeholder='Tag']");
    private By inputTag = By.xpath("//label[@for='tags']/following-sibling::ul//input[@placeholder='Tag']");
    private By listdropdownTag = By.xpath("//div[@id='inputTagsWrapper']//ul[@id='ui-id-2']/li[@class='ui-menu-item']/div");
    private By getValueTag(String valueTag) {
        By xpathTag = By.xpath("//div[@id='inputTagsWrapper']//ul[@id='ui-id-2']//div[text()='" + valueTag + "']");
        return xpathTag;
    }
    // private By inputEditTag = By.xpath("//div[@id='inputTagsWrapper']/input[@id='tags']");
    private By inputEditTag = By.xpath("//input[@id='tags']");
    //   private By inputEditTag = By.xpath("//input[@id='tags']/following-sibling::ul//span[@class='tagit-label']");
    // private By inputEditTag = By.xpath("(//input[@id='tags']/following-sibling::ul)/descendant::span[@class='tagit-label']");
    private By iconCloseTag = By.xpath("//a[@class='tagit-close' and normalize-space()='×']");


    private By labelName = By.xpath("//form[@id='lead_form']//div[@app-field-wrapper='name']/label[@for='name']");
    private By inputName = By.xpath("//form[@id='lead_form']//input[@id='name']");

    private By labelAddress = By.xpath("//div[@app-field-wrapper='address']//label[@for='address']");
    private By inputAddress = By.xpath("//div[@app-field-wrapper='address']//textarea[@id='address']");

    private By labelPosition = By.xpath("//form[@id='lead_form']//label[text()='Position']");
    private By inputPosition = By.xpath("//form[@id='lead_form']//input[@id='title']");

    private By labelCity = By.xpath("//div[@app-field-wrapper='city']//label[@for='city']");
    private By inputCity = By.xpath("//div[@app-field-wrapper='city']//input[@id='city']");

    private By labelEmailAddress = By.xpath("//form[@id='lead_form']//label[@for='email']");
    private By inputEmailAddress = By.xpath("//form[@id='lead_form']//input[@id='email']");

    private By labelState = By.xpath("//div[@app-field-wrapper='state']//label[@for='state']");
    private By inputState = By.xpath("//div[@app-field-wrapper='state']//input[@id='state']");

    private By labelWebsite = By.xpath("//form[@id='lead_form']//label[@for='website']");
    private By inputWebsite = By.xpath("//form[@id='lead_form']//input[@id='website']");

    private By labelCountry = By.xpath("//div[@app-field-wrapper='country']//label[@for='country']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By inputSearchCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input[@type='search']");
    // private By dropdownListCountry = By.xpath("//div[@app-field-wrapper='country']//span[text()='Vietnam']");
    private By getValueCountry(String valueCountry) {
        By xpathCountry = By.xpath("//div[@app-field-wrapper='country']//span[text()='" + valueCountry + "']");
        return xpathCountry;
    }

    private By labelPhone = By.xpath("//div[@app-field-wrapper='phonenumber']//label[@for='phonenumber']");
    private By inputPhone = By.xpath("//div[@app-field-wrapper='phonenumber']//input[@id='phonenumber']");

    private By labelZipCode = By.xpath("//div[@app-field-wrapper='zip']/label[@for='zip']");
    private By inputZipCode = By.xpath("//div[@app-field-wrapper='zip']//input[@id='zip']");

    private By labelLeadValue = By.xpath("//label[@for='lead_value']");
    private By inputLeadValue = By.xpath("//div[@data-toggle='tooltip']//input[@name='lead_value']");
    private By iconLeadValue = By.xpath("/html/body/div[11]/div/div/div[2]/div/div/div[2]/div/div/form/div[1]/div[3]/div[7]/div[6]/div/div");

    private By labelDefaultLanguage = By.xpath("//label[@for='default_language']");
    private By dropdownDefaultLanguage = By.xpath("//button[@data-id='default_language']");
    private By inputSearchDefaultLanguage = By.xpath("//button[@data-id='default_language']/following-sibling::div//input[@type='search']");
    private By dropdownListDefaultLanguage = By.xpath("//div[@id='bs-select-9']//span[normalize-space()='Vietnamese']");
    private By getValueDefaultLanguage(String valueDefaultLanguage) {
        By xpathDefaultLanguage = By.xpath("//button[@data-id='default_language']/following-sibling::div//span[contains(normalize-space(),'" + valueDefaultLanguage + "')]");
        return xpathDefaultLanguage;
    }

    private By labelCompany = By.xpath("//div[@app-field-wrapper='company']//label[@for='company']");
    private By inputCompany= By.xpath("//div[@app-field-wrapper='company']//input[@id='company']");

    private By labelDescription = By.xpath("//label[@for='description']");
    private By inputDescription = By.xpath("//div[@app-field-wrapper='description']//textarea[@id='description']");

    private By labelDateContacted = By.xpath("//label[@for='custom_contact_date']");
    private By inputDateContacted = By.xpath("//input[@id='custom_contact_date']");
    private By iconDateContacted = By.xpath("/html/body/div[11]/div/div/div[2]/div/div/div[2]/div/div/form/div[1]/div[3]/div[9]/div[2]/div/div[1]/div/div/div");

    private By inputLastContacted = By.xpath("//label[@for='lastcontact']/following-sibling::div/input[@id='lastcontact']");


    private By labelCheckboxPublic = By.xpath("//label[@for='lead_public']");
    private By checkboxPublic = By.xpath("//input[@id='lead_public']");

    private By labelCheckboxContactedToday = By.xpath("//label[@for='contacted_today']");
    private By checkboxContactedToday= By.xpath("//input[@id='contacted_today']");

    private By buttonClose= By.xpath("//form[@id='lead_form']/div[2]/button[normalize-space()='Close']");

    private By buttonSave = By.xpath("//button[normalize-space()='Save' and @id='lead-form-submit']");

    private By alertErrorMessageRequired = By.xpath("//p[starts-with(text(), 'This field is required.')]");
    private By alertErrorMessageLeadValue = By.xpath("//p[@id='lead_value-error' and text() = 'Please enter a valid number.']");
    private By alertErrorMessageEmail = By.xpath("//div[@app-field-wrapper='email']//p[@id='email-error']");

    private By headerEditPage = By.xpath("//div[@id='lead-modal']//h4[@class='modal-title']");
    private By tabProfile = By.xpath("//ul[@role='tablist']/li[@class='active']");


    private By addLeadSuccessMessage = By.xpath("//span[@class='alert-title' and normalize-space()='Lead added successfully.']/parent::div");
    private By updateLeadSuccessMessage = By.xpath("//span[@class='alert-title' and normalize-space()='Lead updated successfully.']/parent::div");
    private By deleteLeadSuccessMessage = By.xpath("//span[@class='alert-title' and normalize-space()='Lead deleted']/parent::div");

    private By iconClosePopupLeadDetail(String headerLeadDetail) {
        By xpath = By.xpath("//h4[contains(normalize-space(),'" + headerLeadDetail + "')]/preceding-sibling::button[@aria-label='Close']");
        return xpath;
    }

    public void clickIconLeadsSummary() {
        WebUI.clickElement(iconLeadsSummary);
        WebUI.sleep(2);
        WebUI.waitForPageLoaded();
    }

    public void verifyLeadSummaryDisplay() {
        String actualCurrentUrl = WebUI.getCurrentURL();
        String expectedUrl = "https://crm.anhtester.com/admin/leads";

        Assert.assertTrue((WebUI.checkExistsElement(headerLeadPage, 2) && actualCurrentUrl.equals(expectedUrl)),
                "Failed to navigate to the Lead menu");
    }

    public String getTotalLeadTotalActive() {
        WebUI.waitForElementVisible(labelLeadTotalActive);
        String totalStatusActive = WebUI.getElementText(labelLeadTotalActive);
        return totalStatusActive;
    }

    public String getTotalLeadTotalCustomer() {
        WebUI.waitForElementVisible(labelLeadTotalCustomer);
        String totalStatusCustomer = WebUI.getElementText(labelLeadTotalCustomer);
        return totalStatusCustomer;
    }


    public int getTotalLeads() {
        int totalLeads = Integer.parseInt(getTotalLeadTotalCustomer()) +
                Integer.parseInt(getTotalLeadTotalActive());
        return totalLeads;
    }


    //------------------------------------------------------------------------


    public void verifyBtnAddNewLead(){

        WebUI.clickElement(buttonNewLead);
        WebUI.sleep(1);
        Assert.assertTrue(WebUI.checkExistsElement(headerAddNewLead, 2), "Mở popup Add New Lead không thành công");

        // So sánh header có đúng với kết quả mk mong muốn hay k
        //    softAssert.assertEquals(headerAddNewLead, "Add new lead", "Header Add new lead is not correct");

    }

    public void fillDataLeads(String status, String source, String assigned, String tag, String name, String address, String position,
                                     String city, String emailAddress, String state, String website, String country, String phone, String zipCode,
                                     String leadValue, String language, String company, String description, String dateContacted, int flag, int flagEdit){


        WebUI.clickElement(dropdownStatus);
        WebUI.setTextElement(inputSearchStatus, status);
        WebUI.clickElement(getValueStatus(status));


        WebUI.clickElement(dropdownSource);
        WebUI.setTextElement(inputSearchSource, source);
        WebUI.clickElement(getValueSource(source));

        WebUI.clickElement(dropdownAssigned);
        WebUI.setTextElement(inputSearchAssigned, assigned);
        WebUI.clickElement(getValueAssigned(assigned));

        if (flagEdit == 1) {
            WebUI.clickElement(iconCloseTag);
            WebUI.clearTextElement(inputName);
          //  WebUI.clearTextElement(inputAddress);
            WebUI.clearTextElement(inputPosition);
            WebUI.clearTextElement(inputCity);
            WebUI.clearTextElement(inputEmailAddress);
            WebUI.clearTextElement(inputState);
            WebUI.clearTextElement(inputWebsite);
            WebUI.clearTextElement(inputPhone);
            WebUI.clearTextElement(inputZipCode);
            WebUI.clearTextElement(inputLeadValue);
            WebUI.clearTextElement(inputCompany);
            WebUI.clearTextElement(inputDescription);
            WebUI.clearTextElement(inputLastContacted);

            WebUI.clickElement(labelPhone);
            WebUI.clickElement(labelPhone);

            WebUI.scrollAtBottom(dropdownStatus);

            WebUI.clickElement(inputTag);

        }

        WebUI.setTextAndKeyElement(inputTag,tag, Keys.ENTER);
        WebUI.clickElement(labelTag);
        WebUI.clickElement(labelTag);

        WebUI.setTextElement(inputName, name);
       // WebUI.setTextElement(inputAddress, address);
        WebUI.setTextElement(inputPosition, position);
        WebUI.setTextElement(inputCity, city);
        WebUI.setTextElement(inputEmailAddress, emailAddress);
        WebUI.setTextElement(inputState, state);
        WebUI.setTextElement(inputWebsite, website);

        WebUI.clickElement(dropdownCountry);
        WebUI.setTextElement(inputSearchCountry, country);
        WebUI.clickElement(getValueCountry(country));

        WebUI.setTextElement(inputPhone, phone);
        WebUI.setTextElement(inputZipCode, zipCode);
        WebUI.setTextElement(inputLeadValue, leadValue);

        WebUI.clickElement(dropdownDefaultLanguage);
        WebUI.setTextElement(inputSearchDefaultLanguage, language);
        WebUI.clickElement(getValueDefaultLanguage(language));

        WebUI.setTextElement(inputCompany, company);
        WebUI.setTextElement(inputDescription, description);

       WebUI.clickElement(labelCheckboxPublic);

        if (flagEdit == 0) {
            WebUI.clickElement(labelCheckboxContactedToday);
            WebUI.setTextElement(inputDateContacted, dateContacted);
            WebUI.clickElement(labelPhone);
            WebUI.clickElement(labelPhone);
        } else {
            WebUI.clearTextElement(inputLastContacted);
            WebUI.setTextElement(inputLastContacted, dateContacted);
            WebUI.clickElement(labelPhone);
            WebUI.clickElement(labelPhone);
        }

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
        //   Thread.sleep(1000);
    }


    public void verifyEmailResult(boolean expectValid, String expectedErrorMsg) {
        // List<WebElement> checkErrorEmail = driver.findElements(By.xpath(alertErrorMessageEmail));

        List<WebElement> checkErrorEmail = WebUI.getWebElements(alertErrorMessageEmail);
        System.out.println("checkErrorEmail.size(): " + checkErrorEmail.size());

        if (expectValid) {
            Assert.assertEquals(checkErrorEmail.size(), 0, "Không thể thêm mới vì Email lỗi !!! Vui lòng kiểm tra lại");
        } else {
            // Case: email SAI / TRÙNG → PHẢI có lỗi
            Assert.assertTrue(checkErrorEmail.size() > 0, "Nhập email sai/trùng nhưng không hiển thị lỗi Email !!!");

            String actualMsg = checkErrorEmail.get(0).getText().trim();
            Assert.assertTrue(actualMsg.contains(expectedErrorMsg),
                    "Thông báo lỗi email không đúng. Expected: " + expectedErrorMsg + " | Actual: " + actualMsg);
        }
    }

    public void clickButtonSave(){
        // WebUI.scrollAtBottom(buttonSave);
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);
    }


    public void clickIconClosePopupLeadDetail(String name, int flagEdit){
        WebUI.scrollAtTop(iconClosePopupLeadDetail(name));
        WebUI.clickElement(iconClosePopupLeadDetail(name));
        WebUI.sleep(1);
    }

    public void searchLeads(String leadsName){

        DriverManager.getDriver().navigate().refresh();
        WebUI.sleep(2);
        WebUI.clearTextElement(inputSearch);
        WebUI.setTextElement(inputSearch, leadsName);
        WebUI.sleep(2);
        WebUI.waitForElementVisible(getFirstRowItemLeadName(leadsName));
        WebUI.sleep(2);

        Assert.assertTrue(WebUI.checkExistsElement(getFirstRowItemLeadName(leadsName), 2), "Không đúng giá trị Lead vừa thêm mới");
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

    public void clickButtonEdit(String leadName) {
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(WebUI.getWebElement(getFirstRowItemLeadName(leadName))).perform();
        WebUI.sleep(1);
        WebUI.clickElement(buttonEdit(leadName));
        WebUI.sleep(1);
    }

    public void verifyEditLead(
            String status, String source, String assigned, String tag,String name, String address,
            String position, String city, String emailAddress,
            String state, String website, String country, String phone,
            String zipCode, String leadValue, String language, String company,
            String description, String dateContacted
    ){

        // verify đã vào được màn Edit hay chưa
        List<WebElement> profileTabs = WebUI.getWebElements(tabProfile);
        //  List<WebElement> profileTabs = driver.findElements(By.xpath(tabProfile));
        Assert.assertTrue(profileTabs.size() > 0, "FAILED: Không tìm thấy tab Profile, chưa vào được màn hình Edit Lead.");


        //Verify có vào đúng màn Edit có Tiêu đề edit chứa tên Lead mong muốn theo name
        // String actualTitledit = driver.findElement(By.xpath(headerEditPage)).getText();
//        String actualTitledit = WebUI.getWebElement(headerEditPage).getText();
//        System.out.println("Tiêu đề popup: " + actualTitledit);
//        Assert.assertTrue(actualTitledit.contains(name), "FAILED: Tiêu đề popup không chứa tên Lead! Mở sai pop-up Lead edit hoặc chưa mở được popup edit.");


        compareFieldAttribute(status, dropdownStatus, "title");
        compareFieldAttribute(source, dropdownSource, "title");
        compareFieldAttribute(assigned, dropdownAssigned, "title");
        // compareFieldAttribute(tag, inputEditTag, "value");
        compareFieldAttribute(name, inputName, "value");
       // compareFieldAttribute(address, inputAddress, "value");
        compareFieldAttribute(position, inputPosition, "value");
        compareFieldAttribute(city, inputCity, "value");
        compareFieldAttribute(emailAddress, inputEmailAddress, "value");
        compareFieldAttribute(state, inputState, "value");
        compareFieldAttribute(website, inputWebsite, "value");
        compareFieldAttribute(country, dropdownCountry, "title");
        compareFieldAttribute(phone, inputPhone, "value");
        compareFieldAttribute(zipCode, inputZipCode, "value");
        compareFieldAttribute(leadValue + ".00", inputLeadValue, "value");
        compareFieldAttribute(language, dropdownDefaultLanguage, "title");
        compareFieldAttribute(company, inputCompany, "value");
        compareFieldAttribute(description, inputDescription, "value");
        compareFieldAttribute(dateContacted, inputLastContacted, "value");
        //verifyCheckboxSelected(checkboxPublic);
    }

    public void clickButtonDelete(String leadName){


        WebElement firstRowDelete = WebUI.getWebElement(firstRow);
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(firstRowDelete).perform();
        WebUI.sleep(2);

        WebUI.clickElement(linkDelete);

    }

    public void confirmAlertDelete(){
        DriverManager.getDriver().switchTo().alert().accept();
        WebUI.sleep(2);
    }



}


