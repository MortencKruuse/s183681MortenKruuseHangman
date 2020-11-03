package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Highscores extends AppCompatActivity implements View.OnClickListener {
    private ListView lv;
    ArrayList<String> list;
    Button backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        backbtn = findViewById(R.id.backButton);
        list = new ArrayList<>();
        list.add("Andreas 1 forkerte");
        list.add("Signe 2 forkerte");
        list.add("Jacob 3 forkerte");
        list.add("Silas 4 forkerte");
        list.add("Johannes 5 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        list.add("Julie 6 forkerte");
        lv = findViewById(R.id.highscorelist);
        backbtn.setOnClickListener(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, list);

        lv.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}