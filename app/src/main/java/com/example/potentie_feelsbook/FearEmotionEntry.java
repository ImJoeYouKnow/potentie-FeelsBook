package com.example.potentie_feelsbook;

//An object representing an emotion that is recorded by the user, extending EmotionEntry
//Used to isolate emotion records from each other
public class FearEmotionEntry extends EmotionEntry {

    private String name="Fear";
    private int icon =R.mipmap.ic_fear;

    FearEmotionEntry(){
        //Note is set to default, IE blank.
    }
    FearEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return this.icon;
    }
}