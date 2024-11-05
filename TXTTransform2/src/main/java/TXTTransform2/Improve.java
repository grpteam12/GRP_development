package TXTTransform2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static TXTTransform2.TXTOutput.Output;

public class Improve {
    protected static void Improvement(String filePath,String outputPath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int counterRow = 0;
            int numberOfParts = 0;
            int counterScore = 0;
            String str1 = "";
            String str2 = "";
            while ((line = br.readLine()) != null) {
                // 使用逗号分割每行的字符串
                String[] parts = line.split(",");
                numberOfParts = parts.length;
                //do nothing with the first line
                if (counterRow == 0){
                    str1 = line;
                }
                if (counterRow != 0) {
                    for (int i = 1; i < numberOfParts; i++) {
                        if (Double.parseDouble(parts[i])>0){
                            counterScore++;
                        }
                    }
                    //if the object have more than 10 scores,then it could be used in the next stage.
                    //so write it into the file
                    if (counterScore >= 10) {
                        str2 = str2 + "\n" + line;
                    }
                }
                counterRow++;
                counterScore = 0;
            }
            //get output text file
            Output(str1,str2,outputPath);
            //Finished all the work
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
