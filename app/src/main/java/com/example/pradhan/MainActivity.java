package com.example.pradhan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView toSignUp, toResetPass;
    EditText mail, pass;
    Button LogInbtn;
    String get_mail = "", get_pass = "";

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    Map<String, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toSignUp = findViewById(R.id.to_signUpfrmLog);
        toResetPass = findViewById(R.id.to_forgetPasswordFrmLog);
        LogInbtn = findViewById(R.id.logInBtn);
        mail = findViewById(R.id.login_mail);
        pass = findViewById(R.id.login_pass);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        ProgressBar progressBar=findViewById(R.id.progressBarMain);

        map = new HashMap<>();


        FirebaseUser user=firebaseAuth.getCurrentUser();
        if (user != null) {
            Intent intent= new Intent(MainActivity.this,FirstActivity.class);
               startActivity(intent);
               finish();
        }



        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        toResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        LogInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_mail = mail.getText().toString().trim();
                get_pass = pass.getText().toString().trim();
                map.put("email", get_mail);
                map.put("password", get_pass);



                if (TextUtils.isEmpty(get_mail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(get_pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(get_mail, get_pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    if (get_pass.length() < 6) {
                                        pass.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


//                Intent intent= new Intent(MainActivity.this,FirstActivity.class);
//                startActivity(intent);
            }
        });


    }
}