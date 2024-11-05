package TXTTransform2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TXTOutput {
    public static void Output(String str1,String str2,String outputPath){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
                //这里根据您的需要格式化输出内容
                bw.write(str1);
                bw.write(str2);
                bw.newLine();
                System.out.println("Data written to " + outputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
