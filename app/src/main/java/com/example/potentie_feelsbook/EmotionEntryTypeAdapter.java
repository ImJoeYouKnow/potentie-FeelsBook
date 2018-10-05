package com.example.potentie_feelsbook;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Used to adapt the EmotionEntry type to Json format, also used to convert Json into EmotionEntry objects if they are save to file.
public class EmotionEntryTypeAdapter implements JsonSerializer<EmotionEntry>, JsonDeserializer<EmotionEntry> {
    @Override
    public JsonElement serialize(EmotionEntry emotionEntry, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        //Convert to ISO
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date = df.format(emotionEntry.getDate());
        jsonObject.addProperty("name", emotionEntry.getName());
        jsonObject.addProperty("date", date);
        jsonObject.addProperty("note", emotionEntry.getNote());
        return jsonObject;
    }
    @Override
    public EmotionEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        EmotionEntry emotion;
        switch (name) {
            case "Anger":
                emotion = new AngerEmotionEntry();
                break;
            case "Joy":
                emotion = new JoyEmotionEntry();
                break;
            case "Surprise":
                emotion = new SurpriseEmotionEntry();
                break;
            case "Love":
                emotion = new LoveEmotionEntry();
                break;
            case "Fear":
                emotion = new FearEmotionEntry();
                break;
            case "Sadness":
                emotion = new SadnessEmotionEntry();
                break;
            default:
                //Should never happen, but this avoids a crash.
                emotion = new AngerEmotionEntry();
                Log.d("CMPUT 301","Default emotion type was used when reading from Json file.");
        }


        emotion.setNote(jsonObject.get("note").getAsString());
        Date date;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            date = df.parse(jsonObject.get("date").getAsString());
        }catch(ParseException e){
            date = new Date();
            Log.d("CMPUT 301","Encountered an error while parsing date.");
        }
        emotion.setDate(date);

        return emotion;
    }


}
