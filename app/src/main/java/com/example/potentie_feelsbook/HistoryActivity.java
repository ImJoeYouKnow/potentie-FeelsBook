package com.example.potentie_feelsbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class HistoryActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openActivityHome();
                    finishActivity();
                    return true;
                case R.id.navigation_history:
                    //do nothing.
                    return true;
                case R.id.navigation_stats:
                    openActivityStats();
                    finishActivity();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_history);

        ListView listView = findViewById(R.id.history_list);
        final ArrayList<EmotionEntry> list = new ArrayList<>(EmotionListController.getEmotionHistoryList().getEmotions());
        final ArrayAdapter<EmotionEntry> emotionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(emotionAdapter);

         EmotionListController.getEmotionHistoryList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<EmotionEntry> emotionEntry = EmotionListController.getEmotionHistoryList().getEmotions();
                list.addAll(emotionEntry);
                emotionAdapter.notifyDataSetChanged();
            }
        });

         //Credit: Atif Mahmood(https://stackoverflow.com/users/1111918/atif-mahmood) - https://stackoverflow.com/a/13821611
        Collections.sort(list, new Comparator<EmotionEntry>(){
            public int compare(EmotionEntry obj1, EmotionEntry obj2) {
                return obj2.getDate().compareTo(obj1.getDate());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                return false;
            }
        });

    }

    public void openActivityHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }

    public void openActivityStats(){
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }
    public void finishActivity(){
        this.finish();
    }
}
