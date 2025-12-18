package common;


import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    public SoftAssert softAssert;

  //   private WebDriver driver;  // driver toàn cục, đơn luồng

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browserName) throws InterruptedException {
        // gán @Optional("chrome"): cho trường hợp lỡ như quên truyền tham số thì nó sẽ lấy giá trị mặc định = Chrome


        WebDriver driver; //Khai báo driver cục bộ

        switch (browserName.trim().toLowerCase()) {
            case "chrome":  // Nếu như tham số browserName = Chrome thì khởi tạo initChromeDriver
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default: // nếu k rơi vào 3 trường hợp trên thì chạy với Chrome
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = new ChromeDriver();
        }

        // gọi hàm setDriver và truyền driver cục bộ vô
        // Khi đó driver cục bộ có gtri khởi tạo là gì, thì nó sẽ tự động set vô 1 cái luồng
        // Vd: createDriver tương ứng vs 5 testcase, nó sẽ chạy 5 lần thì nó sẽ khởi tạo 5 cái driver và nó set vô Thread 5 lần
        //
        DriverManager.setDriver(driver);  // tự động set driver cục bộ vô cho từng luồng riêng biệt
        DriverManager.getDriver().manage().window().maximize(); // phải gọi DriverManager.getDriver(): thì nó mới tự động truy xuất driver theo từng luồng
        softAssert = new SoftAssert();

    }

    @AfterMethod
    public void closeDriver(){
        if(DriverManager.getDriver() != null){
            DriverManager.quit();
        }
        softAssert.assertAll();
    }
}
