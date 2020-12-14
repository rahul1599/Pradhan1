package com.example.pradhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Profileactivity extends AppCompatActivity {

      TextView name,mobile,email,gender,dob,address;
      String st_name, st_mobile,st_email,st_gender, st_dob,st_address,userId,st_not_given="Not Provided";
      FirebaseFirestore firebaseFirestore;
      FirebaseAuth firebaseAuth;

      ImageView edit_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);


        name=findViewById(R.id.name_profile);
        mobile=findViewById(R.id.mobile_profile);
        email=findViewById(R.id.email_profile);
        gender=findViewById(R.id.gender_profile);
        dob=findViewById(R.id.dob_profile);
        address=findViewById(R.id.address_profile);
        edit_personal=findViewById(R.id.edit_personal_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        userId=firebaseAuth.getCurrentUser().getUid().toString();



        DocumentReference documentReference=firebaseFirestore.collection("Details").document(userId);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (!task.getResult().equals(null)){
                Map<String,Object> obj=task.getResult().getData();

                    st_name = obj.get("name").toString();
                    st_mobile = obj.get("mobile").toString();
                    st_address = obj.get("address").toString();
                    st_dob = obj.get("dob").toString();
                    st_gender = obj.get("gender").toString();
                    st_email = firebaseAuth.getCurrentUser().getEmail().toString();


                    name.setText(st_name);
                    mobile.setText(st_mobile);
                    email.setText(st_email);
                    gender.setText(st_gender);
                    dob.setText(st_dob);
                    address.setText(st_address);
                }else{

                    name.setText(st_not_given);
                    mobile.setText(st_not_given);
                    email.setText(st_not_given);
                    gender.setText(st_not_given);
                    dob.setText(st_not_given);
                    address.setText(st_not_given);

                }


            }
        });











        edit_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Profileactivity.this,EditTextActivity.class);
                startActivity(intent);
            }
        });



    }
}