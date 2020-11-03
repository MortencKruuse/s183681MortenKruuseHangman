package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lost extends AppCompatActivity implements View.OnClickListener {
    Button backbtn;
    Button playagainbtn;
    Galgelogik galgelogik;
    TextView attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        backbtn = findViewById(R.id.buttonLostBack);
        playagainbtn = findViewById(R.id.buttonLostPlayAgain);
        backbtn.setOnClickListener(this);
        playagainbtn.setOnClickListener(this);
        attempts = findViewById(R.id.tvAttemptsLost);
        galgelogik = Galgelogik.getInstance();
        ini();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            if (v.getId() == findViewById(R.id.buttonLostBack).getId()) {
                startActivity(new Intent(Lost.this,MainActivity.class));
            }
            else{
                startActivity(new Intent(Lost.this,HangmanGame.class));
            }
        }

    }
    @SuppressLint("SetTextI18n")
    public void ini(){
        attempts.setText("The word was " + galgelogik.getOrdet());
    }
}