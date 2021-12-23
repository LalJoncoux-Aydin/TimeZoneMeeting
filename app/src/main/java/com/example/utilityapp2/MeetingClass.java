package com.example.utilityapp2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MeetingClass implements Parcelable {
    private final String _name;
    private final String _description;
    private final int _hour;
    private final int _minute;
    private final String _utc;
    public ArrayList _members;

    public MeetingClass(String name, String description, int hour, int minute, String utc) {
        _name = name;
        _description = description;
        _hour = hour;
        _minute = minute;
        _utc = utc;
        _members = new ArrayList<MembersClass>();
    }

    protected MeetingClass(Parcel in) {
        _name = in.readString();
        _description = in.readString();
        _hour = in.readInt();
        _minute = in.readInt();
        _utc = in.readString();
        _members = in.readArrayList(MembersClass.class.getClassLoader());
    }

    public static final Creator<MeetingClass> CREATOR = new Creator<MeetingClass>() {
        @Override
        public MeetingClass createFromParcel(Parcel in) {
            return new MeetingClass(in);
        }

        @Override
        public MeetingClass[] newArray(int size) {
            return new MeetingClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_description);
        dest.writeInt(_hour);
        dest.writeInt(_minute);
        dest.writeString(_utc);
        dest.writeList(_members);
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }

    public String getUtc() {
        return _utc;
    }

    public ArrayList getMembers() { return _members; }
}
