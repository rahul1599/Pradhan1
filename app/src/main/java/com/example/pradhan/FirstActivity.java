package com.example.pradhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    FrameLayout frameLayout;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    ProgressBar progressBar;
    CardView card_profile,card_addproducts,card_stocks,card_ordersummary,card_aboutus,card_products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        auth=FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawerLayout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);
        frameLayout=findViewById(R.id.fragment_container);
        progressBar=findViewById(R.id.progressBarF);
        card_profile=findViewById(R.id.profile_card);
        card_addproducts=findViewById(R.id.addproduct_card);
        card_stocks=findViewById(R.id.stocks_card);
        card_ordersummary=findViewById(R.id.ordersummary_card);
        card_products=findViewById(R.id.products_card);
        card_aboutus=findViewById(R.id.aboutus_card);





        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(FirstActivity.this, MainActivity.class));
                    finish();
                }
            }
        };



        card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FirstActivity.this,Profileactivity.class);
                startActivity(intent);
            }
        });



        card_addproducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FirstActivity.this,Addproductactivity.class);
                startActivity(intent);
            }
        });



        card_stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FirstActivity.this,StocksActivity.class);
                startActivity(intent);
            }
        });



        card_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FirstActivity.this,ProductActivity.class);
                startActivity(intent);
            }
        });



        card_ordersummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FirstActivity.this,OrderSummaryActivity.class);
                startActivity(intent);
            }
        });


        card_aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FirstActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });






        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle=
                new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                        R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){

            case R.id.menu_Profile:{
                Intent intent= new Intent(FirstActivity.this,Profileactivity.class);
                startActivity(intent);
                Toast.makeText(this,"You are on Profile",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_add_products:{
                Intent intent= new Intent(FirstActivity.this,Addproductactivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_stocks:{
//                fragment=new StockFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();

                Intent intent= new Intent(FirstActivity.this,StocksActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_logout:{
                signOut();
                break;

            }
            case R.id.menu_about_Us:{
                Intent intent= new Intent(FirstActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.menu_order_summary:{
                Intent intent= new Intent(FirstActivity.this,OrderSummaryActivity.class);
                startActivity(intent);
                break;

            }



        }




        drawerLayout.closeDrawer(GravityCompat.START);



        return true;
    }

    public void signOut() {
        auth.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
    
    
    
}