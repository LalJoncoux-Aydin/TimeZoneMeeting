package com.example.utilityapp2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public final ArrayList<MeetingClass> listMeeting = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Action Bar Style
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.grey)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent newGroupIntent = new Intent(MainActivity.this, NewMeetingActivity.class);
            startActivityForResult(newGroupIntent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String title = data.getStringExtra("meeting_title");
                String description = data.getStringExtra("meeting_description");
                int hour = data.getIntExtra("meeting_hour",0);
                int minute = data.getIntExtra("meeting_minute",0);
                String utc = data.getStringExtra("meeting_utc");
                MeetingClass _newMeeting = new MeetingClass(title, description, hour, minute, utc);
                listMeeting.add(_newMeeting);
                ((MeetingListFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.meeting_list_frag))).refreshFragment();
            }
        }
    }
}