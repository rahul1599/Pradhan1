package com.example.pradhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Addproductactivity extends AppCompatActivity {

    EditText name, quantity;
    Button submit_btn;
    String get_name = "", get_qty = "";
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Map<String, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproductactivity);

        name = findViewById(R.id.addproduct_name);
        quantity = findViewById(R.id.addproduct_initial_qty);
        submit_btn = findViewById(R.id.submit_btn_addProduct);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        map = new HashMap<>();


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_name = name.getText().toString().trim();
                get_qty = quantity.getText().toString().trim();

                if (get_qty.isEmpty()) {

                    Toast.makeText(Addproductactivity.this, "Krapya  qty ka Rikt Sthan bhare", Toast.LENGTH_SHORT).show();

                } else {
                    if (get_name.isEmpty()) {

                        Toast.makeText(Addproductactivity.this, "naam ka Rikt Sthan bhare", Toast.LENGTH_SHORT).show();
                    } else {

                        map.put("name", get_name);
                        map.put("quantity", get_qty);


                        firebaseFirestore.collection("Details").document(firebaseAuth.getCurrentUser().getUid().toString())
                                .collection("Products").document(get_name)
                                .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(Addproductactivity.this, "Nya maal jod dia poori list dekh le", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Addproductactivity.this, StocksActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Addproductactivity.this, "kuch jhol h re tere data m, kaam nhi hua", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });


                    }
                }
            }


        });

    }
}