package com.example.phrasesappforfinalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Login extends AppCompatActivity {
    private Button goReg, login;
    private EditText email, password;
    private FirebaseAuth finalAuth;


    public void goRegPage(View view){
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar b = getSupportActionBar();
        if (b != null){
            b.hide();
        }

        finalAuth = FirebaseAuth.getInstance();

        goReg = findViewById(R.id.goToReg);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginCode();
            }
        });


    }

    private void LoginCode(){
        String logEmail = email.getText().toString();
        String logPassword = password.getText().toString();

        if(TextUtils.isEmpty(logEmail) || TextUtils.isEmpty(logPassword)){
            Toast.makeText(Login.this, "გთხოვთ შეავსოთ ყველა ველი!", Toast.LENGTH_SHORT).show();
            return;
        } else if (logPassword.length() < 6){
            Toast.makeText(Login.this, "პაროლის სიგრძე უნდა აღემატებოდეს 6-ს!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(Login.this, "გთხოვთ მოიცადოთ...", Toast.LENGTH_SHORT).show();
        finalAuth.signInWithEmailAndPassword(logEmail, logPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(Login.this, "თქვენ წარმატებით გაიარეთ ავტორიზაცია!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this,"თქვენ ვერ გაიარეთ ავტორიზაცია!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


