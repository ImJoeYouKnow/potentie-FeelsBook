package com.example.potentie_feelsbook;

public class SadnessEmotionEntry extends EmotionEntry {

    private String name="Sadness";

    SadnessEmotionEntry(){
        //Note is set to default, IE blank.
    }
    SadnessEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }
}