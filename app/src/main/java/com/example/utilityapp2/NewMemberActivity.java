package com.example.utilityapp2;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class NewMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        // Action Bar Style
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.grey)));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.new_member_action_bar_layout);
    }

    public void onCancelCreateElem(View v) {
        finish();
    }

    public void onCreateElem(View v) {
        String title = ((TextView) findViewById(R.id.add_member_name)).getText().toString();
        String utc = ((Spinner)findViewById(R.id.add_member_utc)).getSelectedItem().toString();

        Intent previousScreen = new Intent();
        previousScreen.putExtra("member_name", title).putExtra("member_utc", utc);
        setResult(RESULT_OK, previousScreen);
        finish();
    }
}