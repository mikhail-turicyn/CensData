package com.MIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class CensCalc {
    final double PHI = (1 + sqrt(5)) / 2;

    double f(double lambda, List<Double> data, List<Double> dataCens) {
        int k = data.size();
        double sum = 0;
        for (int i = 0; i < dataCens.size(); i++) {
            sum += dataCens.get(i) / (1 - exp(-lambda * dataCens.get(i)));
        }
        sum += (double) k / lambda -
                data.stream().collect(Collectors.summingDouble((p) -> p)) -
                dataCens.stream().collect(Collectors.summingDouble((p) -> p));
        return sum;
    }

    double accuracyLeft(double lambda, double k, List<Double> dataCens) {
        double sum = 0;
        for (int i = 0; i < dataCens.size(); i++) {
            sum += pow(dataCens.get(i), 2) * exp(-lambda * dataCens.get(i)) /
                    pow((1 - exp(-lambda * dataCens.get(i))), 2);
        }
        return 1 / (k / pow(lambda, 2) + sum);
    }

    double find(double infinum, double supremum, double epsilon, List<Double> data, List<Double> dataCens) {
        double x;
        while (supremum - infinum > epsilon) {
            x = (infinum + supremum) / 2;
            if (f(supremum, data, dataCens) * f(x, data, dataCens) < 0)
                infinum = x;
            else
                supremum = x;
        }
        return (infinum + supremum) / 2;
    }

    public ArrayList generateExpData(int count, double lambda) {
        Random rand = new Random();
        ArrayList result = new ArrayList();
        for (int i = 0; i < count; i++) {
            result.add(log(rand.nextDouble()) / (-lambda));
        }
        return result;
    }

    public double rightCens2(List<Double> data, List<Double> dataCens) {
        double sum = 0;
        for (double el : data) {
            sum += el;
        }
        for (double el : dataCens) {
            sum += el;
        }
        return data.size() / sum;
    }

    public double rightCens(List<Double> data, List<Double> dataCens) {
        return data.size()
                / (data.stream().collect(Collectors.summingDouble((p) -> p))
                + dataCens.stream().collect(Collectors.summingDouble((p) -> p)));
    }

//    public double leftCens( List<Double> data,List<Double> dataCens){
//        Random rand = new Random();
//        int k = data.size();
//        double lambda=0.001;
//        double sum = 10;
//        double delta =0.001;
//        //перебираем лямбды
//        while(Math.abs(sum) > delta) {
//            sum =  0;
//            double t = 0;
//            for (int i = 0; i < dataCens.size(); i++) {
//                t = dataCens.get(i);
////                sum = (t*Math.log(1-t)/(-lambda))/
////                        (1-Math.log(1-t)/(-lambda));
//                sum = (t*Math.exp(-lambda*t))/(1-Math.exp(-lambda*t));
//            }
//            System.out.println("sum data is: "+sum);
//            sum += (double)k/lambda - data.stream().collect(Collectors.summingDouble((p)->p));
//            System.out.println("sum is: "+sum);
//            lambda = lambda - 0.0001;
//        }
//        return lambda;
//    }

    public double accuracyRight(double eval, int count) {
        return pow(eval, 2) / count;
    }
}
