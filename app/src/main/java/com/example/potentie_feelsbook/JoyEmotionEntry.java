package com.example.potentie_feelsbook;

public class JoyEmotionEntry extends EmotionEntry {

    private String name="Joy";
    private int icon =R.mipmap.ic_joy;

    JoyEmotionEntry(){
        //Note is set to default, IE blank.
    }
    JoyEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return this.icon;
    }
}