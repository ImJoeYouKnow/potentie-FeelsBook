package com.example.potentie_feelsbook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {


    EmotionListController emotionListControl = new EmotionListController();

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
                    openActivityHistory();
                    finishActivity();
                    return true;
                case R.id.navigation_stats:
                    //do nothing.
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_stats);

        setupStats();

    }

    public void openActivityHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }

    public void openActivityHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }

    public void finishActivity(){
        this.finish();
    }

    public void setupStats(){
        TextView anger_stats = findViewById(R.id.stats_anger);
        TextView joy_stats = findViewById(R.id.stats_joy);
        TextView surprise_stats = findViewById(R.id.stats_surprise);
        TextView love_stats = findViewById(R.id.stats_love);
        TextView fear_stats = findViewById(R.id.stats_fear);
        TextView sadness_stats = findViewById(R.id.stats_sadness);

        String string_anger = "ANGER: " + emotionListControl.getCount("Anger");
        String string_joy = "JOY: " + emotionListControl.getCount("Joy");
        String string_surprise = "SURPRISE: " + emotionListControl.getCount("Surprise");
        String string_love = "LOVE: " + emotionListControl.getCount("Love");
        String string_fear = "FEAR: " + emotionListControl.getCount("Fear");
        String string_sadness = "SADNESS: " + emotionListControl.getCount("Sadness");



        anger_stats.setText(string_anger);
        joy_stats.setText(string_joy);
        surprise_stats.setText(string_surprise);
        love_stats.setText(string_love);
        fear_stats.setText(string_fear);
        sadness_stats.setText(string_sadness);
    }
}
