package TXTImporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static TXTImporter.AssessorNameChecker.AChecker;
import static TXTImporter.AssessorPosition.APosition;
import static TXTImporter.TXTOutput.Output;
import static TXTImporter.TargetNameChecker.TChecker;
import static TXTImporter.TargetPosition.TPosition;
import static TXTImporter.main.*;
import static TXTImporter.main.listA;

public class ReadData {
    protected static void ReadDataDemo(String filePath,String outputPath){
        int counterForAssessor = 0;
        int counterForTarget = 0;
        ArrayList<Assessor> listA = new ArrayList<>();
        ArrayList<Target> listT = new ArrayList<>();
        ArrayList<Score> listS = new ArrayList<>();
//        Assessor a = new Assessor();
//        Target t = new Target();
//        Score s = new Score();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 使用逗号分割每行的字符串
                String[] parts = line.split(",");

//                 //输出分割后的内容
//                System.out.println("UserID: " + parts[0]);
//                System.out.println("ProductID: " + parts[1]);
//                System.out.println("Rating: " + parts[2]);
//                System.out.println("Timestamp: " + parts[3]);
//                System.out.println(); // 输出空行以便于阅读

                //Table establishment
                //initialization
//                for (Assessor assessor : listA) {
//                    if (assessor.name.equals(parts[0])) {
//                        Assessor a = assessor;
//                        break;
//                    }
//                }
//                for (Assessor assessor : listA) {
//                    if (!assessor.name.equals(parts[0])) {
//                        Assessor a = new Assessor();
//                        break;
//                    }
//                }
                Assessor a = new Assessor();
                Target t = new Target();
                Score s = new Score();
                if (listA.isEmpty()){
                    counterForAssessor = 0;
                    counterForTarget = 0;
                    a.id = counterForAssessor;
                    a.name = parts[0];
                    listA.add(a);
                    t.id = counterForTarget;
                    t.name = parts[1];
                    listT.add(t);
                    //store scores
                    s.a = a;
                    s.t = t;
                    s.score = Double.parseDouble(parts[2]);
                    counterForAssessor++;
                    counterForTarget++;
                } else {    //not the first pne
                    //find the position
//                    int indexA = listA.indexOf(parts[0]);
//                    int indexT = listT.indexOf(parts[1]);
                    //not found in Target list
                    if (!TChecker(parts[1],listT)){
                        t.id = counterForTarget;
                        t.name = parts[1];
                        listT.add(t);
                        s.t = t;    //record target
                        counterForTarget++;
                    }
                    // not found in Assessor list
                    if (!AChecker(parts[0],listA)){
                        a.id = counterForAssessor;
                        a.name = parts[0];
                        listA.add(a);
                        s.a = a;    //record Assessor
                        counterForAssessor++;
                    }
                    //Target have been recorded
                    if (TChecker(parts[1],listT)){
                        s.t = listT.get(TPosition(parts[1],listT));
                    }
                    if (AChecker(parts[0],listA)){
                        s.a = listA.get(APosition(parts[0],listA));
                    }
                    s.score = Double.parseDouble(parts[2]);
                }
                listS.add(s);
                //End of Score, Assessor, Target recording
            }
            ///testing
//                    for (int i =0; i < listT.size(); i++) {
//                        System.out.println(listT.get(i).name);
//                    }
//                    for (int i =0; i < listA.size(); i++) {
//                        System.out.println(listA.get(i).name);
//                    }
//                    for (int i =0; i < listS.size(); i++) {
//                        System.out.println(String.valueOf(listS.get(i).score));
//                    }
//                    System.out.println(listS.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //BuildTable
        int rows = listA.size();
        System.out.println("Rows: " + rows);
        int columns = listT.size();
        System.out.println("Columns: " + columns);
        int numScore = listS.size();
        System.out.println("Num Score: " + numScore);
        double [][] ScoreTable = new double[rows][columns];
        StringBuilder TargetNameTable = new StringBuilder(",");
        Score tempScore = new Score();
        int AssessorPosition = 0;
        int TargetPosition = 0;
        StringBuilder AssessorNameScoreTable = new StringBuilder();

        for (int i = 0; i < columns; i++) {
            TargetNameTable.append(listT.get(i).name);
//            System.out.println(TargetNameTable.toString());///////////////////
            if (i < columns - 1) {
                TargetNameTable.append(",");
            }
        }
        String str1 = TargetNameTable.toString();
        String str2 = "";   //for the second part of output
//        System.out.println(str1);    //print the first line
        //begin with the work of ScoreTable
        for (int i = 0; i < numScore; i++) {
            tempScore = listS.get(i);
            AssessorPosition = tempScore.a.id;
            TargetPosition = tempScore.t.id;
            ScoreTable[AssessorPosition][TargetPosition] = tempScore.score;
        }
        //finish ScoreTable
        //do the rest output
        for (int i = 0; i < rows; i++) {
            AssessorNameScoreTable = new StringBuilder();
            AssessorNameScoreTable.append(listA.get(i).name).append(",");
            for (int j = 0; j < columns; j++) {
                AssessorNameScoreTable.append(String.valueOf(ScoreTable[i][j]));
                if (j < columns - 1) {
                    AssessorNameScoreTable.append(",");
                }
            }
            str2 = str2 +"\n" + AssessorNameScoreTable.toString();
//            System.out.println(AssessorNameScoreTable.toString());
        }
        //get output text file
        Output(str1,str2,outputPath);
        //Finished all the work
    }

}

