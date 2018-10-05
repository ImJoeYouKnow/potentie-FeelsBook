package com.example.potentie_feelsbook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Primarly used for loading and saving of emotion entries to the file system
public class EmotionListManager{
    //consultation Credit - Anders Johnson - adj@ualberta.ca
    static ArrayList<EmotionEntry> emotionHistoryList = new ArrayList<>(EmotionListController.getEmotionHistoryList().getEmotions());
    private static final String FILENAME = "emotions.sav";

    static public EmotionHistoryList loadEmotionList(Context context){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(EmotionEntry.class, new EmotionEntryTypeAdapter());
            Gson gson = gsonBuilder.create();
            EmotionHistoryList savedList = gson.fromJson(reader, EmotionHistoryList.class);
            EmotionListController.setEmotionHistoryList(savedList);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    static public void saveEmotionList(Context context){
        try{
        Log.d("CMPUT 301","SAVE TO DISK WAS CALLED");
        FileOutputStream fos = context.openFileOutput(FILENAME, 0);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter writer = new BufferedWriter(osw);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(EmotionEntry.class, new EmotionEntryTypeAdapter());
        Gson gson = gsonBuilder.create();
        gson.toJson(EmotionListController.getEmotionHistoryList(), writer);
        writer.flush();
        fos.close();
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        Log.e("CMPUT 301", "GENERATING NEW LIST");
            emotionHistoryList=new ArrayList<>();
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
}
