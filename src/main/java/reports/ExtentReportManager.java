package reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// dùng để cấu hình những đường dẫn xuất ra report, tên report, thông tin cơ bản của report
public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
       // cấu hình những đường dẫn xuất ra report
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreport/extentreport.html");

        // tên report
        reporter.config().setReportName("Extent Report | Anh Tester");
        extentReports.attachReporter(reporter);

        // thông tin cơ bản của report
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Anh Tester");
        extentReports.setSystemInfo("Author", "Anh Tester");
        return extentReports;
    }

}
