package com.example.potentie_feelsbook;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

//A controller used for adding and removing EmotionEntries from the EmotionHistoryList singleton
public class EmotionListController{

    private static EmotionHistoryList emotionHistoryList = null;

    //Lazy Singleton - Credit: Abram Hindle (https://www.youtube.com/watch?v=uLnoI7mbuEo)
    static public EmotionHistoryList getEmotionHistoryList(){
        if (emotionHistoryList == null){
            emotionHistoryList = new EmotionHistoryList();
        }
        return emotionHistoryList;
    }

    static public void setEmotionHistoryList(EmotionHistoryList oldList){
        emotionHistoryList = oldList;
        String test = "PASSED THROUGH SUCCESS" + oldList.toString();
        Log.d("CMPUT 301",test);
    }

    public void addEmotionEntry(EmotionEntry emotion){
        getEmotionHistoryList().addEmotion(emotion);
        getEmotionHistoryList().notifyListeners();
        Log.d("CMPUT 301", "adding Entry");
    }

    public void removeEmotionEntry(EmotionEntry emotion){
        getEmotionHistoryList().removeEmotion(emotion);
        getEmotionHistoryList().notifyListeners();
    }

    public int getCount(String type){
        return getEmotionHistoryList().getCount(type);
    }

}
