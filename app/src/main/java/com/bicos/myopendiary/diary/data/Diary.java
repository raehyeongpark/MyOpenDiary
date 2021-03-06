package com.bicos.myopendiary.diary.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by raehyeong.park on 2017. 3. 8..
 */

@IgnoreExtraProperties
public class Diary implements Parcelable {

    private String mTitle;

    private String mDesc;

    private String mUid;

    public Diary() {
    }

    public Diary(String title, String desc, String uid) {
        mTitle = title;
        mDesc = desc;
        mUid = uid;
    }

    protected Diary(Parcel in) {
        mTitle = in.readString();
        mDesc = in.readString();
        mUid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDesc);
        dest.writeString(mUid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Diary> CREATOR = new Creator<Diary>() {
        @Override
        public Diary createFromParcel(Parcel in) {
            return new Diary(in);
        }

        @Override
        public Diary[] newArray(int size) {
            return new Diary[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", mTitle);
        result.put("desc", mDesc);
        result.put("uid", mUid);

        return result;
    }
}
