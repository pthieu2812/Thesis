package com.example.doantest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText emailID,password;
    private TextView forgotpwd;
    private Button loginBtn;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        mFirebaseAuth = FirebaseAuth.getInstance();
        //Anh xa
        forgotpwd = (TextView) findViewById(R.id.forgotpassword);
        emailID = (EditText) findViewById(R.id.emailID);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        emailID.setHint("Email");
        password.setHint("Password");
        //Login Button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty() && pwd.isEmpty()) {
                    emailID.setError("Please enter email id");
                    emailID.requestFocus();
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if (email.isEmpty()) {
                    emailID.setError("Please enter email id");
                    emailID.requestFocus();
                }
                else if (pwd.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else {
                    progressDialog();
                }
            }
        });
        //Forgot Password
        forgotpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPass.class);
                startActivity(intent);
            }
        });
    }

    public void progressDialog(){
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        final String email = emailID.getText().toString();
                        String pwd = password.getText().toString();
                        if (!email.isEmpty() && !pwd.isEmpty()) {
                            mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, " Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                        String admin = "admin@gmail.com";
                                        int isAdmin = email.compareTo(admin);
                                        if(isAdmin == 0) {
                                            Intent intent = new Intent(LoginActivity.this, admin.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });
                        }
                    }
                },1000);
    }
}