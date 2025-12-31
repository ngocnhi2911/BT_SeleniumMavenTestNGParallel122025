package helpers;

import java.awt.Color;
import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;  // Khởi tạo và lưu trữ phiên làm việc của file excel
    private Sheet sheet;  // Lưu trữ data theo từng sheet
    private Cell cell; //Lấy data theo từng cột (ô)
    private Row row; //Lấy data theo từng dòng
    private CellStyle cellstyle; // kiểu format của ô đó (VD: căn giữa, in dậm, tô màu,..)
    private Color mycolor;

    // Hỗ trợ để khai báo đối tượng, lưu trữ dữ liệu theo mảng 2 chiều
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    //DÙng để đọc data trong sheet đó rồi mới truy xuất dữ liệu trong sheet đó ra
    public void setExcelFile(String ExcelPath, String SheetName){
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {  // Kiểm tra file có tồn tại không? nếu file k tồn tại thfi báo lỗi
                System.out.println("File doesn't exist.");
            }

            fis = new FileInputStream(ExcelPath);  // đọc đường dẫn file excel (ExcelPath) mà mk chỉ định
            workbook = WorkbookFactory.create(fis); // Khởi tạo phiên làm viêc đối vs file excel chuyên biệt
            sheet = workbook.getSheet(SheetName); // lấy sheet theo tên


            // Đọc data theo từng ô của sheet đó
            if (sheet == null) { // nếu không tìm thấy sheet đó sẽ báo lỗi
                throw new Exception("Sheet name doesn't exist.");
            }

            this.excelFilePath = ExcelPath;

            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Lấy data theo từng ô
    // với index: sẽ hiểu file bắt đầu từ 0
    //Lưu ý: với columnIndex: nó sẽ hiểu là cột thứ 0, nghĩa là cột A là cột 0, cột B là cột 1
    public String getCellData(int columnIndex, int rowIndex) {
        try {
            cell = sheet.getRow(rowIndex).getCell(columnIndex);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Gọi ra hàm này nè, truyền tên cột và vị trí dòng
    public String getCellData(String columnName, int rowIndex) {
        if (columns.get(columnName) == null) { // Nếu không tìm thấy tên cột thì sẽ báo lỗi k tồn tại
            try {
                throw new Exception("Column name doesn't exist.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return getCellData(columns.get(columnName), rowIndex);
    }

    //Ghi data vào lại file excel (cần 3 tham số: giá trị cần lưu, tên cột cần lưu, vị trí dòng cần lưu)
    public void setCellData(String text, int columnIndex, int rowIndex) {
        try {
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(columnIndex);

            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(text);

            // Chỉnh format ô đó
            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL); // màu nền của ô
            style.setAlignment(HorizontalAlignment.CENTER); // căn theo chiều ngang
            style.setVerticalAlignment(VerticalAlignment.CENTER);  // căn theo chiều dọc

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //Ghi data vào lại file excel (cần 3 tham số: giá trị cần lưu, tên cột cần lưu, vị trí dòng cần lưu)
    public void setCellData(String text, String columnName, int rowIndex) {
        try {
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

//  Đọc data từ Excel file với DataProvider TestNG (đọc toàn bộ dữ liệu trong file excel)
    public Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;
        try {
            // load the file
            FileInputStream fis = new FileInputStream(filePath);

            // load the workbook
            workbook = new XSSFWorkbook(fis);

            // load the sheet
            Sheet sh = workbook.getSheet(sheetName);

            // load the row
            Row row = sh.getRow(0);

            //
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            System.out.println(noOfRows + " - " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];

            //
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);

                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        default:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return data;
    }

    // Đọc Excel data với số dòng trong khoảng chỉ định (sử dụng HashTable của Java)
    //Hàm này dùng cho trường hợp nhiều Field trong File Excel
    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    //Get last row number (lấy vị trí dòng cuối cùng tính từ 0)
    public int getLastRowNum() {
        return sheet.getLastRowNum();
    }

    //Lấy số dòng có data đang sử dụng
    public int getPhysicalNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        System.out.println("Excel Path: " + excelPath);
        Object[][] data = null;

        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                try {
                    System.out.println("File Excel path not found.");
                    throw new IOException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(excelPath);

            workbook = new XSSFWorkbook(fis);

            sheet = workbook.getSheet(sheetName);

            int rows = getLastRowNum();
            int columns = getColumns();

            System.out.println("Row: " + rows + " - Column: " + columns);
            System.out.println("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(colNum, 0), getCellData(colNum, rowNums));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    // Get data from specific rows
    public Object[][] getDataFromSpecificRows(String excelPath, String sheetName, int[] rowNumbers) {
        System.out.println("Excel File: " + excelPath);
        System.out.println("Sheet Name: " + sheetName);
        System.out.println("Reading data from specific rows: " + Arrays.toString(rowNumbers));

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                System.out.println("File Excel path not found.");
                throw new FileNotFoundException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet name not found.");
                throw new RuntimeException("Sheet name not found.");
            }

            int columns = getColumns();
            System.out.println("Column count: " + columns);

            // Khởi tạo mảng data với kích thước bằng số lượng dòng được chỉ định
            data = new Object[rowNumbers.length][columns];

            // Đọc dữ liệu từ các dòng được chỉ định
            for (int i = 0; i < rowNumbers.length; i++) {
                int rowNum = rowNumbers[i];
                // Kiểm tra xem dòng có tồn tại không
                if (rowNum > sheet.getLastRowNum()) {
                    System.out.println("WARNING: Row " + rowNum + " does not exist in the sheet.");
                    // Gán giá trị rỗng cho dòng không tồn tại
                    for (int j = 0; j < columns; j++) {
                        data[i][j] = "";
                    }
                    continue;
                }

                for (int j = 0; j < columns; j++) {
                    data[i][j] = getCellData(j, rowNum);
                }
            }

            // Đóng workbook và FileInputStream
            workbook.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Exception in getDataFromSpecificRows: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    // Get data from specific rows with Hashtable
    public Object[][] getDataHashTableFromSpecificRows(String excelPath, String sheetName, int[] rowNumbers) {
        System.out.println("Excel File: " + excelPath);
        System.out.println("Sheet Name: " + sheetName);
        System.out.println("Reading data from specific rows: " + Arrays.toString(rowNumbers));

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                System.out.println("File Excel path not found.");
                throw new FileNotFoundException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet name not found.");
                throw new RuntimeException("Sheet name not found.");
            }

            int columns = getColumns();
            // Khởi tạo mảng data với kích thước bằng số lượng dòng được chỉ định
            data = new Object[rowNumbers.length][1];

            // Đọc dữ liệu từ các dòng được chỉ định
            for (int i = 0; i < rowNumbers.length; i++) {
                int rowNum = rowNumbers[i];
                // Kiểm tra xem dòng có tồn tại không
                if (rowNum > sheet.getLastRowNum()) {
                    System.out.println("WARNING: Row " + rowNum + " does not exist in the sheet.");
                    data[i][0] = new Hashtable < String, String > ();
                    continue;
                }

                Hashtable < String, String > table = new Hashtable < > ();
                for (int j = 0; j < columns; j++) {
                    // Lấy tên cột từ dòng đầu tiên (header)
                    String columnName = getCellData(j, 0);
                    // Lấy giá trị từ dòng hiện tại và cột j
                    String cellValue = getCellData(j, rowNum);
                    // Thêm vào Hashtable
                    table.put(columnName, cellValue);
                }
                data[i][0] = table;
            }

            // Đóng workbook và FileInputStream
            workbook.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Exception in getDataHashTableFromSpecificRows: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    public List<Hashtable<String, String>> getListExcelData(String filePath, String sheetName) {

        List<Hashtable<String, String>> data = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            int lastRow = sheet.getLastRowNum();
            int lastCol = headerRow.getLastCellNum();

            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                Hashtable<String, String> table = new Hashtable<>();

                for (int j = 0; j < lastCol; j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = row.getCell(j).toString();
                    table.put(key, value);
                }
                data.add(table);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}



