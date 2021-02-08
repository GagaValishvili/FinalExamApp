package com.example.phrasesappforfinalexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private Button register, goBack;
    private ImageButton calendar;
    private EditText fullName, password, email, date1;
    private FirebaseAuth finalAuth;
    private FirebaseFirestore finalStore;
    int myDate, myMonth, myYear;
    String userID;

    public void goLogPage(View view){
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar c = getSupportActionBar();
        if (c != null){
            c.hide();
        }

        finalAuth = FirebaseAuth.getInstance();
        finalStore = FirebaseFirestore.getInstance();

        date1 = findViewById(R.id.date1);
        calendar = findViewById(R.id.calendar);
        register = findViewById(R.id.register);
        goBack = findViewById(R.id.goBack);
        fullName = findViewById(R.id.fullName);
        password = findViewById(R.id.password2);
        email = findViewById(R.id.email2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterCode();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                myDate = calendar.get(Calendar.DAY_OF_MONTH);
                myMonth = calendar.get(Calendar.MONTH);
                myYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            date1.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, myYear, myMonth, myDate);
                datePickerDialog.show();
            }
        });




    }






    public void RegisterCode() {
        String regFullName = fullName.getText().toString();
        String regEmail = email.getText().toString();
        String regBirthDate = date1.getText().toString();
        String regPassword = password.getText().toString();



        if(TextUtils.isEmpty(regFullName) || TextUtils.isEmpty(regEmail) || TextUtils.isEmpty(regPassword)){
            Toast.makeText(Register.this, "გთხოვთ შეავსოთ ყველა ველი!", Toast.LENGTH_SHORT).show();
            return;
        } else if (regPassword.length() < 6) {
            Toast.makeText(Register.this, "პაროლის სიგრძე უნდა აღემატებოდეს 6-ს!", Toast.LENGTH_SHORT).show();
            return;
        }





        Toast.makeText(Register.this,"გთხოვთ მოიცადოთ...", Toast.LENGTH_SHORT).show();
        finalAuth.createUserWithEmailAndPassword(regEmail, regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    userID = finalAuth.getCurrentUser().getUid();
                    DocumentReference myRef = finalStore.collection("Users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("Full Name", regFullName);
                    user.put("Birth Date", regBirthDate);
                    user.put("Email", regEmail);
                    myRef.set(user);
                    Toast.makeText(Register.this,"თქვენ წარმატებით დარეგისტრირდით!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Register.this,"თქვენ ვერ გაიარეთ რეგისტრაცია!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}