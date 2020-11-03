package com.example.s183681mortenkruusehangman;

import java.util.Comparator;


public class ScoreSorter implements Comparator<Highscore>{
    @Override
    public int compare(Highscore o1, Highscore o2) {
        return o2.compareTo(o1);
    }
}


