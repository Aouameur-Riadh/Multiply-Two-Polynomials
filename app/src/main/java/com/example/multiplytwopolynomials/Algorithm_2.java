package com.example.multiplytwopolynomials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm_2 {
    ArrayList<ExponentAndCoefficient> p = new ArrayList<>();
    ArrayList<ExponentAndCoefficient> q = new ArrayList<>();

    public Algorithm_2(String p, String q) {
        this.p = Conv(p);
        this.q = Conv(q);

    }

    public Algorithm_2() {
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


    int [ ] multiply(int [ ] p, int [ ] q, int n)
    {
        // There are not more terms that could be divided and the problem couldnt be made any smaller
        if (n == 1)
        {
            int [ ] temp = {p[0] * q[0]};
            return temp;
        }
        // dividing the problem to 'd' number of coefficients
        int d = n/2;
        //split p and q into arrays that store low and high order terms respectively
        int [ ] pHigh = new int[d];
        int [ ] qHigh = new int [d];
        int [ ] pLow = new int[d-n%2];
        int [ ] qLow = new int[d-n%2];

        // assigning the respective coefficients to low and high arrays of p and q
        for (int i = 0; i < d; i++)
        {
            pHigh[i] = p[i+d];
            qHigh[i] = q[i+d];
            pLow[i] = p[i];
            qLow[i] = q[i];
        }

        // the arrays which stores the addition which helps us in making the
        // number of multiplication operations 3, for more information refer description
        int [ ] addLowHighP = new int[d] ;
        int [ ] addLowHighQ = new int[d];

        // initialising arrays
        for(int i = 0 ; i<(d) ; i++)
        {
            addLowHighP[i] = pLow[i]+pHigh[i];
            addLowHighQ[i] = qLow[i]+qHigh[i];
        }

        // These are the 3 subproblems

        // The pLow.qLow part of the multiplication
        int [ ] lowPQ = multiply(pLow,qLow,d);

        // middle term of the solution
       int [ ] middle = multiply(addLowHighP,addLowHighQ,d);

        // the last term pHigh.qHigh.x^n
        int [ ] highPQ = multiply(pHigh,qHigh,d);

        // the array that stores the product of the polynomials
        int [ ] pq = new int[(2 * n)-1];

        // storing the collected values in the product array, refer description for more information
        for (int i = 0; i < n-1; i++)
        {
            pq[i] += lowPQ[i];
            pq[i+d] += middle[i]  - lowPQ[i] - highPQ[i];
            pq[i+2*d] += highPQ[i];
        }
        // returns the array that has mulitplication of two polynomials
        return pq;
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
        if (n == m && isPowerOfTwo(n)) {
            long t1 = System.nanoTime();
            int[] prod = multiply(A1, A2, m);
            long t2 = System.nanoTime();
            String st = printPoly(prod, m + n - 1);
            String st2 = printPoly(A1, m);
            String st3 = printPoly(A2, n);
            info.put("1", st);
            info.put("2", st2);
            info.put("3", st3);
            info.put("4", "O(n^log₂3) = O(n^1.58)");
            info.put("6", t2 - t1 + "");
            return info;
        }else {

            info.put("1","Error");
            return info;
        }


    }

   boolean isPowerOfTwo(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
                == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

}
