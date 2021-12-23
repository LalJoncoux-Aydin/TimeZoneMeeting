package com.example.utilityapp2;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;

public class MembersClass implements Parcelable {
    private final String _name;
    private final int _hour;
    private final int _minute;
    private final String _utc;
    private int _day;

    private int getUtcValue(String utc)
    {
        int utc_nb = 0;
        if (utc.charAt(0) == '-') {
            utc_nb = Integer.parseInt(utc.substring(1));
            utc_nb = -utc_nb;
        } else {
            utc_nb = Integer.parseInt(utc.substring(1));
        }
        return utc_nb;
    }

    public MembersClass(String name, int hour, int minute, String utc, String meeting_utc) throws ParseException {
        _name = name;
        _day = 0;
        _utc = utc;

        int meet_utc = getUtcValue(meeting_utc.substring(3));
        int member_utc = getUtcValue(utc.substring(3));
        int difference = (meet_utc < 0 && member_utc > 0 ? Math.abs(meet_utc) + member_utc : meet_utc - member_utc);
        int tmp_hour = (meet_utc < 0 && member_utc > 0 ? hour + difference : hour - difference);

        if (tmp_hour > 24) {
            tmp_hour = tmp_hour - 24;
            _day = 1;
        } else if (tmp_hour < 0) {
            tmp_hour = 24 + tmp_hour;
            _day = -1;
        }

        _hour = tmp_hour;
        _minute = minute;
    }

    protected MembersClass(Parcel in) {
        _name = in.readString();
        _hour = in.readInt();
        _minute = in.readInt();
        _utc = in.readString();
    }

    public static final Creator<MembersClass> CREATOR = new Creator<MembersClass>() {
        @Override
        public MembersClass createFromParcel(Parcel in) {
            return new MembersClass(in);
        }

        @Override
        public MembersClass[] newArray(int size) {
            return new MembersClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeInt(_hour);
        dest.writeInt(_minute);
        dest.writeString(_utc);
    }

    public String getName() {
        return _name;
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

    public int getDay() {
        return _day;
    }
}
