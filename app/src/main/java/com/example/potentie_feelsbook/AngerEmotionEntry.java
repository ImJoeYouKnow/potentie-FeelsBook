package com.example.potentie_feelsbook;

public class AngerEmotionEntry extends EmotionEntry {

    private String name="Anger";

    AngerEmotionEntry(){
        //Note is set to default, IE blank.
    }
    AngerEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }
}