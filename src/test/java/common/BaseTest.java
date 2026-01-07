package common;


import drivers.DriverManager;
import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import helpers.SystemHelper;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class BaseTest {
    public SoftAssert softAssert;

  //   private WebDriver driver;  // driver toàn cục, đơn luồng
    @BeforeSuite
    public void setupBeforeSuite(){
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browserName) throws InterruptedException {
        // gán @Optional("chrome"): cho trường hợp lỡ như quên truyền tham số thì nó sẽ lấy giá trị mặc định = Chrome

        //Nếu file properties có giá trị, nó sẽ ưu tiên lấy giá trị từ file properties
        //Nếu file properties không có giá trị hoặc bị null, nó sẽ lấy giá trị browserName
//        if(PropertiesHelper.getValue("browser").isBlank() || PropertiesHelper.getValue("browser") == null){
//            browserName = browserName;
//        }else {
//            browserName = PropertiesHelper.getValue("browser");
//        }

        if (PropertiesHelper.getValue("browser") != null && !PropertiesHelper.getValue("browser").isBlank()) {
            browserName = PropertiesHelper.getValue("browser");
        }


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
    public void closeDriver(ITestResult result){

        // Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Step
        // Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
        // passed = SUCCESS và failed = FAILURE
//        if (ITestResult.FAILURE == result.getStatus()){
//            CaptureHelper.takeScreenshot(result.getName() + "_" + SystemHelper.getDateTimeNowFormat());
//        }

      //  CaptureHelper.stopRecord();

        if(DriverManager.getDriver() != null){
            DriverManager.quit();
        }
        softAssert.assertAll();
    }
}
