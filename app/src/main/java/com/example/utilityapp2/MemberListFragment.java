package com.example.utilityapp2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MemberListFragment extends Fragment {
    public ListView _list;
    public MembersActivity m_activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.member_fragment, container, false);
        _list = v.findViewById(R.id.member_list_container);
        m_activity = (MembersActivity) getActivity();
        return v;
    }

    public void refreshFragment() {
        if (m_activity != null && m_activity.listMembers.size() > 0) {
            MemberAdapter arrayAdapter = new MemberAdapter(this.getContext(), m_activity.listMembers);
            _list.setAdapter(arrayAdapter);
        }
    }
}