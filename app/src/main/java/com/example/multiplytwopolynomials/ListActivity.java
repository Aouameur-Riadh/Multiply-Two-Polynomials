package com.example.multiplytwopolynomials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void onOpenIterative(View view) {
        startActivity(new Intent(getApplicationContext(),IterativeActivity.class));
    }
    public void onOpenIterativeVsDivideAndConquer(View view) {
        Intent intent =new Intent(ListActivity.this, IterativeVsDivideAndConquer.class);
        startActivity(intent);
    }
    public void onOpenDivideAndConquer(View view) {
        startActivity(new Intent(getApplicationContext(), DivideAndConquerActivity.class));

    }
}