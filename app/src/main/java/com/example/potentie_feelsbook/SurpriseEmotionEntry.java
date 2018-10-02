package com.example.potentie_feelsbook;

public class SurpriseEmotionEntry extends EmotionEntry {

    private String name="Surprise";

    SurpriseEmotionEntry(){
        //Note is set to default, IE blank.
    }
    SurpriseEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }
}