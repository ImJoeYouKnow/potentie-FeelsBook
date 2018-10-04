package com.example.potentie_feelsbook;

import android.util.Log;

import java.util.Collections;

public class EmotionListController {

    private static EmotionHistoryList emotionHistoryList = null;

    //Lazy Singleton - Credit: Abram Hindle (https://www.youtube.com/watch?v=uLnoI7mbuEo)
    static public EmotionHistoryList getEmotionHistoryList(){
        if (emotionHistoryList == null){
            emotionHistoryList = new EmotionHistoryList();
        }
        return emotionHistoryList;
    }

    public void addEmotionEntry(EmotionEntry emotion){
        getEmotionHistoryList().addEmotion(emotion);
        Log.e("CMPUT 301", "adding Entry");
    }

    public void removeEmotionEntry(EmotionEntry emotion){
        getEmotionHistoryList().removeEmotion(emotion);
    }
}
