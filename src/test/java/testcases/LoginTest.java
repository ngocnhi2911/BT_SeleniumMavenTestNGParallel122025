package testcases;

import common.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.LogUtils;

//Luôn luôn kế thừa class BaseTest ở tất cả các testcase --> vì BaseTest dùng để mở browser chorme

@Feature("Tính năng đăng nhập vào hệ thống")
@Story("Đăng nhập bằng thông tin đăng nhập hợp lệ và không hợp lệ")
public class LoginTest extends BaseTest {

    // Khai báo đối tượng trang, cần page nào thì khai báo ra page đó
    private LoginPage loginPage;


    // Nếu có dòng testName và description thì file report sẽ lấy 2 thông tin này
    // Nếu k có dòng testName và description thì file report sẽ lấy thông tin phương thức là testLoginSusscess

    @Description("Xác minh đăng nhập bằng thông tin hợp lệ")
    @Link(name = "https://jira.anhtester.com/login/CRM-01", url = "https://jira.anhtester.com/login/CRM-01")
    @Test(priority = 1, testName = "Login Success", description = "Testcase_Login_TC1")
  //  @Test(priority = 1)
    public void testLoginSusscess() throws InterruptedException {
        // Khởi tạo đối tượng trang đó
        loginPage = new LoginPage(); //drive là hàm xây dựng đã xây dựng ở trang LoginPage

       // LogUtils.info("Starting test: testLoginSusscess");
        loginPage.loginCRM("admin@example.com", "12345678");
        loginPage.verifyLoginSuccess();
    }

    @Description("Xác minh đăng nhập bằng thông tin không hợp lệ - thiếu dữ liệu Email")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "https://jira.anhtester.com/login/CRM-02", url = "https://jira.anhtester.com/login/CRM-02")
    @Test(priority = 2, testName = "Login Failure With Email Null", description = "Testcase_Login_TC2")
    public void testLoginFailureWithEmailNull(){

        loginPage = new LoginPage();
        loginPage.loginCRM("", "123456");
        loginPage.verifyLoginFailWithEmailNull();
    }

    @Description("Xác minh đăng nhập bằng thông tin không hợp lệ - thiếu dữ liệu Password")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "https://jira.anhtester.com/login/CRM-03", url = "https://jira.anhtester.com/login/CRM-03")
    @Test(priority = 3, testName = "Login Failure With Password Null", description = "Testcase_Login_TC3")
    public void testLoginFailureWithPasswordNull(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFailWithPasswordNull();

    }

    @Description("Xác minh đăng nhập bằng thông tin không hợp lệ - dữ liệu Email sai")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "https://jira.anhtester.com/login/CRM-04", url = "https://jira.anhtester.com/login/CRM-04")
    @Test(priority = 4, testName = "Login Failure With Email Invalid", description = "Testcase_Login_TC4")
    public void testLoginFailureWithEmailInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFailWithEmailOrPasswordInvalid();
        throw new SkipException("Skipping The Test Method ");

    }

    @Description("Xác minh đăng nhập bằng thông tin không hợp lệ - dữ liệu Password sai")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "https://jira.anhtester.com/login/CRM-05", url = "https://jira.anhtester.com/login/CRM-05")
    @Test(priority = 5, testName = "Login Failure With Password Invalid", description = "Testcase_Login_TC5")
    public void testLoginFailureWithPasswordInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123abc");
        loginPage.verifyLoginFailWithEmailOrPasswordInvalid();
    }

}
