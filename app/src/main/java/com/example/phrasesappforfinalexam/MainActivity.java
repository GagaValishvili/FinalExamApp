package com.example.phrasesappforfinalexam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    TextView nameView, emailView, dateView, menuView;
    FirebaseAuth myAuth;
    Button menu, logOut, funnyPhrases, interPhrases, lovePhrases, userInfo, close;
    FirebaseFirestore myFireStore;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }

        myAuth = FirebaseAuth.getInstance();
        myFireStore = FirebaseFirestore.getInstance();

        menu = findViewById(R.id.menu);
        logOut = findViewById(R.id.logOut);
        funnyPhrases = findViewById(R.id.funnyPhrases);
        interPhrases = findViewById(R.id.interPhrases);
        lovePhrases = findViewById(R.id.lovePhrases);
        userInfo = findViewById(R.id.userInfo);
        close = findViewById(R.id.close);
        menuView = findViewById(R.id.iconView);
        dateView = findViewById(R.id.dateView);
        nameView = findViewById(R.id.fullNameView);
        emailView = findViewById(R.id.emailView);

        user = myAuth.getCurrentUser().getUid();
        DocumentReference docs = myFireStore.collection("Users").document(user);
        docs.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                nameView.setText(value.getString("Full Name"));
                emailView.setText(value.getString("Email"));
                dateView.setText(value.getString("Birth Date"));
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.INVISIBLE);
                logOut.setVisibility(View.INVISIBLE);
                menuView.setVisibility(View.VISIBLE);
                userInfo.setVisibility(View.VISIBLE);
                interPhrases.setVisibility(View.VISIBLE);
                lovePhrases.setVisibility(View.VISIBLE);
                funnyPhrases.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.VISIBLE);
                logOut.setVisibility(View.VISIBLE);
                menuView.setVisibility(View.INVISIBLE);
                userInfo.setVisibility(View.INVISIBLE);
                interPhrases.setVisibility(View.INVISIBLE);
                lovePhrases.setVisibility(View.INVISIBLE);
                funnyPhrases.setVisibility(View.INVISIBLE);
                close.setVisibility(View.INVISIBLE);

            }
        });




    }
}