package TXTImporter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

import static TXTImporter.BuildTable.BuildTableDemo;
import static TXTImporter.ReadData.ReadDataDemo;

public class main {
    static ArrayList<Assessor> listA = new ArrayList<>();
    static ArrayList<Target> listT = new ArrayList<>();
    static ArrayList<Score> listS = new ArrayList<>();
    public static void main(String[] args) {
        ReadDataDemo("D:\\D\\Magazine_Subscriptions.txt","D:\\D\\output.txt");
//        System.out.println("finish reading data");
//        System.out.println(listA.size());
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
//        BuildTableDemo();
    }

}

