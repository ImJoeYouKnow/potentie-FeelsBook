package com.example.potentie_feelsbook;

public class AngerEmotionEntry extends EmotionEntry {

    private String name="Anger";
    private int icon =R.mipmap.ic_anger;

    AngerEmotionEntry(){
        //Note is set to default, IE blank.
    }
    AngerEmotionEntry(String newNote){
            setNote(newNote);
    }

    public String getName(){
        return name;
    }

    public int getIcon(){
        return this.icon;
    }

}