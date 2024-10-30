package TXTImporter;

import java.util.ArrayList;

public class TargetPosition {
    public static int TPosition(String TargetName, ArrayList<Target> listT){
        int position = -1;
        for (int i = 0; i < listT.size(); i++) {
            if (listT.get(i).name.equals(TargetName)){
                position = i;
                break;
            }
        }
        return position;
    }
}
