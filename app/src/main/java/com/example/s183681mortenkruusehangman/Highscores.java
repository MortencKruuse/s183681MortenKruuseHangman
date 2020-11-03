package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class Highscores extends AppCompatActivity implements View.OnClickListener {
    private ListView lv;
    HighscoreList highscoreList;
    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String data;
    public static final String TAG = Highscores.class.getSimpleName();
    ArrayList<Highscore> highscoreArrayList;
    Button backbtn;
    List<String> lines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        backbtn = findViewById(R.id.backButton);
        highscoreList = HighscoreList.getInstance();
        highscoreArrayList = highscoreList.getHighscores();
        lv = findViewById(R.id.highscorelist);
        lines = new ArrayList<>();
        checkStorage();
        try {
            writeToFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        highscoreArrayList.clear();

        readFromFile();

        try {
            resetFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String lines : lines) {
            String[] arrOfStr = lines.split(" ");
            highscoreArrayList.add(new Highscore(arrOfStr[0], Integer.parseInt(arrOfStr[1])));
        }

        lines.clear();

        Collections.sort(highscoreArrayList);

        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readFromFile();

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

    public void writeToFile() throws IOException {

        try {
            FileOutputStream fos = new FileOutputStream(myExternalFile);
            for (Highscore highscores : highscoreArrayList) {
                data = highscores.toString();
                fos.write(data.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.clear();
        Log.d(TAG, "Saved");

    }

    public void readFromFile() {
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                lines.add(strLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Read");
    }

    public void checkStorage() {
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
        } else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
        }


    }
//These 3 methods have been taken from https://www.journaldev.com/9400/android-external-storage-read-write-save-file
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public void resetFile() throws IOException {
        if(myExternalFile.delete()){
            Log.d(TAG,"Slettet");
        }
    }
}

