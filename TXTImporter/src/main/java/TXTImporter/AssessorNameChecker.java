package TXTImporter;

import java.util.ArrayList;

public class AssessorNameChecker {
    public static boolean AChecker(String AssessorName, ArrayList<Assessor>listA){
        boolean flag = false;
        for (int i = 0; i < listA.size(); i++){
            if (listA.get(i).name.equals(AssessorName)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
