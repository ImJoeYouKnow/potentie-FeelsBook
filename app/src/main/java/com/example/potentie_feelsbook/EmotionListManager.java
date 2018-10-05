package com.example.potentie_feelsbook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
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
import java.util.ArrayList;

//TODO Implements JSONSerializer and JSONDeserializer
public class EmotionListManager{

    static ArrayList<EmotionEntry> emotionHistoryList = new ArrayList<>(EmotionListController.getEmotionHistoryList().getEmotions());
    private static final String FILENAME = "emotions.sav";

    static public EmotionHistoryList loadEmotionList(Context context){
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type listTweetType=new TypeToken<ArrayList<EmotionEntry>>(){}.getType();
            emotionHistoryList = gson.fromJson(reader, listTweetType);
            fis.close();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Log.e("CMPUT 301", "GENERATING NEW LIST");
            emotionHistoryList= new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    static public void saveEmotionList(Context context){
        try{
        Log.e("CMPUT 301","SAVE TO DISK WAS CALLED");
        FileOutputStream fos = context.openFileOutput(FILENAME, 0);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter writer = new BufferedWriter(osw);
        Gson gson  = new Gson();
        gson.toJson(emotionHistoryList, writer);
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

    //@Override
    //public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    //    return null;
    //}

    //@Override
    //public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
     //   return null;
    //}
}
