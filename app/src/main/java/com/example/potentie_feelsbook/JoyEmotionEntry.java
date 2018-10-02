package com.example.potentie_feelsbook;

public class JoyEmotionEntry extends EmotionEntry {

    private String name="Joy";

    JoyEmotionEntry(){
        //Note is set to default, IE blank.
    }
    JoyEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }
}