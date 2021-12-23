package com.example.utilityapp2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class MembersActivity extends AppCompatActivity {
    public MeetingClass _meet;
    public int _position;
    public ArrayList listMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        // Action Bar Style
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.grey)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.member_action_bar_layout);

        _meet = getIntent().getParcelableExtra("meeting");
        _position = getIntent().getIntExtra("position", 0);
        listMembers = _meet.getMembers();
        ((MemberListFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.member_list_frag))).refreshFragment();

        FloatingActionButton fab = findViewById(R.id.new_member);
        fab.setOnClickListener(view -> {
            Intent newGroupIntent = new Intent(MembersActivity.this, NewMemberActivity.class);
            startActivityForResult(newGroupIntent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String name = data.getStringExtra("member_name");
                String utc = data.getStringExtra("member_utc");
                MembersClass _newMember = null;
                try {
                    _newMember = new MembersClass(name, _meet.getHour(), _meet.getMinute(), utc, _meet.getUtc());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                listMembers.add(_newMember);
                _meet._members = listMembers;
                ((MemberListFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.member_list_frag))).refreshFragment();
            }
        }
    }

    public void onGoBack(View v) {
        Intent previousScreen = new Intent();
        previousScreen.putExtra("meeting", _meet).putExtra("position", _position);
        setResult(RESULT_OK, previousScreen);
        finish();
    }
}