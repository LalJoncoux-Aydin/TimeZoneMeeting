package com.example.utilityapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MeetingAdapter extends ArrayAdapter<MeetingClass> {

    public MeetingAdapter(Context context, ArrayList<MeetingClass> meetings) {
        super(context, 0, meetings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MeetingClass meet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meeting_item, parent, false);
        }
        // Lookup view for data population
        TextView group_name = convertView.findViewById(R.id.group_name);
        TextView group_description = convertView.findViewById(R.id.group_description);
        TextView group_time = convertView.findViewById(R.id.group_time);
        TextView group_utc = convertView.findViewById(R.id.group_utc);

        // Populate the data into the template view using the data object
        group_name.setText(meet.getName());
        group_description.setText(meet.getDescription());
        String time_complete = String.valueOf(meet.getHour()) + ":" + String.valueOf(meet.getMinute());
        group_time.setText(time_complete);
        group_utc.setText(meet.getUtc());
        // Return the completed view to render on screen
        return convertView;
    }
}
