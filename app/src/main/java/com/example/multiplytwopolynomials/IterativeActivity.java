package com.example.multiplytwopolynomials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IterativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterative);

       EditText editText_1= (EditText) findViewById(R.id.editText);
       EditText editText_2 = (EditText) findViewById(R.id.editText2);
       EditText editText_3 = (EditText) findViewById(R.id.editText3);
       Button button = findViewById(R.id.btn);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p =  editText_1.getText().toString();
                String q =  editText_2.getText().toString();
                Algorithm_1 algorithm1 = new Algorithm_1(p,q);
                editText_3.setText(
               "P = " + algorithm1.multiplyInfo().get("2")
               +"\nQ = " + algorithm1.multiplyInfo().get("3")
               +"\nP * Q = " + algorithm1.multiplyInfo().get("1")
               +"\nDuration  = " + algorithm1.multiplyInfo().get("6")+" ns"
               +"\nComplexity  = " + algorithm1.multiplyInfo().get("4")
               +"\nNumber of operations  = " + algorithm1.multiplyInfo().get("5")
                );
            }
        });
    }
}