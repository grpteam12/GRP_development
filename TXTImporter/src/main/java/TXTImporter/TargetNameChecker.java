package TXTImporter;

import java.util.ArrayList;

public class TargetNameChecker {
    public static boolean TChecker(String targetName, ArrayList<Target>listT){
        boolean flag = false;
        for (int i = 0; i < listT.size(); i++){
            if (listT.get(i).name.equals(targetName)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
