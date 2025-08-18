package utils;

import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import net.datafaker.Faker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Utilities extends TestContextSetup {
    private final TestContextSetup testContextSetup;
    private static WebDriver driver;

    public Utilities(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.driver = testContextSetup.driver;  // initiate driver to testContextSetup.driver which can simply use "driver" instead of "testContextSetup.driver"
    }

    public static String generateSin() {
        int[] originalDigits = new int[9];

        Random r = new Random();
        // fill initializing values, skipping the last value because that'll be the check
        for (int i = 0; i < originalDigits.length - 1; i++) {
            // don't allow the first digit to be 9 because that's a temp sin
            if (i == 0) {
                originalDigits[i] = r.nextInt(9);
            } else {
                originalDigits[i] = r.nextInt(10);
            }
        }
        int[] doubledDigits = originalDigits.clone();

        // double every other digit
        for (int i = 1; i < originalDigits.length; i += 2) {
            int currentValue = originalDigits[i];
            int doubled = currentValue * 2;

            doubledDigits[i] = doubled > 9 ? doubled - 9 : doubled;
        }

        int sum = Arrays.stream(doubledDigits).sum();

        int checkDigit = ((sum * 9) % 10);
        originalDigits[8] = checkDigit;
        return Arrays.stream(originalDigits).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    public static int Random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static void CSVWriter(String PatientName, String MedicareID, String DateOfBirth) {

        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String stringDate = DateFor.format(date);

        File file = new File("C:\\MyCode\\CCNB\\CCNB\\src\\main\\java\\org\\example\\NewPatient.csv");

        try {
            // create FileWriter object with file as parameter
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(br);

            fr.append("\n" + stringDate + "\n");

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[]{PatientName, MedicareID, String.valueOf(DateOfBirth)});
            writer.writeAll(data);

            // closing writer connection
            writer.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }

    public static void WriteArrayToExcel(String PatientName, String MedicareID, String DateOfBirth) throws IOException {

        // Specify the path of the Excel file
        String excelFilePath = "C:\\MyCode\\CCNB\\CCNB\\src\\main\\java\\org\\example\\NewPatient.xlsx";
        // Create a file object
        File file = new File(excelFilePath);
        // Create a FileInputStream object to read the Excel file
        FileInputStream inputStream = new FileInputStream(file);
        // Create a Workbook object using XSSFWorkbook class
        Workbook workbook = new XSSFWorkbook(inputStream);
        // Specify the name of the sheet to which you want to write data
        Sheet sheet = workbook.getSheet("Sheet1");
        // Define the data that you want to write to the Excel file
        //String[] data = {"John", "Doe", "30"};
        String[] data = {PatientName, MedicareID, String.valueOf(DateOfBirth)};

        // Get the next available row in the sheet
        int rowCount = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowCount);
        // Loop through the data array and write each element to a new cell
        for (int i = 0; i < data.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data[i]);
            System.out.print(data[i] + '\t');
        }
        System.out.print("\n");

        // Create a FileOutputStream object to write data to the Excel file
        FileOutputStream outputStream = new FileOutputStream(file);

        // Write the changes to the Excel file
        workbook.write(outputStream);

        // Close the workbook, the input stream, and the output stream
        workbook.close();
        inputStream.close();
        outputStream.close();
        return;
    }

    public static void screenShot(String screenshotName) {

        // Get current date and time
        LocalDateTime now = LocalDateTime.now();

        // If you want to format the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-");
        String formattedDateTime = now.format(formatter);
        String screenshot_filename="C:\\SeleniumProject\\Salesforce\\Screenshots\\"+formattedDateTime+screenshotName+".jpg";
        takeScreenshot(driver, screenshot_filename);
    }
    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved to: " + destFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }


}

