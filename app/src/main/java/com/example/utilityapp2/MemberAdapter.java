package com.example.utilityapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemberAdapter extends ArrayAdapter<MembersClass> {
    public MemberAdapter(Context context, ArrayList<MembersClass> members) {
        super(context, 0, members);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MembersClass member = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.member_item, parent, false);
        }
        // Lookup view for data population
        TextView group_name = convertView.findViewById(R.id.group_name);
        TextView group_time = convertView.findViewById(R.id.group_time);
        TextView group_utc = convertView.findViewById(R.id.group_utc);
        TextView group_day = convertView.findViewById(R.id.group_day);

        // Populate the data into the template view using the data object
        group_name.setText(member.getName());
        String time_complete = String.valueOf(member.getHour()) + ":" + String.valueOf(member.getMinute());
        group_time.setText(time_complete);
        group_utc.setText(member.getUtc());
        if (member.getDay() != 0) {
            String day = String.valueOf(member.getDay());
            if (member.getDay() > 0)
                day = "+" + day;
            group_day.setText(day);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
