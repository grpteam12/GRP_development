package TXTTransform2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class getColumnToDelete {
    protected static int getColumnsToDelete(String filePath, int columns){
        int counter = 0;
        boolean flag = false; //to decide if it is the first row
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 使用逗号分割每行的字符串
                String[] parts = line.split(",");
                if (!flag){
                    flag = true;
                    continue;
                }
                if (Double.parseDouble(parts[columns]) > 0){
                    counter++;
                }
                if (counter == 10){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (counter < 10){
            return columns;
        }else {
            return 0;
        }
    }
}
