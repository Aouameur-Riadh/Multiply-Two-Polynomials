package com.example.multiplytwopolynomials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class DivideAndConquerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divide_and_conquer);
        EditText editText_1= (EditText) findViewById(R.id.editText);
        EditText editText_2 = (EditText) findViewById(R.id.editText2);
        EditText editText_3 = (EditText) findViewById(R.id.editText3);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p =  editText_1.getText().toString();
                String q =  editText_2.getText().toString();
                Algorithm_2 algorithm2 = new Algorithm_2(p,q);
                HashMap<String, String> hashMap =algorithm2.multiplyInfo();
                if (hashMap.get("1").equals("Error")){
                    editText_3.setText("Check your polynomials");
                }else
                { editText_3.setText(
                            "P = " + hashMap.get("2")
                                    +"\nQ = " + hashMap.get("3")
                                    +"\nP * Q = " + hashMap.get("1")
                                    +"\nDuration  = " +hashMap.get("6")+" ns"
                                    +"\nComplexity  = " + hashMap.get("4")

                    );
                }

            }
        });

    }
}