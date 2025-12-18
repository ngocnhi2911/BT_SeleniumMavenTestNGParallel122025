package keywords;

import drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebUI {

    // Khai báo static hết để lấy tên class.tên hàm cho dễ (không cần khai báo đối tượng class0
    // Biến static mới mang vô hàm static dùng được
    private static int WAIT_TIMEOUT = 10;
    private static double STEP_TIME = 1;
    private static int PAGE_LOAD_TIMEOUT = 20;
 //   private static WebDriver driver;  //driver này trở thành driver toàn cục, k cần phải khai báo WebDriver driver ở các hàm nữa mà có thể sử dụng thẳng luôn

    
    public static void sleep(double second) {
        try {
            Thread.sleep((long) second * 1000);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }

    private static void logConsole(String message) {
        System.out.println(message);
    }


    public static void highlightElement(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red';", getWebElement(by));
    }

    public static void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    public static void highlightElement(By by, String color) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid " + color + "';", getWebElement(by));
    }

    public static void highlightElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid " + color + "';", element);
    }

    public static WebElement waitForElementVisible(By by) {
        WebElement element = null;
        try {
          //  System.out.println("Driver in WeUI: " + driver);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            // Khi có lỗi xảy ra sẽ in ra thông tin theo ý muốn của mk
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return element;
    }

    // Hàm đợi nhiều phần tử
//    public static List<WebElement> waitForAllElementVisible(By by) {
//        List<WebElement> element = null;
//        try {
//            //  System.out.println("Driver in WeUI: " + driver);
//            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
//            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//            highlightElement(by);
//            return element;
//        } catch (Throwable error) {
//            // Khi có lỗi xảy ra sẽ in ra thông tin theo ý muốn của mk
//            logConsole("Timeout waiting for the element Visible. " + by.toString());
//            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
//        }
//        return element;
//    }



    public static WebElement waitForElementVisible(By by, int seconds) {
        WebElement element = null;

        try {
          //  System.out.println("Driver in WeUI: " + driver);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(500));
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            // Khi có lỗi xảy ra sẽ in ra thông tin theo ý muốn của mk
            logConsole("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return element;
    }

    public static List<WebElement> waitForAllElementsVisible(By by) {
        List<WebElement> element = null;
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
            element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            System.out.println("Timeout waiting for all elements to be visible. " + by.toString());
            Assert.fail("Timeout waiting for all elements to be visible. " + by.toString());
        }
        return element;
    }

    //Kiểm tra khi xóa phần tử, nếu xóa thì phải k hiển thị trên giao diện và DOM,
    // nhưng nếu phần tử vẫn hiển thị trên giao diện hoặc DOM thì phải báo lỗi
    public static WebElement waitForElementNotVisible(By by) {

        WebElement element = null;

        try {
            //  System.out.println("Driver in WeUI: " + driver);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            // Khi có lỗi xảy ra sẽ in ra thông tin theo ý muốn của mk
            logConsole("Timeout waiting for the element NOT Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element NOT Visible. " + by.toString());
        }
        return element;
    }


    public static WebElement waitForElementPresent(By by) {
        WebElement element = null;

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            logConsole("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
        return element;
    }

    public static WebElement waitForElementToBeClickable(By by) {
        WebElement element = null;

        try {
          //  System.out.println("Driver in WeUI: " + driver);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT), Duration.ofMillis(500));
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            // Khi có lỗi xảy ra sẽ in ra thông tin theo ý muốn của mk
            logConsole("Timeout waiting for the element to be clickable. " + by.toString());
            Assert.fail("Timeout waiting for the element to be clickable. " + by.toString());
        }
        return element;
    }



    public static WebElement waitForElementToBeClickable(By by, int seconds) {
        WebElement element = null;

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds), Duration.ofMillis(500));
            //Duration.ofMillis(500): là quãng tgian nghỉ trong mỗi blog, tgian đảo lại. Ví dụ trong tgian tối đa 5s, thì cứ 0.5s nó quét lại 1 lần
            element = wait.until(ExpectedConditions.elementToBeClickable(by));
            highlightElement(by);
            return element;
        } catch (Throwable error) {
            // Khi có lỗi xảy ra sẽ in ra thông tin theo ý muốn của mk
            logConsole("Timeout waiting for the element to be clickable. " + by.toString());
            Assert.fail("Timeout waiting for the element to be clickable. " + by.toString());
        }
        return element;
    }

    public static void switchToFrame(By by) {

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(WAIT_TIMEOUT));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Switch To Frame. " + by.toString());
            Assert.fail("Timeout waiting for Switch To Frame. " + by.toString());
        }
    }

    public static void switchToParentFrame() {
        DriverManager.getDriver().switchTo().parentFrame();
    }

    public static void switchToDefaultContentFrame() {
        DriverManager.getDriver().switchTo().defaultContent();
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static boolean checkExistsElement(By by) {
        List<WebElement> element = getWebElements(by);
        if (element.size() > 0) {
            System.out.println("Phần tử tồn tại: true " + by);
            return true;
        } else {
            System.out.println("Phần tử không tồn tại: false " + by);
            return false;
        }
    }

    public static boolean checkExistsElement(By by, int timeoutSeconds) {
        try {
            //Chờ cho đến khi DriverManager.getDriver().findElements(by) tìm thấy ít nhất một phần tử (list không rỗng) - xuất hiện trong DOM.
            WebElement element = waitForElementVisible(by, timeoutSeconds);
            highlightElement(element);
            System.out.println("Element found: " + by.toString() + " within " + timeoutSeconds + " seconds.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element not found: " + by.toString() + " within " + timeoutSeconds + " seconds.");
            return false;
        }
    }

    public static boolean checkElementDisplay(By by, int timeoutSeconds) {
        try {
            //Chờ cho đến khi DriverManager.getDriver().findElements(by) tìm thấy ít nhất một phần tử (list không rỗng) - xuất hiện trong DOM.
            WebElement element = waitForElementVisible(by, timeoutSeconds);
            highlightElement(element);
            System.out.println("Element found: " + by.toString() + " within " + timeoutSeconds + " seconds.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element not found: " + by.toString() + " within " + timeoutSeconds + " seconds.");
            return false;
        }

    }

    public static boolean checkSeletedElement(By by) {
        waitForElementPresent(by);
        WebElement element = getWebElement(by);
        if (element.isSelected()) {
            System.out.println("Phần tử đã tích chọn: true " + by);
            return true;
        } else {
            System.out.println("Phần tử không tích chọn: false " + by);
            return false;
        }
    }

    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        System.out.println("Open URL: " + url);
    }

    public static String getCurrentURL() {
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        return currentUrl;
    }

    public static void clearTextElement(By by) {
        sleep(STEP_TIME);
        waitForElementVisible(by).clear();
      //  getWebElement(by).clear();
        System.out.println("Clear text on element:" + by.toString());
    }

    public static void setTextElement(By by, String text) {
        sleep(STEP_TIME);
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text);
        System.out.println("Set text " + text + " on element: " + by);
    }

    public static void setTextAndKeyElement(By by, String text, Keys key) {
        sleep(STEP_TIME);
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text, key);
        System.out.println("Set text and key " + text + " on element: " + by);
    }

    public static void clickElement(By by) {
        sleep(STEP_TIME);
        waitForElementToBeClickable(by);
        getWebElement(by).click();
        System.out.println("Click to element: " + by);
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        System.out.println("Get text on element: " + by);
        String text = element.getText();
        System.out.println("==> TEXT: " + text);
        return text;
    }

    public static String getElementAttribute(By by, String attribute) {
        waitForElementVisible(by);
        WebElement element = getWebElement(by);
        System.out.println("Get attribute on element: " + by);
        String textAttribute = element.getAttribute(attribute);
        System.out.println("==> Attribute: " + textAttribute);
        return textAttribute;
    }

    public static void scrollAtTop(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
    }

    public static void scrollAtBottom(By by) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
    }

    // Click bằng Actions
    public static void actionClick(By locator) {
        Actions act = new Actions(DriverManager.getDriver());
        act.moveToElement(DriverManager.getDriver().findElement(locator)).click().perform();
    }

    // Move to element
    public static boolean moveToElement(By by) {
        sleep(STEP_TIME);

        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(getWebElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static void actionSendKeys(By locator, String text) {
        sleep(STEP_TIME);
        Actions actions = new Actions(DriverManager.getDriver());
        WebElement element = DriverManager.getDriver().findElement(locator);
        actions.moveToElement(element).click().sendKeys(text).perform();
    }

    //Chờ đợi trang load xong mới thao tác

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            logConsole("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }


    // Hàm kiểm tra sự tồn tại của phần tử với lặp lại nhiều lần
    public static boolean checkElementExist(By by, int maxRetries, int waitTimeMillis) {
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                WebElement element = getWebElement(by);
                if (element != null) {
                    System.out.println("Tìm thấy phần tử ở lần thử thứ " + (retryCount + 1));
                    return true; // Phần tử được tìm thấy
                }
            } catch (NoSuchElementException e) {
                System.out.println("Không tìm thấy phần tử. Thử lại lần " + (retryCount + 1));
                retryCount++;
                try {
                    Thread.sleep(waitTimeMillis); // Chờ trước khi thử lại
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        // Trả về false nếu không tìm thấy phần tử sau maxRetries lần
        logConsole("Không tìm thấy phần tử sau " + maxRetries + " lần thử.");
        return false;
    }

    public static String getElementCssValue(By by, String cssPropertyName) {
        WebElement element = waitForElementVisible(by);
        System.out.println("Get CSS value " + cssPropertyName + " of element " + by);
        String value = element.getCssValue(cssPropertyName);
        System.out.println("==> CSS value: " + value);
        return value;
    }

    // Giống vs dragAndDropElement, nó dùng hàm action
    public static boolean dragAndDrop(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.dragAndDrop(getWebElement(fromElement), getWebElement(toElement)).perform();
            //action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    //thao tác tay
    public static boolean dragAndDropElement(By fromElement, By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.clickAndHold(getWebElement(fromElement)).moveToElement(getWebElement(toElement)).release(getWebElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            logConsole(e.getMessage());
            return false;
        }
    }

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        System.out.println("Verify equals: " + actual + " and " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        System.out.println("Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

}
