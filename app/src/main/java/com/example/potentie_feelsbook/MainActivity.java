/*

    FeelsBook: Allows recording of feelings to analyze these feelings over time
    Copyright (C) 2018 Joseph Potentier-Neal potentie@ualberta.ca
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
 */

package com.example.potentie_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

//First place that opens when the app starts
//Optional comment can be entered before taping an emotion to create a record
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EmotionListController emotionListControl = new EmotionListController();
    private EmotionListManager emotionListManager;


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

        //
        EmotionListManager.loadEmotionList(this);
        setupButtons();


    }

    //https://www.youtube.com/watch?v=GtxVILjLcw8 thanks to "Coding in flow" for the tutorial, this can also be found all over online.
    @Override
    public void onClick(View v) {

        String note = fetchText();

        switch(v.getId()){
            case R.id.angerEmoji:
                Toast.makeText(this, "angerEmoji was clicked", Toast.LENGTH_SHORT).show();
                AngerEmotionEntry angerEmotion = new AngerEmotionEntry(note);
                emotionListControl.addEmotionEntry(angerEmotion);

                //TODO ImageView centerImage = findViewById(R.id.centerImage);
                //TODO centerImage.setImageResource(R.mipmap.ic_center_anger);
                //TODO ImageViewAnimatedChange(this, centerImage, R.mipmap.ic_center_anger);
                break;
            case R.id.joyEmoji:
                Toast.makeText(this, "joyEmoji was clicked", Toast.LENGTH_SHORT).show();
                JoyEmotionEntry joyEmotion = new JoyEmotionEntry(note);
                emotionListControl.addEmotionEntry(joyEmotion);
                break;
            case R.id.surpriseEmoji:
                Toast.makeText(this, "surpriseEmoji was clicked", Toast.LENGTH_SHORT).show();
                SurpriseEmotionEntry surpriseEmotion = new SurpriseEmotionEntry(note);
                emotionListControl.addEmotionEntry(surpriseEmotion);
                break;
            case R.id.loveEmoji:
                Toast.makeText(this, "loveEmoji was clicked", Toast.LENGTH_SHORT).show();
                LoveEmotionEntry loveEmotion = new LoveEmotionEntry(note);
                emotionListControl.addEmotionEntry(loveEmotion);
                break;
            case R.id.fearEmoji:
                Toast.makeText(this, "fearEmoji was clicked", Toast.LENGTH_SHORT).show();
                FearEmotionEntry fearEmotion = new FearEmotionEntry(note);
                emotionListControl.addEmotionEntry(fearEmotion);
                break;
            case R.id.sadnessEmoji:
                Toast.makeText(this, "sadnessEmoji was clicked", Toast.LENGTH_SHORT).show();
                SadnessEmotionEntry sadnessEmotion = new SadnessEmotionEntry(note);
                emotionListControl.addEmotionEntry(sadnessEmotion);
                break;
        }
        emotionListManager.saveEmotionList(this);
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
    public String fetchText(){
        EditText text = findViewById(R.id.editable_text);
        String toReturn = text.getText().toString();
        //clears text box when button is clicked.
        text.setText("");
        if(toReturn.length() > 100){
            Toast.makeText(this, "Text too long, not recorded", Toast.LENGTH_SHORT).show();
            return "";
        }
        else {
            return toReturn;
        }
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

}




