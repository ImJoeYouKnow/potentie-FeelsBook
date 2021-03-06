package com.example.potentie_feelsbook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class HistoryActivity extends AppCompatActivity {

    Dialog myPopup;
    Calendar datePicked;
    EmotionListController emotionListControl = new EmotionListController();
    private static EmotionListManager emotionListManager = new EmotionListManager();
    private static final String TAG = "HistoryActivity";


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
                Log.d("CMPUT 301","Notifying adapter of changes");
                emotionAdapter.notifyDataSetChanged();
                //Crashed here, need custom Json Serializer.
                emotionListManager.saveEmotionList(getApplicationContext());
            }
        });

         //Credit: Atif Mahmood(https://stackoverflow.com/users/1111918/atif-mahmood) - https://stackoverflow.com/a/13821611
        Collections.sort(list, new Comparator<EmotionEntry>(){
            public int compare(EmotionEntry obj1, EmotionEntry obj2) {
                return obj2.getDate().compareTo(obj1.getDate());
            }
        });

        myPopup = new Dialog(this);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                showPopup(view, list.get(pos));
                emotionAdapter.notifyDataSetChanged();
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

    //Credit -- https://www.youtube.com/watch?v=0DH2tZjJtm0
    public void showPopup(View v, EmotionEntry obj){

        myPopup.setContentView(R.layout.custom_popup);

        //set icon in popup
        ImageView icon = myPopup.findViewById(R.id.popup_icon);
        icon.setImageResource(obj.getIcon());
        //set date textView
        TextView date = myPopup.findViewById(R.id.popup_date);
        String placeholder = "Date: "+ obj.getDateFormatted();
        date.setText(placeholder);
        //set note TextView if needed
        if(!obj.getNote().isEmpty()){
            TextView note = myPopup.findViewById(R.id.popup_note);
            placeholder = "Note: "+ obj.getNote();
            note.setText(placeholder);
        }

        //Couldn't delete until made final record of the emotion.
        final EmotionEntry finalEmotion = obj;
        Button deleteEntry = myPopup.findViewById(R.id.popup_button_delete);
        deleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotionListControl.removeEmotionEntry(finalEmotion);
                myPopup.dismiss();
            }
        });

        final Button editNote = myPopup.findViewById(R.id.popup_button_note);
        editNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldText = finalEmotion.getNote();
                final TextView popup_note = myPopup.findViewById(R.id.popup_note);
                popup_note.setVisibility(View.GONE);
                final EditText editable = myPopup.findViewById(R.id.popup_edit_note);
                editable.setVisibility(View.VISIBLE);
                editable.setText(oldText);
                final Button saveButton = myPopup.findViewById(R.id.popup_button_note_save);
                saveButton.setVisibility(View.VISIBLE);
                editNote.setVisibility(View.GONE);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newText = editable.getText().toString();
                        finalEmotion.setNote(newText);
                        popup_note.setVisibility(View.VISIBLE);
                        editable.setVisibility(View.GONE);
                        editNote.setVisibility(View.VISIBLE);
                        saveButton.setVisibility(View.GONE);
                        String tempText = "Date:" + newText;
                        popup_note.setText(tempText);
                    }

                });

            }
        });

        //Edit date - (Not working)
        Button editDate = myPopup.findViewById(R.id.popup_button_date);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });




        ImageButton exit = myPopup.findViewById(R.id.popup_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPopup.dismiss();
            }
        });
        final EmotionEntry temp = obj;
        myPopup.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                try {
                    String test = "Setting date: "+ datePicked.getTime().toString() + " on object= " + temp.toString();
                    Log.d("CMPUT 301",test);
                    temp.setDate(datePicked.getTime());
                    EmotionListManager.saveEmotionList(getApplicationContext());

                }catch(NullPointerException e){
                    EmotionListManager.saveEmotionList(getApplicationContext());
                    Log.d("CMPUT", "Null pointer caught");
                }
            }
        });

        myPopup.show();

    }
    public void finishActivity(){
        this.finish();
    }



    //Credit https://stackoverflow.com/a/35745881 - Abhishek (https://stackoverflow.com/users/5242161/abhishek)
    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        datePicked = Calendar.getInstance();
        new DatePickerDialog(HistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                datePicked.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(HistoryActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        datePicked.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datePicked.set(Calendar.MINUTE, minute);
                        //Log.v(TAG, "The choosen one " + datePicked.getTime());
                    }
                },currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();

    }
}
