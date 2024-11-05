package TXTTransform2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class getColumn {
    protected static int getColumns(String filePath){
        int columns = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 使用逗号分割每行的字符串
                String[] parts = line.split(",");
                columns = parts.length;
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columns;
    }
}
