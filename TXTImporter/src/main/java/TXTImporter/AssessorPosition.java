package TXTImporter;

import java.util.ArrayList;

public class AssessorPosition {
    public static int APosition(String AssessorName, ArrayList<Assessor> listA){
        int position = -1;
        for (int i = 0; i < listA.size(); i++) {
            if (listA.get(i).name.equals(AssessorName)){
                position = i;
                break;
            }
        }
        return position;
    }
}
