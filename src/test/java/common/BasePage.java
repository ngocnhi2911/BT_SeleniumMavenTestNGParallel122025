package common;

import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CustomersPage;
import pages.DashboardPage;
import pages.LeadsPage;
import pages.TasksPage;


public class BasePage {

    public By menuLeads = By.xpath("//span[@class='menu-text' and normalize-space()='Leads']");
    public By menuDashboard = By.xpath("//span[@class='menu-text' and normalize-space()='Dashboard']");
    public By menuTasks = By.xpath("//ul[@id='side-menu']//span[@class='menu-text' and normalize-space()='Tasks']");
    public By menuCustomers = By.xpath("//ul[@id='side-menu']//span[@class='menu-text' and normalize-space()='Customers']");

   // Khai báo các hàm xử lý chung (hàm xử lý click vào các menu Dashboard, Lead, Task)

//    public void clickMenuDashboard(){
//        WebUI.clickElement(driver, menuDashboard);
//
//    }

    public DashboardPage clickMenuDashboard(){
        WebUI.clickElement(menuDashboard);
        return new DashboardPage();
    }

    public LeadsPage clickMenuLead(){
        WebUI.clickElement(menuLeads);
        return new LeadsPage();
    }

    public TasksPage clickMenuTask(){
        WebUI.clickElement(menuTasks);
        return new TasksPage();
    }

    public CustomersPage clickMenuCustomers(){
        WebUI.clickElement(menuCustomers);
        return new CustomersPage();
    }

}
