package com.example.potentie_feelsbook;


//An object representing an emotion that is recorded by the user, extending EmotionEntry
//Used to isolate emotion records from each other
public class SurpriseEmotionEntry extends EmotionEntry {

    private String name="Surprise";
    private int icon =R.mipmap.ic_surprise;

    SurpriseEmotionEntry(){
        //Note is set to default, IE blank.
    }
    SurpriseEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return this.icon;
    }
}