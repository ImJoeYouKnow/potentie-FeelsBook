package com.example.potentie_feelsbook;

//import android.graphics.drawable.Icon;

import java.util.Date;

public abstract class EmotionEntry {
    private Date date;
    private String note = "";

    public Date getDate(){
        return this.date;
    }
    public void setDate(Date newDate){
        this.date=newDate;
    }

    public String getNote(){
        return this.note;
    }
    public void setNote(String newNote){
        this.note=newNote;
    }

    public abstract String getName();

    //Saved for later if I have time
    //public abstract Icon getIcon();
}
