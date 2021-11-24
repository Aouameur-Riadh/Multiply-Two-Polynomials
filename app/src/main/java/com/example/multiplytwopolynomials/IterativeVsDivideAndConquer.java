package com.example.multiplytwopolynomials;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Random;

public class IterativeVsDivideAndConquer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterative_vs_divide_and_conquer);
        ArrayList<Entry> data1 = new ArrayList<>();
        ArrayList<Entry> data2 = new ArrayList<>();
        LoadingDialog loadingDialog = new LoadingDialog(IterativeVsDivideAndConquer.this);
        loadingDialog.startLoadingDialog();

        int size =0;
        Random random = new Random();
        for (int i=1;i<=5;i++){
            size = (int) Math.pow(2,i);
            int[] t1 = new int[size];
            int[] t2 = new int[size];
            for (int k = 0; k<size; k++){
                t2[k]= Math.abs(random.nextInt());
                t1[k]= Math.abs(random.nextInt());
            }
            Algorithm_1 algorithm_1 = new Algorithm_1();
            long time1 = System.nanoTime();
            algorithm_1.multiply(t1,t2,size,size);
            long time2 = System.nanoTime();


            Algorithm_2 algorithm_2 = new Algorithm_2();
            long time3 = System.nanoTime();
            algorithm_2.multiply(t1,t2,size);
            long time4 = System.nanoTime();


            data1.add(new Entry(size,time2-time1));
            data2.add(new Entry(size,time4-time3));
        }

        LineChart lineChart = findViewById(R.id.lineChart);

        LineDataSet lineDataSet_1 = new LineDataSet(data1,"Iterative");
        lineDataSet_1.setLineWidth(2);
        lineDataSet_1.setDrawValues(false);
        lineDataSet_1.setColor(Color.RED);
        lineDataSet_1.setCircleRadius(6);
        lineDataSet_1.setCircleHoleRadius(3);
        lineDataSet_1.setDrawCircles(false);
        lineDataSet_1.setDrawHighlightIndicators(true);
        lineDataSet_1.setHighlightEnabled(true);
        lineDataSet_1.setHighLightColor(Color.RED);
        lineDataSet_1.setValueTextSize(12);
        lineDataSet_1.setValueTextColor(Color.DKGRAY);
        lineDataSet_1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        LineDataSet lineDataSet_2 = new LineDataSet(data2,"Divide  And Conquer");
        lineDataSet_2.setLineWidth(2);
        lineDataSet_2.setDrawValues(false);


        lineDataSet_2.setColor(Color.CYAN);
        lineDataSet_2.setCircleRadius(6);
        lineDataSet_2.setCircleHoleRadius(3);
        lineDataSet_2.setDrawCircles(false);
        lineDataSet_2.setDrawHighlightIndicators(true);
        lineDataSet_2.setHighlightEnabled(true);
        lineDataSet_2.setHighLightColor(Color.CYAN);
        lineDataSet_2.setValueTextSize(12);
        lineDataSet_2.setValueTextColor(Color.BLACK);
        lineDataSet_2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        LineData lineData = new LineData();

        lineData.addDataSet(lineDataSet_1);
        lineData.addDataSet(lineDataSet_2);
        lineChart.setData(lineData);
        lineChart.invalidate();

        loadingDialog.dismissDialog();

    }


}