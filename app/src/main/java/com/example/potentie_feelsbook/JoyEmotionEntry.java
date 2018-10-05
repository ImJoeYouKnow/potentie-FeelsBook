package com.example.potentie_feelsbook;

//An object representing an emotion that is recorded by the user, extending EmotionEntry
//Used to isolate emotion records from each other
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