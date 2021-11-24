package com.example.multiplytwopolynomials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm_1 {
    ArrayList<ExponentAndCoefficient> p = new ArrayList<>();
    ArrayList<ExponentAndCoefficient> q = new ArrayList<>();

    public Algorithm_1(String p, String q) {
        this.p = Conv(p);
        this.q = Conv(q);

    }

    public Algorithm_1() {
    }

    public ArrayList<ExponentAndCoefficient> Conv(String poly) {
        ArrayList<ExponentAndCoefficient> arrayList = new ArrayList<>();
        // Does not handle a missing + or - on the first term


        Pattern p = Pattern.compile("\\+?(?<coef>-?\\d*)(?<var>x?\\^?)(?<exp>\\d*)");
        Matcher m = p.matcher(poly);

        while (m.find()) {
            if (!m.group("coef").equals("")) {
                int a = Integer.parseInt(m.group("coef"));
                ExponentAndCoefficient exponentAndCoefficient = new ExponentAndCoefficient(a);
                arrayList.add(exponentAndCoefficient);
                if (null != m.group("exp") && !m.group("exp").equals("")) {
                    int b = Integer.parseInt(m.group("exp"));
                    exponentAndCoefficient.setB(b);
                }
            }


        }
        return arrayList;
    }


    @Override
    public String toString() {
        return "Algorithm_1{" +
                "p=" + p +
                ", q=" + q +
                '}';
    }

    int[] multiply(int A[], int B[],
                   int m, int n) {
        int[] prod = new int[m + n - 1];

        // Initialize the product polynomial
        for (int i = 0; i < m + n - 1; i++) {
            prod[i] = 0;
        }

        // Multiply two polynomials term by term
        // Take ever term of first polynomial
        for (int i = 0; i < m; i++) {
            // Multiply the current term of first polynomial
            // with every term of second polynomial.
            for (int j = 0; j < n; j++) {
                prod[i + j] += A[i] * B[j];
            }
        }

        return prod;
    }

    String printPoly(int poly[], int n) {
        String st = "";
        for (int i = 0; i < n; i++) {
            System.out.print(poly[i]);
            st = st + poly[i];

            if (i != 0) {
                System.out.print("x^" + i);
                st = st + "x^" + i;
            }
            if (i != n - 1) {
                System.out.print(" + ");
                st = st + " + ";
            }
        }
        return st;
    }

    int[] convArrayListToArray(ArrayList<ExponentAndCoefficient> exponentAndCoefficients) {
        ArrayList<ExponentAndCoefficient> arrayList = new ArrayList<>();
        Collections.sort(exponentAndCoefficients, ExponentAndCoefficient.Sort);
        for (int i = 0; i < exponentAndCoefficients.size(); i++) {
            int res = 0;
            int b = exponentAndCoefficients.get(i).getB();
            for (int j = 0; j < exponentAndCoefficients.size(); j++) {
                if (exponentAndCoefficients.get(i).getB() == exponentAndCoefficients.get(j).getB()) {
                    res = res + exponentAndCoefficients.get(j).getA();
                }
            }
            arrayList.add(new ExponentAndCoefficient(res, b));
        }
        int max = 0;
        for (ExponentAndCoefficient exponentAndCoefficient : arrayList) {
            if (exponentAndCoefficient.getB() > max) {
                max = exponentAndCoefficient.getB();
            }
        }
        int[] a = new int[max + 1];
        for (int i = 0; i < max + 1; i++) {
            a[i] = 0;
        }

        for (int i = 0; i < arrayList.size(); i++) {
            a[arrayList.get(i).getB()] = arrayList.get(i).getA();
        }
        return a;
    }

    public HashMap<String, String> multiplyInfo() {
        HashMap<String, String> info = new HashMap<String, String>();
        int[] A1 = convArrayListToArray(this.p);
        int[] A2 = convArrayListToArray(this.q);
        int m = A1.length;
        int n = A2.length;
        long t1 = System.nanoTime();
        int[] prod = multiply(A1, A2, m, n);
        long t2 = System.nanoTime();
        String st = printPoly(prod, m + n - 1);
        String st2 = printPoly(A1, m);
        String st3 = printPoly(A2, n);
        info.put("1", st);
        info.put("2", st2);
        info.put("3", st3);
        info.put("4", "O(n^2)");
        info.put("5", n * m + "");
        info.put("6", t2 - t1 + "");
        return info;
    }


}
