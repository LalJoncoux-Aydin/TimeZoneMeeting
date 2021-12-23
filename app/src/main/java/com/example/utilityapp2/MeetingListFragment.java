package com.example.utilityapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Objects;

public class MeetingListFragment extends Fragment {
    private static final int RESULT_OK = -1;
    public ListView _list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.meeting_fragment, container, false);
        _list = v.findViewById(R.id.meeting_list_container);
        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MeetingClass meet = ((MainActivity) Objects.requireNonNull(getActivity())).listMeeting.get(position);
                Intent intent = new Intent(getActivity(), MembersActivity.class);
                intent.putExtra("meeting", meet).putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
        return v;
    }

    public void refreshFragment() {
        MainActivity m_activity = (MainActivity) getActivity();

        if (m_activity != null) {
            if (m_activity.listMeeting.size() > 0) {
                MeetingAdapter arrayAdapter = new MeetingAdapter(this.getContext(), m_activity.listMeeting);
                _list.setAdapter(arrayAdapter);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                MeetingClass class_replace = data.getParcelableExtra("meeting");
                int position = data.getIntExtra("position", 0);
                ((MainActivity) Objects.requireNonNull(getActivity())).listMeeting.set(position, class_replace);
            }
        }
    }
}