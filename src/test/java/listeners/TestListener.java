package listeners;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("Setup môi trường onStart: " + result.getStartDate());

    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("Kết thúc bộ test: " + result.getEndDate());


        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();  // kết thúc và xuất ra report

    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Bắt đầu chạy test case: " + result.getName());
        CaptureHelper.startRecord(result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        //bắt đầu ghi lại tc đó tên gì
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");
        LogUtils.info("==> Status: " + result.getStatus());
        CaptureHelper.stopRecord();

        //Extent Report
        // Ghi log tc đó
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        LogUtils.info("==> Status: " + result.getStatus());
        LogUtils.error("==> Lý do: " + result.getThrowable());
        CaptureHelper.takeScreenshot(result.getName());
        CaptureHelper.stopRecord();

        //Extent Report
        ExtentTestManager.addScreenshot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());  //getThrowable(): dùng để lấy ra lý do
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test case " + result.getName() + " is skipped.");
      //  LogUtils.info("==> Status: " + result.getStatus());

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.SKIP, result.getName() + " is skipped.");

        CaptureHelper.stopRecord();

    }

}
