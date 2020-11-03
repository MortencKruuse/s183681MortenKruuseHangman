package com.example.s183681mortenkruusehangman;

import java.util.ArrayList;

public class HighscoreList {
    private static HighscoreList single_instance = null;
    public ArrayList<Highscore> highscores;

    public static HighscoreList getInstance()
    {
        if (single_instance == null){
            single_instance = new HighscoreList();
            single_instance.addHighscore(new Highscore("Hejsa",5));
        }

        return single_instance;
    }

    public HighscoreList(){
        highscores = new ArrayList<>();
    }

    public void addHighscore(Highscore highscore){
        highscores.add(highscore);
    }
    public ArrayList<Highscore> getHighscores(){
        return highscores;
    }
}
