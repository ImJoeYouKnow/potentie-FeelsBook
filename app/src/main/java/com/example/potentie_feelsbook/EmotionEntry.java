package com.example.potentie_feelsbook;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//An abstract class that represents the framework of an Emotion
public abstract class EmotionEntry {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private Date date = new Date();
    private String printDate = df.format(date);

    private String note = "";

    public Date getDate(){
        return this.date;
    }
    public String getDateFormatted(){
        return this.printDate;
    }
    public void setDate(Date newDate){
        this.date=newDate;
        this.printDate = df.format(date);
        String test = "Setting date: "+ date.toString() + " on object= " + this.toString();
        Log.e("CMPUT 301", test);
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
            return getName() +" | "+ getNote() +" | "+ printDate;
        } else {
            return getName() +" | "+ printDate;
        }
    }
    //Saved for later if I have time
    public abstract int getIcon();
}
