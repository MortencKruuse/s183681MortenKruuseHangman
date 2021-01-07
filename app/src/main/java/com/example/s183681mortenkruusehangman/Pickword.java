package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pickword extends AppCompatActivity implements View.OnClickListener {
    ListView lv;
    Galgelogik galgelogik;
    Button backbtn,startbtn;
    ArrayList<String> words;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickword);
        backbtn = findViewById(R.id.buttonPickWordBack);
        lv = findViewById(R.id.pickwordList);
        startbtn = findViewById(R.id.buttonPickWordStart);
        galgelogik = Galgelogik.getInstance();
        words = galgelogik.getMuligeOrd();
        backbtn.setOnClickListener(this);
        startbtn.setOnClickListener(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        words);

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                galgelogik.setValgtOrd(words.get(position));
                Toast.makeText(Pickword.this,"The word is now " + " "+ words.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            if (v.getId() == findViewById(R.id.buttonPickWordBack).getId()) {
                startActivity(new Intent(Pickword.this, MainActivity.class));
            }
            else if (v.getId() == findViewById(R.id.buttonPickWordStart).getId()) {
                if(galgelogik.getValgtOrd().equals("Intet")){}
                else startActivity(new Intent(Pickword.this, HangmanGame.class));
            }

        }

    }
}