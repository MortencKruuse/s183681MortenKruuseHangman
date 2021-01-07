package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Won extends AppCompatActivity implements View.OnClickListener {
    Button backbtn;
    Button playagainbtn;
    Galgelogik galgelogik;
    TextView attempts;
    HighscoreList highscoreList;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        backbtn = findViewById(R.id.buttonWonBack);
        playagainbtn = findViewById(R.id.buttonWonPlayAgain);
        backbtn.setOnClickListener(this);
        playagainbtn.setOnClickListener(this);
        attempts = findViewById(R.id.tvAttemptsWon);
        galgelogik = Galgelogik.getInstance();
        highscoreList = HighscoreList.getInstance();
        mp = MediaPlayer.create(this,R.raw.wonsound);
        mp.start();

        try {
            highscoreList.addHighscore(new Highscore(galgelogik.getName(),galgelogik.getAntalForkerteBogstaver()));
        }catch (NullPointerException e){
            highscoreList.addHighscore(new Highscore(galgelogik.getName(),0));
        }
        ini();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            if (v.getId() == findViewById(R.id.buttonWonBack).getId()) {
                startActivity(new Intent(Won.this,MainActivity.class));
            }
            else{
                startActivity(new Intent(Won.this,HangmanGame.class));
            }
        }

    }
    @SuppressLint("SetTextI18n")
    public void ini(){
        attempts.setText("The word was " + galgelogik.getOrdet() + " and you had " + galgelogik.getAntalForkerteBogstaver() + " wrong guesses.");
    }
}