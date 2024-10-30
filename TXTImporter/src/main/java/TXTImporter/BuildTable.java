package TXTImporter;

import static TXTImporter.main.*;

public class BuildTable {
    public static void BuildTableDemo() {
        int rows = listA.size();
        int columns = listT.size();
        int numScore = listS.size();
        double [][] ScoreTable = new double[rows][columns];
        StringBuilder TargetNameTable = new StringBuilder(",");
        Score tempScore = new Score();
        int AssessorPosition = 0;
        int TargetPosition = 0;
        StringBuilder AssessorNameScoreTable = new StringBuilder();

        for (int i = 0; i < columns; i++) {
            TargetNameTable.append(listT.get(i));
//            System.out.println(TargetNameTable.toString());///////////////////
            if (i < columns - 1) {
                TargetNameTable.append(",");
            }
        }
        String str = TargetNameTable.toString();
        System.out.println(str);    //print the first line
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
            AssessorNameScoreTable.append(listA.get(i)).append(",");
            for (int j = 0; j < columns; j++) {
                AssessorNameScoreTable.append(String.valueOf(ScoreTable[i][j]));
                if (i < columns - 1) {
                    TargetNameTable.append(",");
                }
            }
            str = AssessorNameScoreTable.toString();
            System.out.println(str);
        }
        //Finished all the work
    }
}
