package com.example.potentie_feelsbook;

import java.util.ArrayList;

public class EmotionHistoryList {
    protected ArrayList<EmotionEntry> emotionHistoryList;
    protected ArrayList<Listener> listeners;
    private int count_anger = 0;
    private int count_joy = 0;
    private int count_surprise = 0;
    private int count_love = 0;
    private int count_fear = 0;
    private int count_sadness = 0;

    public EmotionHistoryList(){
        emotionHistoryList = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    public ArrayList<EmotionEntry> getEmotions(){
        return emotionHistoryList;
    }
    public void addEmotion(EmotionEntry newEmotion){
        emotionHistoryList.add(newEmotion);
        changeCount(newEmotion, true);
        notifyListeners();
    }
    public void removeEmotion(EmotionEntry delEmotion){
        emotionHistoryList.remove(delEmotion);
        changeCount(delEmotion, false);
        notifyListeners();
    }
    public void notifyListeners(){
        for (Listener listener : listeners){
            listener.update();
        }
    }
    public void addListener(Listener l){
        listeners.add(l);
    }
    public void removeListener(Listener l){
        listeners.remove(l);
    }

    //Credit CCID:cpenner consultation
    private void changeCount(EmotionEntry emotion, Boolean mode) {
        String type = emotion.getName();
        int increment;

        //add or subtract(True = Add)
        if (mode) {
            increment = 1;
        } else {
            increment = -1;
        }

        switch (type) {
            case "Anger":
                count_anger += increment;
                break;
            case "Joy":
                count_joy += increment;
                break;
            case "Surprise":
                count_surprise += increment;
                break;
            case "Love":
                count_love += increment;
                break;
            case "Fear":
                count_fear += increment;
                break;
            case "Sadness":
                count_sadness += increment;
                break;
        }
    }
    public int getCount(String type) {
        int count=0;
        switch (type) {
            case "Anger":
                count = count_anger;
                break;
            case "Joy":
                count = count_joy;
                break;
            case "Surprise":
                count = count_surprise;
                break;
            case "Love":
                count = count_love;
                break;
            case "Fear":
                count = count_fear;
                break;
            case "Sadness":
                count = count_sadness;
                break;
        }
        return count;
    }
}
