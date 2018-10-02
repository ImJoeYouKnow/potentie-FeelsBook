package com.example.potentie_feelsbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText noteText;
    private ListView emotionList;
    private ArrayList<EmotionEntry> emotions = new ArrayList<EmotionEntry>();
    private ArrayAdapter<EmotionEntry> adapter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //No need to do anything
                    return true;
                case R.id.navigation_history:
                    openActivityHistory();
                    finishActivity();
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
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        emotionList = findViewById(R.id.history_list);

        setupButtons();


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ArrayAdapter<EmotionEntry>(this,
                R.layout.activity_history, emotions);
        //emotionList.setAdapter(adapter);
    }


    //https://www.youtube.com/watch?v=GtxVILjLcw8 thanks to "Coding in flow" for the tutorial, this can also be found all over online.
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.angerEmoji:
                Toast.makeText(this, "angerEmoji was clicked", Toast.LENGTH_SHORT).show();
                AngerEmotionEntry newEmotion = new AngerEmotionEntry();
                newEmotion.setDate(new Date());
                newEmotion.setNote(fetchText());
                emotions.add(newEmotion);
                //adapter.notifyDataSetChanged();


                //ImageView centerImage = findViewById(R.id.centerImage);
                //centerImage.setImageResource(R.mipmap.ic_center_anger);
                //ImageViewAnimatedChange(this, centerImage, R.mipmap.ic_center_anger);
                break;
            case R.id.joyEmoji:
                Toast.makeText(this, "joyEmoji was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.surpriseEmoji:
                Toast.makeText(this, "surpriseEmoji was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loveEmoji:
                Toast.makeText(this, "loveEmoji was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fearEmoji:
                Toast.makeText(this, "fearEmoji was clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sadnessEmoji:
                Toast.makeText(this, "sadnessEmoji was clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setupButtons(){

        ImageButton angerButton = findViewById(R.id.angerEmoji);
        ImageButton joyButton = findViewById(R.id.joyEmoji);
        ImageButton surpriseButton = findViewById(R.id.surpriseEmoji);
        ImageButton loveButton = findViewById(R.id.loveEmoji);
        ImageButton fearButton = findViewById(R.id.fearEmoji);
        ImageButton sadnessButton = findViewById(R.id.sadnessEmoji);

        angerButton.setOnClickListener(this);
        joyButton.setOnClickListener(this);
        surpriseButton.setOnClickListener(this);
        loveButton.setOnClickListener(this);
        fearButton.setOnClickListener(this);
        sadnessButton.setOnClickListener(this);
    }


    public void openActivityHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
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

    public String fetchText(){
        noteText = findViewById(R.id.textInput);
        String text = noteText.getText().toString();
        return text;
    }

}




