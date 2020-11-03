package com.example.s183681mortenkruusehangman;

import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    File sdCard;
    File file;
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
        sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
        directory.mkdirs();
        file = new File(directory,"mysdfile.txt");
        lines = new ArrayList<>();
        checkStorage();
        readFromFile();
        for (String s:lines
             ) {Log.d(TAG,s);

        }

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
            FileOutputStream fos = new FileOutputStream(file);
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
            FileInputStream fis = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
            myExternalFile = new File(getFilesDir(), filename);
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
        if(file.delete()){
            Log.d(TAG,"Slettet");
        }
    }
}

