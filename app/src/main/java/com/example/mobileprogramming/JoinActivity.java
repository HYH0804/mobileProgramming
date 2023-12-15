package com.example.mobileprogramming;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
// Login Activity 에서 JoinActivity 로 전환
public class JoinActivity extends AppCompatActivity {
    EditText emailEt;
    EditText passwordEt;
    EditText passwordConfirmEt;

    Button joinBtn;
    ImageView backIv;
    /*ProgressBar loginPb;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        passwordConfirmEt = findViewById(R.id.password_confirm_et); //비번 다시 입력
        joinBtn = findViewById(R.id.join_btn);
        backIv = findViewById(R.id.back_iv); //back 버튼

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinActivity.this,LoginActivity.class); //back 버튼 누르면 다시 Login 액티비티로
                startActivity(intent);
                finish();
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString(); //아이디 가져오고
                String password = passwordEt.getText().toString(); //패스워드 가져오고
                String passwordConfirm = passwordConfirmEt.getText().toString(); //변수에 저장

                if (email.equals("")) {
                    Toast.makeText(JoinActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show(); // 이메일 공백 예외처리
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 6자이상 입력해주세요", Toast.LENGTH_SHORT).show(); // 패스워드 자리수 예외 처리
                    return;
                }

                if (!password.equals(passwordConfirm)) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show(); // 패스워드 재입력 서로 다를때
                    passwordConfirmEt.setText("");
                    passwordConfirmEt.requestFocus();
                    return;
                }

                login(email, password);

            }
        });
    }
    public void login(String email, String password){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password) //회원가입 시도
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() { //회원가입 완료 리스너
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { //핸들러
                        if(task.isSuccessful()){ //완료 후 Login Activity 전환
                            Toast.makeText(JoinActivity.this,"회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JoinActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        else { //회원가입 실패한 경우
                            String message = task.getException().getMessage();
                            Toast.makeText(JoinActivity.this,message, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    }

