package TXTTransform2;

import java.io.*;
import java.util.ArrayList;

public class WriteDataStage2 {
    public static  void WriteDataStage2Demo(String filePath,String outputPath, ArrayList<Integer> Columns){
        String str1 = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 使用逗号分割每行的字符串
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i++) {
                    if (i == 0)
                        str1 = str1 + parts[i];
                    if (i != 0 && !Columns.contains(i)) {   // if the column is not to delete
                        str1 = str1 + "," + parts[i];
                    }
                }
                str1 = str1 + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //output
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            bw.write(str1);
            bw.newLine();
            System.out.println("Data written to " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
