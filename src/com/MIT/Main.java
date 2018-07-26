package com.MIT;

import java.util.ArrayList;

public class Main {
    private static final double lambda = 0.003;
    private static int dataCount = 100;
    private static int censCount = 10;

    private static ArrayList<Double> data;
    private static ArrayList dataCens;


    public static void main(String[] args) {
        // write your code here
        double res = 0;
        CensCalc cc = new CensCalc();
//        data = cc.generateExpData(1000, lambda);
//
//        double d[] = new double[data.size()];
//        for (int i = 0; i < data.size(); i++) {
//            d[i]=data.get(i);
//        }
//
//        HistChart histChart = new HistChart("Распределение значений", d, 20, "Распределение значений", "x", "P(x)", null);
//        histChart.pack();
//        histChart.setVisible(true);
        for (int i = 0; i < 5; i++) {
            data = cc.generateExpData(dataCount, lambda);
            dataCens = cc.generateExpData(censCount, lambda);

//            res = cc.find(0,1, 0.000001, data,dataCens );
            res = cc.rightCens(data, dataCens);
            System.out.print("T " + censCount + "labda is" + res + "\t");
//            res = cc.accuracyLeft(res,(double)censCount, dataCens);
            res = cc.accuracyRight(res, dataCount);
            System.out.println("accuracy is" + Math.sqrt(res) + "\t");
            censCount += 10;
        }

//        res = cc.rightCens(data,dataCens );
//        System.out.print("labda is"+ res + "\t");

//        dataCens.stream().forEach(System.out::println);
    }
}
