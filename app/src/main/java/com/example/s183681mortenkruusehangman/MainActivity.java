package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Galgelogik galgelogik;
    Button startbtn;
    Button highscoresbtn;
    TextView tvname;
    Button pickwordbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     /*   if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

      */
        galgelogik = Galgelogik.getInstance();
        startbtn = findViewById(R.id.startButton);
        pickwordbtn = findViewById(R.id.pickwordButton);
        highscoresbtn = findViewById(R.id.highscoreButton);
        tvname = findViewById(R.id.etTextPersonName);
        startbtn.setOnClickListener(this);
        highscoresbtn.setOnClickListener(this);
        pickwordbtn.setOnClickListener(this);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    galgelogik.hentOrdFraRegneark("2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            if (v.getId() == findViewById(R.id.startButton).getId()) {
                galgelogik.setName(tvname.getText().toString());
                System.out.println(galgelogik.getName());
                Toast.makeText(MainActivity.this,"Getting words from spreadsheet",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, HangmanGame.class));
            } else if (v.getId() == findViewById(R.id.highscoreButton).getId()) {
                startActivity(new Intent(MainActivity.this, Highscores.class));

            } else if (v.getId() == findViewById(R.id.pickwordButton).getId()) {
                Toast.makeText(MainActivity.this,"Getting words from spreadsheet",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Pickword.class));
            }

        }

    }
}
