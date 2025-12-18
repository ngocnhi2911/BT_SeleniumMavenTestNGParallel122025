package pages;

import common.BasePage;
import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    private By buttonDashboardOption = By.xpath("//div[@class='screen-options-btn']");
    private By labelTotalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");


    public void verifyDashboardPageDisplayed(){
        boolean isElementDisplayed = WebUI.getWebElements(buttonDashboardOption).size()>0;
        Assert.assertTrue(isElementDisplayed, "Dashboard Page is not displayed");
    }

    public String getTotalConvertedLeads() {
        String totalConvertedLeads = DriverManager.getDriver().findElement(labelTotalConvertedLeads).getText();
        System.out.println("Total Projects In Progress: " + totalConvertedLeads);
        return totalConvertedLeads;
    }


}
