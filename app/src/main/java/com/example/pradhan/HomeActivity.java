package com.example.pradhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    ImageView toProfile,toadproduct,toProducts,tostocks,toorderSum,toaboutus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toProfile=findViewById(R.id.home_to_profile);
        toadproduct=findViewById(R.id.home_to_addproduct);
        toProducts=findViewById(R.id.home_to_allproducts);
        tostocks=findViewById(R.id.home_to_stocks);
        toorderSum=findViewById(R.id.home_to_ordersummary);
        toaboutus=findViewById(R.id.home_to_aboutus);



        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,Profileactivity.class);
                startActivity(intent);
            }
        });

        toadproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,Addproductactivity.class);
                startActivity(intent);
            }
        });
        toProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,ProductActivity.class);
                startActivity(intent);
            }
        });
        tostocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,StocksActivity.class);
                startActivity(intent);
            }
        });
        toorderSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,OrderSummaryActivity.class);
                startActivity(intent);
            }
        });

        toaboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }
}