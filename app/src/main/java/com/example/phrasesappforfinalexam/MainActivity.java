package com.example.phrasesappforfinalexam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    TextView fullNameView, icon1, getFullNameV, getEmailV, getDateV, userInfoHeader, fullNameView2, emailView2, dateView2;
    FirebaseAuth myAuth;
    Button  logOut, close2;
    FirebaseFirestore myFireStore;
    String user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        myAuth = FirebaseAuth.getInstance();
        myFireStore = FirebaseFirestore.getInstance();

        icon1 = findViewById(R.id.icon1);
        logOut = findViewById(R.id.logOut);
        getDateV = findViewById(R.id.getDateV);
        fullNameView = findViewById(R.id.fullNameView);
        getFullNameV = findViewById(R.id.getFullNameV);
        getEmailV = findViewById(R.id.getEmailV);
        close2 = findViewById(R.id.close2);
        userInfoHeader = findViewById(R.id.userInfoHeader1);
        fullNameView2 = findViewById(R.id.fullNameView2);
        emailView2 = findViewById(R.id.emailView2);
        dateView2 = findViewById(R.id.dateView2);

        user = myAuth.getCurrentUser().getUid();
        DocumentReference docs = myFireStore.collection("Users").document(user);
        docs.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                fullNameView.setText(value.getString("Full Name"));
                getFullNameV.setText(value.getString("Full Name"));
                getEmailV.setText(value.getString("Email"));
                getDateV.setText(value.getString("Birth Date"));
            }
        });


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAuth.signOut();
                Intent sign_out = new Intent(MainActivity.this, Login.class);
                startActivity(sign_out);
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icon1.setVisibility(View.INVISIBLE);
                close2.setVisibility(View.INVISIBLE);
                fullNameView2.setVisibility(View.INVISIBLE);
                emailView2.setVisibility(View.INVISIBLE);
                dateView2.setVisibility(View.INVISIBLE);
                getDateV.setVisibility(View.INVISIBLE);
                getEmailV.setVisibility(View.INVISIBLE);
                getFullNameV.setVisibility(View.INVISIBLE);
                userInfoHeader.setVisibility(View.INVISIBLE);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int myId = item.getItemId();
        if (myId == R.id.userInfoHeader) {
            userInfoHeader.setVisibility(View.VISIBLE);
            icon1.setVisibility(View.VISIBLE);
            close2.setVisibility(View.VISIBLE);
            fullNameView2.setVisibility(View.VISIBLE);
            emailView2.setVisibility(View.VISIBLE);
            dateView2.setVisibility(View.VISIBLE);
            getDateV.setVisibility(View.VISIBLE);
            getEmailV.setVisibility(View.VISIBLE);
            getFullNameV.setVisibility(View.VISIBLE);
        } else if( myId == R.id.interPhrasesHeader) {
            Intent intent = new Intent(MainActivity.this, ActivityPage.class);
            intent.putExtra("abc", "ბრძნული გამონათქვამები");
            startActivity(intent);
        } else if(myId == R.id.lovePhrasesHeader) {
            Intent intent = new Intent(MainActivity.this, ActivityPage.class);
            intent.putExtra("abc", "სასიყვარულო ფრაზები");
            startActivity(intent);
        } else if(myId == R.id.moviePhrasesHeader) {
            Intent intent = new Intent(MainActivity.this, ActivityPage.class);
            intent.putExtra("abc", "ფრაზები კინო-ფილმებიდან");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}