package com.example.s183681mortenkruusehangman;

public class Highscore implements Comparable<Highscore> {
    private String name;
    private int wrong;



    public Highscore(String name, int wrong){
        super();
        this.name = name;
        this.wrong = wrong;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    @Override
    public String toString(){
        return name + " " + wrong + "\n";
    }

    @Override
    public int compareTo(Highscore o)    {
        int comparescore = ((Highscore)o).getWrong();
        return this.getWrong()-comparescore;
    }
}
