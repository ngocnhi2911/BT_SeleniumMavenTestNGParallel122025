package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {

    // Là file trợ giúp xử lý file properties
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src/test/resources/configs/configs.properties";

    // Khai báo tất cả đường dẫn trỏ đến file properties rồi sau đó đọc hết file properties đó gom vào 1 cục
    public static Properties loadAllFiles() { // dùng để load file Properties lên, để đọc được hết file Properties

        // LinkedList: kiểu dữ liệu giúp lưu trữ data dưới dạng danh sách đường dẫn
        LinkedList<String> files = new LinkedList<>();
        // Add tất cả file Properties vào đây theo mẫu
        files.add("src/test/resources/configs/configs.properties");
//        files.add("src/test/resources/configs/local.properties");
//        files.add("src/test/resources/configs/production.properties");

        try {
            properties = new Properties();  // Nếu k khởi tạo sẽ lỗi Null Point except, đại diện cho nhiều file

            for (String f : files) { // duyệt qua từng file
                Properties tempProp = new Properties(); // chỉ đại diện cho 1 file properties (đại diện cho file f)
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);  //FileInputStream: dùng đrr đọc file
                tempProp.load(file);  //load file đó lên
                properties.putAll(tempProp);  //đẩy tất cả vào properties
            }
            return properties;
        } catch (IOException ioe) {
            return new Properties();
        }
    }

    // Đọc 1 file properties, mk có thể truyền đường dẫn vào
    public static void setFile(String relPropertiesFilePath) { //relPropertiesFilePath: đường dẫn cục bộ
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePath;  // ổ đĩa + đường dẫn properties
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Khai báo đường dẫn cố định
    public static void setDefaultFile() {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get: lấy dl
    public static String getValue(String key) {
        String value = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Lấy giá trị từ file đã Set
            value = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    public static void setValue(String key, String value) {
        try {
            if (file == null) {
                properties = new Properties();
                file = new FileInputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
                properties.load(file);
                file.close();
                out = new FileOutputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
            }
            //Ghi vào cùng file Prop với file lấy ra
            out = new FileOutputStream(linkFile);
            System.out.println(linkFile);
            properties.setProperty(key, value);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
