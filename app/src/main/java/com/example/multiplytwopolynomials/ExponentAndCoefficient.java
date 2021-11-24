package com.example.multiplytwopolynomials;

import java.util.Comparator;

public class ExponentAndCoefficient {
    private int a;
    private int b;

    public ExponentAndCoefficient(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public ExponentAndCoefficient(int a) {
        this.a = a;
        this.b = 0;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "a=" + a +
                ",b=" + b ;
    }

    public static Comparator<ExponentAndCoefficient> Sort = new Comparator<ExponentAndCoefficient>() {

        public int compare(ExponentAndCoefficient s1, ExponentAndCoefficient s2) {

            int rollno1 = s1.b;
            int rollno2 = s2.b;

            /*For ascending order*/
            return rollno1-rollno2;

            /*For descending order*/
            //rollno2-rollno1;
        }};
}
