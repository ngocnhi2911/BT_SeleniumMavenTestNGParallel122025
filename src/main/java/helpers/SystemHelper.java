package helpers;

import java.io.File;

public class SystemHelper {

    //Dùng để lấy đường dẫn hiện tại của ng dùng
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
}
