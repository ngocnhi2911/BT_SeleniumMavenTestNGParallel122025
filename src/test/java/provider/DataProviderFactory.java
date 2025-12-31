package provider;

import helpers.ExcelHelper;
import helpers.SystemHelper;
import mapper.LeadDTOMapper;
import mapper.TaskDTOMapper;
import models.LeadDTO;
import models.TaskDTO;
import org.testng.annotations.DataProvider;




import java.util.Hashtable;
import java.util.List;


public class DataProviderFactory {

    @DataProvider(name = "leadData")
    public static Object[][] leadData() {

        ExcelHelper excelHelper = new ExcelHelper();
        List<Hashtable<String, String>> excelData = excelHelper.getListExcelData("src/test/resources/testdata/DataCRM.xlsx", "Lead");

        Object[][] data = new Object[excelData.size()][1];

        for (int i = 0; i < excelData.size(); i++) {
            LeadDTO lead = LeadDTOMapper.mapToLeadDTO(excelData.get(i));
            data[i][0] = lead;
        }
        return data;
    }

    @DataProvider(name = "taskData")
    public static Object[][] taskData() {

        ExcelHelper excelHelper = new ExcelHelper();
        List<Hashtable<String, String>> excelData = excelHelper.getListExcelData("src/test/resources/testdata/DataCRM.xlsx", "Task");

        Object[][] data = new Object[excelData.size()][1];

        for (int i = 0; i < excelData.size(); i++) {
            TaskDTO task = TaskDTOMapper.mapToTaskDTO(excelData.get(i));
            data[i][0] = task;
        }
        return data;
    }

    @DataProvider(name = "leadTaskData")
    public Object[][] leadTaskData() {

        ExcelHelper excelHelper = new ExcelHelper();

        List<Hashtable<String, String>> leadExcel = excelHelper.getListExcelData("src/test/resources/testdata/DataCRM.xlsx", "Lead");

        List<Hashtable<String, String>> taskExcel = excelHelper.getListExcelData("src/test/resources/testdata/DataCRM.xlsx", "Task");

        Object[][] data = new Object[leadExcel.size()][2];

        for (int i = 0; i < leadExcel.size(); i++) {
            LeadDTO leadDTO = LeadDTOMapper.mapToLeadDTO(leadExcel.get(i));
            TaskDTO taskDTO = TaskDTOMapper.mapToTaskDTO(taskExcel.get(i));

            data[i][0] = leadDTO;
            data[i][1] = taskDTO;
        }

        return data;
    }



    /*

       @DataProvider(name = "loginData1")
    public Object[][] getDataLogin1() {
        return new Object [][] {
                {"admin@example.com", 123456},
                {"user@example.com", 123}
        };
    }


    @DataProvider(name = "loginData2", parallel = true)
    public Object[][] getDataLogin2() {
        return new Object [][] {
                {"admin@example.com", 123456, "Admin"},
                {"user@example.com", 123, "User"},
                {"customer@example.com", 123, "Customer"}
        };
    }

    @DataProvider(name = "testVerifyEditLead", parallel = true)
    public Object[][] testVerifyEditLead() {
        return new Object [][] {
                {"Active", "Google", "Admin Anh Tester", "JSC_NEW", "Yến Nhi 1", "Đại Linh", "Tester", "Việt Nam", "ngocnhi1@gmail.com", "Hà Nội", "htester.com.vn", "Vietnam", "0965898989", "0001", "12345", "Vietnamese", "NODO JSC", "htest add new lead", "10-11-2025 00:00:00", 1, 0},
                {"Active", "Google", "Admin Anh Tester", "JSC_NEW", "Yến Nhi 2", "Đại Linh", "Tester", "Việt Nam", "ngocnhi2@gmail.com", "Hà Nội", "htester.com.vn", "Vietnam", "0965898989", "0001", "12345", "Vietnamese", "NODO JSC", "htest add new lead", "10-11-2025 00:00:00", 1, 0}
        };
    }

//    @DataProvider(name = "data_provider_login_excel")
//    public Object[][] dataLoginCRM_FromExcel() {
//        ExcelHelper excelHelper = new ExcelHelper();
//        Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login");
//        System.out.println("Login Data from Excel: " + data);
//        return data;
//    }

    @DataProvider(name = "data_provider_login_excel_hashtable")
    public Object[][] dataLoginHRMFromExcelHashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login", 2, 3);
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_specific_rows", parallel = true)
    public Object[][] data_provider_login_excel_specific_rows() {
        ExcelHelper excelHelper = new ExcelHelper();
        int[] specificRows = new int[] {
                1,
                3,
                4
        }; //Dòng cụ thể cần lấy
        Object[][] data = excelHelper.getDataFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login", specificRows);
        System.out.println("getDataFromSpecificRows: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_specific_rows_hashtable", parallel = true)
    public Object[][] data_provider_login_excel_specific_rows_hashtable() {
        ExcelHelper excelHelper = new ExcelHelper();
        int[] specificRows = new int[] {
                1,
                3,
                4
        }; //Dòng cụ thể cần lấy
        Object[][] data = excelHelper.getDataHashTableFromSpecificRows(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Login", specificRows);
        System.out.println("getDataHashTableFromSpecificRows: " + data);
        return data;
    }


//    @DataProvider(name = "leadData")
//    public Object[][] dataLoginHRMFromExcel() {
//        ExcelHelper excelHelper = new ExcelHelper();
//        Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Lead");
//        System.out.println("Lead Data from Excel: " + data);
//        return data;
//    }

//    @DataProvider(name = "leadData")
//    public static Object[][] leadData() {
//        ExcelHelper excelHelper = new ExcelHelper();
//        List<Hashtable<String, String>> excelData = excelHelper.getExcelData(SystemHelper.getCurrentDir() + "src/test/resources/testdata/DataCRM.xlsx", "Lead"); // đọc từ Excel
//
//        Object[][] data = new Object[excelData.size()][1];
//
//        for (int i = 0; i < excelData.size(); i++) {
//            LeadDTO lead = LeadDTOMapper.mapToLeadDTO(excelData.get(i));
//            data[i][0] = lead;
//        }
//        return data;
//    }
     */






}
