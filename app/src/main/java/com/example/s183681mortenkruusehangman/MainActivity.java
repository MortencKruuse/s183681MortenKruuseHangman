package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Galgelogik galgelogik;
    Button startbtn;
    Button highscoresbtn;
    TextView tvname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        galgelogik = Galgelogik.getInstance();
        startbtn = findViewById(R.id.startButton);
        highscoresbtn = findViewById(R.id.highscoreButton);
        tvname = findViewById(R.id.etTextPersonName);
        startbtn.setOnClickListener(this);
        highscoresbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button){
            if(v.getId() == findViewById(R.id.startButton).getId() && !tvname.getText().toString().equals("Name here")){
                System.out.println("Test");
                galgelogik.setName(tvname.getText().toString());
                System.out.println(galgelogik.getName());
                startActivity(new Intent(MainActivity.this,HangmanGame.class));

            }
            else if(v.getId() == findViewById(R.id.highscoreButton).getId()){
                startActivity(new Intent(MainActivity.this, Highscores.class));
            }
        }

    }

}
