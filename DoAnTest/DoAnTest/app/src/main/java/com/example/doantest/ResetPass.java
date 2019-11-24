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

public class ResetPass extends AppCompatActivity {
    private EditText emailRS;
    private TextView backBtn;
    private Button rstBtn;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        mFirebaseAuth = FirebaseAuth.getInstance();
        //Anh xa
        //backBtn = (TextView) findViewById(R.id.resetbackBtn);
        emailRS = (EditText) findViewById(R.id.emailReset);
        rstBtn = (Button) findViewById(R.id.resetBtn);
        //
        rstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailrs = emailRS.getText().toString();
                if(emailrs.isEmpty()) {
                    emailRS.setError("Bạn chưa nhập email!");
                    emailRS.requestFocus();
                }
                else {
                    progressDialog(emailrs);
                }

            }
        });
    }

    public void progressDialog(final String email){
        final ProgressDialog progressDialog = new ProgressDialog(ResetPass.this,R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing...");
        progressDialog.show();

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                            mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(ResetPass.this, "Thành công! Vui lòng kiểm tra email.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(ResetPass.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(ResetPass.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });

                    }
                },1000);
    }
}
