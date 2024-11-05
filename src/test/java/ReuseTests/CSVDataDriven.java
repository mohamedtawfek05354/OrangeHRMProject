package ReuseTests;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVDataDriven {
    public static List<String[]> readCSVData(String filePath) {
        List<String[]> data = new ArrayList<>();
        try {
            Reader reader = new FileReader(filePath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                String[] row = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String[] getSpecificLine(String filePath, int lineNumber) {
        List<String[]> data = readCSVData(filePath);
        if (lineNumber >= 0 && lineNumber < data.size()) {
            return data.get(lineNumber);
        } else {
            throw new IndexOutOfBoundsException("Invalid line number");
        }
    }
//     public Object[][] getCSVData(String filePath) {
//        Object[][] data = null;
//        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
//            String[] headers = reader.readNext();  // Reading the first line (headers)
//            if (headers == null) {
//                throw new RuntimeException("CSV file is empty.");
//            }
//
//            List<String[]> rows = reader.readAll();  // Reading all remaining rows
//            data = new Object[rows.size()][1];  // TestNG requires Object[][]
//
//            for (int i = 0; i < rows.size(); i++) {
//                String[] row = rows.get(i);
//                Map<String, String> rowData = new HashMap<>();
//
//                for (int j = 0; j < headers.length; j++) {
//                    rowData.put(headers[j], row[j]);
//                }
//
//                data[i][0] = rowData;  // Putting the map in the Object[][]
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
}
