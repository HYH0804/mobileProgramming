package com.example.mobileprogramming;
import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.common.base.Charsets;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginActivity extends AppCompatActivity {

    EditText emailEt;
    EditText passwordEt;

    Button LoginBtn;
    Button JoinBtn;

    float v = 0;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    final int REQ_GOOGLE_SIGNIN = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser user = auth.getCurrentUser();//인증된 정보를 저장하고 있다가 들어갈 때마다 로그인 안하게
        updateUI(user); //로그인이 되어있을때만

        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password);

        LoginBtn = findViewById(R.id.login_btn);
        JoinBtn = findViewById(R.id.join_btn);





        JoinBtn.setOnClickListener(new View.OnClickListener() { //회원가입
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });


        LoginBtn.setOnClickListener(new View.OnClickListener() { //로그인 클릭 리스너
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString(); //View 에서 email 가져오고
                String password = passwordEt.getText().toString(); // View에서 password 가져오고
                if (email.equals("")) {
                    Toast.makeText(LoginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 1) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                //여기까지 예외처리

                login(email, password);
            }
        });
    }



    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*loadingPb.setVisibility(GONE);*/
                        if (task.isSuccessful()) { //로그인 성공시
                            FirebaseUser user = auth.getCurrentUser(); //로그인 정보를 받아옴
                            String email = user.getEmail(); //로그인 정보를 불러와서 toast로 띄움
                            Toast.makeText(LoginActivity.this, email + "님 로그인되셨습니다", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else { //로그인 실패시
                            String message = task.getException().getMessage();
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser user) { //로그인 되고 나서 다시 Activity 돌아가기
        if (user != null) {//사용자 로그인이 null이 아닐때만
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }





}
