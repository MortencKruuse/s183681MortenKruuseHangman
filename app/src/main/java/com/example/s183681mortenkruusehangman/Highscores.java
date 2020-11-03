package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class Highscores extends AppCompatActivity implements View.OnClickListener {
    private ListView lv;
    HighscoreList highscoreList;
    public static final String TAG = Highscores.class.getSimpleName();
    ArrayList<Highscore> highscoreArrayList;
    Button backbtn;
    List<String> lines;
    SharedPreferences sharedPreferences;
    Map<String, ?> stringMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        backbtn = findViewById(R.id.backButtonhighscores);
        highscoreList = HighscoreList.getInstance();
        highscoreArrayList = highscoreList.getHighscores();
        lv = findViewById(R.id.highscorelistview);
        sharedPreferences = getSharedPreferences("Highscore",MODE_PRIVATE);

        lines = new ArrayList<>();

        readFromSharedPrefs();

        lines.clear();

        Collections.sort(highscoreArrayList);

        writeToSharedPrefs();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        lines);
        lv.setAdapter(arrayAdapter);
        backbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public void makeViewable(){
        for (Highscore highscore: highscoreArrayList) {
            lines.add(highscore.toString());
        }
    }

    public void readFromSharedPrefs(){
        stringMap = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : stringMap.entrySet()){
            String[] strings = entry.getValue().toString().split(" ");
            strings[1] = strings[1].replaceAll("\n","");
            highscoreArrayList.add(new Highscore(strings[0],Integer.parseInt(strings[1])));
        }
    }

    public void writeToSharedPrefs(){
        sharedPreferences.edit().clear().apply();
        for (int i = 0; i < highscoreArrayList.size(); i++) {
            sharedPreferences.edit().putString(String.valueOf(i),highscoreArrayList.get(i).toString()).apply();
        }
        makeViewable();
        highscoreArrayList.clear();
    }

    }


