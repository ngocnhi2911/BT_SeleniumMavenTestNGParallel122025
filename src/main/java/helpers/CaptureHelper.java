package helpers;

import drivers.DriverManager;
import org.monte.media.FormatKeys;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelper  extends ScreenRecorder {

    /*
    public static void takeScreenshot(String screenshotName){
        // Tạo tham chiếu của TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        // Gọi hàm để chụp ảnh màn hình - getScreenshotAs
        File source = ts.getScreenshotAs(OutputType.FILE);
        // Kiểm tra folder tồn tại. Nếu không thì tạo mới folder theo đường dẫn
        File theDir = new File(PropertiesHelper.getValue("screenshot_path"));
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        //Lưu file ảnh với tên cụ thể vào đường dẫn
        try {
            FileHandler.copy(source, new File(PropertiesHelper.getValue("screenshot_path") + screenshotName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     */

    public static void takeScreenshot(String screenshotName) {
        WebDriver driver = DriverManager.getDriver();

        // 1️⃣ Check driver tồn tại
        if (driver == null) {
            System.out.println("Driver is NULL. Cannot take screenshot: " + screenshotName);
            return;
        }

        // 2️⃣ Check driver có hỗ trợ screenshot không
        if (!(driver instanceof TakesScreenshot)) {
            System.out.println("Driver does not support screenshot: " + driver.getClass());
            return;
        }

        TakesScreenshot ts = (TakesScreenshot) driver;

        try {
            // 3️⃣ Chụp screenshot
            File source = ts.getScreenshotAs(OutputType.FILE);

            // 4️⃣ Tạo folder nếu chưa tồn tại
            String screenshotPath = PropertiesHelper.getValue("screenshot_path");
            File theDir = new File(screenshotPath);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            // 5️⃣ Lưu file
            FileHandler.copy(
                    source,
                    new File(screenshotPath + File.separator + screenshotName + ".png")
            );

        } catch (Exception e) {
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
    }


//
//    // Record with Monte Media library
//    public static ScreenRecorder screenRecorder;
//    public String name;
//
//    //Hàm xây dựng
//    public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
//        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
//        this.name = name;
//    }
//
//    //Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
//    @Override
//    protected File createMovieFile(Format fileFormat) throws IOException {
//
//        if (!movieFolder.exists()) {
//            movieFolder.mkdirs();
//        } else if (!movieFolder.isDirectory()) {
//            throw new IOException("\"" + movieFolder + "\" is not a directory.");
//        }
//
//        return new File(movieFolder, name + "-" + SystemHelper.getDateTimeNowFormat() + "." + Registry.getInstance().getExtension(fileFormat));
//    }
//
//    // Start record video
//    public static void startRecord(String recordName) {
//        //Tạo thư mục để lưu file video vào
//        File file = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("video_record_path"));
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = screenSize.width;
//        int height = screenSize.height;
//
//        Rectangle captureSize = new Rectangle(0, 0, width, height);
//
//        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
//        try {
//            screenRecorder = new CaptureHelper(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, recordName);
//            screenRecorder.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    // Stop record video
//    public static void stopRecord() {
//        try {
//            if (screenRecorder != null) {
//                screenRecorder.stop();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    // ✅ ThreadLocal để mỗi testcase có recorder riêng
    private static ThreadLocal<ScreenRecorder> recorder = new ThreadLocal<>();
    private String name;

    public CaptureHelper(GraphicsConfiguration cfg,
                         Rectangle captureArea,
                         Format fileFormat,
                         Format screenFormat,
                         Format mouseFormat,
                         Format audioFormat,
                         File movieFolder,
                         String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {

        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException(movieFolder + " is not a directory.");
        }

        return new File(
                movieFolder,
                name + "-" + SystemHelper.getDateTimeNowFormat()
                        + "." + Registry.getInstance().getExtension(fileFormat)
        );
    }

    // ================= START RECORD =================
    public static void startRecord(String recordName) {

        try {
            File folder = new File(SystemHelper.getCurrentDir()
                    + PropertiesHelper.getValue("video_record_path"));

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureSize = new Rectangle(0, 0, screenSize.width, screenSize.height);

            GraphicsConfiguration gc =
                    GraphicsEnvironment.getLocalGraphicsEnvironment()
                            .getDefaultScreenDevice()
                            .getDefaultConfiguration();

            ScreenRecorder screenRecorder = new CaptureHelper(
                    gc,
                    captureSize,
                    new Format(
                            MediaTypeKey, MediaType.FILE,
                            MimeTypeKey, MIME_AVI
                    ),
                    new Format(
                            MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, 24,
                            FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, 15 * 60
                    ),
                    new Format(
                            MediaTypeKey, MediaType.VIDEO,
                            EncodingKey, "black",
                            FrameRateKey, Rational.valueOf(30)
                    ),
                    null,
                    folder,
                    recordName
            );

            recorder.set(screenRecorder);
            screenRecorder.start();

        } catch (Exception e) {
            System.out.println("⚠️ Cannot start video record: " + e.getMessage());
        }
    }

    // ================= STOP RECORD =================
    public static void stopRecord() {
        ScreenRecorder screenRecorder = recorder.get();

        if (screenRecorder != null) {
            try {
                screenRecorder.stop();
            } catch (Exception e) {
                System.out.println("⚠️ Cannot stop video record: " + e.getMessage());
            } finally {
                recorder.remove();
            }
        }
    }



}
