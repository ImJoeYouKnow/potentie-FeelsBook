package com.example.potentie_feelsbook;

public class LoveEmotionEntry extends EmotionEntry {

    private String name="Love";
    private int icon =R.mipmap.ic_joy;

    LoveEmotionEntry(){
        //Note is set to default, IE blank.
    }
    LoveEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return this.icon;
    }
}