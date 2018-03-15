package com.MIT;

import java.util.ArrayList;

public class Main {
    private static final double lambda = 0.001;
    private static int dataCount = 50000;
    private static int censCount = 1000;

    private static ArrayList data;
    private static ArrayList dataCens;


    public static void main(String[] args) {
        // write your code here
        double res = 0;
        CensCalc cc = new CensCalc();
        for (int i = 0; i < 5; i++) {
            data = cc.generateExpData(dataCount, lambda);
            dataCens = cc.generateExpData(censCount, lambda);
//            res = cc.find(0,1, 0.000001, data,dataCens );
            res = cc.rightCens(data, dataCens);
            System.out.print("T " + censCount + "labda is" + res + "\t");
//            res = cc.accuracyLeft(res,(double)censCount, dataCens);
            res = cc.accuracyRight(res, dataCount);
            System.out.println("accuracy is" + res + "\t");
            censCount += 500;
        }

//        res = cc.rightCens(data,dataCens );
//        System.out.print("labda is"+ res + "\t");

//        dataCens.stream().forEach(System.out::println);
    }
}
