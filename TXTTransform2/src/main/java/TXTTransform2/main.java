package TXTTransform2;

import java.util.ArrayList;

import static TXTTransform2.Improve.Improvement;
import static TXTTransform2.WriteDataStage2.WriteDataStage2Demo;
import static TXTTransform2.getColumn.getColumns;
import static TXTTransform2.getColumnToDelete.getColumnsToDelete;


public class main {
    public static void main(String[] args) {
        ArrayList<Integer> DeleteColumns = new ArrayList<>();
        //get the number of columns
        int column = getColumns("D:\\D\\Magazine_Subscriptions_output.txt");
        //System.out.println("Column: " + column);
        //decide which column to be deleted
        int deleteNumber = 0;
        for (int i = 1; i < column; i++) {
            deleteNumber = getColumnsToDelete("D:\\D\\Magazine_Subscriptions_output.txt", i);
            if (deleteNumber != 0){
                DeleteColumns.add(i);
            }
        }
        System.out.println(DeleteColumns.size());
        //rewrite the table
        WriteDataStage2Demo("D:\\D\\Magazine_Subscriptions_output.txt","D:\\D\\Magazine_Subscriptions_output_stage2.txt", DeleteColumns);

    }
}
