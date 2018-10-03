package com.example.potentie_feelsbook;

import java.util.ArrayList;

public class EmotionHistoryList {
    protected ArrayList<EmotionEntry> emotionHistoryList;
    protected ArrayList<Listener> listeners;

    public EmotionHistoryList(){
        emotionHistoryList = new ArrayList<>();
        listeners = new ArrayList<>();
    }
    public ArrayList<EmotionEntry> getEmotions(){
        return emotionHistoryList;
    }
    public void addEmotion(EmotionEntry newEmotion){
        emotionHistoryList.add(newEmotion);
        notifyListeners();
    }
    public void removeEmotion(EmotionEntry delEmotion){
        emotionHistoryList.remove(delEmotion);
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
}
