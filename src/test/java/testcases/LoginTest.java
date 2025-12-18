package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

//Luôn luôn kế thừa class BaseTest ở tất cả các testcase --> vì BaseTest dùng để mở browser chorme
public class LoginTest extends BaseTest {

    // Khai báo đối tượng trang, cần page nào thì khai báo ra page đó
    private LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSusscess() throws InterruptedException {
        // Khởi tạo đối tượng trang đó
        loginPage = new LoginPage(); //drive là hàm xây dựng đã xây dựng ở trang LoginPage

        loginPage.loginCRM("admin@example.com", "123456");
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void testLoginFailureWithEmailNull(){

        loginPage = new LoginPage();
        loginPage.loginCRM("", "123456");
        loginPage.verifyLoginFailWithEmailNull();
    }

    @Test(priority = 3)
    public void testLoginFailureWithPasswordNull(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "");
        loginPage.verifyLoginFailWithPasswordNull();

    }

    @Test(priority = 4)
    public void testLoginFailureWithEmailInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin123@example.com", "123456");
        loginPage.verifyLoginFailWithEmailOrPasswordInvalid();

    }

    @Test(priority = 5)
    public void testLoginFailureWithPasswordInvalid(){
        loginPage = new LoginPage();
        loginPage.loginCRM("admin@example.com", "123abc");
        loginPage.verifyLoginFailWithEmailOrPasswordInvalid();
    }

}
