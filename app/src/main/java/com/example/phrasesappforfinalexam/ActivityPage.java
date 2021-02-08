package com.example.phrasesappforfinalexam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ActivityPage extends AppCompatActivity {
    ListView myList;
    TextView headLine;
    FirebaseFirestore myStore;
    DocumentReference myRef, myRef2;

    String[] authors = {"ჯ.რ.რ ტოლკინი", "ჯექსონ ბრაუნი", "ანდრე ჟიდი", "ჩაკ პალანიკი", "დევიდ მიშელი", "ანნა რიჩი",
    "ჰარპერ ლი", "პაულო კოელიო", "ლუის კეროლი", "პიტაკუს ლორი"};
    String[] authors2 = {"ლევ ტოლსტოი", "კონსტანტინ ხაბენსკი", "ვიტალი გიბერტი", "კოკო შანელი", "ჟორჟ სანდი",
    "ალექსანდრე პუშკინი", "მარინა ცვეტაევა", "ანტონ ჩეხოვი", "მოემი", "პაულო კოელიო"};
    String[] authorPhrases2 = new  String[10];
    String[] authorPhrases = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.hide();
        }


        myList = findViewById(R.id.myListView);
        headLine = findViewById(R.id.satauri);


        Intent intent = getIntent();
        String text = intent.getStringExtra("abc");
        headLine.setText(text);





        myStore = FirebaseFirestore.getInstance();
        myRef = myStore.collection("InterestingPhrases").document("WPig98D4bc2ws153SDTW");
        myRef2 = myStore.collection("LovePhrases").document("fu0t0E2BM4ILQJz4glVl");
        MyAdapter adapter = new MyAdapter();
        myList.setAdapter(adapter);

        myRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                authorPhrases[0] = value.getString("1");
                authorPhrases[1] = value.getString("2");
                authorPhrases[2] = value.getString("3");
                authorPhrases[3] = value.getString("4");
                authorPhrases[4] = value.getString("5");
                authorPhrases[5] = value.getString("6");
                authorPhrases[6] = value.getString("7");
                authorPhrases[7] = value.getString("8");
                authorPhrases[8] = value.getString("9");
                authorPhrases[9] = value.getString("10");
            }
        });

        myRef2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                authorPhrases2[0] = value.getString("1");
                authorPhrases2[1] = value.getString("2");
                authorPhrases2[2] = value.getString("3");
                authorPhrases2[3] = value.getString("4");
                authorPhrases2[4] = value.getString("5");
                authorPhrases2[5] = value.getString("6");
                authorPhrases2[6] = value.getString("7");
                authorPhrases2[7] = value.getString("8");
                authorPhrases2[8] = value.getString("9");
                authorPhrases2[9] = value.getString("10");
            }
        });



    }


    class MyAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = getLayoutInflater().inflate(R.layout.listview_design,null);
            TextView authorName = myView.findViewById(R.id.author);
            TextView phrases = myView.findViewById(R.id.phrase);

            if (headLine.getText().toString().equals("ბრძნული გამონათქვამები")){
                authorName.setText(authors[position]);
                phrases.setText(authorPhrases[position]);
            }
            if (headLine.getText().toString().equals("სასიყვარულო ფრაზები")) {
                authorName.setText(authors2[position]);
                phrases.setText(authorPhrases2[position]);
            }

            return myView;
        }
    }
}


