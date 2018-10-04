package com.example.potentie_feelsbook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public abstract class EmotionEntry {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    String nowAsISO = df.format(new Date());

    private String note = "";

    public String getDate(){
        return this.nowAsISO;
    }
    public void setDate(String newDate){
        this.nowAsISO= newDate;
    }

    public String getNote(){
        return this.note;
    }
    public void setNote(String newNote){
        this.note=newNote;
    }

    public abstract String getName();

    public String toString() {
        if (!getNote().isEmpty()) {
            return getName() +" | "+ getNote() +" | "+ getDate();
        } else {
            return getName() +" | "+ getDate();
        }
    }
    //Saved for later if I have time
    public abstract int getIcon();
}
