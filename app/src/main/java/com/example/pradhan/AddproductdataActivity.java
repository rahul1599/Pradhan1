package com.example.pradhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddproductdataActivity extends AppCompatActivity {

    Button addqtysubmit;
    RadioGroup category;
    RadioButton sold_bought;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproductdata);
        addqtysubmit=findViewById(R.id.submit_btn_addProduct);
        category=findViewById(R.id.edit_select_category);




        addqtysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}