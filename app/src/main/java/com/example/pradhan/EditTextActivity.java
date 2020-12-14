package com.example.pradhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditTextActivity extends AppCompatActivity {

    DatePicker dob_select;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    LinearLayout dob_layout;
    EditText edit_name, edit_mobile, edit_address;
    RadioGroup edit_gender_grp;
    RadioButton gender_radio;
    Button submit_btn_form;
    String name = "", mobile = "", dob_month = "", dob_year = "", dob_day = "", gender = "", address = "", user_id = "";
    int id = 0;
    Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        dob_select = findViewById(R.id.date_picker_profile);
        dob_layout = findViewById(R.id.date_picker_layout);
        edit_name = findViewById(R.id.edit_name_profile);
        edit_address = findViewById(R.id.edit_address_profile);
        edit_mobile = findViewById(R.id.edit_mobile_profile);
        edit_gender_grp = findViewById(R.id.edit_gender_profile);
        submit_btn_form = findViewById(R.id.edit_submitform_btn);


        map = new HashMap<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        user_id = firebaseAuth.getCurrentUser().getUid().toString();

        submit_btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edit_name.getText().toString().trim();
                mobile = edit_mobile.getText().toString().trim();
                address = edit_address.getText().toString().trim();

                if (id == 0) {

                }
                dob_day = dob_select.getDayOfMonth() + "/";
                int dob_month_num = dob_select.getMonth() +1;
                dob_month=dob_month_num+"/";
                dob_year = dob_select.getYear() + " ";
                String dob = dob_day + dob_month + dob_year;


                if (name.isEmpty()) {
                    Toast.makeText(EditTextActivity.this, "Naam de re", Toast.LENGTH_SHORT).show();
                } else {
                    id = edit_gender_grp.getCheckedRadioButtonId();

                    if (id==0) {
                        Toast.makeText(EditTextActivity.this, "Apna ling b nhi pta kya", Toast.LENGTH_SHORT).show();
                    } else {

                        gender_radio = findViewById(id);
                        gender = gender_radio.getText().toString().trim();

                        if (address.isEmpty()) {
                            Toast.makeText(EditTextActivity.this, "Kholi ka jgh bta re", Toast.LENGTH_SHORT).show();
                        }else{
                            if (mobile.isEmpty()) {
                                Toast.makeText(EditTextActivity.this, "Data update krna h to Number de", Toast.LENGTH_SHORT).show();
                            } else {


                                map.put("name", name);
                                map.put("mobile", mobile);
                                map.put("gender", gender);
                                map.put("address", address);
                                map.put("dob", dob.trim());


                                firebaseFirestore.collection("Details").document(user_id)
                                        .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(EditTextActivity.this, "Kaam ho gya maamu", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(EditTextActivity.this, Profileactivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(EditTextActivity.this, "Tera kaam nhi hua", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }

                        }

                    }
                }


            }
        });

    }
}