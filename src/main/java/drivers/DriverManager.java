package drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    // Khởi tạo đối tượng drvier theo kiểu ThreadLocal để driver đó có thể lưu trữ nhiều luồng giá trị khác nhau
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // final: định nghĩa hằng, mà hằng k thể update giá trị --> nên nó k gán lại dc giá trị cho driver
    // nó chỉ nhận gtri 1 lần, k thay đổi dc gtri

    // private: để nó k thay đổi đc cấu trúc
    private DriverManager() {
    }

    // hàm get(), set(), remove() đều thuộc ThreadLocal
    // hàm get(): để lấy ra từng cái session driver theo từng luồng khác nhau
    public static WebDriver getDriver() {
        return driver.get(); // lấy giá trị từ trong ThreadLocal
    }

    // hàm set(): set cho nó 1 giá trị driver cục bộ, và nó tự động set vô luồng ThreadLocal<WebDriver>
    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver); // set giá trị vào ThreadLocal
    }

    // remove: xóa đi luồng đó
//    public static void quit() {
//        DriverManager.driver.get().quit(); //quit(): dùng để tắt giá trị/reset giá trị driver về null
//        driver.remove(); // xóa luôn giá trị Thread đó
//    }

    public static void quit() {
        WebDriver driverInstance = driver.get();
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();
        }
    }

}
