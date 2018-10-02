package com.example.potentie_feelsbook;

public class FearEmotionEntry extends EmotionEntry {

    private String name="Fear";

    FearEmotionEntry(){
        //Note is set to default, IE blank.
    }
    FearEmotionEntry(String newNote){
        setNote(newNote);
    }

    public String getName(){
        return name;
    }
}