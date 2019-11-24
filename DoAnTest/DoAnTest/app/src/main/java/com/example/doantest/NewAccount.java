package com.example.doantest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewAccount extends AppCompatActivity {
    private EditText regEmail,regPass,regConfirm;
    private FirebaseAuth mFirebaseAuth;
    private Button regBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        mFirebaseAuth = FirebaseAuth.getInstance();
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPas);
        regConfirm = (EditText) findViewById(R.id.regPasConfirm);
        regBtn = (Button) findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regEmail.getText().toString();
                String pass = regPass.getText().toString();
                String confirm = regConfirm.getText().toString();
//                FirebaseUser user = mFirebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Toast.makeText(NewAccount.this, user.getUid(), Toast.LENGTH_LONG).show();
//                } else {
//                    // User is signed out
//                    Toast.makeText(NewAccount.this, "out", Toast.LENGTH_LONG).show();
//                }
                if(email.isEmpty()){
                    regEmail.setError("Bạn chưa nhập email!");
                    regEmail.requestFocus();
                }
                else if(pass.isEmpty()) {
                    regPass.setError("Bạn chưa nhập mật khẩu!");
                    regPass.requestFocus();
                }
                else if(confirm.isEmpty()) {
                    regConfirm.setError("Bạn chưa nhập mật khẩu xác nhận!");
                    regConfirm.requestFocus();
                }
                else if(pass.length() < 6) {
                    regPass.setError("Mật khẩu phải lớn hơn 6 kí tự!");
                    regPass.requestFocus();
                }
                else if(!pass.equals(confirm)) {
                    regConfirm.setError("Mật khẩu xác nhận không trùng với mật khẩu!");
                    regConfirm.requestFocus();
                }
                else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(NewAccount.this ,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(NewAccount.this, "Tạo tài khoản mới thành công!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(NewAccount.this, "Không Thành công: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

    }

}
