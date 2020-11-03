package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Won extends AppCompatActivity implements View.OnClickListener {
    Button backbtn;
    Button playagainbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        backbtn = findViewById(R.id.buttonWonBack);
        playagainbtn = findViewById(R.id.buttonWonPlayAgain);
        backbtn.setOnClickListener(this);
        playagainbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            if (v.getId() == findViewById(R.id.buttonWonBack).getId()) {
                startActivity(new Intent(Won.this,MainActivity.class));
            }
        }
        else{
            startActivity(new Intent(Won.this,HangmanGame.class));
        }
    }
}