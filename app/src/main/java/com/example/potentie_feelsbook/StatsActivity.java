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

public class StatsActivity extends AppCompatActivity {



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

        getListOfViews();

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

    public ArrayList<View> getListOfViews(){
        TextView anger_stats = findViewById(R.id.stats_anger);
        TextView joy_stats = findViewById(R.id.stats_joy);
        TextView surprise_stats = findViewById(R.id.stats_surprise);
        TextView love_stats = findViewById(R.id.stats_love);
        TextView fear_stats = findViewById(R.id.stats_fear);
        TextView sadness_stats = findViewById(R.id.stats_sadness);

        anger_stats.setText("ANGER: ");
        joy_stats.setText("JOY: ");
        surprise_stats.setText("SURPRISE: ");
        love_stats.setText("LOVE: ");
        fear_stats.setText("FEAR: ");
        sadness_stats.setText("SADNESS: ");
        return null;
    }
}
