package com.example.potentie_feelsbook;

//An object representing an emotion that is recorded by the user, extending EmotionEntry
//Used to isolate emotion records from each other
public class SadnessEmotionEntry extends EmotionEntry {

    private String name="Sadness";
    private int icon =R.mipmap.ic_sadness;

    SadnessEmotionEntry(){
        //Note is set to default, IE blank.
    }
    SadnessEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return this.icon;
    }
}